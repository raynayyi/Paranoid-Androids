package com.example.olivia.myapplication.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.olivia.myapplication.model.Report;
import com.example.olivia.myapplication.model.ReportManager;
import com.example.olivia.myapplication.model.User;

import java.util.List;


public class ViewReportActivity extends AppCompatActivity {
    private Button createButton, cancelButton;

    private ReportManager manager = new ReportManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_layout);
        final User user = (User) getIntent().getSerializableExtra("user");
        //This is a sample report
        //final PurityReport report1 = new PurityReport("Feb17", "8:00", 1234, "John Smith", "GA", "BAD", 300);

        //The reports need to be added to an array to be shown
        final List<Report> reports = manager.getList();


        ListAdapter adapter = new ArrayAdapter<Report>(this, android.R.layout.simple_list_item_1, reports);
        final ListView reportList = (ListView) findViewById(R.id.report_list);
        createButton = (Button) findViewById(R.id.create_report);
        cancelButton = (Button) findViewById(R.id.cancel_report);
        reportList.setAdapter(adapter);
        reportList.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(ViewReportActivity.this, ShowReportActivity.class);
                        intent.putExtra("selectedReport", (Report) parent.getItemAtPosition(position));
                        Report selectedReport = (Report) reportList.getSelectedItem();
                        //intent.putExtra("selectedReport", selectedReport);
                        startActivity(intent);
                        finish();
                    }
                }
        );

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewReportActivity.this, ReportActivity.class);
                //intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewReportActivity.this, MainActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        });
    }
}
