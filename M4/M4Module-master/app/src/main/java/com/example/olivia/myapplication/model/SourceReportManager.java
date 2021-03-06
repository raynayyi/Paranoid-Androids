package com.example.olivia.myapplication.model;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shuopeng Zhou on 3/13/2017.
 * This page is to store all source reports in an arraylist with all sourcereports
 *
 */

public class SourceReportManager {
    SourceReport u;// Modified by Shuopeng Zhou to enable accessing the report from ViewReportActivity

    private static List<SourceReport> reports = new ArrayList<SourceReport>();

    public void addReport(String time, String location, LatLng reportLatLng,String type,
                          String condition, int reportNum, String date) {
        reports.add(new SourceReport(time, location, reportLatLng, type, condition, reportNum, date));
    }
    public int size() {
        return reports.size();
    }

    public List<SourceReport> getList() {
        return reports;
    }
}
