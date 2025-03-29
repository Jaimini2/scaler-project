package org.example.ProductCatalogService.controllers;

import org.example.ProductCatalogService.dtos.ProductDTO;
import org.example.ProductCatalogService.models.Product;
import org.example.ProductCatalogService.services.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/*
REST supports
GET,
POST,
PUT,
PATCH,
DELETE
 */

@RestController
@RequestMapping("/products")
public class ProductController {

   private  IProductService productService;

    public ProductController(IProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProduct(){
        return productService.getAllProducts();
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long productId){
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
       /* try {

            headers.add("Called by","smart Frontend");
            if(productId < 1){
                headers.add("called by ","Pagal Frontend");
                throw new IllegalAccessException("Id is not valid");
            }
            Product product = productService.getProduct(productId);
            return new ResponseEntity<>(product, headers,HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }*/

        try{
            if(productId < 1){
                throw new IllegalArgumentException("Invalid product ID , please pass some valid Id");
            }
            Product product = productService.getProduct(productId);
            return new ResponseEntity<>(product,HttpStatus.OK);
        }catch (Exception e){
            throw e;
        }
    }



    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product createProduct(@RequestBody ProductDTO productDto){
        return productService.createProduct(getProduct(productDto));
    }

    public Product replaceProduct(@PathVariable("id") Long id,@RequestBody ProductDTO productDTO){
        return productService.replaceProduct(id,getProduct(productDTO));
    }

    private Product getProduct(ProductDTO productDTO){
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setImageURL(productDTO.getImageURL());
        return product;
    }
}
