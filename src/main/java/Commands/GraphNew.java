package Commands;

import Graphs.Graph;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import static Graphs.AllGraphs.addGraph;
import static Graphs.AllGraphs.getAllGraphs;

public class GraphNew extends ListenerAdapter {
    public void onMessageReceived(MessageReceivedEvent e) {
        if (e.getMessage().getContentRaw().indexOf("!graph new; ") == 0) {
            if (e.getMessage().getContentRaw().matches("(!graph new; )+(directed;|undirected;)+[(\\ )+(a-zA-Z0-9)+]+(;)[(\\ )+(a-zA-Z0-9)+(\\ )+(a-zA-Z0-9)+(,)]+(;)")) {
                String param[] = e.getMessage().getContentRaw().split("; ");
                boolean success = newGraph(e.getAuthor().getName(), param);
                if (success == true) {
                    e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", graful tau a fost creat cu succes. Poti vedea ce grafuri ai folosind \"!graph show\".").queue();
                } else {
                    e.getChannel().sendMessage("eroare :(").queue();
                }
            } else {
                e.getChannel().sendMessage("eroare").queue();
            }
        }
    }

    boolean newGraph(String user, String param[]) {
        try {
            var g = new Graph(user);
            g.setDir(param[1]);
            int NumberGraphs = getAllGraphs().size();
            NumberGraphs = NumberGraphs++;
            String name = "g" + NumberGraphs;
            g.setName(name);
            String vertexes[] = param[2].split(" ");
            for (int i = 0; i < vertexes.length; i++) {
                g.addVertex(vertexes[i]);
            }
            param[3] = param[3].substring(0, param[3].length() - 1);
            String edges[] = param[3].split(", ");
            for (int i = 0; i < edges.length; i++) {
                String labels[] = edges[i].split(" ");
                g.addEdge(labels[0], labels[1]);
            }
            addGraph(g);
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }

    }
}
