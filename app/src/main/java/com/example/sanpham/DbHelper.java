package com.example.sanpham;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(@Nullable Context context) {
        super(context, "SanPham", null, 14);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE SanPham(MaSP INTEGER PRIMARY KEY AUTOINCREMENT, " + "TenSP TEXT, GiaTien REAL, Image TEXT)";
        db.execSQL(sql);

        String data = "INSERT INTO SanPham VALUES(1, 'Bánh ngọt Lamington', '#12,00', 'https://recipes.net/wp-content/uploads/2022/04/Roasted-Veggie-Enchilada-Casserole-Recipe-1536x865.jpg')," +
                "(2, 'Fantales', '#1,900', 'https://recipes.net/wp-content/uploads/2023/12/how-to-cook-quinoa-salad-1702362222.jpg')," +
                "(3, 'Hamburger củ dền', '#1,900', 'https://recipes.net/wp-content/uploads/2020/04/fish-fillets-lorange-recipe-scaled.jpg')," +
                "(4, 'Bánh nướng cà', '#1,900', 'https://cailonuong.com/wp-content/uploads/2023/05/banh-kem-bap-3-683x1024.jpeg')," +
                "(5, 'Fantales', '#1,900', 'https://recipes.net/wp-content/uploads/2023/12/how-to-cook-quinoa-salad-1702362222.jpg')";
        db.execSQL(data);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if (i != i1){
            db.execSQL("DROP TABLE IF EXISTS SanPham");
            onCreate(db);
        }
    }
}