package uet.oop.bomberman.music;

import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.net.URISyntaxException;

public class Sound {
    private static MediaView mediaViewMusic = new MediaView();
    @FXML
    public static void onPlayAudio() {
//        MediaView mediaView = new MediaView();
        try {
            String path = Sound.class.getResource("/sound/Für Elise MP3.mp3").toURI().toString();
            Media media = new Media(path);
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaViewMusic.setMediaPlayer(mediaPlayer);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        mediaViewMusic.getMediaPlayer().play();
    }

    public static void offPlayAudio() {
        mediaViewMusic.getMediaPlayer().stop();
    }

    public static void BombExplode() {
        MediaView mediaView = new MediaView();
        try {
            String path = Sound.class.getResource("/sound/src_main_resources_assets_sounds_bomb_explored.mp3").toURI().toString();
            Media media = new Media(path);
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        mediaView.getMediaPlayer().play();
    }
    public static void PlacedBomb() {
        MediaView mediaView = new MediaView();
        try {
            String path = Sound.class.getResource("/sound/src_main_resources_assets_sounds_placed_bomb.mp3").toURI().toString();
            Media media = new Media(path);
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        mediaView.getMediaPlayer().play();
    }
    public static void StageStart() {
        MediaView mediaView = new MediaView();
        try {
            String path = Sound.class.getResource("/sound/src_main_resources_assets_sounds_Stage_Start.mp3").toURI().toString();
            Media media = new Media(path);
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        mediaView.getMediaPlayer().play();
    }
    public static void Walk1() {
        MediaView mediaView = new MediaView();
        try {
            String path = Sound.class.getResource("/sound/src_main_resources_assets_sounds_walk_1.mp3").toURI().toString();
            Media media = new Media(path);
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        mediaView.getMediaPlayer().play();
    }
    public static void Walk2() {
        MediaView mediaView = new MediaView();
        try {
            String path = Sound.class.getResource("/sound/src_main_resources_assets_sounds_walk_2.mp3").toURI().toString();
            Media media = new Media(path);
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        mediaView.getMediaPlayer().play();
    }
    public static void Walk3() {
        MediaView mediaView = new MediaView();
        try {
            String path = Sound.class.getResource("/sound/src_main_resources_assets_sounds_walk_4.mp3").toURI().toString();
            Media media = new Media(path);
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        mediaView.getMediaPlayer().play();
    }
}