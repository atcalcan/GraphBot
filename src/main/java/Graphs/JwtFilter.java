package Graphs;

import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Priority;
import javax.servlet.Filter;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Context;
import java.io.IOException;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class JwtFilter implements ContainerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Context
    private UriInfo uriInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String path = uriInfo.getPath();
        logger.info("Request path: {}", path);
        logger.debug("Request URI: {}", uriInfo.getRequestUri());

        if (path.equals("auth/login")) {
            logger.info("Bypassing authentication for login endpoint");
            return;
        }

        String authorizationHeader = requestContext.getHeaderString("Authorization");
        logger.debug("Authorization header: {}", authorizationHeader);

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            logger.warn("Missing or invalid Authorization header");
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            return;
        }

        String token = authorizationHeader.substring("Bearer".length()).trim();
        logger.debug("Extracted token: {}", token);

        try {
            Claims claims = JwtUtil.validateToken(token);
            logger.info("Token validated successfully");
            requestContext.setProperty("claims", claims);
        } catch (Exception e) {
            logger.error("Token validation failed", e);
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}