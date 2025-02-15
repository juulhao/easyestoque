package com.easyestoqueltda.easyestoque.model;

import lombok.Data;

@Data
public class DTOCreateProductRequest  {
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String code;
}
