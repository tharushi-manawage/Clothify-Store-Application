package edu.icet.pos.dto.tm;

import java.time.LocalDate;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class EmployeeTable02 {
    private String userID;
    private String userType;
    private LocalDate dob;
    private String nicNumber;
    private String city;
    private String province;
    private String contactNumber2;
    private String userStatus;
}