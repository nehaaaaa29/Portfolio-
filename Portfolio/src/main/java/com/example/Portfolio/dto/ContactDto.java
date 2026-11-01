package com.example.Portfolio.dto;

import com.example.Portfolio.entity.ContactMessage;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
public class ContactDto {
    @Data
    public static class Request {
        @NotBlank private String senderName;
        @Email @NotBlank private String senderEmail;
        private String subject;
        @NotBlank private String message;
    }

    @Data
    public static class Response {
        private Long id;
        private String senderName;
        private String senderEmail;
        private String subject;
        private String message;
        private ContactMessage.MessageStatus status;
        private LocalDateTime receivedAt;
    }

    @Data
    public static class StatusUpdateRequest {
        private ContactMessage.MessageStatus status;
    }



}
