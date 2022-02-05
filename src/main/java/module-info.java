module com.toko.toko {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.toko.toko to javafx.fxml;
    exports com.toko.toko;
}