package Commands;

import Commands.GraphCommands.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GraphCommand extends ListenerAdapter {
    public void onMessageReceived(MessageReceivedEvent e) {
        if (e.getMessage().getContentRaw().indexOf("!graph display") == 0) {
            Thread command = new Thread(new GraphDisplay(e));
            command.start();
        } else if (e.getMessage().getContentRaw().indexOf("!graph run") == 0) {
            Thread command = new Thread(new GraphRun(e));
            command.start();
        } else if (e.getMessage().getContentRaw().indexOf("!graph new") == 0) {
            Thread command = new Thread(new GraphNew(e));
            command.start();
        } else if (e.getMessage().getContentRaw().indexOf("!graph help") == 0) {
            Thread command = new Thread(new GraphHelp(e));
            command.start();
        } else if (e.getMessage().getContentRaw().indexOf("!graph show") == 0) {
            Thread command = new Thread(new GraphShow(e));
            command.start();
        } else if (e.getMessage().getContentRaw().equalsIgnoreCase("!graph")) {
            Thread command = new Thread(new GraphHelp(e));
            command.start();
        }
    }
}
