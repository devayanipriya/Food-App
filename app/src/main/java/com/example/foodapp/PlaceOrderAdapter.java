package com.example.foodapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlaceOrderAdapter extends RecyclerView.Adapter<PlaceOrderAdapter.ViewHolder>{

    Context context;
    int single_data;
    public ArrayList<OrderModel> modelArrayList;
    SQLiteDatabase sqLiteDatabase;
    ChangeStatusListener changeStatusListener;

    public void setModel(ArrayList<OrderModel> models){
        this.modelArrayList = models;
    }

    public interface ChangeStatusListener{
        void onItemChangeListener(int position, OrderModel model);
    }

    public PlaceOrderAdapter(Context context, int single_data, ArrayList<OrderModel> modelArrayList, SQLiteDatabase sqLiteDatabase) {
        this.context = context;
        this.single_data = single_data;
        this.modelArrayList = modelArrayList;
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @NonNull
    @Override
    public PlaceOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_data, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceOrderAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final OrderModel model = modelArrayList.get(position);
        if (model != null) {
            holder.position = position;
            byte [] image = model.getAvatar();
            Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
            holder.imageavatar.setImageBitmap(bitmap);
            holder.txtname.setText("Hotel: "+model.getHotelName()+"\n Meal: "+model.getMeal()+"\nPrice: "+model.getPrice());
            if(model.isSelect()){
                holder.view.setBackgroundColor(context.getResources().getColor(R.color.customSecondary));
            }
        }
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderModel model = modelArrayList.get(position);
                if(model.isSelect()){
                    model.setSelect(false);
                }else{
                    model.setSelect(true);
                }
                modelArrayList.set(holder.position, model);
                if(changeStatusListener!=null){
                    changeStatusListener.onItemChangeListener(holder.position, model);
                }
                notifyItemChanged(holder.position);
            }
        });
    }


    @Override
    public int getItemCount() {
        if(modelArrayList != null){
            return modelArrayList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageavatar;
        TextView txtname;
        View view;
        int position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            imageavatar = (ImageView) itemView.findViewById(R.id.nmmealImage);
            txtname = (TextView) itemView.findViewById(R.id.nmTextView);
        }
    }
}

