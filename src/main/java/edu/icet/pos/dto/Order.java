package edu.icet.pos.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Order {
    private String orderID;
    private String employeeID;
    private String operator;
    private List<OrderDetail> orderDetailList;
    private Integer totalItems;
    private Double netTotal;
    private String paymentType;
    private LocalDate orderDate;
}