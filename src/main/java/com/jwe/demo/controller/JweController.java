package com.jwe.demo.controller;

import com.jwe.demo.dto.DecryptRequest;
import com.jwe.demo.dto.DecryptResponse;
import com.jwe.demo.dto.EncryptRequest;
import com.jwe.demo.dto.EncryptResponse;
import com.jwe.demo.service.JweService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class JweController {

    private final JweService jweService;

    public JweController(JweService jweService) {
        this.jweService = jweService;
    }

    @PostMapping("/encrypt")
    public ResponseEntity<EncryptResponse> encrypt(@RequestBody EncryptRequest request) {
        try {
            String token = jweService.encrypt(request);
            return ResponseEntity.ok(new EncryptResponse(token, "OK"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new EncryptResponse(null, e.getMessage()));
        }
    }

    @PostMapping("/decrypt")
    public ResponseEntity<DecryptResponse> decrypt(@RequestBody DecryptRequest request) {
        try {
            return ResponseEntity.ok(jweService.decrypt(request.getJweToken()));
        } catch (Exception e) {
            DecryptResponse res = new DecryptResponse();
            res.setMensaje(e.getMessage());
            return ResponseEntity.badRequest().body(res);
        }
    }
}
