package Commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GraphHelp extends ListenerAdapter {
    public void onMessageReceived(MessageReceivedEvent e) {
        if (e.getMessage().getContentRaw().equalsIgnoreCase("!graph help")) {

            e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", am auzit ca ai nevoie de ajutor la grafuri.\nPot face câteva lucruri cu grafuri. Dar prima dată trebuie să stabilim cum arată graful pe care vrei sa lucrăm.\nÎţi pot arăta ce grafuri ai deja, dacă foloseşti comanda *\"!graph show\"*, sau pot crea un graf nou, dar trebuie să îmi dai câteva detalii despre acest graf, în acest format:\n\n\"!graph new; directed/undirected; 0 1 2 3; 1 2, 0 2, 2 3;\"\n\nAici, new îmi arată că vrei un graf nou, directed sau undirected îmi spune caracterul grafului, 0, 1, 2, şi 3 sunt nodurile, iar restul sunt muchiile.").queue();
        }

    }
}
