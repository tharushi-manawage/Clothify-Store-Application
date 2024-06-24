package edu.icet.pos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.Date;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
public class UserEntity {

    @Id
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