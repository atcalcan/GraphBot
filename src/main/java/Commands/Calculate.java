package Commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Calculate extends ListenerAdapter {
    public void onMessageReceived(MessageReceivedEvent e) {
        String[] param = e.getMessage().getContentRaw().split(" ");
        if (param[0].equalsIgnoreCase("!calculate")) {
            if (param[0].equalsIgnoreCase(e.getMessage().getContentRaw()) || param.length != 4) {
                e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", trebuie să îmi specifici parametrii după această comandă. Mesajul ar trebui să fie sub forma următoare: \"!calculate suma/diferenta/inmultire/impartire x y\", iar eu îţi voi da rezultatul.").queue();
            } else if (param[1].equalsIgnoreCase("suma")) {
                double rezultat = Double.parseDouble(param[2]) + Double.parseDouble(param[3]);
                e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", rezultatul este " + rezultat + ".").queue();
            } else if (param[1].equalsIgnoreCase("diferenta")) {
                double rezultat = Double.parseDouble(param[2]) - Double.parseDouble(param[3]);
                e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", rezultatul este " + rezultat + ".").queue();
            } else if (param[1].equalsIgnoreCase("inmultire")) {
                double rezultat = Double.parseDouble(param[2]) * Double.parseDouble(param[3]);
                e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", rezultatul este " + rezultat + ".").queue();
            } else if (param[1].equalsIgnoreCase("impartire")) {
                double rezultat = Double.parseDouble(param[2]) / Double.parseDouble(param[3]);
                e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", rezultatul este " + rezultat + ".").queue();
            }
        }

    }
}
