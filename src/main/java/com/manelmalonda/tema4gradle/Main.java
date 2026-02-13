package com.manelmalonda.tema4gradle;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.openai.OpenAiChatModel;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // PUNTO 8: DEBATE ENTRE DOS IAs

        OpenAiChatModel model = OpenAiChatModel.builder()
                .baseUrl("http://localhost:11434/v1")
                .apiKey("ignore")
                .modelName("gemma:2b")
                .build();


        String tema = "La tortilla de patata, ¿con cebolla o sin cebolla?";
        System.out.println("=== COMIENZO DEL DEBATE: " + tema + " ===");

        List<ChatMessage> historial = new ArrayList<>();

        historial.add(new SystemMessage("Eres un experto debatiente. Tus respuestas deben ser breves, intensas y directas."));

        String mensajeInicial = "¿Qué opinas sobre " + tema + "?";
        historial.add(new UserMessage(mensajeInicial));
        System.out.println("Moderador: " + mensajeInicial);

        for (int i = 1; i <= 5; i++) {
            System.out.println("\n--- Turno " + i + " ---");

            AiMessage respuestaIA = model.chat(historial).aiMessage();

            System.out.println("IA Debatiente: " + respuestaIA.text());


            historial.add(respuestaIA);

            historial.add(new UserMessage(respuestaIA.text()));

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}