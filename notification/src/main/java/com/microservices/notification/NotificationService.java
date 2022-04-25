package com.microservices.notification;

import com.microservices.clients.notification.NotificationRequest;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void send(NotificationRequest notificationRequest) {
        notificationRepository.save(
                Notification.builder()
                        .toUserId(notificationRequest.toUserId())
                        .toUserEmail(notificationRequest.toUserEmail())
                        .sender("MicroservicesApp")
                        .message(notificationRequest.message())
                        .sentAt(LocalDateTime.now())
                        .build()
        );
    }
}
