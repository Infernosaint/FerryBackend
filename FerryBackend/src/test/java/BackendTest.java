

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.mycompany.contracttest.BackendHolder;
import control.FerryMaster;
import generalstuff.DepartureDetail;
import generalstuff.DepartureIdentifier;
import generalstuff.LineIdentifier;
import generalstuff.LineSummary;
import generalstuff.ReservationDetail;
import generalstuff.ReservationIdentifier;
import java.util.Collection;
import java.util.Date;
import javax.persistence.EntityManager;
import model.Reservation;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    BackendManagerTest.class
})
public class BackendTest {

    @BeforeClass
    public static void setupClass() {
        FerryMaster ferryMaster = new FerryMaster();
        BackendHolder.manager = ferryMaster;
    }

    @Test
    public void fullTest() {
        assumeTrue(1 < 0);
        FerryMaster ferryMaster = new FerryMaster();
        EntityManager em = ferryMaster.em;
        Collection<LineSummary> lineSummaries = ferryMaster.getLines();
        if (lineSummaries.size() > 0) {
            Collection<DepartureDetail> departuresDetails = ferryMaster.getDepartures(new LineIdentifier(lineSummaries.iterator().next().getId()), new Date());
            ReservationDetail resDet = ferryMaster.saveReservation(new DepartureIdentifier(departuresDetails.iterator().next().getId()), 1, 1, 1, 0, 0, "Mads");
            em.getTransaction().begin();
            System.out.println(resDet.getId());
            Reservation resCheck = em.find(Reservation.class, resDet.getId());
            em.getTransaction().commit();
            System.out.println(resCheck.getId());
            System.out.println(resCheck.getCustomer());
            ReservationDetail resDetNew = ferryMaster.updateReservation(new ReservationIdentifier(resDet.getId()), new DepartureIdentifier(resDet.getDepartureSummary().getId()), 13, 2, 3, 0, 0, "Kaloyan");
            System.out.println(resDetNew.getCustomerName());
            em.getTransaction().begin();
            System.out.println(resDetNew.getId());
            Reservation resCheckNew = em.find(Reservation.class, resDetNew.getId());
            em.getTransaction().commit();
            System.out.println(resCheckNew.getCustomer());
        } else {
            System.out.println("No current lines!");
        }
    }
}
