package com.apnasapnamoney.videostatus.api;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.apnasapnamoney.videostatus.views.BaseActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.HashMap;

public class UploadVideoApi {

    BaseActivity baseActivity;


    public UploadVideoApi(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;


    }


    public void uploadFile(final HashMap<String, String> videoHashMap, final String categoryName, final File file1) {
        baseActivity.showDialog();
        Uri file = Uri.fromFile(file1);
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference riversRef = storageRef.child("videos/" + file.getLastPathSegment());
        final UploadTask uploadTask = riversRef.putFile(file);

// Register observers to listen for when the download is done or if it fails
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                baseActivity.showToast(exception.getMessage());
                baseActivity.hideDialog();
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                videoHashMap.put("url", taskSnapshot.getMetadata().getDownloadUrl().toString());

                uploadData(videoHashMap, categoryName);
                //baseActivity.showToast(taskSnapshot.getMetadata().getDownloadUrl().toString());
            }
        });
    }

    public void uploadData(HashMap<String, String> hashMap, String category) {

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("videos").child(category).push().setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                baseActivity.hideDialog();
                if (task.isSuccessful()) {
                    baseActivity.showToast("video Uploaded successfully");
                    baseActivity.finish();
                } else {
                    baseActivity.showToast(task.getException().getMessage());
                }
            }
        });


    }


}
