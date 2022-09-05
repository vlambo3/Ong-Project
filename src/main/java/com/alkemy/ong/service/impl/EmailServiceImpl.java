package com.alkemy.ong.service.impl;

import com.alkemy.ong.utils.MailUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.alkemy.ong.service.IEmailService;
import static com.sendgrid.Method.POST;

import java.util.Locale;

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

    private final MessageSource messageSource;
    private final SendGrid sendGrid;
    private final static String ENDPOINT = "mail/send";

    @Value("${sendgrid.organization.email}")
    private String FROM;

    @Value("${sendgrid.organization.subject}")
    private String SUBJECT;

    private final String TYPE = MailUtils.TYPE;
    private final String BODY = MailUtils.TEMPLATE;

    @Override
    public Response sendEmail(String toEmail) {
        Mail mail = new Mail(new Email(FROM),
                SUBJECT,
                new Email(toEmail),
                new Content(TYPE, BODY));

        try {
            Request request = new Request();
            request.setMethod(POST);
            request.setEndpoint(ENDPOINT);
            request.setBody(mail.build());
            Response response = sendGrid.api(request);
            return response;
        } catch (Exception e) {
            throw new RuntimeException(messageSource.getMessage("error-sending-email",
                                    new Object[] { toEmail }, Locale.US) + e.getMessage());
        }
    }
}
