package com.internship.vendor.repository;

import com.internship.vendor.enitity.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class vendorService{
    @Autowired
    vendorRepository repo;

    public Vendor addVendor(Vendor vendor) {
        return repo.save(vendor);
    }

    public Vendor getVendor(Long vendorId) {
        Vendor vendor = repo.findByVendorId(vendorId);
        return vendor;
    }

    public Vendor deleteVendor(Long vendorId) {
        Vendor v = repo.findByVendorId(vendorId);
        repo.delete(v);
        return v;
    }

    public List<Vendor> getAllVendors() {
        return repo.findAll();
    }
}