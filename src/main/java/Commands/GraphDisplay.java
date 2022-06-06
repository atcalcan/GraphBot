package Commands;

import Graphs.Graph;
import Graphs.Vertex;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static Graphs.AllGraphs.getGraph;
import static guru.nidi.graphviz.model.Factory.mutGraph;
import static guru.nidi.graphviz.model.Factory.mutNode;

public class GraphDisplay extends ListenerAdapter {
    public void onMessageReceived(MessageReceivedEvent e) {
        if (e.getMessage().getContentRaw().indexOf("!graph display") == 0) {
            String param[] = e.getMessage().getContentRaw().split(" ");
            Graph g = getGraph(param[2]);
            RenderGraph(g, e);
//            File graphFile = new File("graph.png");


        }

    }

    private void RenderGraph(Graph g, MessageReceivedEvent e) {
        MutableGraph myGraph = mutGraph(g.getName());
        if (g.getDir().equalsIgnoreCase("directed")) {
            myGraph.setDirected(true);
        } else if (g.getDir().equalsIgnoreCase("undirected")) {
            myGraph.setDirected(false);
        }
        List<Vertex> allVertices = g.getAllVertices();
        List<Vertex> visited = new ArrayList();
        for (Vertex i : allVertices) {
            myGraph.add(mutNode(i.getLabel()));
            List<Vertex> adjVerteces = g.getAdjVertices(i.getLabel());
            for (Vertex j : adjVerteces) {
                if (g.getDir().equalsIgnoreCase("directed") || !visited.contains(j)) {
                    myGraph.add(mutNode(i.getLabel()).addLink(mutNode(j.getLabel())));
                }
            }
            if (g.getDir().equalsIgnoreCase("undirected")) {
                visited.add(i);
            }
        }
        File graphFile = new File("graph.png");
        try {
            Graphviz.fromGraph(myGraph).width(747).render(Format.PNG).toFile(graphFile);
        } catch (IOException err) {
            throw new RuntimeException(err);
        }
        if (Objects.nonNull(graphFile)) {
            e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", acesta este graful tău *" + g.getName() + "*.").addFile(graphFile).queue();
        } else {
            e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", nu am reuşit să generez o imagine pentru graful tău.").queue();
        }
    }
}
