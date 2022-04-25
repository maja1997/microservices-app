package com.microservices.user;

import com.microservices.clients.bot_detector.BotCheckResponse;
import com.microservices.clients.bot_detector.BotDetectorClient;
import com.microservices.clients.notification.NotificationClient;
import com.microservices.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BotDetectorClient botDetectorClient;
    private final NotificationClient notificationClient;

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

        notificationClient.sendNotification(
                new NotificationRequest(
                        user.getId(),
                        user.getEmail(),
                        String.format("Hi %s, you are successfully registered!",
                                user.getFirstName())
                )
        );
    }
}
