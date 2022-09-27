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
import java.util.Base64;
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

    public String uploadFile(String base64File, String imageName) throws Exception {
        String fileUrl = "";
        try {
            File file = convertBase64ToFile(base64File, imageName);
            String key = new Date().getTime() + "-" + imageName.replace(" ", "_");
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType("image/png");
            s3.putObject(new PutObjectRequest(bucketName, key, file)
                            .withMetadata(objectMetadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead));
            file.delete();
            fileUrl = endpointUrl + "/" + bucketName + "/" + key;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return fileUrl;
    }

    private File convertBase64ToFile(String base64File, String fileName) throws IOException {
        String[] base64Components = base64File.split(",");
        if (base64Components.length != 2) {
            throw new BadRequestException("Wrong input");
        }
        byte[] bytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Components[1]);
        File convFile = new File(fileName);
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(bytes);
        fos.close();
        return convFile;
    }

    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        s3 = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_1)
                .build();
    }

}
