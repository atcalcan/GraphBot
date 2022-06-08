package Commands.GraphCommands;

import Graphs.AllGraphs;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class GraphShow implements Runnable {
    private MessageReceivedEvent e;

    public GraphShow(MessageReceivedEvent e) {
        this.e = e;
    }

    public void run() {
        if (e.getMessage().getContentRaw().indexOf("!graph show") == 0) {
            String userGraphs = AllGraphs.whatGraphs(e.getAuthor().getName());
            if (userGraphs != "") {
                e.getChannel().sendMessage("Grafurile lui " + e.getAuthor().getAsMention() + " sunt: " + AllGraphs.whatGraphs(e.getAuthor().getName())).queue();
            } else {
                e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", nu ai creat grafuri încă. Poţi face asta folosind *\"!graph new\"*. Pentru mai multe detalii, foloseşte *\"!graph help\"*.").queue();
            }
        }
    }
}
