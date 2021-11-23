package com.unicaldas.golotodo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private CheckBox chb_terminos;
    private TextView tev_terminos;
    private Button btn_registerFinish;
    private final int ACTIVIDAD_TERMINOS=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        chb_terminos = findViewById(R.id.chb_terminos);
        btn_registerFinish = findViewById(R.id.registerFinish);
        tev_terminos = findViewById(R.id.tev_terminos);

        //registerFinish.setEnabled(false);


        btn_registerFinish.setOnClickListener(this);
        tev_terminos.setOnClickListener(this);

        chb_terminos.setEnabled(false);

        chb_terminos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                btn_registerFinish.setEnabled(isChecked);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registerFinish:
                Toast.makeText(this, getString(R.string.txt_registerAction), Toast.LENGTH_SHORT).show();
                Intent intent_register= new Intent(this,LOGIN.class);
                startActivity(intent_register);
                break;

            case R.id.tev_terminos:
                Toast.makeText(this, getString(R.string.txt_registerAction), Toast.LENGTH_SHORT).show();
                Intent intent_terminos= new Intent(this,TerminosActivity.class);
                //startActivity(intent_terminos);
                startActivityForResult(intent_terminos,ACTIVIDAD_TERMINOS);
                break;




        }



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTIVIDAD_TERMINOS) {
            if(resultCode== Activity.RESULT_OK){
                chb_terminos.setChecked(true);

            }
            else {
                chb_terminos.setChecked(false);
            }
        }
    }
}