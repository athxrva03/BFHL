package com.example.bfhl.controller;

import com.example.bfhl.dto.BfhlRequestDto;
import com.example.bfhl.dto.BfhlResponseDto;
import com.example.bfhl.service.BfhlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*") // For frontend calls
public class BfhlController {

    private final BfhlService bfhlService;

    public BfhlController(BfhlService bfhlService) {
        this.bfhlService = bfhlService;
    }

    // 🎯 1. Health Check Endpoint (Direct base URL par chalega)
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> getHealth() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        return ResponseEntity.ok(response);
    }

    // 🎯 2. Actual POST API Endpoint (Jo tumhara sahi chal raha hai)
    @PostMapping("/bfhl")
    public ResponseEntity<BfhlResponseDto> handlePost(@RequestBody BfhlRequestDto request) {
        try {
            BfhlResponseDto response = bfhlService.processData(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            BfhlResponseDto errorResponse = new BfhlResponseDto();
            errorResponse.setIsSuccess(false);
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    // 🎯 3. Optional: BFHL GET Endpoint (Agar tester /bfhl par GET mare toh 405 na aaye)
    @GetMapping("/bfhl")
    public ResponseEntity<Map<String, Integer>> handleGet() {
        Map<String, Integer> response = new HashMap<>();
        response.put("operation_code", 1);
        return ResponseEntity.ok(response);
    }
}