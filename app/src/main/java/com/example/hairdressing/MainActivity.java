// MainActivity.java
package com.example.hairdressing;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> selectedServices = new ArrayList<>();
    private ArrayList<String> selectedPrices = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupServiceBox(R.id.box_hair, R.id.text_hair);
        setupServiceBox(R.id.box_nails, R.id.text_nails);
        setupServiceBox(R.id.box_shaving, R.id.text_shaving);
        setupServiceBox(R.id.box_make_up, R.id.text_make_up);
        setupServiceBox(R.id.box_solar, R.id.text_solar);
        setupServiceBox(R.id.box_wash, R.id.text_wash);

        LinearLayout cartButton = findViewById(R.id.cart_button);
        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MiscActivity.class);
                intent.putStringArrayListExtra("services", selectedServices);
                intent.putStringArrayListExtra("prices", selectedPrices);
                startActivity(intent);
            }
        });
    }

    private void setupServiceBox(int boxId, int textId) {
        final LinearLayout box = findViewById(boxId);
        final TextView text = findViewById(textId);

        box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable background = box.getBackground();
                if (background.getConstantState().equals(getResources().getDrawable(R.drawable.box_background).getConstantState())) {
                    box.setBackgroundResource(R.drawable.box_background_selected);
                    selectedServices.add(text.getText().toString().split(" - ")[0]);
                    selectedPrices.add(text.getText().toString().split(" - ")[1]);
                } else {
                    box.setBackgroundResource(R.drawable.box_background);
                    selectedServices.remove(text.getText().toString().split(" - ")[0]);
                    selectedPrices.remove(text.getText().toString().split(" - ")[1]);
                }
            }
        });
    }
}
