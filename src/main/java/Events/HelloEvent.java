package Events;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class HelloEvent extends ListenerAdapter {
    public void onMessageReceived(MessageReceivedEvent event) {
        String[] messageSent = event.getMessage().getContentRaw().split("[.;:, ]+");
        if (messageSent[0].equalsIgnoreCase("hello")) {
            if (!event.getAuthor().isBot())
                event.getChannel().sendMessage("Hello there, " + event.getAuthor().getAsMention() + "!").queue();
        }
    }
}
