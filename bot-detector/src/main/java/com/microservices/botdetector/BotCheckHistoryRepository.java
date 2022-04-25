package com.microservices.botdetector;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BotCheckHistoryRepository extends JpaRepository<BotCheckHistory, Integer> {
}
