package commands;

import audio.PlayerManager;
import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;
import org.jetbrains.annotations.NotNull;

import java.net.URI;
import java.net.URISyntaxException;

public class PlayCommand extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        final TextChannel channel = event.getTextChannel();
        String message = event.getMessage().getContentRaw();

        if (message.startsWith("*play")) {

            final Member member = event.getMember();
            final GuildVoiceState memberVoiceState = member.getVoiceState();
            final Member self = event.getGuild().getSelfMember();
            final GuildVoiceState selfVoiceState = self.getVoiceState();
            final AudioManager audioManager = event.getGuild().getAudioManager();
            final AudioChannel memberChannel = memberVoiceState.getChannel();

            if (!selfVoiceState.inAudioChannel()) {
                audioManager.openAudioConnection(memberChannel);
                channel.sendMessageFormat("Connecting to `\uD83D\uDD0A %s`", memberChannel.getName()).queue();
            }

            if (!memberVoiceState.inAudioChannel()) {
                channel.sendMessage("You need to be in a voice channel for this command to work").queue();
                return;
            }

            String link = message.substring(6);

            if (link.equalsIgnoreCase("aram")) {
                PlayerManager.getInstance().loadAndPlay(channel, "https://www.youtube.com/watch?v=Qb6Bd1J954o&ab_channel=Festynbymbymbym");
                PlayerManager.getInstance().loadAndPlay(channel, "https://www.youtube.com/watch?v=yMR0bpTlS_A&ab_channel=Vst");
                PlayerManager.getInstance().loadAndPlay(channel, "https://www.youtube.com/watch?v=HMjXFFhCUo8&ab_channel=charlesdarwin55");
                PlayerManager.getInstance().loadAndPlay(channel, "https://www.youtube.com/watch?v=3SEGoMM-cFY&ab_channel=DjHalt");
            } else if (!isUrl(link)) {
                link = "ytsearch:" + link;
                PlayerManager.getInstance().loadAndPlay(channel, link);
            } else {
                PlayerManager.getInstance().loadAndPlay(channel, link);
            }
        }
    }

    private boolean isUrl (String url){
        try {
            new URI(url);
            return true;
        } catch (URISyntaxException e) {
            return false;
        }
    }
}
