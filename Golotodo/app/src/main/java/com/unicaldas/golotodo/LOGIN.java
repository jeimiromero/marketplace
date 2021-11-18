package com.unicaldas.golotodo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LOGIN extends AppCompatActivity implements View.OnClickListener {

    private Button btn_register;
    private Button btn_login;
    private EditText edt_username;
    private EditText edt_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_register = findViewById(R.id.btn_register);
        btn_login = findViewById(R.id.btn_login);
        edt_username = findViewById(R.id.edt_username);
        edt_password = findViewById(R.id.edt_password);

        btn_register.setOnClickListener(this);
        btn_login.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                String email = edt_username.getText().toString();
                String password = edt_password.getText().toString();
                if(email.equals("golotodo@email.com") && password.equals("grupo2")){
                    Toast.makeText(this,getString(R.string.txt_LoginAction), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this,DrawerActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(this,getString(R.string.txt_LoginError), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_register:
                Toast.makeText(this, getString(R.string.txt_registerWill), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,Register.class);
                startActivity(intent);
                break;
        }
    }
}