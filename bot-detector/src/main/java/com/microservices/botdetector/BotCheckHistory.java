package com.microservices.botdetector;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BotCheckHistory {

    @Id
    @SequenceGenerator(
            name = "bot_id_sequence",
            sequenceName = "bot_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "bot_id_sequence"
    )
    private Integer id;
    private Integer userId;
    private Boolean isBot;
    private LocalDateTime createdAt;
}
