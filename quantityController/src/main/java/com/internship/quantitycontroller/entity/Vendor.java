package com.internship.quantitycontroller.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vendor {
    Long vendorId;
    String vendorName;
    String vendorPhoneNumber;
    String bankDetails;
}
