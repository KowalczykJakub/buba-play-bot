package commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class PingCommand extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        String message = event.getMessage().getContentRaw();
        String authorName = event.getAuthor().getName();

        if (message.equalsIgnoreCase("*ping")) {
            event.getTextChannel().sendMessage("pong").queue();
        }
    }
}
