package com.example.bfhl.service;

import com.example.bfhl.dto.BfhlRequestDto;
import com.example.bfhl.dto.BfhlResponseDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BfhlServiceImpl implements BfhlService {

    @Override
    public BfhlResponseDto processData(BfhlRequestDto request) {
        BfhlResponseDto response = new BfhlResponseDto();
        
        // Tumhare details perfectly updated hain!
        response.setUserId("atharva_rathore_03082004");
        response.setEmail("atharvarathore231053@acropolis.in");
        response.setRollNumber("0827IT231035");

        if (request == null || request.getData() == null) {
            response.setIsSuccess(false); // Fixed: compiler issue resolved here
            return response;
        }

        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        int numericSum = 0;
        
        StringBuilder alphaConcatRaw = new StringBuilder();

        for (String item : request.getData()) {
            if (item == null) continue;

            if (item.matches("-?\\d+")) { 
                // It's a number
                int val = Integer.parseInt(item);
                numericSum += val;
                if (val % 2 == 0) {
                    evenNumbers.add(item);
                } else {
                    oddNumbers.add(item);
                }
            } else if (item.matches("[a-zA-Z]+")) {
                // It's an alphabet string
                alphabets.add(item.toUpperCase());
                alphaConcatRaw.append(item);
            } else {
                // It's a special character
                specialCharacters.add(item);
            }
        }

        // Logic for Alternating Caps on REVERSED string
        String reversedAlpha = alphaConcatRaw.reverse().toString();
        StringBuilder alternatingCapsStr = new StringBuilder();
        for (int i = 0; i < reversedAlpha.length(); i++) {
            char c = reversedAlpha.charAt(i);
            if (i % 2 == 0) {
                alternatingCapsStr.append(Character.toUpperCase(c));
            } else {
                alternatingCapsStr.append(Character.toLowerCase(c));
            }
        }

        response.setOddNumbers(oddNumbers);
        response.setEvenNumbers(evenNumbers);
        response.setAlphabets(alphabets);
        response.setSpecialCharacters(specialCharacters);
        response.setSum(String.valueOf(numericSum));
        response.setConcatString(alternatingCapsStr.toString());
        response.setIsSuccess(true); // Fixed: compiler issue resolved here

        return response;
    }
}