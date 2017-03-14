package com.example.olivia.myapplication.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.olivia.myapplication.model.SourceReportManager;
import com.example.olivia.myapplication.model.User;
import com.example.olivia.myapplication.model.waterQuality;
import com.example.olivia.myapplication.model.waterType;
import com.google.android.gms.maps.model.LatLng;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CreateSourceReportActivity extends AppCompatActivity {
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_create_source_report);
        user = (User) getIntent().getSerializableExtra("user"); //Obtaining data
        String address = "Address";
        LatLng reportLatLng = new LatLng(-33.852, 151.211);;
        try {
            Bundle extras = getIntent().getExtras();

            String ifNull= extras.getString("address");
            if (ifNull.length() != 0) {
                address = ifNull;
            }
            Double latitude = extras.getDouble("latitude");
            Double longitude = extras.getDouble("longitude");
            reportLatLng = new LatLng(latitude, longitude);

        } catch (Exception e) {
            System.out.println(e);
        }
        final String address1 = address;
        final LatLng reportLatLng1 = reportLatLng;
        //This is a ReportManager object that will store the new report
        final SourceReportManager manager = new SourceReportManager();

        //Initializes water conditions spinner
        final Spinner etSpinner = (Spinner) findViewById(R.id.etConditionSpinner_source);
        final ArrayAdapter<String> adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, waterQuality.values());
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        etSpinner.setAdapter(adapter2);

        final Spinner etSpinner2 = (Spinner) findViewById(R.id.etTypeSpinner_source);
        final ArrayAdapter<String> adapter3 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, waterType.values());
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        etSpinner2.setAdapter(adapter3);

        final Button mapButton = (Button) findViewById(R.id.location_button_source);
        //final EditText etTime = (EditText) findViewById(R.id.etTime);
        final TextView etLocation = (TextView) findViewById(R.id.addressTV_source);
        etLocation.setText(address);
        final EditText etVirusPPM = (EditText) findViewById(R.id.etVirusPPM_source);
        final EditText etContaminatePPM = (EditText) findViewById(R.id.etContaminatePPM_source);
        final Button registerButton = (Button) findViewById(R.id.createButton_source);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Gets current time and date
                Calendar c = new GregorianCalendar();
                SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
                SimpleDateFormat todayFormat = new SimpleDateFormat("MMMM dd");
                final String todayDate = "" + todayFormat.format(c.getTime()).toString();

                //Gets information from textboxes
                final String time = "" + timeFormat.format(c.getTime()).toString();
                final String location = "ad";
                final String type = etSpinner2.getSelectedItem().toString();
                final String condition = etSpinner.getSelectedItem().toString();
                //Checks to see if there is a missing input
                if (/*time.isEmpty() || */location.isEmpty() ) {
                    AlertDialog.Builder myAlert = new AlertDialog.Builder(CreateSourceReportActivity.this);
                    myAlert.setMessage("Time,location,virusPPM and comninationPPM required")
                            .setPositiveButton("Back", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .create();
                    myAlert.show();
                } else {
                    manager.addReport(time, address1, reportLatLng1, type, condition,
                            manager.size() + 1, todayDate);
                    Intent intent = new Intent(CreateSourceReportActivity.this, MainActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                    finish();
                    //startActivity(new Intent(getApplicationContext(), MainActivity.class)); John
                    //finish();
                }
            }
        });
        //cancel button that takes a user back to the welcome screen
        final Button cancelButton = (Button) findViewById(R.id.cancelButton_source);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateSourceReportActivity.this, MainActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        });

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateSourceReportActivity.this, PickSourceReportLocationActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        });
    }
}