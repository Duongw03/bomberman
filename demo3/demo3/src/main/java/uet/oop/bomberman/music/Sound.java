package uet.oop.bomberman.music;

import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.net.URISyntaxException;
import java.util.Objects;

public class Sound {

    @FXML
    public static void onPlayAudio() {
        MediaView mediaView = new MediaView();
        try {
            String path = Objects.requireNonNull(Sound.class.getResource("/fur-elise-2755.mp3")).toURI().toString();
            Media media = new Media(path);
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        mediaView.getMediaPlayer().seek(mediaView.getMediaPlayer().getStartTime());
        mediaView.getMediaPlayer().play();
    }
}