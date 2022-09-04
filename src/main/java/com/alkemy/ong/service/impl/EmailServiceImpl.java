package com.alkemy.ong.service.impl;

import com.alkemy.ong.utils.MailUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alkemy.ong.service.IEmailService;
import static com.sendgrid.Method.POST;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements IEmailService {

    private final SendGrid sendGrid;
    private Response response;
    private Request request;
    private final static String ENDPOINT = "mail/send";

    //@Value("${sendgrid.organization.email}")
    private final String FROM = "prueba@hotmail.com";

    //@Value("${sendgrid.organization.subject}")
    private final String SUBJECT ="Welcome";

    //@Value("${sendgrid.organization.type}")
    private final String TYPE = MailUtils.TYPE;

    //@Value("${sendgrid.organization.body}")
    private final String BODY = MailUtils.TEMPLATE;

    @Override
    public Response sendEmail(String toEmail) {
        Mail mail = new Mail(new Email(FROM),
                SUBJECT,
                new Email(toEmail),
                new Content(TYPE, BODY));        

        try {

            request.setMethod(POST);
            request.setEndpoint(ENDPOINT);
            request.setBody(mail.build());

           // response = sendGrid.api(request);

        } catch (Exception e) {
            throw new RuntimeException("Error sending email via SendGrid to " + toEmail + ": " + e.getMessage());
        }

        return response;
    }
}
