package com.booking.ticketmybus.controller.vendor;


import com.booking.ticketmybus.dto.shared.UserDTO;
import com.booking.ticketmybus.dto.vendor.request.VendorRegisterRequest;
import com.booking.ticketmybus.entity.User;
import com.booking.ticketmybus.service.vendor.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendor")
public class AuthController {

    @Autowired
    private VendorService vendorService;

    @PostMapping("/register")
    public ResponseEntity<?> registerVendor(@RequestBody VendorRegisterRequest request) {
        try {
            User vendor = vendorService.registerVendor(request);
            return ResponseEntity.ok("Vendor registered successfully with ID: " + vendor.getId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
