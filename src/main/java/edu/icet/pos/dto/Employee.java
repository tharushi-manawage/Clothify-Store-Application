package edu.icet.pos.dto;

import java.time.LocalDate;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Employee {
    private String employeeID;
    private String userID;
    private String name;
    private String email;
    private String contactNumber;
    private LocalDate appointedDate;
    private String employeeStatus;
}