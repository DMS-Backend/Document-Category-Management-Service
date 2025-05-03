package com.example.hello_world_auth.service;

import com.example.hello_world_auth.dto.TranslationMessageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TranslationConsumer {

    // Inject your document service here to update the translated title
    // private final DocumentService documentService;

    @KafkaListener(topics = "${kafka.topic.translation-response:translation-responses}", 
                  containerFactory = "kafkaListenerContainerFactory",
                  groupId = "${spring.kafka.consumer.group-id:document-service}")
    public void consumeTranslationResponse(TranslationMessageDTO response) {
        log.info("Received translation response for document: {}", response.getDocId());
        
        // Here you would update your document with the translated title
        // For example:
        // documentService.updateTranslatedTitle(response.getDocId(), response.getTranslatedContent());
        
        log.info("Successfully processed translation for document: {}", response.getDocId());
    }
}