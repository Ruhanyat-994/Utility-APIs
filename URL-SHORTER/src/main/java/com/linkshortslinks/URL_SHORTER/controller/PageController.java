package com.linkshortslinks.URL_SHORTER.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Collections;

@Controller
public class PageController {

    // Provide safe defaults so Mustache never errors on missing fields
    @ModelAttribute
    public void addDefaults(Model model) {
        if (!model.containsAttribute("shortUrl"))   model.addAttribute("shortUrl", "");
        if (!model.containsAttribute("shortCode"))  model.addAttribute("shortCode", "");
        if (!model.containsAttribute("longUrl"))    model.addAttribute("longUrl", "");
        if (!model.containsAttribute("clickCount")) model.addAttribute("clickCount", 0);
        if (!model.containsAttribute("timestamps")) model.addAttribute("timestamps", Collections.emptyList());
    }

    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    @GetMapping("/stats")
    public String statsPage() {
        return "stats";
    }
}
