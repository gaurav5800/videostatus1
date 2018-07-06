package com.apnasapnamoney.videostatus.views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.apnasapnamoney.videostatus.R;
import com.apnasapnamoney.videostatus.api.CategoryApi;
import com.apnasapnamoney.videostatus.api.UploadVideoApi;
import com.apnasapnamoney.videostatus.imagechooser.api.ChooserType;
import com.apnasapnamoney.videostatus.model.CategoriesResponse;
import com.apnasapnamoney.videostatus.utils.FileUtils;
import com.apnasapnamoney.videostatus.utils.Utilities;
import com.apnasapnamoney.videostatus.videotrimmer.VideoTrimmerActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UploadVideoActivity extends BaseActivity implements CategoryApi.CategoriesInterface, AdapterView.OnItemSelectedListener, View.OnClickListener {

    private static final int TRIM_VIDEO_CODE = 523;
    private AppCompatSpinner catgorieSpinner;
    ArrayList<CategoriesResponse.Categories> categoriesList;
    private String categoryName;
    private ImageView mImagView;
    private Uri originalVideoUri;
    private File file;
    private EditText mVideoTitleEditText,mEmailEditText,mNameEditText;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_upload_video;
    }

    @Override
    protected void initViews() {

        categoriesList = new ArrayList<>();

        catgorieSpinner = (AppCompatSpinner) findViewById(R.id.spinner_category);
        catgorieSpinner.setOnItemSelectedListener(this);
        mImagView = findViewById(R.id.image_view_video_title);
        mImagView.setOnClickListener(this);
        findViewById(R.id.btn_add_group).setOnClickListener(this);

        findViewById(R.id.image_view_back_arrow).setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_add_group:
                HashMap<String,String> videoHashMap=new HashMap<>();
                videoHashMap.put("email","gaurav@gmail.com");
                videoHashMap.put("category",categoryName);
                videoHashMap.put("name","cc");
                videoHashMap.put("title","video");
                UploadVideoApi uploadVideoApi = new UploadVideoApi(this);
                uploadVideoApi.uploadFile(videoHashMap,categoryName,file);

                break;
            case R.id.image_view_back_arrow:
                finish();
                break;
            case R.id.image_view_video_title:
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("video/*");
                startActivityForResult(intent, ChooserType.REQUEST_PICK_VIDEO);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (data.getData() != null && requestCode == ChooserType.REQUEST_PICK_VIDEO) {
                originalVideoUri = data.getData();
                String realPath = FileUtils.getPath(this, data.getData());
                Toast.makeText(UploadVideoActivity.this, "" + realPath, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, VideoTrimmerActivity.class);
                intent.putExtra("REAL_VIDEO_PATH", realPath);
                startActivityForResult(intent, TRIM_VIDEO_CODE);


            } else if (requestCode == TRIM_VIDEO_CODE) {

                setVideoImage(data);
            }


        }
    }

    private void setVideoImage(Intent data) {

        int cc = data.getIntExtra("position", 0);

        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(this, originalVideoUri);
        long currentPosition = cc;
        if (currentPosition < 100) {
            currentPosition = currentPosition * 1000000;
        } else if (currentPosition < 100000) {
            currentPosition = currentPosition * 1000;
        }
        Bitmap bitmap = mediaMetadataRetriever.getFrameAtTime(currentPosition, MediaMetadataRetriever.OPTION_CLOSEST_SYNC);
        //Bitmap bitmap1 = mediaMetadataRetriever.getFrameAtTime(2000, MediaMetadataRetriever.OPTION_CLOSEST_SYNC);

        //var path=FileUtils.getPath(baseActivity,croppedVideoUri)
          file = new File(data.getData().getPath());

        mImagView.setImageBitmap(bitmap);
    }
}
