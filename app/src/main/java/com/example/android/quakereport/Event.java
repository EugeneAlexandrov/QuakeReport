package com.example.android.quakereport;

class Event {
    private String mDate;
    private float mMag;
    private String mCity;

    public Event(String Date, float Mag, String City) {
        mDate = Date;
        mMag = Mag;
        mCity = City;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String Date) {
        mDate = Date;
    }

    public float getmMag() {
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
