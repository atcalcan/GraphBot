package Commands.GraphCommands;

import Graphs.GraphMatrix;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import static Graphs.AllGraphs.*;

public class GraphRun implements Runnable {
    private MessageReceivedEvent e;

    public GraphRun(MessageReceivedEvent e) {
        this.e = e;
    }

    public void run() {
        if (e.getMessage().getContentRaw().indexOf("!graph run dfs") == 0) {
            String param[] = e.getMessage().getContentRaw().split(" ");
            if (checkGraph(e.getAuthor().getName(), param[3])) {
                e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", rezultatul pentru *DFS* pe graful tău, pornind din " + param[4] + ", este " + depthFirstTraversal(getGraph(param[3]), param[4]) + ".").queue();
            } else {
                e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", ai structurat greşit comanda. Încearcă din nou.").queue();
            }

        } else if (e.getMessage().getContentRaw().indexOf("!graph run bfs") == 0) {
            String param[] = e.getMessage().getContentRaw().split(" ");
            if (checkGraph(e.getAuthor().getName(), param[3])) {
                e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", rezultatul pentru *BFS* pe graful tău, pornind din " + param[4] + ", este " + breadthFirstTraversal(getGraph(param[3]), param[4]) + ".").queue();
            } else {
                e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", ai structurat greşit comanda. Încearcă din nou.").queue();
            }
        } else if (e.getMessage().getContentRaw().indexOf("!graph run hamCycle") == 0) {
            String param[] = e.getMessage().getContentRaw().split(" ");
            if (checkGraph(e.getAuthor().getName(), param[3])) {
                String hamCycle = new GraphMatrix(getGraph(param[3])).hamCycle();
                if (!hamCycle.equals("NO")) {
                    e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", în graful tău *" + param[3] + "* există un ciclu hamiltonian: " + hamCycle + ".").queue();
                } else {
                    e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", în graful tău *" + param[3] + "* NU există niciun ciclu hamiltonian.").queue();
                }
            } else {
                e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", ai structurat greşit comanda. Încearcă din nou.").queue();
            }
        } else if (e.getMessage().getContentRaw().indexOf("!graph run colorsMax") == 0) {
            String param[] = e.getMessage().getContentRaw().split(" ");
            if (checkGraph(e.getAuthor().getName(), param[3])) {
                String maxColoring = new GraphMatrix(getGraph(param[3])).getMaxColoring();
                String results[] = maxColoring.split(";");
                e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", graful tău *" + param[3] + "* poate fi colorat cu cel puţin " + results[0] + " culori, astfel:\n" + results[1] + ".").queue();
            } else {
                e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", ai structurat greşit comanda. Încearcă din nou.").queue();
            }
        }
    }
}
