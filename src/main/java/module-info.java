module com.example.playlist {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.playlist to javafx.fxml;
    exports com.example.playlist;
}