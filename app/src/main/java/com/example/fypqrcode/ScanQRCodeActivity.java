package com.example.fypqrcode;

import com.journeyapps.barcodescanner.CaptureActivity;

public class ScanQRCodeActivity extends CaptureActivity {

    @Override
    public void onBackPressed() {
        // Add your code here to handle the back button press
        // For example, you can finish the activity to return back to the previous screen
        finish();
    }
}

