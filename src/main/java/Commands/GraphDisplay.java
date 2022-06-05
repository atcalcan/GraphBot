package Commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.File;
import java.util.Objects;

public class GraphDisplay extends ListenerAdapter {
    public void onMessageReceived(MessageReceivedEvent e) {
        if (e.getMessage().getContentRaw().indexOf("!graph display") == 0) {
            File graph = new File("graph.png");


            if (Objects.nonNull(graph)) {
                e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", acesta este graful tău.").addFile(graph).queue();
            } else {
                e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", acesta este graful tău.").queue();
            }
        }

    }
}
