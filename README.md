# Demo JWE - Grupo 01

Demostración de JSON Web Encryption (JWE) para la exposición de Criptografía.

## Tecnologías
- Java 21
- Spring Boot 3
- Nimbus JOSE + JWT

## Ejecutar
```bash
mvn spring-boot:run
```

## Endpoints (puerto 9876)

### 1. Cifrar datos
```
POST http://localhost:9876/api/encrypt
Content-Type: application/json

{
  "usuario": "Vidal",
  "curso": "Criptografia"
}
```

### 2. Descifrar datos
```
POST http://localhost:9876/api/decrypt
Content-Type: application/json

{
  "jweToken": "eyJhbGciOiJSU0EtT0FFUC0yNTYiLCJlbmMiOiJBMjU2R0NN..."
}
```

## Cómo funciona JWE
JWE usa dos tipos de cifrado combinados:

1. **RSA-OAEP-256 (Asimétrico)**: Cifra la clave AES con la clave pública RSA
2. **A256GCM (Simétrico AES-256)**: Cifra el contenido con la clave AES

## Estructura del token JWE
```
HEADER.ENCRYPTED_KEY.IV.CIPHERTEXT.TAG
```
- HEADER: Algoritmos usados
- ENCRYPTED_KEY: Clave AES cifrada con RSA
- IV: Vector de inicialización
- CIPHERTEXT: Datos cifrados con AES
- TAG: Firma de autenticación
