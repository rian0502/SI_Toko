package com.toko.toko.Model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class BarangPenjualan {
    private StringProperty nama_barang;
    private DoubleProperty harga_barang;
    private IntegerProperty jumlahBarang;
    private DoubleProperty total_harga;

    public BarangPenjualan(String nama_barang, double harga_barang, int jumlahBarang, double total_harga) {
       setNama_barang(nama_barang);
       setHarga_barang(harga_barang);
       setJumlahBarang(jumlahBarang);
       setTotal_harga(total_harga);
    }

    public String getNama_barang() {
        return nama_barang.get();
    }

    public StringProperty nama_barangProperty() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang.set(nama_barang);
    }

    public double getHarga_barang() {
        return harga_barang.get();
    }

    public DoubleProperty harga_barangProperty() {
        return harga_barang;
    }

    public void setHarga_barang(double harga_barang) {
        this.harga_barang.set(harga_barang);
    }

    public int getJumlahBarang() {
        return jumlahBarang.get();
    }

    public IntegerProperty jumlahBarangProperty() {
        return jumlahBarang;
    }

    public void setJumlahBarang(int jumlahBarang) {
        this.jumlahBarang.set(jumlahBarang);
    }

    public double getTotal_harga() {
        return total_harga.get();
    }

    public DoubleProperty total_hargaProperty() {
        return total_harga;
    }

    public void setTotal_harga(double total_harga) {
        this.total_harga.set(total_harga);
    }
}
