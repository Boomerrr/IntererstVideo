package com.example.think.videodemo.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.think.videodemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LogInActivity extends Activity implements View.OnClickListener{

    @BindView(R.id.user_name)
    EditText user_name;

    @BindView(R.id.user_password)
    EditText user_password;

    @BindView(R.id.forget_password)
    TextView forget_password;

    @BindView(R.id.sign_in)
    TextView sign_in;

    @BindView(R.id.enter)
    Button enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        ButterKnife.bind(this);

        initActivity();
    }

    private void initActivity() {

        forget_password.setOnClickListener(this);
        sign_in.setOnClickListener(this);
        enter.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.forget_password:
                forget_password_function();
                break;
            case R.id.sign_in:
                sign_in_function();
                break;
            case R.id.enter:
                enter_function();
                break;
        }
    }

    private void enter_function() {
        String userName = user_name.getText().toString();
        String userPassword = user_password.getText().toString();
        if(userName.equals("") || userPassword.equals("")){
            Toast.makeText(this,"请正确输入内容",Toast.LENGTH_SHORT).show();
        }else{

        }
    }

    private void sign_in_function() {
        startActivity(new Intent(this,SignInActivity.class));
    }

    private void forget_password_function() {
        Toast.makeText(this,"那就没办法咯",Toast.LENGTH_SHORT).show();
    }
}
