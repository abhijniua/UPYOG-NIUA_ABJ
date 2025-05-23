package org.egov.web.notification.mail.consumer;

import java.util.HashMap;

import org.egov.web.notification.mail.consumer.contract.EmailRequest;
import org.egov.web.notification.mail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmailNotificationListener {

    
    private EmailService emailService;
    
    private ObjectMapper objectMapper;

    @Autowired
    public EmailNotificationListener(EmailService emailService, ObjectMapper objectMapper) {
        this.emailService = emailService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "${kafka.topics.notification.mail.name}")
    public void listen(final HashMap<String, Object> record) {
       // log.info("Entering mail topic");
    	EmailRequest emailRequest = objectMapper.convertValue(record, EmailRequest.class);
        emailService.sendEmail(emailRequest.getEmail());
       //log.info("Printing Records");
       //log.info("Recors is ================================"+record);

        
    }
    
    

}
