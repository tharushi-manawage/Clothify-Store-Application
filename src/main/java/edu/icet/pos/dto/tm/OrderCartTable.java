package edu.icet.pos.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class OrderCartTable {
    private String productID;
    private String item;
    private String size;
    private Integer qty;
    private Double unitPrice;
    private Double discount;
    private Double subTotal;
}