package edu.icet.pos.dto.tm;

import java.time.LocalDate;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class EmployeeTable01 {
    private String employeeID;
    private String userID;
    private String name;
    private String email;
    private String contactNumber1;
    private LocalDate appointedDate;
    private String employeeStatus;
}