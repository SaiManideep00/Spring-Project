package com.example.demo.Services;

import com.example.demo.Models.Category;
import com.example.demo.Models.Product;
import com.example.demo.dtos.ProductDTO;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.ProductRepository;
import com.mysql.cj.jdbc.exceptions.PacketTooBigException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("selfServiceProduct")
public class ProductSelfService implements ProductService{
    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    ProductSelfService(ProductRepository productRepository, CategoryRepository categoryRepository)
    {
        this.productRepository=productRepository;
        this.categoryRepository=categoryRepository;
    }
    ProductDTO convertProductToDto(Product product)
    {
        return new ProductDTO(product.getTitle(), product.getDescription(), product.getPrice(),product.getImage(),product.getCategory().getDescription());
    }
    @Override
    public ProductDTO getProductById(Long id) throws ProductNotFoundException {
        Product product=productRepository.findById(id).orElseThrow(()->new ProductNotFoundException(id,"Product not found with given id"));
        return convertProductToDto(product);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products=productRepository.findAll();
        List<ProductDTO> productDTOList=products.stream().map(this::convertProductToDto).toList();
        return productDTOList;
    }


    @Override
    public ProductDTO replaceProduct(Long id, Product product) throws ProductNotFoundException {
        Product productFound=productRepository.findById(id).orElseThrow(()->new ProductNotFoundException(id,"Product is not found with given id"));
        productFound.setTitle(product.getTitle());
        productFound.setPrice(product.getPrice());
        productFound.setImage(product.getImage());
        productFound.setDescription(product.getDescription());
        productFound.setCategory(product.getCategory());
        Product updatedProduct= productRepository.save(productFound);
        return convertProductToDto(updatedProduct);

    }

    @Override
    public ProductDTO updateProduct(Long id, Map<String,Object> values) throws ProductNotFoundException {

        Product product=productRepository.findById(id).orElseThrow(()->new ProductNotFoundException(id,"Product Not found with given id"));

        values.forEach((key,value)->{
            switch (key)
            {
                case "title":
                    product.setTitle((String) value);
                break;
                case "description":product.setDescription((String) value);
                break;
                case "price":product.setPrice((Double) value);
                break;
                case "image":product.setImage((String) value);
                break;
                case "category":product.getCategory().setDescription((String) value);
                break;
            }
        });
        Product updatedProduct=productRepository.save(product);
        return convertProductToDto(updatedProduct);

    }

    @Override
    public Product createProduct(Product product) {

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) throws ProductNotFoundException {
        if(productRepository.existsById(id))
            productRepository.deleteById(id);
        else
            throw new ProductNotFoundException(id,"Product Not found with given id");
    }
}
