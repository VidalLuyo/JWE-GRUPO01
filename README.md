# Demo JWE - Grupo 01

JWE (JSON Web Encryption) with symmetric AES-256 encryption.

## Tech Stack
- Java 21
- Spring Boot 3
- Nimbus JOSE

## Run
```bash
mvn spring-boot:run
```

## Endpoints (port 9876)

### POST /api/encrypt
```json
{"usuario":"Vidal","curso":"Criptografia"}
```

### POST /api/decrypt
```json
{"jweToken":"eyJ..."}
```

## Algorithm
- **A256GCM**: AES-256-GCM symmetric encryption
- **DIR**: Direct key agreement (shared secret key)
