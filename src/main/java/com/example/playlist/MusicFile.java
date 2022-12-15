package com.example.playlist;

import javafx.collections.MapChangeListener;
import javafx.scene.media.Media;

import java.io.File;

public class MusicFile {
    Media mediaFile;
    String fileName;
    String title;
    String artist;
    String length;

    public MusicFile(File file){
        mediaFile = new Media(file.toURI().toString());
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
//        artist = String.valueOf(mediaFile.getMetadata().get("artist"));
//        title = String.valueOf(mediaFile.getMetadata().get("Title"));
//        duration = String.valueOf(mediaFile.getMetadata().get("length"));
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
}
