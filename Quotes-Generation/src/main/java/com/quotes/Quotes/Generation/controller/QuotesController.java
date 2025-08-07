package com.quotes.Quotes.Generation.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quotes.Quotes.Generation.entity.Quotes;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

@RestController
public class QuotesController {
    private List<Quotes> quotes;
    private final Random random = new Random();

    @PostConstruct
    public void init() throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = new ClassPathResource("quotes.json").getInputStream();
        quotes = mapper.readValue(inputStream, new TypeReference<List<Quotes>>() {});
    }
    @GetMapping("/quote")
    public Quotes getQuotes(){
        int index = random.nextInt(quotes.size());
        return quotes.get(index);
    }



}
