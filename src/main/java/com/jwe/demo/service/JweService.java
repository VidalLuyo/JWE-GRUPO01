package com.jwe.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwe.demo.dto.DecryptResponse;
import com.jwe.demo.dto.EncryptRequest;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSAEncrypter;
import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.stereotype.Service;

// JWE: RSA-OAEP-256 (asimétrico) cifra la clave AES, A256GCM (simétrico AES-256) cifra el payload
@Service
public class JweService {

    private final RSAKey rsaKey;
    private final ObjectMapper objectMapper;

    public JweService(RSAKey rsaKey) {
        this.rsaKey = rsaKey;
        this.objectMapper = new ObjectMapper();
    }

    public String encrypt(EncryptRequest request) throws Exception {
        String json = objectMapper.writeValueAsString(request);
        System.out.println("Cifrando: " + json);

        JWEObject jwe = new JWEObject(
            new JWEHeader.Builder(JWEAlgorithm.RSA_OAEP_256, EncryptionMethod.A256GCM)
                .keyID(rsaKey.getKeyID()).build(),
            new Payload(json)
        );
        jwe.encrypt(new RSAEncrypter(rsaKey.toRSAPublicKey()));
        return jwe.serialize();
    }

    public DecryptResponse decrypt(String jweToken) throws Exception {
        JWEObject jwe = JWEObject.parse(jweToken);
        jwe.decrypt(new RSADecrypter(rsaKey.toRSAPrivateKey()));
        String json = jwe.getPayload().toString();
        System.out.println("Descifrado: " + json);
        return DecryptResponse.fromJson(json);
    }
}
