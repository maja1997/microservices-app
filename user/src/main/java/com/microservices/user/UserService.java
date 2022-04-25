package com.microservices.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    public void registerUser(UserRegistrationRequest request) {
        User user = User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .username(request.username())
                .email(request.email())
                .build();
        userRepository.saveAndFlush(user);

        BotCheckResponse botCheckResponse = restTemplate.getForObject(
                "http://bot-detector/api/v1/bot-check/{userId}",
                BotCheckResponse.class,
                user.getId()
        );

        if (botCheckResponse.isBot()) {
            throw new IllegalStateException("User is a bot!");
        }
    }
}
