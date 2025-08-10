package com.palindromeChecker.palindromeChecker.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Palindrome {
    @JsonProperty("Keyword")
    private String  keyword;

}
