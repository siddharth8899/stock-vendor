package com.internship.stock.enitity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vendor {
    Long vendorId;
    String vendorName;
    String vendorPhoneNumber;
    String vendorEmailId;
    String bankDetails;

}
