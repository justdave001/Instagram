package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActive";
    public EditText etUsername;
    public EditText etPassword;
    public AppCompatButton loginBtn;
    public Button signupBtn;
    ImageView loginImage;
    ParseUser currentUser = ParseUser.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (currentUser!=null){
            goMainActivity();
        }
        loginImage  = findViewById(R.id.loginImage);

        etUsername=findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        loginBtn  = findViewById(R.id.loginBtn);
        loginBtn  = findViewById(R.id.loginBtn);

        Glide.with(this).load(R.drawable.nav_logo_whiteout).into(loginImage);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                loginUser(username,password);
            }
        });



    }

    private void loginUser(String username, String password) {
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e!=null){
                    Toast.makeText(LoginActivity.this,"Invalid Login crednetials",Toast.LENGTH_SHORT).show();
                    Log.e("LoginActivity","issue with login",e);
                        return;
                    }
                goMainActivity();
                Toast.makeText(LoginActivity.this,"successful login!", Toast.LENGTH_SHORT).show();
        }
        });
    }

    private void goMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}