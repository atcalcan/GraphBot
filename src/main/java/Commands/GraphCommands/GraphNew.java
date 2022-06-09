package Commands.GraphCommands;

import Graphs.AllGraphs;
import Graphs.Database.DAO.GraphDAO;
import Graphs.Graph;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import static Graphs.AllGraphs.getAllGraphs;

public class GraphNew implements Runnable {
    private MessageReceivedEvent e;

    public GraphNew(MessageReceivedEvent e) {
        this.e = e;
    }

    public void run() {
        if (e.getMessage().getContentRaw().indexOf("!graph new; ") == 0) {
            if (e.getMessage().getContentRaw().matches("(!graph new; )+(directed;|undirected;)+[(\\ )+(a-zA-Z0-9)+]+(;)[(\\ )+(a-zA-Z0-9)+(\\ )+(a-zA-Z0-9)+(,)]+(;)")) {
                String param[] = e.getMessage().getContentRaw().split("; ");
                String name = newGraph(e.getAuthor().getName(), param);
                if (!name.equals("ERROR")) {
                    e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", graful tau *" + name + "* a fost creat cu succes. Poti vedea ce grafuri ai folosind `!graph show`, sau poţi să îţi vezi orice graf folosind `!graph display g?`.").queue();
                } else {

                    e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", a apărut o eroare la crearea grafului tău. Verifică parametrii oferiţi. *Pentru a putea să creez o muchie, trebuie ca ambele noduri să fi fost declarate*.").queue();

                }
            }
        } else {
            e.getChannel().sendMessage("A apărut o eroare de formatare. Comanda de creare se scrie astfel:\n```!graph new; (directed /undirected ); node1 node2 ... ; edge1, edge2, ...;\n- creează un graf nou\n" +
                    "node1, node2 - numele nodurilor\n" +
                    "edge1, edge2 - muchii de forma: \"nume1 nume2\"```").queue();

        }
    }

    String newGraph(String user, String param[]) {
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
            GraphDAO.AddGraph(g);
            AllGraphs.allGraphs = GraphDAO.getAllDBGraphs();
            System.out.println(g);
            return name;
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            return "ERROR";
        }

    }
}
