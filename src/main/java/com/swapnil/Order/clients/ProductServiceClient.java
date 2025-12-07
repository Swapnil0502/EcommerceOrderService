package com.swapnil.Order.clients;

import com.swapnil.Order.dto.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductServiceClient {

    private RestTemplate restemplate;

    public ProductServiceClient(RestTemplate restTemplate){
        this.restemplate = restTemplate;
    }

    public ProductDto getProductbyId(Long productId){
        String url = "http://localhost:3001/api/products/getProductById/" + productId;
        ResponseEntity<ProductDto> response = restemplate.getForEntity(url,ProductDto.class);
        return response.getBody();
    }
}
