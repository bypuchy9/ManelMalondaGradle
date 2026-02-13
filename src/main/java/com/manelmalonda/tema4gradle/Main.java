package com.manelmalonda.tema4gradle;

import dev.langchain4j.model.openai.OpenAiChatModel;

public class Main {
    public static void main(String[] args) {

        // PUNTO 6: Configuración del modelo y prueba simple
        var model = OpenAiChatModel.builder()
                .baseUrl("http://localhost:11434/v1")
                .apiKey("ignore")
                .modelName("gemma:2b")
                .build();


        System.out.println("Enviando mensaje a la IA...");
        String respuesta = model.chat("Hola, cuéntame un chiste muy breve.");



        System.out.println("Respuesta de la IA:");
        System.out.println(respuesta);
    }
}