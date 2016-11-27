package main.java;

/**
 * Created by shivangipatwardhan on 11/26/16.
 */
public class HospitalContact {
    String idNumber;
    String name;
    String address;
    String phoneNumber;

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public HospitalContact(String idNumberInput, String nameInput){
        idNumber = idNumberInput;
        name = nameInput;
    }


    public HospitalContact(String idNumberInput, String nameInput, String addressInput){
        idNumber = idNumberInput;
        name = nameInput;
        address = addressInput;
    }

    public void addPhoneNumber(String phoneNumberInput){
        phoneNumber = phoneNumberInput;
    }

    public String getIdNumber(){
        return this.idNumber;
    }
}

