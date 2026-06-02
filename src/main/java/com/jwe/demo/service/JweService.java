package com.jwe.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwe.demo.dto.DecryptResponse;
import com.jwe.demo.dto.EncryptRequest;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jose.crypto.DirectDecrypter;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import org.springframework.stereotype.Service;

// JWE con cifrado simétrico solo (AES-256-GCM)
@Service
public class JweService {

    private final OctetSequenceKey key;
    private final ObjectMapper mapper;

    public JweService(OctetSequenceKey key) {
        this.key = key;
        this.mapper = new ObjectMapper();
    }

    public String encrypt(EncryptRequest req) throws Exception {
        String json = mapper.writeValueAsString(req);
        System.out.println("Cifrando: " + json);

        JWEObject jwe = new JWEObject(
            new JWEHeader.Builder(JWEAlgorithm.DIR, EncryptionMethod.A256GCM)
                .keyID(key.getKeyID()).build(),
            new Payload(json)
        );
        jwe.encrypt(new DirectEncrypter(key.toSecretKey()));
        return jwe.serialize();
    }

    public DecryptResponse decrypt(String token) throws Exception {
        JWEObject jwe = JWEObject.parse(token);
        jwe.decrypt(new DirectDecrypter(key.toSecretKey()));
        String json = jwe.getPayload().toString();
        System.out.println("Descifrado: " + json);
        return DecryptResponse.fromJson(json);
    }
}
