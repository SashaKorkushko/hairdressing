// ResultActivity.java
package com.example.hairdressing;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ArrayList<String> selectedServices = getIntent().getStringArrayListExtra("services");
        ArrayList<String> selectedPrices = getIntent().getStringArrayListExtra("prices");
        String selectedTime = getIntent().getStringExtra("selectedTime");
        String selectedMaster = getIntent().getStringExtra("selectedMaster");

        TextView totalPriceTextView = findViewById(R.id.total_price);
        TextView selectedMasterTextView = findViewById(R.id.selected_master);
        TextView selectedTimeTextView = findViewById(R.id.selected_time);

        int totalPrice = 0;
        for (String price : selectedPrices) {
            totalPrice += Integer.parseInt(price.replace("$", ""));
        }

        // Создаем SpannableString для покраски текста
        Spannable totalPriceSpannable = new SpannableString("The total price of services becomes:\n" + totalPrice + "$");
        Spannable masterSpannable = new SpannableString("Master:\n" + selectedMaster);
        Spannable timeSpannable = new SpannableString("Time:\n" + selectedTime);

        // Покраска текста в красный цвет
        int redColor = getResources().getColor(R.color.red);
        totalPriceSpannable.setSpan(new ForegroundColorSpan(redColor), 37, totalPriceSpannable.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        masterSpannable.setSpan(new ForegroundColorSpan(redColor), 8, masterSpannable.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        timeSpannable.setSpan(new ForegroundColorSpan(redColor), 6, timeSpannable.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Установка Spannable текста
        totalPriceTextView.setText(totalPriceSpannable);
        selectedMasterTextView.setText(masterSpannable);
        selectedTimeTextView.setText(timeSpannable);

        Button closeAppButton = findViewById(R.id.close_app_button);
        closeAppButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity(); // Закрыть приложение
            }
        });

        Button homeScreenButton = findViewById(R.id.home_screen_button);
        homeScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
    }
}
