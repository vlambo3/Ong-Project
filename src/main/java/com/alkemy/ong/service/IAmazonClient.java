package com.alkemy.ong.service;

public interface IAmazonClient {

    String uploadFile(String base64, String fileName) throws Exception;

}
