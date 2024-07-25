package edu.icet.pos.dto;

import java.time.LocalDate;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class User {
    private String userID;
    private String userType;
    private String name;
    private LocalDate dob;
    private String email;
    private String password;
    private LocalDate registeredDate;
    private String nicNumber;
    private String city;
    private String province;
    private String contactNumber1;
    private String contactNumber2;
    private String userStatus;
}