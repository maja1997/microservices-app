package com.microservices.botdetector;


import com.microservices.clients.bot_detector.BotCheckResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/bot-check")
@AllArgsConstructor
@Slf4j
public class BotController {

    private final BotCheckService botCheckService;

    @GetMapping(path = "{userId}")
    public BotCheckResponse isFraudster(
            @PathVariable("userId") Integer userID) {
        boolean isFraudulentUser = botCheckService.isFraudulentUser(userID);
        log.info("Bot check request for user {}", userID);

        return new BotCheckResponse(isFraudulentUser);
    }
}
