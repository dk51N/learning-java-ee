package com.learning_java_ee.boundary;

import com.learning_java_ee.control.CarStorageException;
import com.learning_java_ee.entity.Car;
import com.learning_java_ee.entity.EngineType;
import com.learning_java_ee.entity.Specification;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.stream.JsonCollectors;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("cars")
public class CarsResource {

    @Inject
    CarManufacturer carManufacturer;

    @Context
    UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonArray retrieveCars(@NotNull @QueryParam("filter") EngineType engineType) {
        return carManufacturer.retrieveCars()
                .stream()
                .map(car -> Json.createObjectBuilder()
                        .add("color", car.getColor().name())
                        .add("engine", car.getEngineType().name())
                        .add("id", car.getIdentifier())
                        .add("hello", "value")
                        .build())
                .collect(JsonCollectors.toJsonArray());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCar(@Valid @NotNull Specification specification) throws CarStorageException {
        Car car = carManufacturer.manufactureCar(specification);

        URI uri = uriInfo.getBaseUriBuilder()
                .path(CarsResource.class)
                .path(CarsResource.class, "retrieveCar")
                .build(car.getIdentifier());

        return Response.created(uri).build();
    }

//    @GET
//    @Path("{id}")
//    public Car retrieveCar(@PathParam("id") String identifier) {
//        return carManufacturer.retrieveCar(identifier);
//    }
}
