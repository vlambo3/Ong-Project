package com.alkemy.ong.service.impl;

import com.alkemy.ong.exception.BadRequestException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

@Service
public class AmazonClientPrueba {

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

    public String upload(String base64file, String imageName) {
        String[] base64Components = base64file.split(",");
        if (base64Components.length != 2) {
            throw new BadRequestException("Wrong input");
        }
        byte[] bytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Components[1]);
        return uploadFile(bytes, imageName);
    }

    private String uploadFile(byte[] bytes, String imageName) {
        String fileUrl = "";
        try {
            File file = convertBytesToFile(bytes, imageName);
            String fileName = generateFileName(imageName);
            fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;
            uploadFileTos3bucket(fileName, file);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileUrl;
    }

    private File convertBytesToFile(byte[] bytes, String imageName) throws IOException {
        File convFile = new File(imageName);
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(bytes);
        fos.close();
        return convFile;
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private String generateFileName(String imageName) {
        return new Date().getTime() + "-" + imageName.replace(" ", "_");
    }

    private void uploadFileTos3bucket(String fileName, File file) {
        s3.putObject(new PutObjectRequest(bucketName, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }
}