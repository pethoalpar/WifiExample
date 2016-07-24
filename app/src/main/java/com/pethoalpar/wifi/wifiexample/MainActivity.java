package com.pethoalpar.wifi.wifiexample;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button enableButton;
    private Button dissableButton;
    private TextView ssidTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enableButton = (Button) this.findViewById(R.id.buttonEnable);
        dissableButton = (Button) this.findViewById(R.id.buttonDissable);
        ssidTextView = (TextView) this.findViewById(R.id.textView);

        final WifiManager wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);

        enableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!wifiManager.isWifiEnabled()){
                    wifiManager.setWifiEnabled(true);
                }
            }
        });

        dissableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(wifiManager.isWifiEnabled()){
                    wifiManager.setWifiEnabled(false);
                }
            }
        });

        if(wifiManager.isWifiEnabled()) {
            List<ScanResult> list = wifiManager.getScanResults();
            StringBuilder sb = new StringBuilder();
            for (ScanResult scanResult : list) {
                sb.append(scanResult.SSID + "\n");
            }
            ssidTextView.setText(sb.toString());
        }
    }
}
