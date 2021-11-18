package com.unicaldas.golotodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private CheckBox checkbox;
    private Button registerFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        checkbox = findViewById(R.id.checkbox);
        registerFinish = findViewById(R.id.registerFinish);

        registerFinish.setEnabled(false);

        checkbox.setOnClickListener(this);
        registerFinish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registerFinish:
                Toast.makeText(this, getString(R.string.txt_registerAction), Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkbox:
                if(checkbox.isChecked()){
                    registerFinish.setEnabled(true);
                    Toast.makeText(this, getString(R.string.txt_Enable), Toast.LENGTH_SHORT).show();
                }else{
                    registerFinish.setEnabled(false);
                    Toast.makeText(this, getString(R.string.txt_Disable), Toast.LENGTH_SHORT).show();
                }
                break;

        }

    }
}