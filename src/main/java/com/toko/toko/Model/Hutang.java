package com.toko.toko.Model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Hutang {
    private StringProperty nama = new SimpleStringProperty();
    private StringProperty alamat = new SimpleStringProperty();
    private StringProperty tanggal_hutang = new SimpleStringProperty();
    private DoubleProperty jumlah_hutang = new SimpleDoubleProperty();

    public Hutang(String nama, String alamat, String tanggal_hutang, double jumlah_hutang) {
        setNama(nama);
        setAlamat(alamat);
        setTanggal_hutang(tanggal_hutang);
        setJumlah_hutang(jumlah_hutang);
    }

    public String getNama() {
        return nama.get();
    }

    public StringProperty namaProperty() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama.set(nama);
    }

    public String getAlamat() {
        return alamat.get();
    }

    public StringProperty alamatProperty() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat.set(alamat);
    }

    public String getTanggal_hutang() {
        return tanggal_hutang.get();
    }

    public StringProperty tanggal_hutangProperty() {
        return tanggal_hutang;
    }

    public void setTanggal_hutang(String tanggal_hutang) {
        this.tanggal_hutang.set(tanggal_hutang);
    }

    public double getJumlah_hutang() {
        return jumlah_hutang.get();
    }

    public DoubleProperty jumlah_hutangProperty() {
        return jumlah_hutang;
    }

    public void setJumlah_hutang(double jumlah_hutang) {
        this.jumlah_hutang.set(jumlah_hutang);
    }
}
