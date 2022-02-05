module com.toko.toko {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    opens com.toko.toko to javafx.fxml;
    opens com.toko.toko.Model to javafx.fxml;
    exports com.toko.toko;
    exports com.toko.toko.Controller;
    opens com.toko.toko.Controller to javafx.fxml;
}