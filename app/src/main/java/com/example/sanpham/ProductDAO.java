package com.example.sanpham;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class ProductDAO {
    private DbHelper dbHelper;

    public ProductDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public ArrayList<Product> getListSanPham() {
        ArrayList<Product> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.beginTransaction();
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM SanPham", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    list.add(new Product(cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3)));
                } while (cursor.moveToNext());
                database.setTransactionSuccessful();
            }
        } catch (Exception e) {
            Log.e("SanPhamDAO", "getListSanPham" + e);
        } finally {
            database.endTransaction();
        }
        return list;
    }

    public boolean addSanPham(Product product) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("TenSP", product.getTenSP());
        cv.put("GiaTien", product.getGiaTien());
        cv.put("Image", product.getImage());
        long check = db.insert("SanPham", null, cv);
        return check != -1;
    }

    public boolean deleteSanPham(int maSP) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int deletedRows = db.delete("SanPham", "MaSP=?", new String[]{String.valueOf(maSP)});
        return deletedRows > 0;
    }
}