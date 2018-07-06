package com.apnasapnamoney.videostatus.api;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.apnasapnamoney.videostatus.views.BaseActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class UploadVideoApi {

    BaseActivity baseActivity;


    public UploadVideoApi(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;


    }


    public void uploadFile(File file1) {
        Uri file = Uri.fromFile(file1);
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference riversRef = storageRef.child("videos/" + file.getLastPathSegment());
        UploadTask uploadTask = riversRef.putFile(file);

// Register observers to listen for when the download is done or if it fails
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                baseActivity.showToast(exception.getMessage());
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                baseActivity.showToast(taskSnapshot.getMetadata().getDownloadUrl().toString());
            }
        });
    }

    public void uploadData(String url){




    }


}
