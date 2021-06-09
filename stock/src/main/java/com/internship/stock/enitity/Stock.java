package com.internship.stock.enitity;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long stockId;
    String stockName;
    Long stockQty;
    Long vendorId;
}
