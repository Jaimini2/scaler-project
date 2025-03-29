package org.example.ProductCatalogService.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.ProductCatalogService.models.BaseModel;
import org.example.ProductCatalogService.models.Category;

@Getter
@Setter
public class    ProductDTO extends BaseModel {
    private String name;

    private String description;

    private String imageURL;

    private Double price;

    private Category category;
}
