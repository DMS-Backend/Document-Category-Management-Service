package com.example.hello_world_auth.controller;

import com.example.hello_world_auth.service.TranslationProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
@Slf4j
public class DocumentController {

    private final TranslationProducer translationProducer;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Map<String, Object>> createDocument(@RequestBody Map<String, String> document) {
        log.info("Received document creation request");
        
        // Here in a real implementation you would save the document to your database
        // For demonstration purposes, we'll generate a document ID
        String docId = UUID.randomUUID().toString();
        String docName = document.get("title");
        String docContent = document.get("content");
        
        // Send the document title for translation via Kafka
        translationProducer.sendTranslationRequest(docId, docName, docContent);
        
        Map<String, Object> response = new HashMap<>();
        response.put("id", docId);
        response.put("message", "Document created and sent for translation");
        
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> listDocuments() {
        log.info("Listing documents");
        return ResponseEntity.ok("Document list would be shown here");
    }
}