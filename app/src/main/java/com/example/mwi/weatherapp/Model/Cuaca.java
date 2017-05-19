package com.example.mwi.weatherapp.Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mwi on 5/19/17.
 */

public class Cuaca {
    private Long help;
    private String tanggal, status;
    private int derajat;

    public Cuaca(String tanggal, String status, int derajat) {
        this.tanggal = tanggal;
        this.status = status;
        this.derajat = derajat;
    }

    public String getTanggal() {
        help = Long.parseLong(tanggal) * 1000L;
        try {
            DateFormat sdf = new SimpleDateFormat("EEE, dd MMM");
            Date netDate = (new Date(help));
            return sdf.format(netDate);
        } catch (Exception ex) {
            return "xx";
        }
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDerajat() {
        return derajat;
    }

    public void setDerajat(int derajat) {
        this.derajat = derajat;
    }
}
