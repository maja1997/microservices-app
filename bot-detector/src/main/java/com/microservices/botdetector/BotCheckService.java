package com.microservices.botdetector;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class BotCheckService {

    private final BotCheckHistoryRepository botCheckHistoryRepository;

    public boolean isFraudulentUser(Integer userId) {
        botCheckHistoryRepository.save(
                BotCheckHistory.builder()
                        .userId(userId)
                        .isBot(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return false;
    }

}
