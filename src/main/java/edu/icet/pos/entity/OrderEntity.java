package edu.icet.pos.entity;

import edu.icet.pos.dto.OrderDetail;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.util.List;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
public class OrderEntity {

    @Id
    private String id;
    private String employeeID;
    private String operator;
    private List<OrderDetail> orderDetailList;
    private Integer totalItems;
    private Double netTotal;
    private String paymentType;
    private LocalDate orderDate;
}