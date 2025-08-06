package com.quotes.Quotes.Generation.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWarDeployment;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quotes {
    @JsonProperty("Quran")
    private String quran;
    @JsonProperty("Ayat")
    private String ayat;

}
