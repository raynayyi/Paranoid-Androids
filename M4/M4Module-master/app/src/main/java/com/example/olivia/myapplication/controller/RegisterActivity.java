package com.example.olivia.myapplication.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.olivia.myapplication.model.User;
import com.example.olivia.myapplication.model.UserManager;
import com.example.olivia.myapplication.model.userType;

import static com.example.olivia.myapplication.controller.R.styleable.Spinner;
//import com.example.olivia.myapplication.model.User;
//import com.example.olivia.myapplication.model.UserManager;

//import static com.example.olivia.myapplication.model.UserManager.*;

/**
 * A register page that lets new users register for a new account with a username and a password
 * Cancel button will take you bacck to the welcome page.
 *
 *
 */
public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final UserManager manager = new UserManager();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final Spinner etSpinner = (Spinner) findViewById(R.id.userTypeSpinner);
        final ArrayAdapter<String> adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, userType.values());
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        etSpinner.setAdapter(adapter2);

        final EditText etId = (EditText) findViewById(R.id.etId);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etAddress = (EditText) findViewById(R.id.etAddress);

        final Button registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String id = etId.getText().toString();
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();
                final String email = etEmail.getText().toString();
                final String address = etAddress.getText().toString();
                final String userType = etSpinner.getSelectedItem().toString();
                User newUser = new User(id, username, password, email, address, userType);
                manager.addUser(id, username, password, email, address, userType);
                startActivity(new Intent(getApplicationContext(), WelcomeScreen.class));
            }
        });

        final Button cancelButton = (Button) findViewById(R.id.cancelButton);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),WelcomeScreen.class));
            }
        });
    }
}
