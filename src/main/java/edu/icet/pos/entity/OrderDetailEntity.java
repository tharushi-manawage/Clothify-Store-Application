package edu.icet.pos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
public class OrderDetailEntity {

    @Id
    private String id;
    private String productID;
    private String item;
    private String size;
    private Integer qty;
    private Double unitPrice;
    private Double discount;
    private Double subTotal;
}