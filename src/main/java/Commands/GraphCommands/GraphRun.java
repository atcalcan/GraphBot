package Commands.GraphCommands;

import Graphs.GraphMatrix;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import static Graphs.AllGraphs.*;
import static java.lang.Integer.parseInt;

public class GraphRun implements Runnable {
    private final MessageReceivedEvent e;
    private static String commands =
            "```!graph run dfs graphName startNode\n- rulează algoritmul Depth First Search/Transversal pe graful graphName, pornind din nodul startNode```" +
                    "```!graph run bfs graphName startNode\n- rulează algoritmul Breadth First Search/Transversal pe graful graphName, pornind din nodul startNode```" +
                    "```!graph run hamCycle graphName\n- caută un ciclu hamiltonian în graful graphName şi îl trimite dacă există```" +
                    "```!graph run hamPath graphName\n- caută un drum hamiltonian în graful graphName şi îl trimite dacă există```" +
                    "```!graph run minColoring graphName\n- află numărul minim de culori în care poate fi colorat graful graphName şi trimite distribuţia culorilor```" +
                    "```!graph run mColoring graphName m\n- află dacă graful graphName este *m*-colorabil şi trimite distribuţia cea mai eficientă a culorilor```" +
                    "";

    public GraphRun(MessageReceivedEvent e) {
        this.e = e;
    }

    public void run() {
        String param[] = e.getMessage().getContentRaw().split(" ");
        try {
            if (e.getMessage().getContentRaw().indexOf("!graph run dfs") == 0) {
                if (checkGraph(e.getAuthor().getName(), param[3])) {
                    e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", rezultatul pentru *DFS* pe graful tău, pornind din " + param[4] + ", este " + DFS(getGraph(param[3]), param[4]) + ".").queue();
                } else {
                    e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", nu ai creat încă un graf numit *" + param[3] + "*.").queue();
                }

            } else if (e.getMessage().getContentRaw().indexOf("!graph run bfs") == 0) {
                if (checkGraph(e.getAuthor().getName(), param[3])) {
                    e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", rezultatul pentru *BFS* pe graful tău, pornind din " + param[4] + ", este " + BFS(getGraph(param[3]), param[4]) + ".").queue();
                } else {
                    e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", nu ai creat încă un graf numit *" + param[3] + "*.").queue();

                }
            } else if (e.getMessage().getContentRaw().indexOf("!graph run hamCycle") == 0) {
                if (checkGraph(e.getAuthor().getName(), param[3])) {
                    String hamCycle = new GraphMatrix(getGraph(param[3])).hamCycle();
                    if (!hamCycle.equals("NO")) {
                        e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", în graful tău *" + param[3] + "* există un ciclu hamiltonian: " + hamCycle + ".").queue();
                    } else {
                        e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", în graful tău *" + param[3] + "* NU există niciun ciclu hamiltonian.").queue();
                    }
                } else {
                    e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", nu ai creat încă un graf numit *" + param[3] + "*.").queue();
                }
            } else if (e.getMessage().getContentRaw().indexOf("!graph run hamPath") == 0) {
                if (checkGraph(e.getAuthor().getName(), param[3])) {
                    String hamPath = new GraphMatrix(getGraph(param[3])).hamPath();
                    if (!hamPath.equals("NO")) {
                        e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", în graful tău *" + param[3] + "* există un drum hamiltonian: " + hamPath + ".").queue();
                    } else {
                        e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", în graful tău *" + param[3] + "* NU există niciun drum hamiltonian.").queue();
                    }
                } else {
                    e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", nu ai creat încă un graf numit *" + param[3] + "*.").queue();
                }
            } else if (e.getMessage().getContentRaw().indexOf("!graph run minColoring") == 0) {
                if (checkGraph(e.getAuthor().getName(), param[3])) {
                    String maxColoring = new GraphMatrix(getGraph(param[3])).getMaxColoring();
                    String results[] = maxColoring.split(";");
                    e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", graful tău *" + param[3] + "* poate fi colorat cu cel puţin " + results[0] + " culori, astfel:\n" + results[1] + ".").queue();
                } else {
                    e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", nu ai creat încă un graf numit *" + param[3] + "*.").queue();

                }
            } else if (e.getMessage().getContentRaw().indexOf("!graph run mColoring") == 0) {
                if (checkGraph(e.getAuthor().getName(), param[3])) {
                    GraphMatrix graphMatrix = new GraphMatrix(getGraph(param[3]));
                    String results = graphMatrix.graphColoringTest(graphMatrix.getGraphMatrixInt(), parseInt(param[4]));
                    if (!results.equals("NO")) {
                        e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", graful tău *" + param[3] + "* POATE fi colorat cu " + param[4] + " culori, astfel:\n" + results + ".").queue();
                    } else {
                        if (!param[4].equals("1")) {
                            e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", graful tău *" + param[3] + "* NU POATE fi colorat cu " + param[4] + " culori.").queue();
                        } else {
                            e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", graful tău *" + param[3] + "* NU POATE fi colorat o singură culoare.").queue();
                        }
                    }
                } else {
                    e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", nu ai creat încă un graf numit *" + param[3] + "*.").queue();

                }
            } else if (e.getMessage().getContentRaw().equalsIgnoreCase("!graph run")) {
                e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", uite ce programe pot rula pe grafuri:\n" + commands).queue();
            }
        } catch (ArrayIndexOutOfBoundsException | NullPointerException exception) {
            e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", ai structurat greşit comanda. Încearcă din nou, sau foloseşte `!graph run` pentru mai multe detalii despre comenzi.").queue();
        }
    }
}
