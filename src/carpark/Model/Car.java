package carpark.Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 *
 * @author MW5
 */
public class Car {
    private final IntegerProperty location;
    private final StringProperty regNum;
    private final StringProperty make;
    private final StringProperty model;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty phoneNumber;
    private final StringProperty startDateTime;
    
    public Car(Integer location,
                String regNum,
                String make,
                String model,
                String firstName,
                String lastName,
                String phoneNumber,
                LocalDateTime startDateTime) {
        //przy tworezeniu obiektu klasy Car ustawiamy odrazu pola string property
        this.location = new SimpleIntegerProperty(location);
        this.regNum = new SimpleStringProperty(regNum);
        this.make = new SimpleStringProperty(make);
        this.model = new SimpleStringProperty(model);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.startDateTime = new SimpleStringProperty(stringifyDateTime(startDateTime));
    }
    //zmienia date z dateTime na string
    private String stringifyDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String formattedDateTime = localDateTime.format(formatter);
        return formattedDateTime;
    }
    //miejsca
    public Integer getLocation() {
        return location.get();
    }
    public void setLocation(Integer location) {
        this.location.set(location);
    }
    public IntegerProperty locationProperty() {
        return location;
    }
    //numer rejestracyjny
    public String getRegNum() {
        return regNum.get();
    }
    public void setRegNum(String regNum) {
        this.regNum.set(regNum);
    }
    public StringProperty regNumProperty() {
        return regNum;
    }
    //marka
    public String getMake() {
        return make.get();
    }
    public void setMake(String make) {
        this.make.set(make);
    }
    public StringProperty makeProperty() {
        return make;
    }
    //model
    public String getModel() {
        return model.get();
    }
    public void setModel(String model) {
        this.model.set(model);
    }
    public StringProperty modelProperty() {
        return model;
    }
    //imię
    public String getFirstName() {
        return firstName.get();
    }
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }
    public StringProperty firstNameProperty() {
        return firstName;
    }
    //nazwisko
    public String getLastName() {
        return lastName.get();
    }
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }
    public StringProperty lastNameProperty() {
        return lastName;
    }
    //telefon
    public String getPhoneNumber() {
        return phoneNumber.get();
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }
    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }
    //data rozpoczęcia parkowania
    public String getStartDateTime() {
        return startDateTime.get();
    }
    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime.set(stringifyDateTime(startDateTime));
    }
    public StringProperty startDateTimeProperty() {
        return startDateTime;
    }
}
