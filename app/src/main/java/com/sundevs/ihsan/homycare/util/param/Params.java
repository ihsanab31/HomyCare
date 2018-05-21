package com.sundevs.ihsan.homycare.util.param;

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 0878253827096
 * on Sunday, 19-11-2017
 * ------------------------------
 * This class for Login Dialog
 */

public class Params {

    // user register
    public static final String id_user = "id_user";
    public static final String image = "image";
    public static final String token = "token";
    public static final String password = "password";

    //list suster
    public static final String lat= "lat";
    public static final String lng = "lng";
    public static final String status = "status";
    public static final String id_suster = "id_suster";
    public static final String nama = "nama";
    public static final String gambar = "gambar";
    public static final String alamat = "alamat";
    public static final String umur = "umur";
    public static final String no_hp = "no_hp";
    public static final String jarak = "jarak";
    public static final String pedidikan = "pedidikan";
    public static final String harga = "harga";

    //parameter kondisi
    public static final String tag_json_obj = "json_obj_req";
    public static int success = 0;
    public static final String TAG_SUCCESS="success";
    public static final String TAG_MESSAGE="message";

    //url
    public static final String URL_REGISTER ="/register.php";
    public static final String URL_LOGIN ="/login.php";
    public static final String URL_MAPS ="/maps.php";
    public static final String URL_JARAK ="/list_suster.php?lat=";
    public static final String URL_EDIT_PROFILE = "/editprofile.php";
}
