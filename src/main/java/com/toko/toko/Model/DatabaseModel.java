package com.toko.toko.Model;

import com.toko.toko.Model.KoneksiDatabase.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseModel {
    private final Connection connection;

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
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e);
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
}
