package com.internship.vendor.controller;

import com.internship.vendor.enitity.Vendor;
import com.internship.vendor.repository.vendorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendors")
public class VendorController {
    @Autowired
    vendorService service;

    @PostMapping("/")
    @ApiOperation(value = "Add Vendors in the database",
            notes = "kindly provide Vendor Id, Vendor Name, phone number and Bank Details",
            response = Vendor.class
    )
    public Vendor addVendor(@ApiParam(value = "Vendor to be added in DB", required = true)
            @RequestBody Vendor vendor){
        return service.addVendor(vendor);
    }

    @GetMapping("/")
    public List<Vendor> getVendors(){
        return service.getAllVendors();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Vendor from the database by Vendor id",
            notes = "kindly provide Vendor Id to retrieve Vendor Details",
            response = Vendor.class
    )
    public Vendor getVendor(@ApiParam(value = "Vendor ID for the vendor you need to retrieve", required = true)
            @PathVariable("id") Long vendorId)

    {
        return service.getVendor(vendorId);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete Vendor in the database",
            notes = "kindly provide Vendor Id to delete the Vendor from the Database",
            response = Vendor.class
    )
    public Vendor deleteVendor(@PathVariable ("id") Long vendorId){
        return service.deleteVendor(vendorId);
    }
}
