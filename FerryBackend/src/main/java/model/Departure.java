/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import generalstuff.*;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;

/**
 *
 * @author Infernosaint
 */
@Entity(name = "DEPARTURES") 
public class Departure implements Serializable{
    private static final long serialVersionUID = 1L;    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Temporal(DATE)
    private Date departureTime;
    
    @ManyToOne
    @JoinColumn(name="LINE_ID",referencedColumnName="LINE_ID")
    private Line line;
    @ManyToOne
    @JoinColumn(name="FERRY_ID",referencedColumnName="FERRY_ID")
    private Ferry ferry;
    private long pricePerPerson;
    private long pricePerCar;
    private long pricePerLorry;
    private long pricePerHeavy;
    private long pricePerResident;
    private int remainingPeople;
    private int remainingCars;
    private int remainingLorries;
    private int remainingHeavy;

    public Departure(){
        
    }
    
    public Departure(int id, Date departureTime, Line line, Ferry ferry, long pricePerPerson, long pricePerCar, long pricePerLorry, long pricePerHeavy, long pricePerResident, int remainingPeople, int remainingCars, int remainingLorries, int remainingHeavy) {
        this.id = id;
        this.departureTime = departureTime;
        this.line = line;
        this.ferry = ferry;
        this.pricePerPerson = pricePerPerson;
        this.pricePerCar = pricePerCar;
        this.pricePerLorry = pricePerLorry;
        this.pricePerHeavy = pricePerHeavy;
        this.pricePerResident = pricePerResident;
        this.remainingPeople = remainingPeople;
        this.remainingCars = remainingCars;
        this.remainingLorries = remainingLorries;
        this.remainingHeavy = remainingHeavy;
    }
    
    
    public Departure(Date departureTime, Line line, Ferry ferry, long pricePerPerson, long pricePerCar, long pricePerLorry, long pricePerHeavy, long pricePerResident, int remainingPeople, int remainingCars, int remainingLorries, int remainingHeavy) {
        this.departureTime = departureTime;
        this.line = line;
        this.ferry = ferry;
        this.pricePerPerson = pricePerPerson;
        this.pricePerCar = pricePerCar;
        this.pricePerLorry = pricePerLorry;
        this.pricePerHeavy = pricePerHeavy;
        this.pricePerResident = pricePerResident;
        this.remainingPeople = remainingPeople;
        this.remainingCars = remainingCars;
        this.remainingLorries = remainingLorries;
        this.remainingHeavy = remainingHeavy;
    }
    
    
    
    
    public DepartureIdentifier getDepartureIdentifier() {
        DepartureIdentifier departureIdentifier = new DepartureIdentifier(id);
        return departureIdentifier;
    }
    public DepartureSummary getDepartureSummary() {
        DepartureSummary departureSummary = new DepartureSummary(departureTime, line.getLineSummary(), ferry.getFerrySummary(), id);
        return departureSummary;
    }
    public DepartureDetail getDepartureDetail() {
        DepartureDetail departureDetail = new DepartureDetail(pricePerPerson, pricePerCar, pricePerLorry, pricePerHeavy, pricePerResident, remainingPeople, remainingCars, remainingLorries, remainingHeavy, departureTime, line.getLineSummary(), ferry.getFerrySummary(), id);
        return departureDetail;
    }

    public int getId() {
        return id;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public Line getLine() {
        return line;
    }

    public Ferry getFerry() {
        return ferry;
    }

    public long getPricePerPerson() {
        return pricePerPerson;
    }

    public long getPricePerCar() {
        return pricePerCar;
    }

    public long getPricePerLorry() {
        return pricePerLorry;
    }

    public long getPricePerHeavy() {
        return pricePerHeavy;
    }

    public long getPricePerResident() {
        return pricePerResident;
    }

    public int getRemainingPeople() {
        return remainingPeople;
    }

    public int getRemainingCars() {
        return remainingCars;
    }

    public int getRemainingLorries() {
        return remainingLorries;
    }

    public int getRemainingHeavy() {
        return remainingHeavy;
    }
    
    
    
}
