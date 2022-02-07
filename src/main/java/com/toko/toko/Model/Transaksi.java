package com.toko.toko.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class Transaksi {
    private IntegerProperty id_transaksi = new SimpleIntegerProperty();
    private StringProperty tanggal_transaksi = new SimpleStringProperty();

    public Transaksi(int id_transaksi, String tanggal_transaksi) {
        setTanggal_transaksi(tanggal_transaksi);
        setId_transaksi(id_transaksi);
    }

    public int getId_transaksi() {
        return id_transaksi.get();
    }

    public IntegerProperty id_transaksiProperty() {
        return id_transaksi;
    }

    public void setId_transaksi(int id_transaksi) {
        this.id_transaksi.set(id_transaksi);
    }

    public String getTanggal_transaksi() {
        return tanggal_transaksi.get();
    }

    public StringProperty tanggal_transaksiProperty() {
        return tanggal_transaksi;
    }

    public void setTanggal_transaksi(String tanggal_transaksi) {
        this.tanggal_transaksi.set(tanggal_transaksi);
    }
}
