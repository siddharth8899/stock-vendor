package com.internship.stock.enitity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WrapperPojo {
    Stock stock;
    Vendor vendor;
}
