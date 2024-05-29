// MiscActivity.java
package com.example.hairdressing;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class MiscActivity extends AppCompatActivity {

    private ArrayList<String> selectedServices;
    private ArrayList<String> selectedPrices;
    private LinearLayout selectedMasterBox = null;
    private Button selectedTimeButton = null;
    private String selectedTime = "";
    private String selectedMaster = "";
    private int defaultColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_misc);

        selectedServices = getIntent().getStringArrayListExtra("services");
        selectedPrices = getIntent().getStringArrayListExtra("prices");

        setupMasterBox(R.id.master_box_tatiana, "Tatiana");
        setupMasterBox(R.id.master_box_maria, "Maria");
        setupMasterBox(R.id.master_box_irina, "Irina");

        setupTimeButton(R.id.button_11);
        setupTimeButton(R.id.button_01);
        setupTimeButton(R.id.button_02);
        setupTimeButton(R.id.button_03);
        setupTimeButton(R.id.button_05);
        setupTimeButton(R.id.button_06);

        defaultColor = ContextCompat.getColor(this, R.color.purple_500);

        Button confirmButton = findViewById(R.id.confirm_button);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MiscActivity.this, ResultActivity.class);
                intent.putStringArrayListExtra("services", selectedServices);
                intent.putStringArrayListExtra("prices", selectedPrices);
                intent.putExtra("selectedTime", selectedTime);
                intent.putExtra("selectedMaster", selectedMaster);
                startActivity(intent);
            }
        });
    }

    private void setupMasterBox(int boxId, final String masterName) {
        final LinearLayout box = findViewById(boxId);
        box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedMasterBox != null) {
                    selectedMasterBox.setBackgroundColor(defaultColor);
                }
                box.setBackgroundResource(R.drawable.box_background_selected);
                selectedMasterBox = box;
                selectedMaster = masterName;
            }
        });
    }

    private void setupTimeButton(int buttonId) {
        final Button button = findViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedTimeButton != null) {
                    selectedTimeButton.setBackgroundTintList(getResources().getColorStateList(R.color.white));
                }
                selectedTimeButton = button;
                selectedTime = button.getText().toString();
            }
        });
    }
}
