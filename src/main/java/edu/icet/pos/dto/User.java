package edu.icet.pos.dto;

import lombok.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class User {
    private String id;
    private String type;
    private String name;
    private Date dob;
    private String email;
    private String password;
    private Date joinedDate;
    private String nic;
    private String city;
    private String province;
    private String contactNumber1;
    private String contactNumber2;
    private String status;
}