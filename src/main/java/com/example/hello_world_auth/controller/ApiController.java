package com.example.hello_world_auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
public class ApiController {

    // Simple hello test
    @GetMapping("/hello")
    public String helloEndpoint() {
        log.info("Accessed /api/hello endpoint");
        return "Hello, authenticated user!";
    }

    // Category endpoints
    @PostMapping("/category.create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String createCategory() {
        log.info("Accessed /api/category.create endpoint");
        return "Category created successfully!";
    }

    @PutMapping("/category.edit")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editCategory() {
        log.info("Accessed /api/category.edit endpoint");
        return "Category edited successfully!";
    }

    @GetMapping("/category.list")
    @PreAuthorize("hasRole('ROLE_USER')" + " or hasRole('ROLE_ADMIN')")
    public String listCategories() {
        log.info("Accessed /api/category.list endpoint");
        return "Here are the categories.";
    }

    // Document endpoints
    @GetMapping("/document.list")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String listDocuments() {
        log.info("Accessed /api/document.list endpoint");
        return "Here are the documents.";
    }

    @PostMapping("/document.create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String createDocument() {
        log.info("Accessed /api/document.create endpoint");
        return "Document created successfully!";
    }
}
