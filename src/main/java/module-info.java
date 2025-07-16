module org.example.catalog {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.catalog to javafx.fxml;
    exports org.example.catalog;
}