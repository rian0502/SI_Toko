package com.toko.toko.Controller;


import com.toko.toko.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ControlHome implements Initializable {

    @FXML
    public TextField tfID_barang;
    @FXML
    public TextField tfHargaModal;
    @FXML
    public TextField tfHargaJual;
    @FXML
    public TextField tfNamaBarang;
    @FXML
    public Spinner SpStockBarang;
    @FXML
    public Button btnAddBarang;
    @FXML
    public Button btnUbahBarang;
    @FXML
    public Button btnHapusBarang;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tfID_barang.setEditable(false);

    }

    public void handleLogoutAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("LoginPage.fxml")));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void handleSimpanAction(ActionEvent event) {
    }

    public void handleCancelAction(ActionEvent event) {
        btnUbahBarang.setDisable(false);
        btnHapusBarang.setDisable(false);
        btnAddBarang.setDisable(false);
        tfID_barang.clear();
        tfHargaJual.clear();
        tfNamaBarang.clear();
    }

    public void handleAddAction(ActionEvent event) {
        btnUbahBarang.setDisable(true);
        btnHapusBarang.setDisable(true);
    }

    public void handleUpdateAction(ActionEvent event) {
        btnAddBarang.setDisable(true);
        btnHapusBarang.setDisable(true);
    }

    public void handleDeleteAction(ActionEvent event) {
        btnUbahBarang.setDisable(true);
        btnAddBarang.setDisable(true);
    }
}
