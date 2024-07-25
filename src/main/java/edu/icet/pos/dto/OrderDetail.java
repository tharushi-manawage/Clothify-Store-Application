package edu.icet.pos.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class OrderDetail {
    private String orderID;
    private String productID;
    private String item;
    private String size;
    private Integer qty;
    private Double unitPrice;
    private Double discount;
    private Double subTotal;
}