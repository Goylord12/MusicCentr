package com.example.playlist;

import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.File;

class MusicFileTest {
    MusicFile musicFile;

    @BeforeAll
    static void initJfxRuntime() {
        Platform.startup(() -> {});
    }

    @BeforeEach
    void initTest() {
        musicFile = new MusicFile(new File("C:/Users/Ixor/Music/01-Carry-Me-Away-_Extended-Mix_.mp3"));
    }

    @Test
    void getArtist() {
        musicFile.mediaPlayer.setOnReady(new Runnable() {
            @Override
            public void run() {
                String artist = musicFile.getArtist();
                Assertions.assertEquals("lapix", artist);
            }
        });
    }

    @Test
    void getLength() {
        musicFile.mediaPlayer.setOnReady(new Runnable() {
            @Override
            public void run() {
                String length = musicFile.getLength();
                System.out.println(length+"\n");
                Assertions.assertEquals("4:01",length);
            }
        });

    }

    @Test
    void getTitle() {
        musicFile.mediaPlayer.setOnReady(new Runnable() {
            @Override
            public void run() {
                String title = musicFile.getTitle();
                Assertions.assertEquals("Carry me away", title);
            }
        });

    }
}