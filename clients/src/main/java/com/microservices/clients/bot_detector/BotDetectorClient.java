package com.microservices.clients.bot_detector;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "bot-detector")
public interface BotDetectorClient {

    @GetMapping(path = "api/v1/bot-check/{userId}")
    BotCheckResponse isFraudster(
            @PathVariable("userId") Integer userID);
}
