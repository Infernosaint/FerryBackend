/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import generalstuff.*;
import java.util.Date;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;

/**
 *
 * @author Infernosaint
 */
@Entity(name = "RESERVATIONS") 
public class Reservation implements Serializable {
    
    private static final long serialVersionUID = 1L;    
    @Id
    @Column(name = "RESERVATION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Departure departure;
    private String customer;
    private int residents;
    private int aliens;
    private int cars;
    private int lorries;
    private int heavyMachinery;
    private int totalPrice;
    @Temporal(DATE)
    private Date dateMade;
    
    public Reservation(){
        
    }

    public Reservation(Departure departure, String customer, int residents, int aliens, int cars, int lorries, int heavyMachinery, int totalprice) {
        this.departure = departure;
        this.customer = customer;
        this.residents = residents;
        this.aliens = aliens;
        this.cars = cars;
        this.lorries = lorries;
        this.heavyMachinery = heavyMachinery;
        this.totalPrice = totalprice;
        this.dateMade = new Date();
    }

    public ReservationIdentifier getReservationDetailIdentifier() {
        ReservationIdentifier reservationIdentifier = new ReservationIdentifier(id);
        return reservationIdentifier;
    }
    public ReservationSummary getReservationSummary() {
        ReservationSummary reservationSummary = new ReservationSummary(totalPrice, id);
        return reservationSummary;
    }
    public ReservationDetail getReservationDetail() {
        ReservationDetail reservationDetail = new ReservationDetail(dateMade, departure.getDepartureSummary(), customer, aliens, residents, cars, lorries, heavyMachinery, totalPrice, id);
        return reservationDetail;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public Departure getDeparture() {
        return departure;
    }

    public String getCustomer() {
        return customer;
    }

    public int getResidents() {
        return residents;
    }

    public int getAliens() {
        return aliens;
    }

    public int getCars() {
        return cars;
    }

    public int getLorries() {
        return lorries;
    }

    public int getHeavyMachinery() {
        return heavyMachinery;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public Date getDateMade() {
        return dateMade;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDeparture(Departure departure) {
        this.departure = departure;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void setResidents(int residents) {
        this.residents = residents;
    }

    public void setAliens(int aliens) {
        this.aliens = aliens;
    }

    public void setCars(int cars) {
        this.cars = cars;
    }

    public void setLorries(int lorries) {
        this.lorries = lorries;
    }

    public void setHeavyMachinery(int heavyMachinery) {
        this.heavyMachinery = heavyMachinery;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setDateMade(Date dateMade) {
        this.dateMade = dateMade;
    }
    
    

}
