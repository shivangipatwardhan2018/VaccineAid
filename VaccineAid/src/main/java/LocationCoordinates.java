package main.java;

/**
 * Created by shivangipatwardhan on 11/26/16.
 */
public class LocationCoordinates {
    double Latitude;
    double Longitude;

    public LocationCoordinates(double Lat, double Long){
        Latitude = Lat;
        Longitude = Long;
    }

    public String getStringLocation(){
        return Double.toString(this.Latitude)+","+Double.toString(this.Longitude);
    }

}
