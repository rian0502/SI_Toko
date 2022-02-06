package com.toko.toko.Controller;


import com.toko.toko.Main;
import com.toko.toko.Model.Barang;
import com.toko.toko.Model.BarangPenjualan;
import com.toko.toko.Model.DatabaseModel;
import com.toko.toko.Model.Hutang;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ControlHome implements Initializable {

    @FXML
    private Label lbTotalBelanja;
    @FXML
    private TextField tfIDbarang;
    @FXML
    private TableView<BarangPenjualan> tbBelanja;
    @FXML
    private TableColumn<BarangPenjualan, Double> colHargaSatuan;
    @FXML
    private TableColumn<BarangPenjualan, String> colNamaarang;
    @FXML
    private TableColumn<BarangPenjualan, Integer> colJumlaharang;
    @FXML
    private TableColumn<BarangPenjualan, Double> colTotalHarga;
    @FXML
    private TableView<Hutang> tableHutang;
    @FXML
    private TableColumn<Hutang, String> colNama;
    @FXML
    private TableColumn<Hutang, String> colAlamat;
    @FXML
    private TableColumn<Hutang, Double> colJumlah;
    @FXML
    private TableColumn<Hutang, String> colTanggal;
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
    @FXML
    private TextField tfID_barang;
    @FXML
    private TextField tfHargaModal;
    @FXML
    private TextField tfHargaJual;
    @FXML
    private TextField tfNamaBarang;
    @FXML
    private Spinner<Integer> SpStockBarang;
    @FXML
    private Spinner<Integer> spJumlahBarang;
    @FXML
    private Button btnAddBarang;
    @FXML
    private Button btnUbahBarang;
    @FXML
    private Button btnHapusBarang;

    DatabaseModel dbm ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tfID_barang.setEditable(false);
        SpinnerValueFactory<Integer>valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100);
        SpStockBarang.setValueFactory(valueFactory);
        dbm = new DatabaseModel();
        ObservableList<Barang> barangs = dbm.getBarang();
        rowID.setCellValueFactory(id->id.getValue().IDbarangProperty().asObject());
        rowBarang.setCellValueFactory(nama->nama.getValue().nama_barangProperty());
        rowModal.setCellValueFactory(modal->modal.getValue().modal_barangProperty().asObject());
        rowHarga.setCellValueFactory(harga->harga.getValue().harga_jualProperty().asObject());
        rowStock.setCellValueFactory(stock->stock.getValue().stock_barangProperty().asObject());
        tableBarang.setItems(barangs);
        tableBarang.setOnMousePressed(mouseEvent -> {
            tfID_barang.setText(String.valueOf(tableBarang.getSelectionModel().getSelectedItems().get(0).getIDbarang()));
            tfNamaBarang.setText(tableBarang.getSelectionModel().getSelectedItems().get(0).getNama_barang());
            tfHargaModal.setText(String.valueOf(tableBarang.getSelectionModel().getSelectedItems().get(0).getModal_barang()));
            tfHargaJual.setText(String.valueOf(tableBarang.getSelectionModel().getSelectedItems().get(0).getHarga_jual()));
            SpStockBarang.getValueFactory().setValue(tableBarang.getSelectionModel().getSelectedItems().get(0).getStock_barang());
        });
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
        SpStockBarang.getValueFactory().setValue(1);
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
