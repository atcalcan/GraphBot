import Commands.GraphCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Bot {
    public static void main(String args[]) throws Exception {

        JDA jda = JDABuilder.createDefault("OTgyMzM0NzA2Mzc0ODExNjcw.GZH_oH.oe-6fxOYuGZEG0_EBT1DwCwq6ln5xdnJlK-egg").build();
//        var allGraphs = new AllGraphs();
        jda.addEventListener(new GraphCommand());
    }
}
