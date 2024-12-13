package ru.itis.contralwork.entity;

import lombok.*;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@Builder
public class Gift {
    private Long id;
    private String name;
    private String giverName;
    private String receiverName;
    private Timestamp dateOfGiven;
}
