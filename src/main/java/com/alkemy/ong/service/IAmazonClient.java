package com.alkemy.ong.service;

import org.springframework.web.multipart.MultipartFile;

public interface IAmazonClient {

    String uploadFile(MultipartFile multipartFile);

}
