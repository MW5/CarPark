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
    private final IntegerProperty position;
    private final StringProperty make;
    private final StringProperty model;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final IntegerProperty phoneNumber;
    private final ObjectProperty<LocalDateTime> startDate;
    public Car(Integer position,
                String make,
                String model,
                String firstName,
                String lastName,
                Integer phoneNumber,
                LocalDateTime startDate) {
        this.position = new SimpleIntegerProperty(position);
        this.make = new SimpleStringProperty(make);
        this.model = new SimpleStringProperty(model);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.phoneNumber = new SimpleIntegerProperty(phoneNumber);
        this.startDate = new SimpleObjectProperty<LocalDateTime>(startDate); 
    }
}
