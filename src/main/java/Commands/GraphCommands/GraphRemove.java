package Commands.GraphCommands;

import Graphs.Database.DAO.GraphDAO;
import Graphs.Graph;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import static Graphs.AllGraphs.checkGraph;
import static Graphs.AllGraphs.getGraph;

public class GraphRemove implements Runnable {
    private MessageReceivedEvent e;

    public GraphRemove(MessageReceivedEvent e) {
        this.e = e;
    }

    public void run() {
        try {
            String param[] = e.getMessage().getContentRaw().split(" ");
            if (checkGraph(e.getAuthor().getName(), param[2])) {
                Graph g = getGraph(param[2]);
                if (param[3].indexOf("(") != 0) {
                    g.removeVertex(param[3]);
                    GraphDAO.AddGraph(g);
                    e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", am eliminat nodul " + param[3] + " la graful tău *" + param[2] + "*.").queue();
                } else {
                    param[3] = param[3].substring(1, param[3].length() - 1);
                    String[] edge = param[3].split("-");
                    g.removeEdge(edge[0], edge[1]);
                    GraphDAO.AddGraph(g);
                    e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", am eliminat muchia " + param[3] + " la graful tău *" + param[2] + "*.").queue();
                }
            } else {
                e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", nu ai creat încă un graf numit *" + param[2] + "*.").queue();
            }
        } catch (ArrayIndexOutOfBoundsException | NullPointerException exception) {
            e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", ai structurat greşit comanda. Încearcă din nou, sau foloseşte `!graph` pentru mai multe detalii despre comenzi.").queue();
        }
    }
}