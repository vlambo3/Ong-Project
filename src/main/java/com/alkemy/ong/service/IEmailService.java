package com.alkemy.ong.service;

import com.sendgrid.Response;

public interface IEmailService {
    
    Response sendEmail(String toEmail);

}
