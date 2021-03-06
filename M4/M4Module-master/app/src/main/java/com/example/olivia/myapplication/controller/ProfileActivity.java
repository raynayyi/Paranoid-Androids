package com.example.olivia.myapplication.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.olivia.myapplication.model.User;
import com.example.olivia.myapplication.model.UserManager;

/**
 * Profile Screen which user can access after logged in
 * Edit Button enable you to edit your profile information
 * Cancel Button takes you back to dummyApp page
 * @author Ran Yi
 * @version 1.0
 */

public class ProfileActivity extends AppCompatActivity {
    private EditText name;
    private EditText email;
    private EditText homeAddress;
    private EditText password;
    private static User user;
    private Button cancelButton;
    private UserManager manager = new UserManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try {
            super.onCreate(savedInstanceState);
            {
                Bundle extras = getIntent().getExtras();
                if (extras != null) {
                    User user2 = (User) getIntent().getSerializableExtra("user"); //Obtaining data
                    if (user2 != null) {
                        user = user2;
                    }
                }
            }

            setContentView(R.layout.activity_profile_screen);
            name = (EditText) findViewById(R.id.name);
            name.setText(user.getName(), TextView.BufferType.EDITABLE);

            email = (EditText) findViewById(R.id.EmailAddress);
            email.setText(user.getEmail(), TextView.BufferType.EDITABLE);

            homeAddress = (EditText) findViewById(R.id.HomeAddress);
            homeAddress.setText(user.getAddress(), TextView.BufferType.EDITABLE);

            password = (EditText) findViewById(R.id.password);
            password.setText(user.getPassword(), TextView.BufferType.EDITABLE);

            cancelButton = (Button) findViewById(R.id.Cancel);
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    intent.putExtra("user",user);
                    startActivity(intent);
                    //startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            });

            final Button editButton = (Button) findViewById(R.id.edit);
            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String username = name.getText().toString();
                    final String userpassword = password.getText().toString();
                    final String useremail = email.getText().toString();
                    final String useraddress = homeAddress.getText().toString();
                    manager.deleteUser(user.getId());
                    user = new User(user.getId(), username, userpassword, useremail, useraddress, user.getUserType());
                    manager.addUser(user.getId(), username, userpassword, useremail, useraddress, user.getUserType());
                    User.setCurrentUser(user);
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    intent.putExtra("user",user);
                    startActivity(intent);
                    //startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            });

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}