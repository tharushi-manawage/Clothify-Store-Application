package edu.icet.pos.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Sample {
    private String userId;
    private String id;
    private String title;
    private Boolean completed;
}
