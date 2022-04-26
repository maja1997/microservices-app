package com.microservices.user;

import com.microservices.clients.bot_detector.BotCheckResponse;
import com.microservices.clients.bot_detector.BotDetectorClient;
import com.microservices.clients.notification.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BotDetectorClient botDetectorClient;
    private final KafkaTemplate<String, NotificationRequest> kafkaTemplate;
    @Value("${kafka.config.topic.id}")
    private String topicId;

    public void registerUser(UserRegistrationRequest request) {
        User user = User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .username(request.username())
                .email(request.email())
                .build();
        userRepository.saveAndFlush(user);

        BotCheckResponse botCheckResponse = botDetectorClient.isFraudster(user.getId());

        if (botCheckResponse.isBot()) {
            throw new IllegalStateException("User is a bot!");
        }

        NotificationRequest notificationRequest = new NotificationRequest(
                user.getId(),
                user.getEmail(),
                String.format("Hi %s, you are successfully registered!",
                        user.getFirstName())
        );

        kafkaTemplate.send(
                topicId,
                notificationRequest
        );
    }
}
