module com.example.mygame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.mygame to javafx.fxml;
    exports com.example.mygame;
}