package com.internship.vendor.enitity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Vendor details")
@Table(name = "vendor")
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "vendor Id")
    Long vendorId;

    @ApiModelProperty(notes = "vendor name")
    String vendorName;

    @ApiModelProperty(notes = "vendor phone number")
    String vendorPhoneNumber;

    @ApiModelProperty(notes = "vendor email id")
    String vendorEmailId;


    @ApiModelProperty(notes = "vendor Bank Details")
    String bankDetails;
}
