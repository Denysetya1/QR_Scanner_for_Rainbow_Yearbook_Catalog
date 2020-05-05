package com.creative.rainbow.utama;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.creative.rainbow.MainActivity;
import com.creative.rainbow.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class SmandaSem extends MainActivity {
    private CodeScanner mCodeScanner;
    private String namas;
    private String yt;
    private int a=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_layout);

        ImageView ivBgContent = findViewById(R.id.ivBgContent);
        CodeScannerView scannerView = findViewById(R.id.scannerView);
        initBackgroundImg3();

        ivBgContent.bringToFront();

        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        db.collection("smg_sma_02")
                                .get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if (task.isSuccessful()) {
                                            for (QueryDocumentSnapshot document : task.getResult()) {
                                                namas = document.getString("nama");
                                                yt = document.getString("link");
                                                String scan = result.getText();
                                                if (scan.equals(namas)){
                                                    // Parse the URI and create the intent.
                                                    Intent intent = new Intent(Intent. ACTION_VIEW);
                                                    intent.setData(Uri. parse(yt));
                                                    a = 1;
                                                    startActivity(intent);
                                                    // Find an activity to hand the intent and start that activity.
                                                    if (intent.resolveActivity(getPackageManager()) == null) {
                                                        startActivity(intent);
                                                    } else {
                                                        Log.d("ImplicitIntents", "Can't handle this intent!");
                                                    }
                                                }
                                                //Log.d(TAG, document.getId() + " => " + document.getData());
                                            }
                                            if (a == 1){
                                                String message = "Terima Kasih";
                                                showAlertDialog(message);
                                                a = 0;
                                            }else {
                                                String message = "QR Code Tidak Sesuai";
                                                showAlertDialog(message);
                                            }
                                        } else {
                                            //Log.w(TAG, "Error getting documents.", task.getException());
                                        }
                                    }
                                });
                    }
                });
            }
        });

        checkCameraPermission();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkCameraPermission();
    }

    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }

    private void checkCameraPermission(){
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        mCodeScanner.startPreview();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission,
                                                                   PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                })
                .check();
    }

    private void showAlertDialog(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setCancelable(true);

        builder.setPositiveButton(
                "SCAN LAGI",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        mCodeScanner.startPreview();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
