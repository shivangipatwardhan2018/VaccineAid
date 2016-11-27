package main.java;

import java.util.ArrayList;

/**
 * Created by shivangipatwardhan on 11/26/16.
 */
public class HospitalHelpRequest {
    HttpURLConnection http = new HttpURLConnection();

    //Get user Postal Code
    String postalCode = "L6M0E3";
    String countryCode = "IN";
    //Constuct a GeoLocation
    LocationCoordinates locateCoordinatedFromPostalCodes = null;


    public void getHospitalInformation() throws Exception {

        try {
            locateCoordinatedFromPostalCodes = http.sendPostalCodeAndRetrieveJSonFile(postalCode, countryCode);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<HospitalContact> returnedList =  http.sendLocationCoordinatesAndRetrieveNearlyLocationsJSONFile(locateCoordinatedFromPostalCodes);
        ArrayList<HospitalContact> finalSoltion = new ArrayList<HospitalContact>();
        for(int i = 0; i < returnedList.size();i++){
            finalSoltion.add(http.getDetailedInformation(returnedList.get(i)));
        }

        for(int i = 0; i < finalSoltion.size(); i++){
            HospitalContact contact = finalSoltion.get(i);
            System.out.println("");
            System.out.println(contact.getName());
            System.out.println(contact.getAddress());
            System.out.println(contact.getPhoneNumber());
        }

    }

}
