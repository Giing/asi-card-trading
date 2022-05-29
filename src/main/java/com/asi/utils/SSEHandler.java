package com.asi.utils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public class SSEHandler {
    public Map<String, SseEmitter> emitters;

    public SSEHandler() {
        emitters = new HashMap<String, SseEmitter>();
    }

    public SseEmitter addClient() {
        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
        String client = LocalDateTime.now().toString();

        sseEmitter.onCompletion(() -> {
            emitters.remove(client);
            System.out.println("SseEmitter is completed");
        });
        sseEmitter.onTimeout(() -> System.out.println("SseEmitter is timed out"));
        sseEmitter.onError((ex) -> System.out.println("SseEmitter got error:" + ex));

        emitters.put(client, sseEmitter);
        return sseEmitter;
    }
}
