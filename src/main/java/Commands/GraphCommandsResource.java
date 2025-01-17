package Commands;

import Graphs.Database.DAO.GraphDAO;
import Graphs.Graph;
import Commands.GraphCommands.GraphDisplay;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

@Path("/commands")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GraphCommandsResource {

    @POST
    @Path("/addVertex")
    public Response addVertex(@QueryParam("graphName") String graphName, @QueryParam("label") String label) {
        List<Graph> graphs = GraphDAO.getAllDBGraphs();
        Graph graph = graphs.stream()
                .filter(g -> g.getName().equals(graphName))
                .findFirst()
                .orElse(null);

        if (graph == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        graph.addVertex(label);
        GraphDAO.AddGraph(graph);
        return Response.ok(graph).build();
    }

    @POST
    @Path("/addEdge")
    public Response addEdge(@QueryParam("graphName") String graphName, @QueryParam("from") String from, @QueryParam("to") String to) {
        List<Graph> graphs = GraphDAO.getAllDBGraphs();
        Graph graph = graphs.stream()
                .filter(g -> g.getName().equals(graphName))
                .findFirst()
                .orElse(null);

        if (graph == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        graph.addEdge(from, to);
        GraphDAO.AddGraph(graph);
        return Response.ok(graph).build();
    }

    @DELETE
    @Path("/removeVertex")
    public Response removeVertex(@QueryParam("graphName") String graphName, @QueryParam("label") String label) {
        List<Graph> graphs = GraphDAO.getAllDBGraphs();
        Graph graph = graphs.stream()
                .filter(g -> g.getName().equals(graphName))
                .findFirst()
                .orElse(null);

        if (graph == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        graph.removeVertex(label);
        GraphDAO.AddGraph(graph);
        return Response.ok(graph).build();
    }

    @DELETE
    @Path("/removeEdge")
    public Response removeEdge(@QueryParam("graphName") String graphName, @QueryParam("from") String from, @QueryParam("to") String to) {
        List<Graph> graphs = GraphDAO.getAllDBGraphs();
        Graph graph = graphs.stream()
                .filter(g -> g.getName().equals(graphName))
                .findFirst()
                .orElse(null);

        if (graph == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        graph.removeEdge(from, to);
        GraphDAO.AddGraph(graph);
        return Response.ok(graph).build();
    }

    @GET
    @Path("/getGraph")
    @Produces({"application/json", "image/png"})
    public Response getGraph(@QueryParam("graphName") String graphName) {
        List<Graph> graphs = GraphDAO.getAllDBGraphs();
        Graph graph = graphs.stream()
                .filter(g -> g.getName().equals(graphName))
                .findFirst()
                .orElse(null);

        if (graph == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            GraphDisplay.RenderGraph(graph, baos);
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error generating graph image").build();
        }

        byte[] imageData = baos.toByteArray();

        return Response.ok()
                .entity(graph)
                .header("Content-Disposition", "attachment; filename=graph.png")
                .type("image/png")
                .entity(imageData)
                .build();
    }
}