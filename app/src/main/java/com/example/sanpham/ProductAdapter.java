package com.example.sanpham;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.SanPhamViewHolder> {

    private Context context;
    private List<Product> productList;
    private ProductDAO productDAO;

    public ProductAdapter(Context context, List<Product> productList, ProductDAO productDAO) {
        this.context = context;
        this.productList = productList;
        this.productDAO = productDAO;
    }

    @NonNull
    @Override
    public SanPhamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new SanPhamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.edtTenSP.setText(product.getTenSP());
        holder.edtPrice.setText(product.getGiaTien());
        Picasso.get().load(product.getImage()).into(holder.productImage);

        holder.imageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItem(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class SanPhamViewHolder extends RecyclerView.ViewHolder {
        TextView edtTenSP, edtPrice;
        ImageView productImage, imageDelete;

        public SanPhamViewHolder(View itemView) {
            super(itemView);
            edtTenSP = itemView.findViewById(R.id.edtTenSP);
            edtPrice = itemView.findViewById(R.id.edtPrice);
            productImage = itemView.findViewById(R.id.productImage);
            imageDelete = itemView.findViewById(R.id.imageDelete);
        }
    }

    public void deleteItem(int position) {
        int productId = productList.get(position).getMaSP();
        boolean isDeleted = productDAO.deleteSanPham(productId);

        if (isDeleted) {
            productList.remove(position);
            notifyItemRemoved(position);
            Toast.makeText(context, "Sản phẩm đã bị xóa", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Xóa sản phẩm thất bại", Toast.LENGTH_SHORT).show();
        }
    }
}