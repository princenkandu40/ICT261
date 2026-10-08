module org.example.customermanager {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.customermanager to javafx.fxml, javafx.base;
    exports org.example.customermanager;
}