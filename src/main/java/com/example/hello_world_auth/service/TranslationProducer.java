package com.example.hello_world_auth.service;

import com.example.hello_world_auth.dto.TranslationMessageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class TranslationProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    
    @Value("${kafka.topic.translation-request:translation-requests}")
    private String translationRequestTopic;

    public void sendTranslationRequest(String docId, String docName, String docContent) {
        TranslationMessageDTO message = new TranslationMessageDTO(docId, docName, docContent);
        
        log.info("Sending translation request for document: {}", docId);
        
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(translationRequestTopic, docId, message);
        
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Translation request sent successfully for document: {}, offset: {}", 
                         docId, result.getRecordMetadata().offset());
            } else {
                log.error("Unable to send translation request for document: {}", docId, ex);
            }
        });
    }
}