package com.example.myapplication;
import android.widget.Toast;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText etName = findViewById(R.id.et1);
        final EditText etLocation = findViewById(R.id.et2);
        final EditText etPhone = findViewById(R.id.et3);
        final EditText etDescription = findViewById(R.id.et4);
        final EditText etRating = findViewById(R.id.et5);
        Button submitBtn = findViewById(R.id.submitBtn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("RestaurantDetails", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                String uniqueKey = "Restaurant_" + System.currentTimeMillis();
                editor.putString(uniqueKey + "_name", etName.getText().toString());
                editor.putString(uniqueKey + "_location", etLocation.getText().toString());
                editor.putString(uniqueKey + "_phone", etPhone.getText().toString());
                editor.putString(uniqueKey + "_description", etDescription.getText().toString());
                editor.putString(uniqueKey + "_rating", etRating.getText().toString());
                editor.apply();

                Toast.makeText(Register.this, "Restaurant saved successfully!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
