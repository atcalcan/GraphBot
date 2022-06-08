package Commands.GraphCommands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class GraphHelp implements Runnable {
    private MessageReceivedEvent e;

    public GraphHelp(MessageReceivedEvent e) {
        this.e = e;
    }

    public void run() {
        if (e.getMessage().getContentRaw().equalsIgnoreCase("!graph help") || e.getMessage().getContentRaw().equalsIgnoreCase("!graph")) {
            e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", bună! Sunt un bot menit să te ajute să lucrezi cu grafuri. Acestea sunt comenzile mele:\n```!graph new; (directed /undirected ); node1 node2 ... ; edge1, edge2, ...;\n- creează un graf nou\n" +
                    "node1, node2 - numele nodurilor\n" +
                    "edge1, edge2 - muchii de forma: `nume1 nume2` ```\n```!graph show\n- arată toate grafurile utilizatorului```\n```!graph display graphName\n- generează şi trimite o reprezentare grafică a grafului selectat\ngraphName - numele grafului```\n" +
                    "```!graph run command...\ncommand - programul care va rula pe graf\n- încearcă \"!graph run\" pentru mai multe detalii```\n" +
                    "").queue();
        }
    }
}
