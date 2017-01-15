/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carpark.Model;

import java.time.LocalDateTime;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 *
 * @author MW5
 */
public class Car {
    private final IntegerProperty location;
    private final StringProperty make;
    private final StringProperty model;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final IntegerProperty phoneNumber;
    private final ObjectProperty<LocalDateTime> startDateTime;
    
    public Car(Integer location,
                String make,
                String model,
                String firstName,
                String lastName,
                Integer phoneNumber,
                LocalDateTime startDateTime) {
        this.location = new SimpleIntegerProperty(location);
        this.make = new SimpleStringProperty(make);
        this.model = new SimpleStringProperty(model);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.phoneNumber = new SimpleIntegerProperty(phoneNumber);
        this.startDateTime = new SimpleObjectProperty<LocalDateTime>(startDateTime); 
    }
    //location
    public Integer getLocation() {
        return location.get();
    }
    public void setLocation(Integer location) {
        this.location.set(location);
    }
    public IntegerProperty locationProperty() {
        return location;
    }
    //make
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
    //first name
    public String getFirstName() {
        return firstName.get();
    }
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }
    public StringProperty firstNameProperty() {
        return firstName;
    }
    //last name
    public String getLastName() {
        return lastName.get();
    }
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }
    public StringProperty lastNameProperty() {
        return lastName;
    }
    //phone
    public Integer getPhoneNumber() {
        return phoneNumber.get();
    }
    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }
    public IntegerProperty phoneNumberProperty() {
        return phoneNumber;
    }
        //start date
    public LocalDateTime getStartDateTime() {
        return startDateTime.get();
    }
    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime.set(startDateTime);
    }
    public ObjectProperty<LocalDateTime> startDateTimeProperty() {
        return startDateTime;
    }
    

    
}
