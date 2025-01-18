package com.example.qrgenerator;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText text;
    ImageView QRCode, share;
    Button generate;
    Bitmap generatedQR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.textET);
        QRCode = findViewById(R.id.QRCode);
        generate = findViewById(R.id.generateBtn);
        share = findViewById(R.id.shareBtn);

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = text.getText().toString().trim();
                if (!str.isEmpty()) {
                    MultiFormatWriter mWriter = new MultiFormatWriter();
                    try {
                        BitMatrix bitMatrix = mWriter.encode(str, BarcodeFormat.QR_CODE, 350, 350);
                        BarcodeEncoder bEncoder = new BarcodeEncoder();
                        generatedQR = bEncoder.createBitmap(bitMatrix); // Save the QR code as a bitmap
                        QRCode.setImageBitmap(generatedQR);

                        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        manager.hideSoftInputFromWindow(text.getApplicationWindowToken(), 0);
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Enter text to generate QR code", Toast.LENGTH_SHORT).show();
                }
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (generatedQR != null) {
                    shareQRCode();
                } else {
                    Toast.makeText(MainActivity.this, "Generate a QR code first", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void shareQRCode() {
        try {
            // Save the QR Code Bitmap to a file
            File cachePath = new File(getCacheDir(), "images");
            cachePath.mkdirs(); // Create the directory if it doesn't exist
            File file = new File(cachePath, "shared_qr_code.png");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            generatedQR.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.close();

            // Get the file's URI using FileProvider
            Uri qrCodeUri = FileProvider.getUriForFile(this, "com.example.qrgenerator.fileprovider", file);

            // Share the file using an Intent
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.setType("image/*");
            shareIntent.putExtra(Intent.EXTRA_STREAM, qrCodeUri);
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(Intent.createChooser(shareIntent, "Share QR Code via"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
