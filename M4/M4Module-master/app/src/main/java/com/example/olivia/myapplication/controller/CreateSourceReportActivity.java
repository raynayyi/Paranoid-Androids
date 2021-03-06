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
import com.example.olivia.myapplication.model.WaterCondition;
import com.example.olivia.myapplication.model.waterQuality;
import com.example.olivia.myapplication.model.waterType;
import com.google.android.gms.maps.model.LatLng;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * This page allows you to create a new Source Report.
 * If you click the create button, it saves the source report
 * to the ShowSourceReport Activity. If you click cancel, the
 * report is not saved and the app returns to the
 * Main page.
 */
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
        //This is a SourceReportManager object that will store the new Source report
        final SourceReportManager manager = new SourceReportManager();

        //Initializes water conditions spinner
        final Spinner etSpinner = (Spinner) findViewById(R.id.etConditionSpinner_source);
        final ArrayAdapter<String> adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, WaterCondition.values());
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        etSpinner.setAdapter(adapter2);

        //Initializes water type spinner
        final Spinner etSpinner2 = (Spinner) findViewById(R.id.etTypeSpinner_source);
        final ArrayAdapter<String> adapter3 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, waterType.values());
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        etSpinner2.setAdapter(adapter3);

        final Button mapButton = (Button) findViewById(R.id.location_button_source);
        final TextView etLocation = (TextView) findViewById(R.id.addressTV_source);
        etLocation.setText(address);
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
                if (/*time.isEmpty() || */location.isEmpty() || type.contains("SELECT") || condition.contains("SELECT") ) {
                    AlertDialog.Builder myAlert = new AlertDialog.Builder(CreateSourceReportActivity.this);
                    myAlert.setMessage("water type and water condition are required")
                            .setPositiveButton("Back", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .create();
                    myAlert.show();
                } else if (etLocation.getText().toString().equals("Address")) {
                    AlertDialog.Builder myAlert = new AlertDialog.Builder(CreateSourceReportActivity.this);
                    myAlert.setMessage("Click LOCATION button and set a location")
                            .setPositiveButton("Back", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .create();
                    myAlert.show();
                }
                else {
                    manager.addReport(time, address1, reportLatLng1, type, condition,
                            manager.size() + 1, todayDate);
                    Intent intent = new Intent(CreateSourceReportActivity.this, MainActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                    finish();
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
