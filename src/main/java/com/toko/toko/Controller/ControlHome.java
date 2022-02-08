package com.toko.toko.Controller;


import com.toko.toko.Main;
import com.toko.toko.Model.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

public class ControlHome implements Initializable {
    @FXML
    private TextField tfUangBelanja;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnADD;
    @FXML
    private Button btnClearCount;
    @FXML
    private TextField tfIDbarangKasir;
    @FXML
    private Button btnUbahHutang;
    @FXML
    private Button btnLunas;
    @FXML
    private Button btnSimpanHutang;
    @FXML
    private Button btnTambahHutang;
    @FXML
    private Button btnCloseHutang;
    @FXML
    private TextArea tfAlamatHutang;
    @FXML
    private TextField tfJumlahHutang;
    @FXML
    private TextField tfNamaHutang;
    @FXML
    private Label lbTotalBelanja;
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
    @FXML
    private Button btnNewTransaksi;
    private DatabaseModel dbm ;
    private int idTrx = 0;
    private double totalharga = 0;

    public void refreshTableBarang(){
        ObservableList<Barang> barangs = dbm.getBarang();
        rowID.setCellValueFactory(id->id.getValue().IDbarangProperty().asObject());
        rowBarang.setCellValueFactory(nama->nama.getValue().nama_barangProperty());
        rowModal.setCellValueFactory(modal->modal.getValue().modal_barangProperty().asObject());
        rowHarga.setCellValueFactory(harga->harga.getValue().harga_jualProperty().asObject());
        rowStock.setCellValueFactory(stock->stock.getValue().stock_barangProperty().asObject());
        tableBarang.setItems(barangs);
    }
    private void setRoundedbutton(){
        double size = 30;
        btnADD.setShape(new Circle(size));
        btnADD.setMinSize(2*size, 2*size);
        btnADD.setMaxSize(2*size, 2*size);
        btnClearCount.setShape(new Circle(size));
        btnClearCount.setMinSize(2*size, 2*size);
        btnClearCount.setMaxSize(2*size, 2*size);
    }
    private void setValueSP(){
        SpinnerValueFactory<Integer>valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100);
        SpStockBarang.setValueFactory(valueFactory);
        SpinnerValueFactory<Integer>valueFactoryKasir = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100);
        spJumlahBarang.setValueFactory(valueFactoryKasir);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setRoundedbutton();
        tfID_barang.setEditable(false);
        tfNamaBarang.setEditable(false);
        tfHargaJual.setEditable(false);
        tfHargaModal.setEditable(false);
        SpStockBarang.setEditable(false);
        tfIDbarangKasir.setEditable(false);
        spJumlahBarang.setEditable(false);
        btnADD.setDisable(true);
        setValueSP();
        dbm = new DatabaseModel();
        refreshTableBarang();
        tableBarang.setOnMousePressed(mouseEvent -> {
            tfID_barang.setText(String.valueOf(tableBarang.getSelectionModel().getSelectedItems().get(0).getIDbarang()));
            tfNamaBarang.setText(tableBarang.getSelectionModel().getSelectedItems().get(0).getNama_barang());
            tfHargaModal.setText(String.valueOf(tableBarang.getSelectionModel().getSelectedItems().get(0).getModal_barang()));
            tfHargaJual.setText(String.valueOf(tableBarang.getSelectionModel().getSelectedItems().get(0).getHarga_jual()));
            SpStockBarang.getValueFactory().setValue(tableBarang.getSelectionModel().getSelectedItems().get(0).getStock_barang());
        });
    }
    @FXML
    public void handleLogoutAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("LoginPage.fxml")));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void handleSimpanAction() {
        if (!btnAddBarang.isDisable()){
            if(tfNamaBarang.getText().trim().equals("") && tfHargaJual.getText().trim().equals("") && tfHargaModal.getText().trim().equals("")){
                JOptionPane.showMessageDialog(null, "Harap Isi Data Barang dengan lengkap","Warning",JOptionPane.INFORMATION_MESSAGE);
            }else{
                dbm.tambahBarang(new Barang(Integer.parseInt(tfID_barang.getText().trim()),
                        tfNamaBarang.getText().trim(),
                        Double.parseDouble(tfHargaModal.getText().trim()),Double.parseDouble(tfHargaJual.getText().trim()),SpStockBarang.getValue()));
                refreshTableBarang();
                btnCancel.fire();
            }
        }else if(!btnUbahBarang.isDisable()){
            if(tfNamaBarang.getText().trim().equals("") && tfHargaJual.getText().trim().equals("") && tfHargaModal.getText().trim().equals("")){
                JOptionPane.showMessageDialog(null, "Harap Isi Data Barang dengan lengkap","Warning",JOptionPane.INFORMATION_MESSAGE);
            }else{
                dbm.updateBarang(new Barang(
                        Integer.parseInt(tfID_barang.getText().trim()),
                        tfNamaBarang.getText().trim(),
                        Double.parseDouble(tfHargaModal.getText().trim()),
                        Double.parseDouble(tfHargaJual.getText().trim()),
                        SpStockBarang.getValue()));
                refreshTableBarang();
                btnCancel.fire();
            }
        }else{
            dbm.deleteBarang(Integer.parseInt(tfID_barang.getText().trim()));
            refreshTableBarang();
            btnCancel.fire();
        }
    }
    @FXML
    public void handleCancelAction() {
        btnUbahBarang.setDisable(false);
        btnHapusBarang.setDisable(false);
        btnAddBarang.setDisable(false);
        SpStockBarang.getValueFactory().setValue(1);
        tfID_barang.clear();
        tfHargaJual.clear();
        tfNamaBarang.clear();
        tfHargaModal.clear();
        SpStockBarang.getValueFactory().setValue(1);
    }
    @FXML
    public void handleAddAction() {
        tfNamaBarang.setEditable(true);
        tfHargaJual.setEditable(true);
        tfHargaModal.setEditable(true);
        SpStockBarang.setEditable(true);
        tfID_barang.setText(String.valueOf(dbm.getIdBarang()));
        btnUbahBarang.setDisable(true);
        btnHapusBarang.setDisable(true);
    }
    @FXML
    public void handleUpdateAction() {
        tfNamaBarang.setEditable(true);
        tfHargaJual.setEditable(true);
        tfHargaModal.setEditable(true);
        SpStockBarang.setEditable(true);
        btnAddBarang.setDisable(true);
        btnHapusBarang.setDisable(true);
    }
    @FXML
    public void handleDeleteAction() {
        tfNamaBarang.setEditable(false);
        tfHargaJual.setEditable(false);
        tfHargaModal.setEditable(false);
        SpStockBarang.setEditable(false);
        btnUbahBarang.setDisable(true);
        btnAddBarang.setDisable(true);
    }
    public void refreshTableKasir(){
        ObservableList<BarangPenjualan> belanjas = dbm.getTransaksis();
        colNamaarang.setCellValueFactory(nama->nama.getValue().nama_barangProperty());
        colHargaSatuan.setCellValueFactory(harga->harga.getValue().harga_barangProperty().asObject());
        colJumlaharang.setCellValueFactory(jumlah->jumlah.getValue().jumlahBarangProperty().asObject());
        colTotalHarga.setCellValueFactory(total->total.getValue().total_hargaProperty().asObject());
        tbBelanja.setItems(belanjas);
        for (BarangPenjualan barangPenjualan : belanjas){
            totalharga += barangPenjualan.getTotal_harga();
            lbTotalBelanja.setText(String.valueOf(totalharga));
        }
    }
    @FXML
    public void handleAddBelanja() {
        if (dbm.searchBarang(tfIDbarangKasir.getText().trim()) == 0){
            JOptionPane.showMessageDialog(null,"Barang tidak Ada","Pembelian",JOptionPane.WARNING_MESSAGE);
        }else {
            if(dbm.isEmptyBarang(tfIDbarangKasir.getText().trim(),spJumlahBarang.getValue())){
                dbm.selectBelanja(Integer.parseInt(tfIDbarangKasir.getText().trim()), spJumlahBarang.getValue(), idTrx);
                refreshTableKasir();
                refreshTableBarang();
            }else {
                JOptionPane.showMessageDialog(null,"Barang tidak Cukup","Pembelian",JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    @FXML
    public void handleClearKasirAction() {
        tfIDbarangKasir.clear();
        spJumlahBarang.getValueFactory().setValue(1);
    }
    @FXML
    public void handleCetakStruk() {
        tfIDbarangKasir.setEditable(false);
        spJumlahBarang.setEditable(false);
        btnNewTransaksi.setDisable(false);
        btnADD.setDisable(true);
        totalharga = 0.0;
        dbm.resetPenjualan();
    }
    @FXML
    public void handleMakeTs() {
        btnNewTransaksi.setDisable(true);
        tfIDbarangKasir.setEditable(true);
        spJumlahBarang.setEditable(true);
        LocalDate date = LocalDate.now();
        btnADD.setDisable(false);
        idTrx = dbm.getIdTransaksi();
        dbm.tambahTransaksi(new Transaksi(idTrx,date.toString()));

    }
    @FXML
    public void handleTambahButton() {
        btnUbahHutang.setDisable(true);
        btnLunas.setDisable(true);
    }
    @FXML
    public void handleUbahButton() {
        btnLunas.setDisable(true);
        btnTambahHutang.setDisable(true);
    }
    @FXML
    public void handleLunasButton() {
        btnUbahHutang.setDisable(true);
        btnTambahHutang.setDisable(true);
    }
    @FXML
    public void handleCloseAction() {
        btnUbahHutang.setDisable(false);
        btnLunas.setDisable(false);
        btnTambahHutang.setDisable(false);
        tfNamaHutang.clear();
        tfJumlahHutang.clear();
        tfAlamatHutang.clear();
    }
    @FXML
    public void handleSHutangAction() {
        if(!btnTambahHutang.isDisable()){

        }else if(!btnUbahHutang.isDisable()){

        }else{

        }
    }
}
