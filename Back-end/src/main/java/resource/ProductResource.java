package resource;

import entity.ProductEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import service.ProductService;

@Path("/products")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class ProductResource {

    @Inject
    ProductService productService;

    // Create
    @POST
    @Transactional
    public Response createProduct(ProductEntity product) {
        ;
        return Response.ok().entity(productService.createProduct(product.getName(), product.getPrice())).build();
    }

    // Read
    @GET
    public Response getAllProducts() {
        return Response.ok().entity(productService.getAllProducts()).build();
    }

    // Update
    @PUT
    @Path("/{code}")
    @Transactional
    public Response updateProduct(@PathParam("code") Long code, ProductEntity product) {
        return Response.ok().entity(productService.updateProduct(code, product)).build();
    }

    // Delete
    @DELETE
    @Path("/{code}")
    @Transactional
    public Response deleteProduct(@PathParam("code") Long code) {

        // Could have a better exception handling
        boolean deleted = productService.deleteProduct(code);
        if (deleted) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
