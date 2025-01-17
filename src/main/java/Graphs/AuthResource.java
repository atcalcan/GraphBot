package Graphs;

import Graphs.Database.DAO.GraphDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {

    private static final Logger logger = LoggerFactory.getLogger(AuthResource.class);

    @Context
    private UriInfo uriInfo;

    @POST
    @Path("/login")
    public Response login(@HeaderParam("Content-Type") String contentType, UserCredentials credentials) {
        logger.info("Received login request for user: {}", credentials.getUsername());
        logger.debug("Request URI: {}", uriInfo.getRequestUri());

        if (!MediaType.APPLICATION_JSON.equals(contentType)) {
            logger.warn("Unsupported Media Type: {}", contentType);
            return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE).entity("Content-Type must be application/json").build();
        }

        if (credentials.getUsername() == null || credentials.getUsername().isEmpty()) {
            logger.warn("Username is null or empty");
            return Response.status(Response.Status.BAD_REQUEST).entity("Username is required").build();
        }

        List<Graph> graphs = GraphDAO.getAllDBGraphs();
        logger.debug("Fetched graphs from database: {}", graphs);

        boolean userExists = graphs.stream()
                .anyMatch(graph -> graph.getUser().equals(credentials.getUsername()));
        logger.debug("User exists: {}", userExists);

        if (userExists) {
            String token = JwtUtil.generateToken(credentials.getUsername());
            logger.info("User authenticated successfully, generated token: {}", token);
            return Response.ok(new AuthResponse(token)).build();
        }
        logger.warn("User authentication failed for user: {}", credentials.getUsername());
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    public static class UserCredentials {
        @NotNull
        @Size(min = 1)
        private String username;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }

    public static class AuthResponse {
        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public AuthResponse(String token) {
            this.token = token;
        }
    }
}