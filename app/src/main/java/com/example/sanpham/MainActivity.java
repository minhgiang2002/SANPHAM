package com.example.sanpham;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private ProductDAO productDAO;
    TextView edtMaSP, edtTenSP, edtPrice, edtImageName;
    Button btnClear, btnSave;
    ImageButton imageDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize SanPhamDAO
        productDAO = new ProductDAO(this);
        edtMaSP = findViewById(R.id.edtMaSP);
        edtTenSP = findViewById(R.id.edtTenSP);
        edtPrice = findViewById(R.id.edtPrice);
        edtImageName = findViewById(R.id.edtImageName);
        btnClear = findViewById(R.id.btnClear);
        btnSave = findViewById(R.id.btnSave);

        // Get data from DAO and set up the adapter
        ArrayList<Product> listProduct = productDAO.getListSanPham();
        productAdapter = new ProductAdapter(this, listProduct, productDAO);
        recyclerView.setAdapter(productAdapter);

        // Other view initializations and button clicks can go here
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(productAdapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
        FloatingActionButton flButton = findViewById(R.id.flButton);
        flButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View customDialogView = LayoutInflater.from(MainActivity.this).inflate(R.layout.add_items, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(customDialogView);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        btnClearDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtMaSPDialog.setText("");
                edtTenSPDialog.setText("");
                edtPriceDialog.setText("");
                edtImageNameDialog.setText("");
            }
        });
    }

    // Other methods and logic for the activity can be added here
}