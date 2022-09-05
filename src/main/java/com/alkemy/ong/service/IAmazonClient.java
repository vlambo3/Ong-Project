package com.alkemy.ong.service;

import com.alkemy.ong.utils.image.Image;
import org.springframework.web.multipart.MultipartFile;

public interface IAmazonClient {

    String uploadFile(MultipartFile multipartFile);
    public Image save(MultipartFile multipartFile);

    public Image save(String base64, String fileName);

}
