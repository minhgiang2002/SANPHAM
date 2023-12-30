package com.example.sanpham;

public class Product {
    private Integer MaSP;
    private String TenSP;
    private String GiaTien;
    private String Image;

    public Product(Integer maSP, String tenSP, String giaTien, String image) {
        MaSP = maSP;
        TenSP = tenSP;
        GiaTien = giaTien;
        Image = image;
    }

    public Integer getMaSP() {
        return MaSP;
    }

    public void setMaSP(Integer maSP) {
        MaSP = maSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public String getGiaTien() {
        return GiaTien;
    }

    public void setGiaTien(String giaTien) {
        GiaTien = giaTien;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
