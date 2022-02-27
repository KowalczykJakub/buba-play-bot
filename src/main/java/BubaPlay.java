import commands.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class BubaPlay {

    public static void main(String[] args) throws LoginException {

        JDA jda = JDABuilder.createDefault("TOKEN").setActivity(Activity.listening("Hmmmmm")).build();
        jda.addEventListener(new PingCommand());
        jda.addEventListener(new LeaveCommand());
        jda.addEventListener(new PlayCommand());
        jda.addEventListener(new SkipCommand());
        jda.addEventListener(new HelpCommand());
        jda.addEventListener(new TimeoutCommand());
    }
}
