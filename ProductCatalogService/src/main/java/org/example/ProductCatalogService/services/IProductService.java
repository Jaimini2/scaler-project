package org.example.ProductCatalogService.services;

import org.example.ProductCatalogService.dtos.ProductDTO;
import org.example.ProductCatalogService.models.Product;

import java.util.List;

public interface IProductService {

    public List<Product> getAllProducts();

    public Product getProduct(Long productId);

    public Product createProduct(Product product);

    public Product replaceProduct(Long id,Product product);
}
