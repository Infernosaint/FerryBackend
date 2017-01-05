/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import generalstuff.*;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Infernosaint
 */
@Entity(name = "LINES") 
public class Line implements Serializable {

    @Id
    @Column(name = "LINE_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String destinationPort;
    private String departurePort;
    private int duration;
    
    public Line(){
        
    }

    public Line(int id, String destinationPort, String departurePort, int duration) {
        this.id = id;
        this.destinationPort = destinationPort;
        this.departurePort = departurePort;
        this.duration = duration;
    }

    public Line(String destinationPort, String departurePort, int duration) {
        this.destinationPort = destinationPort;
        this.departurePort = departurePort;
        this.duration = duration;
    }

    public LineIdentifier getLineIdentifier() {
        LineIdentifier lineIdentifier = new LineIdentifier(id);
        return lineIdentifier;
    }

    public LineSummary getLineSummary() {

        LineSummary lineSummary = new LineSummary(destinationPort, departurePort, duration, id);
        return lineSummary;
    }

    public LineSummary getLineDetail() {
        LineDetail lineDetail = new LineDetail(destinationPort, departurePort, duration, id);
        return lineDetail;
    }

    public int getId() {
        return id;
    }

    public String getDestinationPort() {
        return destinationPort;
    }

    public String getDeparturePort() {
        return departurePort;
    }

    public int getDuration() {
        return duration;
    }

}
