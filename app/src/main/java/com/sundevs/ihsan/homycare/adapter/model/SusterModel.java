package com.sundevs.ihsan.homycare.adapter.model;

import io.realm.RealmModel;

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 087825382796
 * on Sunday, 19-11-2017
 * ------------------------------
 * This class for
 */

public class SusterModel implements RealmModel {
    private int id_suster;
    private String nama;
    private String alamat;
    private String no_hp;
    private String umur;
    private double lat;
    private double lng;
    private String token;
    private String status;
    private String gambar;
    private String jarak;
    private String pedidikan;
    private int harga;

    public SusterModel(){
    }

    public SusterModel(int id_suster, String jarak, String nama, String alamat, String no_hp, String umur, double lat, double lng, String token, String status, String gambar, String pedidikan, int harga) {
        this.id_suster = id_suster;
        this.nama = nama;
        this.alamat = alamat;
        this.no_hp = no_hp;
        this.umur = umur;
        this.lat = lat;
        this.lng = lng;
        this.token = token;
        this.status = status;
        this.gambar = gambar;
        this.jarak=jarak;
        this.pedidikan = pedidikan;
        this.harga = harga;
    }

    public int getId_suster() {
        return id_suster;
    }

    public void setId_suster(int id_suster) {
        this.id_suster = id_suster;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getPedidikan() {
        return pedidikan;
    }

    public void setPedidikan(String pedidikan) {
        this.pedidikan = pedidikan;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getJarak() {
        return jarak;
    }

    public void setJarak(String jarak) {
        this.jarak = jarak;
    }
}
