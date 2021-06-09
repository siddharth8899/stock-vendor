package com.internship.stock.enitity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock_id_qty {
    Long stockId;
    String stockName;
    Long stockQty;
}
