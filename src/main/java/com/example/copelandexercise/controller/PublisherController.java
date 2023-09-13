package com.example.copelandexercise.controller;

import com.example.copelandexercise.service.PublisherService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.JMSException;

@RestController
public class PublisherController {

    private final PublisherService publisherService;

    @Autowired
    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @PostMapping(path = "/sendMessage")
    @Operation(summary = "Send a message to subscribers")
    public String sendMessage(@RequestBody String inputMessage) throws JMSException {
        publisherService.sendMessage(inputMessage);
        return inputMessage;
    }
}
