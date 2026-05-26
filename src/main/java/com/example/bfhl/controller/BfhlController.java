package com.example.bfhl.controller;

import com.example.bfhl.dto.BfhlRequestDto;
import com.example.bfhl.dto.BfhlResponseDto;
import com.example.bfhl.service.BfhlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/bfhl")
@CrossOrigin(origins = "*") 
public class BfhlController {

    private final BfhlService bfhlService;

    public BfhlController(BfhlService bfhlService) {
        this.bfhlService = bfhlService;
    }

    // 🎯 FIX: Path me "/" se start karke humne controller ke "/bfhl" prefix ko bypass kar diya
    @GetMapping("/../health")
    // Ya fir agar upar wala tareeka samajh na aaye, toh niche simple annotation hai:
    // @GetMapping(value = "/health", headers = "Connection!=Keep-Alive") 
    // Sabse best hai ki hum direct control bypass karein, niche wala line direct use karo:
    @RequestMapping(value = "/health", method = RequestMethod.GET, name = "health")
    @ResponseBody
    public ResponseEntity<Map<String, String>> getHealthCheck() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        return ResponseEntity.ok(response);
    }

    // Isko aisi hi rehne do, ye /bfhl par chalega
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