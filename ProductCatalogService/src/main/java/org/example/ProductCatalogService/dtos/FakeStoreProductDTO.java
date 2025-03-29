package org.example.ProductCatalogService.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.ProductCatalogService.models.BaseModel;

@Getter
@Setter
public class FakeStoreProductDTO  extends BaseModel {

    private String title;

    private String description;

    private String category;

    private Double price;

    private String image;

    private FakeStoreRatingDTO rating;

}
