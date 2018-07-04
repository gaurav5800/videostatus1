package com.apnasapnamoney.videostatus.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apnasapnamoney.videostatus.R;
import com.apnasapnamoney.videostatus.model.CategoriesResponse;
import com.apnasapnamoney.videostatus.views.BaseActivity;
import com.apnasapnamoney.videostatus.views.videos.UnderCategory;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

/**
 * @author Gaurav
 * @version 1.0
 */
public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.MyViewHolder> {

    BaseActivity baseActivity;
    Context context;
    private ArrayList<CategoriesResponse.Categories> categoriesArrayList;

    public VideosAdapter(BaseActivity baseActivity, ArrayList<CategoriesResponse.Categories> categoriesArrayList) {
        this.baseActivity = baseActivity;
        this.categoriesArrayList = categoriesArrayList;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(baseActivity).inflate(R.layout.videos_adapter_lay, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        Glide.with(baseActivity).load(categoriesArrayList.get(position).getCategory_image_url()).
                 diskCacheStrategy(DiskCacheStrategy.RESULT).
                into(holder.mTitleImageView);

         String title=categoriesArrayList.get(position).getCategory_name();
         if (!TextUtils.isEmpty(title)){
             holder.mTextViewStatus.setText(title);
         }else {
             holder.mTextViewStatus.setText("N/A");
         }

    }

    @Override
    public int getItemCount() {
        return categoriesArrayList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mTitleImageView;
        RelativeLayout main_lay;
        Button letsmeet;
        TextView mTextViewStatus, where_me;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTitleImageView=itemView.findViewById(R.id.image_view_title);
            mTextViewStatus=itemView.findViewById(R.id.text_view_status);
            mTextViewStatus=itemView.findViewById(R.id.text_view_status);
            itemView.findViewById(R.id.main_lay).setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.main_lay:
                    Intent intent = new Intent(baseActivity, UnderCategory.class);
                    baseActivity.startActivity(intent);
                    break;
            }
        }
    }
}
