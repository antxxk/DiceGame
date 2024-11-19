module com.example.dicegame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.dicegame to javafx.fxml;
    exports com.example.dicegame;
}