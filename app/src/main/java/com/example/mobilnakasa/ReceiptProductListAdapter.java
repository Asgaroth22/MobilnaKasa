package com.example.mobilnakasa;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReceiptProductListAdapter extends RecyclerView.Adapter<ReceiptProductListAdapter.ReceiptProductViewHolder> {

    private ArrayList<ProductItem> productList;
    private ReceiptProductListAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }


    public void setOnItemClickListener(ReceiptProductListAdapter.OnItemClickListener listener){
        mListener = listener;
    }
    public static class ReceiptProductViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public TextView price;

        public ReceiptProductViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            name = itemView.findViewById(R.id.receiptProductName);
            price = itemView.findViewById(R.id.receiptProductPrice);

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

    public ReceiptProductListAdapter(ArrayList<ProductItem> productList){
        this.productList = productList;
    }

    @Override
    public ReceiptProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_receipt_product, parent, false);
        ReceiptProductViewHolder ovh = new ReceiptProductViewHolder(view, mListener);
        return ovh;
    }

    @Override
    public void onBindViewHolder(ReceiptProductViewHolder holder, int position) {
        ProductItem currentItem = productList.get(position);
        holder.name.setText(currentItem.getName());
        holder.price.setText(String.valueOf(currentItem.getPrice()));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
