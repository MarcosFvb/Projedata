package service;

import java.math.BigDecimal;
import java.util.List;

import entity.ProductEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import repository.ProductRepository;

@ApplicationScoped
public class ProductService {

    @Inject
    private ProductRepository productRepository;

    // Create
    public ProductEntity createProduct(String name, BigDecimal price) {
        ProductEntity product = new ProductEntity(name, price);
        productRepository.persist(product);
        return product;
    }

    // Read
    public List<ProductEntity> getAllProducts() {
        return productRepository.listAll();
    }

    // Update
    public ProductEntity updateProduct(Long code, ProductEntity updatedProduct) {
        ProductEntity product = productRepository.findById(code);

        if (product == null) {
            return null; // or throw an exception, or return a Response with an error status
        }

        // Just Change what's being changed
        if (updatedProduct.getName() != null) {
            product.setName(updatedProduct.getName());
        }

        if (updatedProduct.getPrice() != null) {
            product.setPrice(updatedProduct.getPrice());
        }

        productRepository.persist(product);

        return product;
    }

    // Delete
    public boolean deleteProduct(Long code) {
        ProductEntity product = productRepository.findById(code);

        if (product == null) {
            return false;
        }

        productRepository.deleteById(code);
        return true;
    }
}
