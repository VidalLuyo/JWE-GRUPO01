package com.jwe.demo.config;

import com.nimbusds.jose.jwk.OctetSequenceKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeyConfig {

    // Generar clave simétrica AES-256 (clave compartida)
    @Bean
    public OctetSequenceKey symmetricKey() {
        byte[] keyBytes = new byte[32]; // 256 bits
        new java.security.SecureRandom().nextBytes(keyBytes);
        OctetSequenceKey key = new OctetSequenceKey.Builder(keyBytes)
            .keyID("symmetric-key")
            .build();
        System.out.println("Clave AES-256 generada");
        return key;
    }
}
