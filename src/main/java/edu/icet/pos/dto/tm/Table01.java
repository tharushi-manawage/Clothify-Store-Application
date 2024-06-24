package edu.icet.pos.dto.tm;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Table01 {
    private String userID;
    private String userType;
    private String name;
    private Date dob;
}
