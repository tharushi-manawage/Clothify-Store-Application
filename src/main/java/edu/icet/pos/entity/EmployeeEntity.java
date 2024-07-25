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
public class EmployeeEntity {

    @Id
    private String id;
    private String userID;
    private String name;
    private String email;
    private String contactNumber;
    private LocalDate appointedDate;
    private String employeeStatus;
}