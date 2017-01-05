/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import generalstuff.DepartureDetail;
import generalstuff.DepartureIdentifier;
import generalstuff.FerryConfigDetail;
import generalstuff.FerryConfigIdentifier;
import generalstuff.FerryConfigSummary;
import generalstuff.FerryDetail;
import generalstuff.FerryIdentifier;
import generalstuff.FerrySummary;
import generalstuff.LineDetail;
import generalstuff.LineIdentifier;
import generalstuff.LineSummary;
import generalstuff.ReservationDetail;
import generalstuff.ReservationIdentifier;
import generalstuff.ReservationSummary;
import interfaces.AdminInterface;
import interfaces.CustomerInterface;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Departure;
import model.Ferry;
import model.FerryConfig;
import model.Line;
import model.Reservation;
import org.glassfish.jersey.internal.inject.Custom;

/**
 *
 * @author Infernosaint
 */
public class FerryMaster implements AdminInterface, CustomerInterface {

    public EntityManagerFactory emf;
    public EntityManager em;

    public FerryMaster() {
        emf = Persistence.createEntityManagerFactory("BPU");
        em = emf.createEntityManager();

    }

    public void initiateDB() {
        

        FerryConfig ferryConfig = new FerryConfig("NOROOF", 10, 1, 1000);
        em.getTransaction().begin();
        em.persist(ferryConfig);
        em.getTransaction().commit();

        Line line = new Line("Mads", "Kaloyan", 2);
        em.getTransaction().begin();
        em.persist(line);
        em.getTransaction().commit();

        Line line2 = new Line("Kaloyan", "Lucas", 2);
        em.getTransaction().begin();
        em.persist(line2);
        em.getTransaction().commit();

        List<Line> supportedLines = new ArrayList<>();
        supportedLines.add(line);
        supportedLines.add(line2);

        Ferry ferry = new Ferry("Mads 2", ferryConfig.getPeopleCapacity(), ferryConfig.getVehicleCapacity(), 0, "Big", supportedLines);
        em.getTransaction().begin();
        em.persist(ferry);
        em.getTransaction().commit();

        em.getTransaction().begin();
        Departure departure1 = new Departure(new Date(), line, ferry, 10, 100, 1000, 10000, 5, 100, 2, 1, 0);
        em.persist(departure1);
        Departure departure2 = new Departure(new Date(), line, ferry, 10, 100, 1000, 10000, 5, 100, 2, 1, 0);
        em.persist(departure2);
        Departure departure3 = new Departure(new Date(), line, ferry, 10, 100, 1000, 10000, 5, 100, 2, 1, 0);
        em.persist(departure3);
        em.getTransaction().commit();

    }

    boolean isWithinRange(Date testDate, Date startDate, Date endDate) {
        return testDate.getTime() >= startDate.getTime()
                && testDate.getTime() <= endDate.getTime();
    }

    @Override
    public Collection<LineSummary> createLine(String name, String departurePort, String destinationPort, double personPrice, double carPrice, double lorryPrice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean updateLine(LineIdentifier lineIdentifier, String name, String departurePort, String destinationPort, double personPrice, double carPrice, double lorryPrice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean deleteLine(LineIdentifier lineIdentifier) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LineDetail getLineDetail(LineIdentifier lineIdentifier) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<LineSummary> getLines() {

        Collection<LineSummary> lineSummaries = new ArrayList<LineSummary>();
        em.getTransaction().begin();
        List<Line> lines = em.createQuery("SELECT line FROM LINES line").getResultList();
        for (Line line : lines) {
            lineSummaries.add(line.getLineSummary());
        }
        em.getTransaction().commit();
        return lineSummaries;
    }

    @Override
    public Collection<FerrySummary> listFerries() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<DepartureDetail> getDepartures(LineIdentifier lineIdentifier, Date departureDate) {
        Collection<DepartureDetail> departuresDetails = new ArrayList<DepartureDetail>();
        em.getTransaction().begin();
        List<Departure> departures = em.createQuery("SELECT departure FROM DEPARTURES departure").getResultList();
        em.getTransaction().commit();
        System.out.println(departures);
        Calendar cal = Calendar.getInstance();
        cal.setTime(departureDate);
        cal.add(Calendar.HOUR_OF_DAY, -12);
        Date startDate = cal.getTime();
        cal.add(Calendar.HOUR_OF_DAY, 24);
        Date endDate = cal.getTime();
        int wantedLineId = lineIdentifier.getId();
        for (Departure departure : departures) {
            if (departure.getLine().getId() == wantedLineId) {
                if (isWithinRange(departure.getDepartureTime(), startDate, endDate)) {
                    departuresDetails.add(departure.getDepartureDetail());
                }
            }
        }
        return departuresDetails;

    }

    @Override
    public FerryDetail getFerryInfo(FerryIdentifier ferry) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<FerrySummary> createFerry(String ferryName, String config) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean removeFerry(FerryIdentifier ferryIdentifier) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean updateFerry(FerryIdentifier ferryIdentifier, String ferryName, String config) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<DepartureDetail> listDepatureInfo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReservationDetail getReservation(ReservationIdentifier reservationIdentifier) {
        em.getTransaction().begin();
        Reservation reservation = em.find(Reservation.class, reservationIdentifier.getId());
        em.getTransaction().commit();
        return reservation.getReservationDetail();
    }

    @Override
    public Boolean deleteDeparture(DepartureIdentifier departureIdentifier) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean createDeparture(LineIdentifier lineIdentifier, FerryIdentifier ferryIdentifier, Boolean alternateConfig, Date departureDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean updateDeparture(DepartureIdentifier departureIdentifier, LineIdentifier lineIdentifier, FerryIdentifier ferryIdentifier, Boolean alternateConfig, Date departureDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReservationDetail saveReservation(DepartureIdentifier departureIdentifier, int passengersNb, int numberOfResidents, int cars, int numberOfHeavyMachinery, int numberOfLorries) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReservationDetail updateReservation(ReservationIdentifier reservationIdentifier, DepartureIdentifier departureIdentifier, int passengersNb, int numberOfResidents, int cars) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean deleteReservation(ReservationIdentifier reservationIdentifier) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean removeFerryConfig(FerryConfigIdentifier ferryConfigIdentifier) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean updateFerryConfig(FerryConfigIdentifier ferryConfigIdentifier, String ferryConfigName, int peopleCapacity, int vehicleCapacity, int weightCapacity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean createFerryConfig(String ferryConfigName, int peopleCapacity, int vehicleCapacity, int weightCapacity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<FerryConfigSummary> listFerryConfigs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FerryConfigDetail getFerryConfigDetail(FerryConfigIdentifier ferryConfigIdentifier) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReservationDetail saveReservation(DepartureIdentifier departureIdentifier, int passengersNb, int numberOfResidents, int cars, int numberOfHeavyMachinery, int numberOfLorries, String customerName) {
        em.getTransaction().begin();
        Departure departure = em.find(Departure.class, departureIdentifier.getId());
        em.getTransaction().commit();
        Reservation reservation = new Reservation(departure, customerName, numberOfResidents, passengersNb, cars, numberOfLorries, numberOfHeavyMachinery, 1000);
        System.out.println("POK" + reservation.getId());
        em.getTransaction().begin();
        em.persist(reservation);
        em.getTransaction().commit();
        System.out.println("POK" + reservation.getId());
        return reservation.getReservationDetail();
    }

    @Override
    public ReservationDetail updateReservation(ReservationIdentifier reservationIdentifier, DepartureIdentifier departureIdentifier, int passengersNb, int numberOfResidents, int cars, int numberOfHeavyMachinery, int numberOfLorries, String customerName) {
        em.getTransaction().begin();
        Reservation reservation = em.find(Reservation.class, reservationIdentifier.getId());
        
        reservation.setAliens(passengersNb);
        reservation.setResidents(numberOfResidents);
        reservation.setCars(cars);
        reservation.setHeavyMachinery(numberOfHeavyMachinery);
        reservation.setLorries(numberOfLorries);
        reservation.setCustomer(customerName);
        em.getTransaction().commit();
        return reservation.getReservationDetail();
    }

}
