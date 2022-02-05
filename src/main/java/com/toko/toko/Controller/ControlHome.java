package com.toko.toko.Controller;


import com.toko.toko.Main;
import com.toko.toko.Model.Barang;
import com.toko.toko.Model.DatabaseModel;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ControlHome implements Initializable {

    @FXML
    private TextField tfID_barang;
    @FXML
    private TextField tfHargaModal;
    @FXML
    private TextField tfHargaJual;
    @FXML
    private TextField tfNamaBarang;
    @FXML
    private Spinner SpStockBarang;
    @FXML
    private Button btnAddBarang;
    @FXML
    private Button btnUbahBarang;
    @FXML
    private Button btnHapusBarang;
    @FXML
    private TableView <Barang>tableBarang;
    @FXML
    private TableColumn<Barang, Integer>rowID;
    @FXML
    private TableColumn<Barang, String>rowBarang;
    @FXML
    private TableColumn<Barang, Double>rowModal;
    @FXML
    private TableColumn<Barang, Double>rowHarga;
    @FXML
    private TableColumn<Barang, Integer>rowStock;
    //FXCollections.observableArrayList(new Barang(1335,"Kopi",13.5,14.0,3));
    DatabaseModel dbm ;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dbm = new DatabaseModel();
        ObservableList<Barang> barangs = dbm.getBarang();
        rowID.setCellValueFactory(id->id.getValue().IDbarangProperty().asObject());
        rowBarang.setCellValueFactory(nama->nama.getValue().nama_barangProperty());
        rowModal.setCellValueFactory(modal->modal.getValue().modal_barangProperty().asObject());
        rowHarga.setCellValueFactory(harga->harga.getValue().harga_jualProperty().asObject());
        rowStock.setCellValueFactory(stock->stock.getValue().stock_barangProperty().asObject());
        tableBarang.setItems(barangs);
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
