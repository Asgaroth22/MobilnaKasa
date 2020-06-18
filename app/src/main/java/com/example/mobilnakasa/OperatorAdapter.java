package com.example.mobilnakasa;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OperatorAdapter extends RecyclerView.Adapter<OperatorAdapter.OperatorViewHolder> {

    private ArrayList<OperatorItem> operatorList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
    public static class OperatorViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;
        public TextView name;
        public TextView role;
        public OperatorViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.operatorImage);
            name = itemView.findViewById(R.id.operatorName);
            role = itemView.findViewById(R.id.operatorRole);

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

    public OperatorAdapter(ArrayList<OperatorItem> operatorList){
        this.operatorList = operatorList;
    }

    @NonNull
    @Override
    public OperatorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_operator, parent, false);
        OperatorViewHolder ovh = new OperatorViewHolder(view, mListener);
        return ovh;
    }

    @Override
    public void onBindViewHolder(@NonNull OperatorViewHolder holder, int position) {
        OperatorItem currentItem = operatorList.get(position);
        holder.imageView.setImageResource(currentItem.getImgResource());
        holder.name.setText(currentItem.getName());
        holder.role.setText(currentItem.getRole());
    }

    @Override
    public int getItemCount() {
        return operatorList.size();
    }
}
