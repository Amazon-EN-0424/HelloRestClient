package com.scorsaro.hellorc.model;

import lombok.Data;

import java.util.List;

@Data
public class Meaning {
    private String partOfSpeech;
    private List<Definition> definitions;
}
