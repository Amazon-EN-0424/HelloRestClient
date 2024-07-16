package com.scorsaro.hellorc.model;

import lombok.Data;

import java.util.List;

@Data
public class WordDefinition {
    private String word;
    private List<Meaning> meanings;
}
