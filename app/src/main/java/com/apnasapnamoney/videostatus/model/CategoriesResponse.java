package com.apnasapnamoney.videostatus.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by jindaldipanshu on 4/7/2018.
 */

public class CategoriesResponse implements Parcelable {

     private ArrayList<Categories> categoriesList;

     public CategoriesResponse(){}

    protected CategoriesResponse(Parcel in) {
        categoriesList = in.createTypedArrayList(Categories.CREATOR);
    }

    public static final Creator<CategoriesResponse> CREATOR = new Creator<CategoriesResponse>() {
        @Override
        public CategoriesResponse createFromParcel(Parcel in) {
            return new CategoriesResponse(in);
        }

        @Override
        public CategoriesResponse[] newArray(int size) {
            return new CategoriesResponse[size];
        }
    };

    public ArrayList<Categories> getCategoriesList() {
        return categoriesList;
    }

    public void setCategoriesList(ArrayList<Categories> categoriesList) {
        this.categoriesList = categoriesList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(categoriesList);
    }

    public static class Categories implements Parcelable{
         private String category_name;
         private String category_image_url;


         public  Categories(){}
        protected Categories(Parcel in) {
            category_name = in.readString();
            category_image_url = in.readString();
        }

        public static final Creator<Categories> CREATOR = new Creator<Categories>() {
            @Override
            public Categories createFromParcel(Parcel in) {
                return new Categories(in);
            }

            @Override
            public Categories[] newArray(int size) {
                return new Categories[size];
            }
        };

        public String getCategory_name() {
             return category_name;
         }

         public void setCategory_name(String category_name) {
             this.category_name = category_name;
         }

         public String getCategory_image_url() {
             return category_image_url;
         }

         public void setCategory_image_url(String category_image_url) {
             this.category_image_url = category_image_url;
         }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(category_name);
            dest.writeString(category_image_url);
        }
    }
}
