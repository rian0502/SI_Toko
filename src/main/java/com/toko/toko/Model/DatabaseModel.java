package com.toko.toko.Model;

import com.toko.toko.Model.KoneksiDatabase.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseModel {
    private final Connection connection;
    private ObservableList<BarangPenjualan> penjualans = FXCollections.observableArrayList();

    public DatabaseModel(){
        this.connection = DatabaseConnection.getKoneksi();
    }
    public boolean loginAkun(Akun akun){
        try {
            String sql = "SELECT akun.username, akun.password FROM akun WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,akun.getUsername());
            preparedStatement.setString(2,akun.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                if (resultSet.getString(1).trim().equals(akun.getUsername()) && resultSet.getString(2).trim().equals(akun.getPassword())){
                    return true;
                }
                break;
            }
        }catch (Exception e){
            System.out.println("EROR CLASS DBM");
            System.out.println(e.getMessage());
        }
        return false;
    }
    public void tambahBarang(Barang barang){
        try {
            String sql = "INSERT INTO barang VALUES(?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, barang.getIDbarang());
            preparedStatement.setString(2,barang.getNama_barang());
            preparedStatement.setDouble(3, barang.getModal_barang());
            preparedStatement.setDouble(4, barang.getHarga_jual());
            preparedStatement.setInt(5, barang.getStock_barang());
            preparedStatement.execute();
            preparedStatement.close();
            JOptionPane.showMessageDialog(null, "Barang Berhasil di Tambah","Barang",JOptionPane.INFORMATION_MESSAGE);
        }catch (Exception e){
            System.out.println("EROR TAMBAH BARANG");
            System.out.println(e.getMessage());
            System.out.println(e);
        }
    }
    public void updateBarang(Barang barang){
        try {
            String sql = "UPDATE barang SET nama_barang = ?, modal_barang = ?, harga_jual=?,stock_barang=? WHERE id_barang=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, barang.getNama_barang());
            preparedStatement.setDouble(2,barang.getModal_barang());
            preparedStatement.setDouble(3, barang.getHarga_jual());
            preparedStatement.setInt(4,barang.getStock_barang());
            preparedStatement.setInt(5, barang.getIDbarang());
            preparedStatement.execute();
            preparedStatement.close();
        }catch (Exception e){
            System.out.println("EROR UPDATE BARANG");
            System.out.println(e);
            System.out.println(e.getMessage());
        }
    }
    public void deleteBarang(int idBarang){
        try {
            String sql = "DELETE FROM barang WHERE id_barang = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,idBarang);
            statement.execute();
            statement.close();
        }catch (Exception e){
            System.out.println("EROR DELETE BARANG");
            System.out.println(e);
            System.out.println(e.getMessage());
        }
    }
    public ObservableList<Barang> getBarang(){
        ObservableList<Barang> barangs = FXCollections.observableArrayList();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM barang";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                barangs.add(new Barang(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getDouble(4),
                        resultSet.getInt(5)));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e);
        }
        return barangs;
    }

    public int getIdBarang(){
        try{
            String sql = "SELECT MAX(id_barang) FROM barang";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                return (resultSet.getInt(1))+1;
            }
        }catch (Exception e){
            System.out.println(e);
            System.out.println(e.getMessage());
        }
        return 0;
    }
    public int getIdTransaksi(){
        int id = 0;
        try {
            String sql = "SELECT MAX(id_transaksi) FROM transaksi";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                return (resultSet.getInt(1)) != 0 ? resultSet.getInt(1)+1 : 12123;
            }
        }catch (Exception e){
            System.out.println(e);
            System.out.println(e.getMessage());
        }
        return id;
    }
    public void tambahTransaksi(Transaksi transaksi){
        try{
            String sql = "INSERT INTO transaksi(id_transaksi, tanggal_transaksi) VALUES(?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, transaksi.getId_transaksi());
            preparedStatement.setString(2,transaksi.getTanggal_transaksi());
            preparedStatement.execute();
            preparedStatement.close();
        }catch (Exception e){
            System.out.println("EROR TRANSAKSI");
            System.out.println(e);
            System.out.println(e.getMessage());
        }
    }
    public void kurangBarang(int id, int jumlah){
        try {
            String sql = "CALL kurangBarang(?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.setInt(2,jumlah);
            preparedStatement.execute();
            preparedStatement.close();
        }catch (Exception e){
            System.out.println(e);
            System.out.println(e.getMessage());
        }
    }
    public void tambahPenjualan(String nama, int jumlah, double harga, double total, int idTrx){
        try {
            String sql = "INSERT INTO penjuala_barang(`nama_barang`, `jumlah_barang`, `harga_barang`, `total_harga`, `id_transaksi`) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nama);
            preparedStatement.setInt(2,jumlah);
            preparedStatement.setDouble(3, harga);
            preparedStatement.setDouble(4, total);
            preparedStatement.setInt(5, idTrx);
            preparedStatement.execute();
            preparedStatement.close();
        }catch (Exception e){
            System.out.println(e);
            System.out.println(e.getMessage());
        }
    }
    public void selectBelanja(int id, int jumlah, int idTrx){
        try {
            String sql = "CALL selectBarang(?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                penjualans.add(new BarangPenjualan(
                        resultSet.getString(1),
                        resultSet.getDouble(2),
                        jumlah,
                        jumlah*resultSet.getDouble(2)));
                tambahPenjualan(resultSet.getString(1),jumlah,resultSet.getDouble(2),jumlah*resultSet.getDouble(2),idTrx);
            }
            kurangBarang(id, jumlah);
        }catch (Exception e){
            System.out.println("EROR SELECT BARANG");
            System.out.println(e);
            System.out.println(e.getMessage());
        }
    }
    public int searchBarang(String id){
        try{
            String sql = "SELECT barang.id_barang FROM barang WHERE barang.id_barang = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                return resultSet.getInt(1);
            }
        }catch (Exception e){
            System.out.println(e);
            System.out.println(e.getMessage());
        }
        return 0;
    }
    public boolean isEmptyBarang(String id, int jumlah){
        try{
            String sql = "SELECT barang.stock_barang FROM barang WHERE barang.id_barang = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                if (jumlah < resultSet.getInt(1)){
                    return true;
                }
            }
        }catch (Exception e){
            System.out.println(e);
            System.out.println(e.getMessage());
        }
        return false;
    }
    public ObservableList<BarangPenjualan> getTransaksis() {
        return penjualans;
    }
    public void resetPenjualan(){
        penjualans.clear();
    }

    public void tambahHutang(Hutang hutang){
        try{
            String sql = "INSERT INTO hutang (nama, alamat, jumlah_hutang, tanggal_hutang) VALUES (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, hutang.getNama());
            statement.setString(2, hutang.getAlamat());
            statement.setDouble(3, hutang.getJumlah_hutang());
            statement.setString(4, hutang.getTanggal_hutang());
            statement.execute();
            statement.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e);
        }
    }
    public void ubahHutang(Hutang hutang){

    }
}
