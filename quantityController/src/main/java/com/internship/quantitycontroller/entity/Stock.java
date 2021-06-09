package com.internship.quantitycontroller.entity;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class Stock {
    Long stockId;
    String stockName;
    Long stockQty;
    Long vendorId;
}
