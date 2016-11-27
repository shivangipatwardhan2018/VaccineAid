package main.java;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

/*
 * Created by shivangipatwardhan on 11/26/16.
 */
public class JSonParser {

    public HospitalContact addAddressAndPhoneNumberToHospitalContacts(HospitalContact inputHospitalContact, String jsonString){

        String formatted_address = null;
        String formatted_phone_number = null;
        JSONObject jObj = null;
        try {
            jObj = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String Status = null;
        try {
            Status = jObj.getString("status");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (Status.equalsIgnoreCase("OK")) {
            JSONObject results = null;
            try {
                results = jObj.getJSONObject("result");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                formatted_address = results.getString("formatted_address");
            } catch (JSONException e) {

            }
            try {
                formatted_phone_number = results.getString("formatted_phone_number");
            } catch (JSONException e) {

            }
        }

        inputHospitalContact.setAddress(formatted_address);
        inputHospitalContact.setPhoneNumber(formatted_phone_number);

        return inputHospitalContact;

    }

    public ArrayList parseInputJSonStringAndReturnNameOfHospital(String JSonString)
    {
        ArrayList<HospitalContact> returnedIdAndName = new ArrayList<HospitalContact>();
        JSONObject jObj = null;
        try {
            jObj = new JSONObject(JSonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String Status = null;
        try {
            Status = jObj.getString("status");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (Status.equalsIgnoreCase("OK")) {
            //We must parse through each layer of the JSON Object and extract the information
            JSONArray results = null;
            try {
                results = jObj.getJSONArray("results");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            for(int i = 0; i<5;i++){
                JSONObject item = null;
                try {
                    item = results.getJSONObject(i);
                } catch (JSONException e) {

                }
                String my_id = null;
                try {
                    my_id = item.getString("place_id");
                } catch (JSONException e) {

                }
                String my_name = null;
                try {
                    my_name = item.getString("name");
                } catch (JSONException e) {

                }
                returnedIdAndName.add(new HospitalContact(my_id, my_name));
            }
        }
        return returnedIdAndName;
    }


    public LocationCoordinates parseInputJSonStringAndReturnLocationCoordinates(String JSonString) throws JSONException {

        LocationCoordinates latlong = null;

        JSONObject jObj = new JSONObject(JSonString);
        String Status = jObj.getString("status");
        if (Status.equalsIgnoreCase("OK")) {
            //We must parse through each layer of the JSON Object and extract the information
            JSONArray results = jObj.getJSONArray("results");
            JSONObject item = results.getJSONObject(0);
            JSONObject geometry = item.getJSONObject("geometry");
            JSONObject location = geometry.getJSONObject("location");
            double latitude = location.getDouble("lat");
            double longitude = location.getDouble("lng");
            latlong = new LocationCoordinates(latitude, longitude);
            //System.out.println("laditude: " + latitude);
            //System.out.println("longitude: " + longitude);
        }
        return latlong;
    }
}
