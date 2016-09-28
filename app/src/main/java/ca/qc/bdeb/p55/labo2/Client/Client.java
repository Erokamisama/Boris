package ca.qc.bdeb.p55.labo2.Client;

import java.io.Serializable;

/**
 * Created by WuTchanKi on 2016-09-13.
 */
public class Client implements Serializable {

    private String firstName;
    private String lastName;
    private String adress;
    private String city;
    private String mail;
    private int age;
    private String gender;
    private long idClient;

    private final char GENDER_MALE = 'M';
    private final char GENDER_FEMALE = 'F';

    public Client(){

    }

    public Client(String nom, String prenom, String adress, String city, String mail, int age, String gender){
        this.firstName = prenom;
        this.lastName = nom;
        this.adress = adress;
        this.city = city;
        this.mail = mail;
        this.age = age;
        this.gender = gender;
    }

    public long getIdClient() {
        return idClient;
    }

    public void setIdClient(long idClient) {
        this.idClient = idClient;
    }

    public Client(String nom, String prenom, String adress, String city, String mail, int age, String gender, long idClient){
        this.firstName = prenom;
        this.lastName = nom;
        this.adress = adress;
        this.city = city;
        this.mail = mail;
        this.age = age;
        this.gender = gender;
        this.idClient = idClient;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
