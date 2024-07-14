package com.example.demo.Services;

import com.example.demo.Models.Category;
import com.example.demo.Models.Product;
import com.example.demo.dtos.FakeStoreProduct;
import com.example.demo.exceptions.ProductNotFoundException;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {
    private  RestTemplate restTemplate;
    FakeStoreProductService(RestTemplate restTemplate)
    {
        this.restTemplate=restTemplate;
    }

    private Product convertDtoToModel(FakeStoreProduct fakeStoreProduct)
    {
        Product p=new Product();
        p.setId(fakeStoreProduct.id());
        p.setTitle(fakeStoreProduct.title());
        p.setDescription(fakeStoreProduct.description());
        p.setPrice(fakeStoreProduct.price());
        p.setImage(fakeStoreProduct.image());
        Category c=new Category();
        c.setDescription(fakeStoreProduct.category());
        p.setCategory(c);
        return p;
    }
    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {

        FakeStoreProduct fakeStoreProduct=restTemplate.getForObject("https://fakestoreapi.com/products/"+id, FakeStoreProduct.class);
        if(fakeStoreProduct==null)
            throw new ProductNotFoundException(id,"Product Not found with provided id");

        return convertDtoToModel(fakeStoreProduct);
        
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProduct[] fakeStoreProductList=restTemplate.getForObject("https://fakestoreapi.com/products/",FakeStoreProduct[].class);
        List<Product> productList=new ArrayList<>();
        if (fakeStoreProductList==null)
            return null;
        for(var x:fakeStoreProductList)
        {
            productList.add(convertDtoToModel(x));
        }
        return  productList;

    }

    @Override
    public Product replaceProductById(Long id, Product product) {

        FakeStoreProduct fakeStoreProduct=new FakeStoreProduct(null, product.getTitle(), product.getPrice(), product.getCategory().getDescription(),product.getDescription(),product.getImage());
        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProduct, FakeStoreProduct.class);
        HttpMessageConverterExtractor<FakeStoreProduct> responseExtractor = new HttpMessageConverterExtractor<>(FakeStoreProduct.class, restTemplate.getMessageConverters());
        FakeStoreProduct response=restTemplate.execute("https://fakestoreapi.com/products/"+id, HttpMethod.PUT, requestCallback, responseExtractor);
        //System.out.println(response);
        ArrayList<ArrayList<Integer>> al=new ArrayList<>();
        return response==null?null:convertDtoToModel(response);

    }
}
