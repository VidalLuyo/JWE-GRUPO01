package com.jwe.demo.config;

import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RsaKeyConfig {

    // Genera par de claves RSA-2048 (cifrado asimétrico para proteger la clave AES)
    @Bean
    public RSAKey rsaKey() throws Exception {
        RSAKey rsaKey = new RSAKeyGenerator(2048).keyID("jwe-demo-key").generate();
        System.out.println("Claves RSA generadas: " + rsaKey.getKeyID());
        return rsaKey;
    }
}
