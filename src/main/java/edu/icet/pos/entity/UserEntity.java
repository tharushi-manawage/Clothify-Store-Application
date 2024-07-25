package edu.icet.pos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDate;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
public class UserEntity {

    @Id
    private String id;
    private String userType;
    private String name;
    private LocalDate dob;
    private String email;
    private String password;
    private LocalDate registeredDate;
    private String nic;
    private String city;
    private String province;
    private String contactNumber1;
    private String contactNumber2;
    private String userStatus;
}