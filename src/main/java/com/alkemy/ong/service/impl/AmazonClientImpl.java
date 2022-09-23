package com.alkemy.ong.service.impl;

import com.alkemy.ong.exception.UnableToSaveEntityException;
import com.alkemy.ong.service.IAmazonClient;
import com.alkemy.ong.utils.image.CustomMultipartFile;
import com.alkemy.ong.utils.image.Image;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.Base64;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class AmazonClientImpl implements IAmazonClient {

    private static final Logger logger = LoggerFactory.getLogger(AmazonClientImpl.class);
    private final MessageSource messageSource;

    private AmazonS3 s3;

    @Value("${amazonS3.endpointUrl}")
    private String endpointUrl;
    @Value("${amazonS3.bucketName}")
    private String bucketName;
    @Value("${amazonS3.accessKey}")
    private String accessKey;
    @Value("${amazonS3.secretKey}")
    private String secretKey;

    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        s3 = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_2)
                .build();
    }

    public Image uploadFile(MultipartFile multipartFile) {

        try {
            File file = convertMultiPartToFile(multipartFile);
            String fileName = generateFileName(multipartFile);
            String fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;
            uploadFileTos3bucket(fileName, file);
            file.delete();
            return new Image(fileName, fileUrl);
        } catch (AmazonServiceException | IOException e) {
            throw new UnableToSaveEntityException(messageSource.getMessage("amazon-unable-to-save-file", null, Locale.US));
        }
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
    }

    private void uploadFileTos3bucket(String fileName, File file) {
        s3.putObject(new PutObjectRequest(bucketName, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }


    @Override
    public Image uploadFile(String base64, String fileName) {
        MultipartFile image = this.base64ToImage(base64, fileName);
        return this.uploadFile(image);
    }

    private MultipartFile base64ToImage(String encoded, String fileName) {

        String trimmedEncodedImage = encoded.substring(encoded.indexOf(",") + 1);

        byte[] decodedBytes = Base64.decode(trimmedEncodedImage);

        CustomMultipartFile customMultipartFile = new CustomMultipartFile(decodedBytes, fileName);

        try {
            customMultipartFile.transferTo(customMultipartFile.getFile());
        } catch (IllegalStateException e) {
            logger.info("IllegalStateException : " + e);
        } catch (IOException e) {
            logger.info("IOException : " + e);
        }

        return customMultipartFile;
    }

    private String generateFileUrl(String fileName) {
        return "https://s3." + s3.getRegionName() + ".amazonaws.com/" + bucketName + "/" + fileName;
    }

}