package resource;

import java.util.List;

import entity.ProductEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import service.ProductService;

@Path("/products")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class ProductResource {

    @Inject
    ProductService productService;

    @GET
    public List<ProductEntity> getAllProducts() {
        return productService.getAllProducts();
    }

    @POST
    @Transactional
    public void createProduct(ProductEntity product) {
        productService.createProduct(product.getCode(), product.getName(), product.getPrice());
    }
}
