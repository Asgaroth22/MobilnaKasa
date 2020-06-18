package com.example.mobilnakasa;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private ArrayList<ProductItem> productList;
    private ProductAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(ProductAdapter.OnItemClickListener listener){
        mListener = listener;
    }
    public static class ProductViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public TextView code;
        public TextView price;

        public ProductViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            name = itemView.findViewById(R.id.productName);
            code = itemView.findViewById(R.id.productCode);
            price = itemView.findViewById(R.id.productPrice);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    if(listener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public ProductAdapter(ArrayList<ProductItem> productList){
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        ProductViewHolder ovh = new ProductViewHolder(view, mListener);
        return ovh;
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        ProductItem currentItem = productList.get(position);
        holder.name.setText(currentItem.getName());
        holder.price.setText(String.valueOf(currentItem.getPrice()));
        holder.code.setText(currentItem.getId());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
