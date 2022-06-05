package Commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import static Graphs.AllGraphs.*;

public class GraphRun extends ListenerAdapter {
    public void onMessageReceived(MessageReceivedEvent e) {
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

        }
    }
}
