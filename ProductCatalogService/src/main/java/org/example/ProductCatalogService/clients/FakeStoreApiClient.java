package org.example.ProductCatalogService.clients;

import org.example.ProductCatalogService.dtos.FakeStoreProductDTO;
import org.example.ProductCatalogService.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FakeStoreApiClient {

    RestTemplateBuilder restTemplateBuilder;

    public FakeStoreApiClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public FakeStoreProductDTO getProduct(Long productId){
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDTO fakeStoreProductDTO = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}",FakeStoreProductDTO.class,productId).getBody();
        return fakeStoreProductDTO;
    }
}
