package com.palindromeChecker.palindromeChecker.controller;

import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class PalindromeController {

    public static boolean isPalindrome(String keyword, int startIndex, int endIndex){
        if(startIndex >= endIndex){
            return true;
        }
        if(keyword.charAt(startIndex) != keyword.charAt(endIndex)){
            return false;
        }
        return isPalindrome(keyword, startIndex+1, endIndex-1);
    }

    @GetMapping("/")
    public String palindromeCheck(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        if (keyword != null && !keyword.isEmpty()) {
            boolean palindrome = isPalindrome(keyword, 0, keyword.length() - 1);
            model.addAttribute("result", true);
            model.addAttribute("keyword", keyword);
            model.addAttribute("isPalindrome", palindrome);
        } else {
            model.addAttribute("result", false);
            model.addAttribute("keyword", "");  // Add this line!
        }
        return "index";
    }



}
