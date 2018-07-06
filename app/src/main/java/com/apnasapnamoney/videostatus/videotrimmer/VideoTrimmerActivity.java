package com.apnasapnamoney.videostatus.videotrimmer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.apnasapnamoney.videostatus.R;
import com.apnasapnamoney.videostatus.views.BaseActivity;

import java.io.File;

import life.knowledge4.videotrimmer.K4LVideoTrimmer;
import life.knowledge4.videotrimmer.interfaces.OnK4LVideoListener;
import life.knowledge4.videotrimmer.interfaces.OnTrimVideoListener;


public class VideoTrimmerActivity extends BaseActivity implements OnTrimVideoListener, OnK4LVideoListener,
        View.OnClickListener {

    private K4LVideoTrimmer mVideoTrimmer/*, mImageTrimmer*/;
    private TextView backBtn;
    private ProgressDialog mProgressDialog;
    String path = "";
    private Uri originalUri;
    private TextView trimTextView;
    private TextView coverTextView;
    private View trimLine;
    private View coverLine;
    private TextView done_crop_txt;
    public ImageView mPlayView;


    @Override
    public int getLayoutId() {
        return R.layout.activity_video_trimmer;
    }

    @Override
    public void initViews() {
        backBtn = (TextView) findViewById(R.id.backBtn);
        mPlayView = ((ImageView) findViewById(R.id.icon_video_play));
        mVideoTrimmer = ((K4LVideoTrimmer) findViewById(R.id.videoTimeLine));
        //mImageTrimmer = ((K4LVideoTrimmer) findViewById(R.id.imageTimeLine));
        done_crop_txt = (TextView) findViewById(R.id.done_crop_txt);
        done_crop_txt.setOnClickListener(this);
        trimTextView = (TextView) findViewById(R.id.trim_textview);
        coverTextView = (TextView) findViewById(R.id.cover_textview);
        trimTextView.setOnClickListener(this);
        coverTextView.setOnClickListener(this);
        trimLine = (View) findViewById(R.id.trim_view);
        coverLine = (View) findViewById(R.id.cover_view);

        Intent extraIntent = getIntent();
        if (extraIntent != null) {
            path = extraIntent.getStringExtra("REAL_VIDEO_PATH");
        }
        //setting progressbar
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("Trimming Video..");

        backBtn.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           finish();
                                       }
                                   }
        );

        originalUri = Uri.parse(path);

        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(path);
        String time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
        long timeInmillisec = Long.parseLong( time );
        int duration = (int) (timeInmillisec / 1000);
        duration++;
        if (mVideoTrimmer != null) {
            mVideoTrimmer.setPlayView(mPlayView);
            mVideoTrimmer.setMaxDuration(29);
            mVideoTrimmer.setOnTrimVideoListener(this);
            mVideoTrimmer.setOnK4LVideoListener(this);
            File rootPath = Environment.getExternalStorageDirectory();
            mVideoTrimmer.setDestinationPath(rootPath + "/Android/data/com.bidjones.android/Videos/");
            mVideoTrimmer.setVideoURI(originalUri);
            mVideoTrimmer.setVideoInformationVisibility(true);
        }
       /* if (mImageTrimmer != null) {
            mImageTrimmer.setPlayView(mPlayView);
            mImageTrimmer.setMaxDuration(duration);
            mImageTrimmer.setOnTrimVideoListener(this);
            mImageTrimmer.setOnK4LVideoListener(this);
            File rootPath = Environment.getExternalStorageDirectory();
            mImageTrimmer.setDestinationPath(rootPath + "/Android/data/com.Android.Ten/Videos/");
            originalUri = Uri.parse(path);
            mImageTrimmer.setVideoURI(originalUri);
            mImageTrimmer.setVideoInformationVisibility(false);
            mImageTrimmer.setRangeBarVisibility(true);
            //mImageTrimmer.setScrollIndicators();
        }*/

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onTrimStarted() {
        mProgressDialog.show();
    }

    @Override
    public void getResult(final Uri uri, final int currentPosition) {
        mProgressDialog.cancel();

       /* runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (uri != null) {
                    Toast.makeText(VideoTrimmerActivity.this, currentPosition + "==video path -" + uri.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });*/
      /*  MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(VideoTrimmerActivity.this, originalUri);
        Bitmap bitmap = mediaMetadataRetriever.getFrameAtTime(currentPosition, MediaMetadataRetriever.OPTION_CLOSEST_SYNC);
        Log.d("","===>"+bitmap);*/
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setDataAndType(uri, "video/mp4");
        intent.putExtra("position", currentPosition);
        //startActivity(intent);
        setResult(RESULT_OK, intent);
        finish();
    }


    @Override
    public void cancelAction() {
        mProgressDialog.cancel();
        mVideoTrimmer.destroy();
        finish();
    }

    @Override
    public void onError(final String message) {
        mProgressDialog.cancel();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(VideoTrimmerActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onVideoPrepared() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //  Toast.makeText(VideoTrimmerActivity.this, "onVideoPrepared", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.trim_textview:
                mVideoTrimmer.setVideoTrimView();
                mVideoTrimmer.setVideoInformationVisibility(true);
                //mVideoTrimmer.setVisibility(View.VISIBLE);
                //mImageTrimmer.setVisibility(View.GONE);
                trimLine.setBackgroundColor(ContextCompat.getColor(VideoTrimmerActivity.this, R.color.gray));
                coverLine.setBackgroundColor(ContextCompat.getColor(VideoTrimmerActivity.this, R.color.gray));
                trimTextView.setTextColor(ContextCompat.getColor(VideoTrimmerActivity.this, android.R.color.black));
                coverTextView.setTextColor(ContextCompat.getColor(VideoTrimmerActivity.this, R.color.gray));

                break;
            case R.id.cover_textview:
                /*mImageTrimmer.setCoverTrimView();
                mImageTrimmer.setVisibility(View.VISIBLE);
                mVideoTrimmer.setVisibility(View.GONE);*/
                mVideoTrimmer.setCoverTrimView();
                mVideoTrimmer.setVideoInformationVisibility(false);
                trimLine.setBackgroundColor(ContextCompat.getColor(VideoTrimmerActivity.this, R.color.gray));
                coverLine.setBackgroundColor(ContextCompat.getColor(VideoTrimmerActivity.this, R.color.gray));
                trimTextView.setTextColor(ContextCompat.getColor(VideoTrimmerActivity.this, R.color.gray));
                coverTextView.setTextColor(ContextCompat.getColor(VideoTrimmerActivity.this, android.R.color.black));

                break;
            case R.id.done_crop_txt:
                //Utilities.showlongToast(CameraUploadActivity.this, "Video length should be between 2-10 seconds");
                mVideoTrimmer.onSaveClicked(this);
                break;
        }
    }

}
