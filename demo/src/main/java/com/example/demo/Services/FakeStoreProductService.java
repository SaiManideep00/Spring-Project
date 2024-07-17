package com.example.demo.Services;

import com.example.demo.Models.Category;
import com.example.demo.Models.Product;
import com.example.demo.dtos.FakeStoreProduct;
import com.example.demo.dtos.ProductDTO;
import com.example.demo.exceptions.ProductNotFoundException;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("fakeStoreService")
public class FakeStoreProductService implements ProductService {
    private  RestTemplate restTemplate;
    FakeStoreProductService(RestTemplate restTemplate)
    {
        this.restTemplate=restTemplate;
    }

    private ProductDTO convertDtoToModel(FakeStoreProduct fakeStoreProduct)
    {
       ProductDTO productDTO=new ProductDTO(fakeStoreProduct.title(), fakeStoreProduct.description(), fakeStoreProduct.price(), fakeStoreProduct.image(), fakeStoreProduct.category());
       return productDTO;
    }
    @Override
    public ProductDTO getProductById(Long id) throws ProductNotFoundException {

        FakeStoreProduct fakeStoreProduct=restTemplate.getForObject("https://fakestoreapi.com/products/"+id, FakeStoreProduct.class);
        if(fakeStoreProduct==null)
            throw new ProductNotFoundException(id,"Product Not found with provided id");

        return convertDtoToModel(fakeStoreProduct);
        
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        FakeStoreProduct[] fakeStoreProductList=restTemplate.getForObject("https://fakestoreapi.com/products/",FakeStoreProduct[].class);

        if (fakeStoreProductList==null)
            return null;
        List<ProductDTO> productDTOList=new ArrayList<>();
        for(var fakeStoreProduct:fakeStoreProductList)
        {
            productDTOList.add(convertDtoToModel(fakeStoreProduct));
        }
        return productDTOList;


    }

    @Override



    public ProductDTO replaceProduct(Long id, Product product) {

        FakeStoreProduct fakeStoreProduct=new FakeStoreProduct(null, product.getTitle(), product.getPrice(), product.getCategory().getDescription(),product.getDescription(),product.getImage());
        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProduct, FakeStoreProduct.class);
        HttpMessageConverterExtractor<FakeStoreProduct> responseExtractor = new HttpMessageConverterExtractor<>(FakeStoreProduct.class, restTemplate.getMessageConverters());
        FakeStoreProduct response=restTemplate.execute("https://fakestoreapi.com/products/"+id, HttpMethod.PUT, requestCallback, responseExtractor);
        //System.out.println(response);
        ArrayList<ArrayList<Integer>> al=new ArrayList<>();
        return response==null?null:convertDtoToModel(response);

    }

    @Override
    public ProductDTO updateProduct(Long id, Map<String,Object> values) {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
