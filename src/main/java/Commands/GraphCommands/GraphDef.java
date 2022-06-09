package Commands.GraphCommands;

import Graphs.Database.DAO.DefinitionDAO;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class GraphDef implements Runnable {
    private MessageReceivedEvent e;

    public GraphDef(MessageReceivedEvent e) {
        this.e = e;
    }

    public void run() {
        if (e.getMessage().getContentRaw().indexOf("!graph def") == 0) {
            String[] param = e.getMessage().getContentRaw().split(" ");

            if (!param[2].equals("")) {
                String query = "";
                for (int i = 2; i< param.length; i++){
                    query = query + param[i] + " ";
                }
                query = query.substring(0, query.length() - 1);
                Map<String, List<String>> definitions = DefinitionDAO.getDefinitions(query);
                String result = getText(definitions);
                if (!result.equals("")) {
                    e.getChannel().sendMessage(e.getAuthor().getAsMention() + "\n" + result).queue();
                } else {
                    e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", nu am găsit nimic care să semene cu query-ul tău. Încearcă altceva.").queue();
                }
            } else {
                e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", trebuie să îmi dai şi o parte din numele conceptului pe care îl cauţi: `!graph def *query*`. Pentru mai multe detalii, foloseşte `!graph`.").queue();
            }
        }
    }

    private String getText(Map<String, List<String>> definitions) {
        Set<String> finds = definitions.keySet();
        String result = "";
        for (String ind : finds) {
            result = result + ind + ": ";
            List<String> defs = definitions.get(ind);
            for (String one : defs) {
                result = result + one + "; ";
            }
            result = result + "\n";
        }
        return result;
    }

}
