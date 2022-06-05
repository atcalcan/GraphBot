package Commands;

import Graphs.AllGraphs;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GraphShow extends ListenerAdapter {
    public void onMessageReceived(MessageReceivedEvent e) {
        if (e.getMessage().getContentRaw().indexOf("!graph show") == 0) {
            String userGraphs = AllGraphs.whatGraphs(e.getAuthor().getName());
            if (userGraphs != "") {
                e.getChannel().sendMessage(e.getAuthor().getAsMention() + "'s graphs are: " + AllGraphs.whatGraphs(e.getAuthor().getName())).queue();
            } else {
                e.getChannel().sendMessage(e.getAuthor().getAsMention() + " has no graphs yet.").queue();
            }

        }
    }
}
