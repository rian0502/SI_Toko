module com.toko.toko {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.toko.toko to javafx.fxml;
    exports com.toko.toko;
    exports com.toko.toko.Controller;
    opens com.toko.toko.Controller to javafx.fxml;
}