package com.alkemy.ong.service;

import com.alkemy.ong.utils.image.Image;
import org.springframework.web.multipart.MultipartFile;

public interface IAmazonClient {

//    Image uploadFile(MultipartFile multipartFile);

    Image uploadFile(String base64, String fileName);

}
