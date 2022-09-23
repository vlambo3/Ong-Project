package com.alkemy.ong.exception;

import org.springframework.security.web.firewall.RequestRejectedException;

public class BadRequestException extends RequestRejectedException{

    public BadRequestException(String message){
        super(message);
    }
}
    
