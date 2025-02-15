package com.easyestoqueltda.easyestoque.model;

import lombok.Data;

@Data
public class DTOCreateProductResponse {
    private long id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String code;

}
