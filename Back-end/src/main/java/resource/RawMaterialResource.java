package resource;

import entity.RawMaterialEntity;
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
import service.RawMaterialService;

@Path("/raw-material")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class RawMaterialResource {

    @Inject
    RawMaterialService rawMaterialService;

    @POST
    @Transactional
    public Response createRawMaterial(RawMaterialEntity rawMaterial) {
        rawMaterialService.createRawMaterial(rawMaterial.getName(), rawMaterial.getQuantity());
        return Response.ok().entity(rawMaterial).build();
    }

    @GET
    public Response getAllRawMaterials() {
        return Response.ok().entity(rawMaterialService.getRawMaterialByCode()).build();
    }

    //Update
    @PUT
    @Path("/{code}")
    @Transactional
    public Response updateRawMaterial(@PathParam("code") Long code, RawMaterialEntity rawMaterial) {
        return Response.ok().entity(rawMaterialService.updateRawMaterial(code, rawMaterial)).build();
    }
    
    @DELETE
    @Path("/{code}")
    @Transactional
    public Response deleteRawMaterial(@PathParam("code") Long code) {

        //Could have a better exception handling
        boolean deleted = rawMaterialService.deleteRawMaterial(code);
        if (deleted) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}