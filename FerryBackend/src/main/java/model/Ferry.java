/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import generalstuff.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 *
 * @author Infernosaint
 */
@Entity(name = "FERRIES") 
public class Ferry implements Serializable {
    @Id
    @Column(name = "FERRY_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int capacityPeople;
    private int capacityCars;
    private int capacityLorries;
    private String ferryType;
    
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="FERRY_LINE",
               joinColumns=
               @JoinColumn(name="FERRY_ID", referencedColumnName="FERRY_ID"),
         inverseJoinColumns=
               @JoinColumn(name="LINE_ID", referencedColumnName="LINE_ID")
       )
    private List<Line> supportedLines;
    
    public Ferry(){
        
    }
    public Ferry( int id, String name, int capacityPeople, int capacityCars, int capacityLorries, String ferryType, List<Line> supportedLines) {
       
        this.id = id;
        this.name = name;
        this.capacityPeople = capacityPeople;
        this.capacityCars = capacityCars;
        this.capacityLorries = capacityLorries;
        this.ferryType = ferryType;
        this.supportedLines = supportedLines;
    }

    public Ferry( String name, int capacityPeople, int capacityCars, int capacityLorries, String ferryType, List<Line> supportedLines) {
       
        this.name = name;
        this.capacityPeople = capacityPeople;
        this.capacityCars = capacityCars;
        this.capacityLorries = capacityLorries;
        this.ferryType = ferryType;
        this.supportedLines = supportedLines;
    }
    
    FerryIdentifier getFerryIdentifier() {
        FerryIdentifier ferryIdentifier = new FerryIdentifier(id);
        return ferryIdentifier;
    }
    FerrySummary getFerrySummary() {
        
        FerrySummary ferrySummary = new FerrySummary(name, getListOfLineIdentifiers(), id);
        return ferrySummary;
    }
    FerrySummary getFerryDetail() {
        FerryDetail ferryDetail = new FerryDetail(capacityPeople, capacityCars, capacityLorries, ferryType, name, getListOfLineIdentifiers(), id);
        return ferryDetail;
    }

    private List<LineIdentifier> getListOfLineIdentifiers(){
        List<LineIdentifier> supportedLineIdentifiers = new ArrayList<LineIdentifier>();
        for(Line line:supportedLines){
            supportedLineIdentifiers.add(line.getLineIdentifier());
        }
        return supportedLineIdentifiers;
    }       
        
    
  
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCapacityPeople() {
        return capacityPeople;
    }

    public int getCapacityCars() {
        return capacityCars;
    }

    public int getCapacityLorries() {
        return capacityLorries;
    }

    public String getFerryType() {
        return ferryType;
    }

    public List<Line> getSupportedLines() {
        return supportedLines;
    }
    
    
    
}
