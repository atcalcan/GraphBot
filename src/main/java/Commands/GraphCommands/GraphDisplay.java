package Commands.GraphCommands;

import Graphs.Graph;
import Graphs.Vertex;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.List;

import static Graphs.AllGraphs.checkGraph;
import static Graphs.AllGraphs.getGraph;
import static guru.nidi.graphviz.model.Factory.mutGraph;
import static guru.nidi.graphviz.model.Factory.mutNode;

public class GraphDisplay implements Runnable {
    private MessageReceivedEvent e;

    public GraphDisplay(MessageReceivedEvent e) {
        this.e = e;
    }

    public void run() {
        try {
            String param[] = e.getMessage().getContentRaw().split(" ");
            if (checkGraph(e.getAuthor().getName(), param[2])) {
                Graph g = getGraph(param[2]);
                RenderGraph(g, e);
            } else {
                e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", nu ai creat încă un graf numit *" + param[2] + "*.").queue();
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", trebuie să îmi spui şi ce graf să îţi trimit, aşa: `!graph display [g?]`.").queue();
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
            List<Vertex> adjVerteces = g.getVertexAdjVertices(i.getLabel());
            for (Vertex j : adjVerteces) {
                if (g.getDir().equalsIgnoreCase("directed") || !visited.contains(j)) {
                    myGraph.add(mutNode(i.getLabel()).addLink(mutNode(j.getLabel())));
                }
            }
            if (g.getDir().equalsIgnoreCase("undirected")) {
                visited.add(i);
            }
        }
//        File graphFile = new File("graph.png");
        PipedInputStream inputPipe;
        PipedOutputStream outputPipe;
        try {
//            Graphviz.fromGraph(myGraph).width(747).render(Format.PNG).toFile(graphFile);
            inputPipe = new PipedInputStream(10000 * 1024);
            outputPipe = new PipedOutputStream(inputPipe);
//            System.out.println("1");
            Graphviz.fromGraph(myGraph).width(500).render(Format.PNG).toOutputStream(outputPipe);
//            System.out.println("2");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        String fileName = e.getAuthor().getName() + "sGraphDisplay.png";
        e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", acesta este graful tău *" + g.getName() + "*.").addFile(inputPipe, fileName).queue();
    }
}
