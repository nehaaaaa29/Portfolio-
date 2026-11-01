package com.example.Portfolio.controller;

import com.example.Portfolio.dto.ApiResponse;
import com.example.Portfolio.service.VisitorService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/visitors")
public class VisitorController {

    @Autowired
    private VisitorService visitorService;

    @PostMapping("/track")
    public ResponseEntity<ApiResponse<Void>> track(
            @RequestParam(defaultValue = "/") String page,
            @RequestParam(required = false) String referrer,
            @RequestParam(required = false) String resolution,
            HttpServletRequest request) {
        visitorService.trackVisit(request, page, referrer, resolution);
        return ResponseEntity.ok(ApiResponse.success("Tracked", null));
    }

    @GetMapping("/stats")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getStats() {
        return ResponseEntity.ok(ApiResponse.success(visitorService.getStats()));
    }
}

