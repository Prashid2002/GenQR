package com.example.qrgenerator;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Choose extends AppCompatActivity {

    TextView generate, scan, scanResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_choose); // Ensure the layout exists with IDs `generate`, `scan`, and `scanResult`

        generate = findViewById(R.id.generate);
        scan = findViewById(R.id.scan);
        scanResult = findViewById(R.id.scanResult);

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Choose.this, MainActivity.class);
                startActivity(intent);
            }
        });

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(Choose.this);
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                intentIntegrator.setPrompt("Scan a QR code");
                intentIntegrator.setOrientationLocked(true);
                intentIntegrator.setBeepEnabled(true);
                intentIntegrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                String scannedData = result.getContents();
                scanResult.setText(scannedData);

                // Check if the scanned data is a URL
                if (scannedData.startsWith("http://") || scannedData.startsWith("https://")) {
                    scanResult.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Open the URL in the browser
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(scannedData));
                            startActivity(browserIntent);
                        }
                    });
                } else {
                    // If scanned data is not a URL, display a toast and reset click listener
                    scanResult.setOnClickListener(null);
                    scanResult.setTextColor(getResources().getColor(android.R.color.black));
                }
            } else {
                // Handle scan cancellation
                Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
                scanResult.setText("Scan cancelled.");
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
