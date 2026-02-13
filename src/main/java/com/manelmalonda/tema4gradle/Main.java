package com.manelmalonda.tema4gradle;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.openai.OpenAiChatModel;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        OpenAiChatModel model = OpenAiChatModel.builder()
                .baseUrl("http://localhost:11434/v1")
                .apiKey("ignore")
                .modelName("gemma:2b")
                .build();

        //PUNTO 7: GESTIÓN DE LA MEMORIA

        List<ChatMessage> historial = new ArrayList<>();

        String mensaje1 = "Hola, soy Manel.";
        System.out.println("Tú: " + mensaje1);

        historial.add(new UserMessage(mensaje1));

        AiMessage respuesta1 = model.chat(historial).aiMessage();
        historial.add(respuesta1);

        System.out.println("IA: " + respuesta1.text());

        String mensaje2 = "¿Recuerdas cómo me llamo?";
        System.out.println("\nTú: " + mensaje2);

        historial.add(new UserMessage(mensaje2));


        AiMessage respuesta2 = model.chat(historial).aiMessage();
        historial.add(respuesta2);

        System.out.println("IA: " + respuesta2.text());
    }
}