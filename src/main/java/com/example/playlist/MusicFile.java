package com.example.playlist;

import javafx.scene.media.Media;

import java.io.File;

public class MusicFile {
    Media mediaFile;
    String fileName;
    String musicName;
    String artist;
    String duration;

    public MusicFile(File file){
        mediaFile = new Media(file.getPath());
        fileName= file.getName();
        artist = String.valueOf(mediaFile.getMetadata().get("artist"));
        musicName = String.valueOf(mediaFile.getMetadata().get("title"));
        duration = String.valueOf(mediaFile.getMetadata().get("length"));
    }
}
