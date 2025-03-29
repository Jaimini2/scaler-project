package org.example.ProductCatalogService.services;


import org.example.ProductCatalogService.clients.FakeStoreApiClient;
import org.example.ProductCatalogService.dtos.FakeStoreProductDTO;
import org.example.ProductCatalogService.dtos.ProductDTO;
import org.example.ProductCatalogService.models.Category;
import org.example.ProductCatalogService.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@Service
public class ProductService implements IProductService {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private FakeStoreApiClient fakeStoreApiClient;
    @Override
    public List<Product> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
       FakeStoreProductDTO[] fakeStoreProductDTO = restTemplate.getForEntity("https://fakestoreapi.com/products" , FakeStoreProductDTO[].class).getBody();
       List<Product> products = new ArrayList<>();

       for (FakeStoreProductDTO fakeStoreProductDTO1 : fakeStoreProductDTO) {
           products.add(getProduct(fakeStoreProductDTO1));
       }
        return products;
    }

    @Override
    public Product getProduct(Long productId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreApiClient.getProduct(productId);
        return getProduct(fakeStoreProductDTO);
    }

    @Override
    public Product createProduct(Product product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDTO fakeStoreProductdtoReq = getFakeStoreProductDTO(product);
        FakeStoreProductDTO fakeStoreProductDTO = restTemplate.postForEntity("https://fakestoreapi.com/products/",fakeStoreProductdtoReq,FakeStoreProductDTO.class).getBody();

        return getProduct(fakeStoreProductDTO) ;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDTO fakeStoreProductDtoReq = getFakeStoreProductDTO(product);
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity = putForEntity("https://fakestoreapi.com/products/{id}",fakeStoreProductDtoReq, FakeStoreProductDTO.class,id);
        return getProduct(fakeStoreProductDTOResponseEntity.getBody());
    }

    public <T> ResponseEntity<T> putForEntity(String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
       RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, HttpMethod.POST, requestCallback, responseExtractor, uriVariables);
    }


    private Product getProduct(FakeStoreProductDTO fakeStoreProductDTO){
        Product product = new Product();
        product.setId(fakeStoreProductDTO.getId());
        product.setName(fakeStoreProductDTO.getTitle());
        product.setPrice(fakeStoreProductDTO.getPrice());
        product.setDescription(fakeStoreProductDTO.getDescription());
        product.setImageURL(fakeStoreProductDTO.getImage());

        Category category = new Category();
        category.setName(fakeStoreProductDTO.getCategory());
        product.setCategory(category);
        return product;
    }

    private FakeStoreProductDTO getFakeStoreProductDTO(Product product){
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setDescription(product.getDescription());
        fakeStoreProductDTO.setPrice(product.getPrice());
        fakeStoreProductDTO.setImage(product.getImageURL());
        fakeStoreProductDTO.setTitle(product.getName());
        if(product.getCategory() != null){
            fakeStoreProductDTO.setCategory(product.getCategory().getName());
        }
        return  fakeStoreProductDTO;
    }


}
