package com.toko.toko.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class transaksi {
    private IntegerProperty id_transaksi;
    private StringProperty tanggal_transaksi;
    private ArrayList<BarangPenjualan> barang_penjualan;

    public transaksi(String tanggal_transaksi, ArrayList<BarangPenjualan> barang_penjualan) {
        setTanggal_transaksi(tanggal_transaksi);
        this.barang_penjualan = barang_penjualan;
    }
    public transaksi(String tanggal_transaksi, BarangPenjualan barang_penjualan) {
        this.barang_penjualan = new ArrayList<>();
        setTanggal_transaksi(tanggal_transaksi);
        this.barang_penjualan.add(barang_penjualan);
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

    public ArrayList<BarangPenjualan> getBarang_penjualan() {
        return barang_penjualan;
    }

    public void setBarang_penjualan(ArrayList<BarangPenjualan> barang_penjualan) {
        this.barang_penjualan = barang_penjualan;
    }
    public void tambahBarang(BarangPenjualan barangPenjualan){
        this.barang_penjualan.add(barangPenjualan);
    }
}
