package com.example.olivia.myapplication.model;

import java.io.Serializable;
import com.google.android.gms.maps.model.LatLng;


import static com.example.olivia.myapplication.controller.R.id.condition;

/**
 * Created by Shuopeng Zhou on 3/1/2017.
 * Create a new report contains time, reportNumber,location, virusPPM and combinationPPM
 */

public class Report implements Serializable {
    private String time;
    private int reportNumber;
    private String location;
    private double virusPPM;
    private double contaminatePPM;
    private String creator;
    private String date;
    private String condition;
    private LatLng reportLatLng;


    public Report(String time, String location, LatLng reportLatLng, double vPPM, double cPPM, String condition,
                  int reportNum, String date) {
        this.time = time;
        this.location = location;
        virusPPM = vPPM;
        contaminatePPM = cPPM;
        this.condition = condition;
        reportNumber = reportNum;
        creator = User.getCurrentUser().toString();
        this.date = date;
        this.reportLatLng = reportLatLng;

    }

    public String toString() {
        return reportNumber + ", " + creator + ", " + location + ", " + date;
    }
    public String showMap() {
        return "No." + reportNumber + ", Condition " + condition + ", Virus " + virusPPM + ", Contamination " + contaminatePPM;
    }
    public String getTime() {
        return time;
    }
    public String getLocation() {
        return location;
    }
    public double getVirusPPM() {
        return virusPPM;
    }
    public double getCombinationPPM() {
        return contaminatePPM;
    }
    public int getReportNumber() {
        return reportNumber;
    }
    public String getCreator() {
        return creator;
    }
    public String getDate() {
        return date;
    }
    public String getCondition() {
        return condition;
    }
    public LatLng getLatLng() { return reportLatLng;}

}
