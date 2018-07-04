package com.apnasapnamoney.videostatus.views;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.apnasapnamoney.videostatus.R;
import com.apnasapnamoney.videostatus.api.CategoryApi;
import com.apnasapnamoney.videostatus.model.CategoriesResponse;
import com.apnasapnamoney.videostatus.utils.Utilities;

import java.util.ArrayList;
import java.util.List;

public class UploadVideoActivity extends BaseActivity implements CategoryApi.CategoriesInterface, AdapterView.OnItemSelectedListener {

    private AppCompatSpinner catgorieSpinner;
    ArrayList<CategoriesResponse.Categories> categoriesList;
    private String categoryName;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_upload_video;
    }

    @Override
    protected void initViews() {

        categoriesList = new ArrayList<>();

        catgorieSpinner = (AppCompatSpinner) findViewById(R.id.spinner_category);
        catgorieSpinner.setOnItemSelectedListener(this);
        findViewById(R.id.image_view_back_arrow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!Utilities.checkInternet(this)) {
            showToast(R.string.no_internet_connection);
        } else {
            CategoryApi dpCategoryApi = new CategoryApi(this, this);
            dpCategoryApi.fetchCategoriesResponse("dp_categories");
        }
    }

    @Override
    public void onSuccess(ArrayList<CategoriesResponse.Categories> categoriesArrayList) {
        if (categoriesArrayList != null && categoriesArrayList.size() > 0) {
            CategoriesResponse.Categories PlusObj = null;
            for (int i = 0; i < categoriesArrayList.size(); i++) {

               /* String name=categoriesArrayList.get(i).getCategory_name();
                if (name.contains("18 Plus")){

                    PlusObj = categoriesArrayList.get(i);
                    PlusObj.setCategory_name("18 +");
                    categoriesArrayList.remove(i);
                }*/

            }
            //categoriesList = categoriesArrayList;
            setCategoriesAdapter(categoriesArrayList);
        } else {
            Toast.makeText(this, "No category found.", Toast.LENGTH_SHORT).show();
        }
    }

    private ArrayList<String> categoriesArrayList;

    private void setCategoriesAdapter(ArrayList<CategoriesResponse.Categories> catgoriesArrayList) {
        categoriesArrayList = new ArrayList<>();
        categoriesArrayList.add("Select category");
        for (int i = 0; i < catgoriesArrayList.size(); i++) {
            categoriesArrayList.add(catgoriesArrayList.get(i).getCategory_name());
        }

        ArrayAdapter<String>
                spinnerArrayAdapter = new ArrayAdapter<String>(this,
                R.layout.spinner_item, categoriesArrayList) {
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {


                    tv.setTextColor(Color.BLACK);
                } else {
                    tv.setTextColor(Color.GRAY);

                }

                return view;

            }

            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                } else {
                    return true;
                }
            }
        };

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        catgorieSpinner.setAdapter(spinnerArrayAdapter);
    }

    @Override
    protected void setAdapter(RecyclerView recyclerView, List<?> mList) {
        super.setAdapter(recyclerView, mList);


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        if (position == 0) {
            return;
        }

        categoryName = categoriesArrayList.get(position);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
