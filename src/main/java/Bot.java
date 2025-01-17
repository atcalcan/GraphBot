import Commands.GraphCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Bot {
    public static void main(String args[]) throws Exception {
        new Thread(() -> {
            try {
                ServerConfig.main(args);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        JDA jda = JDABuilder.createDefault(System.getenv("DISC_TK")).build();
        jda.addEventListener(new GraphCommand());
    }
}
