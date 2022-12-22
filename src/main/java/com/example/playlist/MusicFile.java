package com.example.playlist;

import javafx.collections.MapChangeListener;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.concurrent.TimeUnit;

//класс для представления файлов музыки
public class MusicFile {

    static MusicListController controller;
    Media mediaFile;
    MediaPlayer mediaPlayer;
    private final String fileName;
    private String title;
    private String artist;
    private String length;
    private final String size;

    public MusicFile(File file){
        mediaFile = new Media(file.toURI().toString());

        mediaPlayer = new MediaPlayer(mediaFile);


        mediaPlayer.setOnReady(new Runnable() {

            @Override
            public void run() {
                long minutes = TimeUnit.MILLISECONDS.toMinutes(Integer.parseInt(String.valueOf(mediaPlayer.getTotalDuration()).split("\\.")[0]));
                long seconds = TimeUnit.MILLISECONDS.toSeconds(Integer.parseInt(String.valueOf(mediaPlayer.getTotalDuration()).split("\\.")[0]));
                length = minutes +":"+((seconds-minutes*60)<10?"0"+(seconds-minutes*60):(seconds-minutes*60))+" min";
                if(controller!=null){
                    controller.musicTable.refresh();
                }
            }
        });

        fileName= file.getName();
        mediaFile.getMetadata().addListener((MapChangeListener.Change<? extends String, ?> c) -> {
            if (c.wasAdded()) {
                if ("artist".equals(c.getKey())) {
                    artist = c.getValueAdded().toString();
                } else if ("title".equals(c.getKey())) {
                    title = c.getValueAdded().toString();
                }
            }
        });
        size = file.length() / (1024 * 1024) +"MB";


    }

    public String getArtist() {
        return artist;
    }
    public static void setController(MusicListController cnt){
        controller = cnt;
    }

    public String getLength() {
        return length;
    }

    public String getFileName() {
        return fileName;
    }

    public String getTitle() {
        return title;
    }

    public String getSize() {
        return size;
    }
}
