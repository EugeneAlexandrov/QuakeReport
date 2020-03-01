package com.example.android.quakereport;

class Earthquake {
    private long mDate;
    private double mMag;
    private String mCity;

    public Earthquake(long Date, double Mag, String City) {
        mDate = Date;
        mMag = Mag;
        mCity = City;
    }

    public long getmDate() {
        return mDate;
    }

    public void setmDate(long Date) {
        mDate = Date;
    }

    public double getmMag() {
        return mMag;
    }

    public void setmMag(float Mag) {
        mMag = Mag;
    }

    public String getmCity() {
        return mCity;
    }

    public void setmCity(String City) {
        mCity = City;
    }
}
