package main.java;

/**
 * Created by shivangipatwardhan on 11/26/16.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class HttpURLConnection {
    JSonParser parseJSonFile = new JSonParser();
    ArrayList<HospitalContact> hospitalInformationRetrieved = new ArrayList<HospitalContact>();

    //Get Detailed by Place ID
    //https://maps.googleapis.com/maps/api/place/details/json?placeid=ChIJN1t_tDeuEmsRUsoyG83frY4&key=YOUR_API_KEY

    String returnedJSonFile = null;
    private final String USER_AGENT = "Mozilla/5.0";



    public HospitalContact getDetailedInformation(HospitalContact inputPlace) throws Exception{

        String url = "https://maps.googleapis.com/maps/api/place/details/json?placeid="+inputPlace.getIdNumber()+"&key=AIzaSyBTjjh35oPAK-m9ZDaaFSvLqfU8B3t8zPs";

        URL obj = new URL(url);
        java.net.HttpURLConnection con = (java.net.HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

        StringBuffer response = new StringBuffer();

        while ((returnedJSonFile = in.readLine()) != null) {
            response.append(returnedJSonFile);
        }
        in.close();

        //print result
        returnedJSonFile = response.toString();
        return parseJSonFile.addAddressAndPhoneNumberToHospitalContacts(inputPlace ,returnedJSonFile);
    }

   public ArrayList<HospitalContact> sendLocationCoordinatesAndRetrieveNearlyLocationsJSONFile(LocationCoordinates input) throws Exception{

       String latLong = input.getStringLocation();

       String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?type=hospital&location="+latLong+"&rankby=distance&key=AIzaSyBTjjh35oPAK-m9ZDaaFSvLqfU8B3t8zPs";

       URL obj = new URL(url);
       java.net.HttpURLConnection con = (java.net.HttpURLConnection) obj.openConnection();

       // optional default is GET
       con.setRequestMethod("GET");

       //add request header
       con.setRequestProperty("User-Agent", USER_AGENT);

       int responseCode = con.getResponseCode();

       BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

       StringBuffer response = new StringBuffer();

       while ((returnedJSonFile = in.readLine()) != null) {
           response.append(returnedJSonFile);
       }
       in.close();

       //print result
       returnedJSonFile = response.toString();
       hospitalInformationRetrieved = parseJSonFile.parseInputJSonStringAndReturnNameOfHospital(returnedJSonFile);
       for(int i = 0; i < hospitalInformationRetrieved.size();i++){
           getDetailedInformation(hospitalInformationRetrieved.get(i));
       }

       return hospitalInformationRetrieved;
   }
//&sensor=false&components=country:SG

    public LocationCoordinates sendPostalCodeAndRetrieveJSonFile(String partialAddress, String country) throws Exception {

        String url = "https://maps.google.com/maps/api/geocode/json?address="+partialAddress+",%20"+country+"&‌​components=country:"+country+"|postal_code:"+partialAddress+"&sensor=false&key=AIzaSyBTjjh35oPAK-m9ZDaaFSvLqfU8B3t8zPs";

        URL obj = new URL(url);
        java.net.HttpURLConnection con = (java.net.HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

        StringBuffer response = new StringBuffer();

        while ((returnedJSonFile = in.readLine()) != null) {
            response.append(returnedJSonFile);
        }
        in.close();
        returnedJSonFile = response.toString();
        return parseJSonFile.parseInputJSonStringAndReturnLocationCoordinates(returnedJSonFile);
    }

}

