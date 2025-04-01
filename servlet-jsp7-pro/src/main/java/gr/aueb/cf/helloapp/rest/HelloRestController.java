package gr.aueb.cf.helloapp.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.awt.*;

@Path("hello")
public class HelloRestController {
    @GET
    @Path("cf")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from CF!";
    }
}
