package commands;

import audio.GuildMusicManager;
import audio.PlayerManager;
import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;
import org.jetbrains.annotations.NotNull;

public class TimeoutCommand extends ListenerAdapter {

    @Override
    public void onGuildVoiceLeave(@NotNull GuildVoiceLeaveEvent event) {

        AudioChannel ac = event.getChannelLeft();

        long count = ac.getMembers().stream().filter(member -> !member.getUser().isBot()).count();

        if (count == 0) {
            final AudioManager audioManager = event.getGuild().getAudioManager();
            final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());

            musicManager.scheduler.clearTrackList();
            musicManager.scheduler.nextTrack();
            audioManager.closeAudioConnection();
        }
    }
}
