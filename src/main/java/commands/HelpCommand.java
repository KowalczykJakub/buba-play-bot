package commands;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class HelpCommand extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        final TextChannel channel = event.getTextChannel();
        String message = event.getMessage().getContentRaw();

        if (message.equalsIgnoreCase("*help")) {

            channel.sendMessage("*skip - skip current track \n" +
                    "*play <youtube video title or link> - play a song \n" +
                    "*leave - make the bot leave leave \n" +
                    "*ping - check if the bot works \n" +
                    "*play aram - great playlist especially for playing aram").queue();
        }
    }
}
