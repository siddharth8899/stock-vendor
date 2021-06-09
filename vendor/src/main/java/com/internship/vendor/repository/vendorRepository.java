package com.internship.vendor.repository;

import com.internship.vendor.enitity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface vendorRepository extends JpaRepository<Vendor, Long> {
    Vendor findByVendorId(Long vendorId);
}
