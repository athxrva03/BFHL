package com.example.bfhl.controller;

import com.example.bfhl.dto.BfhlRequestDto;
import com.example.bfhl.dto.BfhlResponseDto;
import com.example.bfhl.service.BfhlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bfhl")
@CrossOrigin(origins = "*") // Crucial for when the frontend tries to call it!
public class BfhlController {

    private final BfhlService bfhlService;

    public BfhlController(BfhlService bfhlService) {
        this.bfhlService = bfhlService;
    }

    @PostMapping
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
}