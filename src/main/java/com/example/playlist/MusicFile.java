package com.example.playlist;

import javafx.collections.MapChangeListener;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class MusicFile {

    Media mediaFile;
    MediaPlayer mediaPlayer;
    String fileName;
    String title;
    String artist;
    String length;
    String size;

    public MusicFile(File file){
        mediaFile = new Media(file.toURI().toString());

        mediaPlayer = new MediaPlayer(mediaFile);


        mediaPlayer.setOnReady(new Runnable() {

            @Override
            public void run() {
                long minutes = TimeUnit.MILLISECONDS.toMinutes(Integer.parseInt(String.valueOf(mediaPlayer.getTotalDuration()).split("\\.")[0]));
                long seconds = TimeUnit.MILLISECONDS.toSeconds(Integer.parseInt(String.valueOf(mediaPlayer.getTotalDuration()).split("\\.")[0]));
                length = minutes +":"+((seconds-minutes*60)<10?"0"+(seconds-minutes*60):(seconds-minutes*60))+" min";

            }
        });

        fileName= file.getName();
        mediaFile.getMetadata().addListener((MapChangeListener.Change<? extends String, ? extends Object> c) -> {
            if (c.wasAdded()) {
                if ("artist".equals(c.getKey())) {
                    artist = c.getValueAdded().toString();
                } else if ("title".equals(c.getKey())) {
                    title = c.getValueAdded().toString();
                }
            }
        });
        size = file.length() / (1024 * 1024) +"MB";
        System.out.print(fileName +" "+ title+" "+ artist+"\n");


    }

    public String getArtist() {
        return artist;
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
