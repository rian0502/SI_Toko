package com.toko.toko.Model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Barang {
    private StringProperty nama_barang;
    private DoubleProperty modal_barang;
    private DoubleProperty harga_jual;
    private IntegerProperty stock_barang;

    public Barang(String nama_barang, double modal_barang, double harga_jual, int stock_barang) {
        setNama_barang(nama_barang);
        setModal_barang(modal_barang);
        setHarga_jual(harga_jual);
        setStock_barang(stock_barang);
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

    public double getModal_barang() {
        return modal_barang.get();
    }

    public DoubleProperty modal_barangProperty() {
        return modal_barang;
    }

    public void setModal_barang(double modal_barang) {
        this.modal_barang.set(modal_barang);
    }

    public double getHarga_jual() {
        return harga_jual.get();
    }

    public DoubleProperty harga_jualProperty() {
        return harga_jual;
    }

    public void setHarga_jual(double harga_jual) {
        this.harga_jual.set(harga_jual);
    }

    public int getStock_barang() {
        return stock_barang.get();
    }

    public IntegerProperty stock_barangProperty() {
        return stock_barang;
    }

    public void setStock_barang(int stock_barang) {
        this.stock_barang.set(stock_barang);
    }
}
