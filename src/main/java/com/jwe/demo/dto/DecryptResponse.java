package com.jwe.demo.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DecryptResponse {
    private String usuario;
    private String curso;
    private String contenidoOriginal;
    private String mensaje;

    /**
     * Constructor que crea la respuesta a partir del JSON descifrado
     */
    public static DecryptResponse fromJson(String jsonContent) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        EncryptRequest request = mapper.readValue(jsonContent, EncryptRequest.class);
        
        DecryptResponse response = new DecryptResponse();
        response.setUsuario(request.getUsuario());
        response.setCurso(request.getCurso());
        response.setContenidoOriginal(jsonContent);
        response.setMensaje("JWE descifrado exitosamente");
        
        return response;
    }
}
