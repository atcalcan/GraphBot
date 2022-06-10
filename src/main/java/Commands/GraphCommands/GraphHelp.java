package Commands.GraphCommands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class GraphHelp implements Runnable {
    private MessageReceivedEvent e;
    private static String commands = "```!graph new; (directed /undirected ); node1 node2 ... ; edge1, edge2, ...;\n- creează un graf nou\nnode1, node2 - numele nodurilor\nedge1, edge2 - muchii de forma: \"nume1 nume2\"```" +
            "```!graph add graphName nodeName\n- adaugă nodul cu numele nodeName în graful cu numele graphName\n!graph add graphName (nodeName1-nodeName2)\n- adaugă o muchie între nodurile nodeName1 şi nodeName2 din graful graphName```" +
            "```!graph rmv/remove graphName\n- ŞTERGE DIN MEMORIE GRAFUL CU NUMELE graphName\n!graph rmv/remove graphName nodeName\n- elimină nodul cu numele nodeName din graful cu numele graphName\n!graph rmv/remove graphName (nodeName1-nodeName2)\n- elimină muchia dintre nodurile nodeName1 şi nodeName2 din graful graphName```" +
            "```!graph show\n- arată toate grafurile utilizatorului```" +
            "```!graph display graphName\n- generează şi trimite o reprezentare grafică a grafului selectat\ngraphName - numele grafului```" +
            "```!graph run command...\ncommand - programul care va rula pe graf\n- încearcă \"!graph run\" pentru mai multe detalii```" +
            "```!graph def query...\n- trimite definiţiile din baza de date care corespund query-ului oferit```";

    public GraphHelp(MessageReceivedEvent e) {
        this.e = e;
    }

    public void run() {
        if (e.getMessage().getContentRaw().equalsIgnoreCase("!graph help") || e.getMessage().getContentRaw().equalsIgnoreCase("!graph")) {
            e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", bună! Sunt un bot menit să te ajute să lucrezi cu grafuri. Acestea sunt comenzile mele:\n" + commands).queue();
        } else {
            e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", am observat că ai încercat să îmi trimiţi o comandă, dar a existat o greşeală de formatare. Acestea sunt comenzile mele:\n" + commands).queue();
        }
    }
}
