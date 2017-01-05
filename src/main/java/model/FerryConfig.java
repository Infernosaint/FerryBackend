/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import generalstuff.*;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Infernosaint
 */
@Entity(name = "FERRYCONFIGS") 
public class FerryConfig implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int peopleCapacity;
    private int vehicleCapacity;
    private int weightCapacity;
    
    public FerryConfig(){
        
    }

    public FerryConfig(int id, String name, int peopleCapacity, int vehicleCapacity, int weightCapacity) {
        this.id = id;
        this.name = name;
        this.peopleCapacity = peopleCapacity;
        this.vehicleCapacity = vehicleCapacity;
        this.weightCapacity = weightCapacity;
    }

    public FerryConfig(String name, int peopleCapacity, int vehicleCapacity, int weightCapacity) {
        this.name = name;
        this.peopleCapacity = peopleCapacity;
        this.vehicleCapacity = vehicleCapacity;
        this.weightCapacity = weightCapacity;
    }
    
    FerryConfigIdentifier getFerryConfigIdentifier() {
        FerryConfigIdentifier ferryConfigIdentifier = new FerryConfigIdentifier(id);
        return ferryConfigIdentifier;
    }
    FerryConfigSummary getFerryConfigSummary() {
        
        FerryConfigSummary ferryConfigSummary = new FerryConfigSummary(name, id);
        return ferryConfigSummary;
    }
    FerryConfigSummary getFerryConfigDetail() {
        FerryConfigDetail ferryConfigDetail = new FerryConfigDetail(peopleCapacity, vehicleCapacity, weightCapacity, name, id);
        return ferryConfigDetail;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPeopleCapacity() {
        return peopleCapacity;
    }

    public int getVehicleCapacity() {
        return vehicleCapacity;
    }

    public int getWeightCapacity() {
        return weightCapacity;
    }
    
    
    
}
