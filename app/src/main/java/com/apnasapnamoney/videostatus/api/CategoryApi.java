package com.apnasapnamoney.videostatus.api;

import android.widget.Toast;

import com.apnasapnamoney.videostatus.model.CategoriesResponse;
import com.apnasapnamoney.videostatus.views.BaseActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by jindaldipanshu on 4/7/2018.
 */

public class CategoryApi {

    public interface CategoriesInterface {
        void onSuccess(ArrayList<CategoriesResponse.Categories> categoriesArrayList);
    }

    private BaseActivity mainActivity;
    private CategoriesInterface categoriesInterface;

    public CategoryApi(BaseActivity mainActivity, CategoriesInterface categoriesInterface) {
        this.mainActivity = mainActivity;
        this.categoriesInterface = categoriesInterface;
    }

    public void fetchCategoriesResponse(String dp_categories) {
        mainActivity.showDialog();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference firebase = ref.child(dp_categories);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mainActivity.hideDialog();
                if (dataSnapshot.exists()) {
                    Iterator<DataSnapshot> friendsIterator = dataSnapshot.getChildren().iterator();
                    ArrayList<CategoriesResponse.Categories> categoriesList = new ArrayList<>();
                    while (friendsIterator.hasNext()) {
                        DataSnapshot friendSnapshot = friendsIterator.next();
                        CategoriesResponse.Categories categories = new CategoriesResponse.Categories();
                        categories.setCategory_name(friendSnapshot.getKey());
                        categories.setCategory_image_url(friendSnapshot.getValue() + "");
                        categoriesList.add(categories);
                    }
                    categoriesInterface.onSuccess(categoriesList);
                } else {
                    mainActivity.showToast("No data found.");
                    Toast.makeText(mainActivity, "No data found.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                mainActivity.hideDialog();
                mainActivity.showToast(databaseError.getMessage());
            }
        };

        firebase.addListenerForSingleValueEvent(valueEventListener);


    }

}
