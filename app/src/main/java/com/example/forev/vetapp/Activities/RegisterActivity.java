package com.example.forev.vetapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.forev.vetapp.Models.RegisterPojo;
import com.example.forev.vetapp.R;
import com.example.forev.vetapp.RestApi.ManagerAll;
import com.example.forev.vetapp.Utils.Warning;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private Button registerButton;
    private EditText registerEmail, registerUsername, registerPassword;
    private TextView registerText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        defineLayout();
        registerToUser();
        toLoginActivity();
    }

    public void defineLayout(){
        registerButton = (Button)findViewById(R.id.registerButton);
        registerEmail = (EditText)findViewById(R.id.registerEmail);
        registerUsername = (EditText) findViewById(R.id.registerUsername);
        registerPassword = (EditText) findViewById(R.id.registerPassword);
        registerText = (TextView) findViewById(R.id.registerText);
    }

    public void registerToUser(){
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = registerEmail.getText().toString();
                String userName = registerUsername.getText().toString();
                String password = registerPassword.getText().toString();
                register(email,userName,password);
                delete();
            }
        });
    }

    public void toLoginActivity(){
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void delete()
    {
        registerEmail.setText("");
        registerUsername.setText("");
        registerPassword.setText("");
    }

    public void register(String userEmail, String userName, String userPass){
        Call<RegisterPojo> req = ManagerAll.getInstance().addUser(userEmail,userName,userPass);
        req.enqueue(new Callback<RegisterPojo>() {
            @Override
            public void onResponse(Call<RegisterPojo> call, Response<RegisterPojo> response) {

                if(response.body().isTf())
                {
                    Toast.makeText(getApplicationContext(),response.body().getText(),Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();

                }else{
                    Toast.makeText(getApplicationContext(),response.body().getText(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterPojo> call, Throwable t) {
                Toast.makeText(getApplicationContext(),Warning.internetProblemText,Toast.LENGTH_LONG).show();
            }
        });
    }
}
