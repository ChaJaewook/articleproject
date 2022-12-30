package com.example.articleproject.objectmapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class Burger {
    //json을 객체로 객체를 json을 만들자

    private String name;
    private int price;
    private List<String> ingredients;
}
