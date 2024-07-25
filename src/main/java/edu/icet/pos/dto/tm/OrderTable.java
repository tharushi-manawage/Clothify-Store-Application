package edu.icet.pos.dto.tm;

import java.time.LocalDate;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class OrderTable {
    private String orderID;
    private String employeeID;
    private String operator;
    private Integer totalItems;
    private Double netTotal;
    private String paymentType;
    private LocalDate orderDate;
}