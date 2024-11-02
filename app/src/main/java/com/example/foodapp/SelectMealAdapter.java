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

public class SelectMealAdapter extends RecyclerView.Adapter<SelectMealAdapter.ViewHolder> {

    Context context;
    int singledata;
    public ArrayList<MealModel> modelArrayList;
    SQLiteDatabase sqLiteDatabase;
    ChangeStatusListener changeStatusListener;

    public void setModel(ArrayList<MealModel> models){
        this.modelArrayList = models;
    }

    public interface ChangeStatusListener{
        void onItemChangeListener(int position, MealModel model);
    }

    public SelectMealAdapter(Context context, int singledata, ArrayList<MealModel> modelArrayList, SQLiteDatabase sqLiteDatabase) {
        this.context = context;
        this.singledata = singledata;
        this.modelArrayList = modelArrayList;
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @NonNull
    @Override
    public SelectMealAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.singledata, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectMealAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final MealModel model = modelArrayList.get(position);
        if (model != null) {
            holder.position = position;
            byte [] image = model.getAvatar();
            Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
            holder.imageavatar.setImageBitmap(bitmap);
            holder.txtname.setText("Hotel: "+model.getHotelName()+"\n Meal: "+model.getMeal()+"\nPrice: "+model.getPrice()+"\nAvailability: "+model.getAvailability());
            if(model.isSelect()){
                holder.view.setBackgroundColor(context.getResources().getColor(R.color.customSecondary));
            }
        }
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MealModel model = modelArrayList.get(position);
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
//    public ArrayList<MealModel> getSelectedModels (){
//        ArrayList<MealModel> selectedItems = new ArrayList<>();
//        for (MealModel model : modelArrayList){
//            if(model.isSelect()) {
//                selectedItems.add(model);
//            }
//        }
//        return selectedItems;
//    }

    @Override
    public int getItemCount() {
        if(modelArrayList != null){
            return modelArrayList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageavatar;
        TextView txtname, rmTv;
        View view;
        int position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            imageavatar = (ImageView) itemView.findViewById(R.id.smmealImage);
            txtname = (TextView) itemView.findViewById(R.id.smTextView);
            rmTv = (TextView) itemView.findViewById(R.id.rmTv);
        }
    }
}
