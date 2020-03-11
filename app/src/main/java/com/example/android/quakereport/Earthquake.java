package com.example.android.quakereport;

class Earthquake {
    private long mDate;
    private double mMag;
    private String mCity;
    private String mURL;

    public Earthquake(long Date, double Mag, String City, String url) {
        mDate = Date;
        mMag = Mag;
        mCity = City;
        mURL = url;
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

    public void setmURL(String mURL) {
        this.mURL = mURL;
    }
    public String getmURL() {
        return mURL;
    }
}
