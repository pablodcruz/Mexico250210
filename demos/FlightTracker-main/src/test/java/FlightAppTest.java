import Application.Application;
import Application.DAO.FlightDAO;
import Application.Model.Flight;
import Application.Service.FlightService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

/**
 * This class tests using the dummy data inserted as part of the Application.Application.databaseSetup() method.
 * It contains the following records:
 *      (1, 'tampa', 'dallas'),
 *      (2, 'tampa', 'reston'),
 *      (3, 'reston', 'morgantown'),
 *      (4, 'morgantown', 'dallas'),
 *      (5, 'tampa', 'dallas'),
 *      (6, 'dallas', 'tampa')
 */
public class FlightAppTest {
    public FlightDAO flightDAO;
    public FlightDAO mockFlightDAO;
    public FlightService flightService;

    /**
     * set up a flightDAO and recreate the database tables and mock data.
     */
    @Before
    public void setUp(){
        Application.databaseSetup();
        flightDAO = new FlightDAO();

        mockFlightDAO = Mockito.mock(FlightDAO.class);
        flightService = new FlightService(mockFlightDAO);
    }


    /**
     * THESE TESTS ARE FOR THE FLIGHTDAO CLASS
     */

    /**
     * The flightDAO should retrieve all flights when getAllFlights is called.
     */
    @Test
    public void flightDAO_GetAllFlightsTest1(){
        List<Flight> allFlights = flightDAO.getAllFlights();
        Flight f1 = new Flight(1, "tampa", "dallas");
        Flight f2 = new Flight(2, "tampa", "reston");
        Flight f3 = new Flight(3, "reston", "morgantown");
        Flight f4 = new Flight(4, "morgantown", "dallas");
        Flight f5 = new Flight(5, "tampa", "dallas");
        Flight f6 = new Flight(6, "dallas", "tampa");
        Assert.assertTrue(allFlights.contains(f1));
        Assert.assertTrue(allFlights.contains(f2));
        Assert.assertTrue(allFlights.contains(f3));
        Assert.assertTrue(allFlights.contains(f4));
        Assert.assertTrue(allFlights.contains(f5));
        Assert.assertTrue(allFlights.contains(f6));
    }

    /**
     * The flightDAO should retrieve a flight with a specific ID when getFlightById is called.
     */
    @Test
    public void flightDAO_GetFlightByIDTest1(){
        Flight flight = flightDAO.getFlightById(6);
        if(flight == null){
            Assert.fail();
        }else{
            Flight f6 = new Flight(6, "dallas", "tampa");
            Assert.assertTrue(flight.equals(f6));
        }
    }
    /**
     * The flightDAO should retrieve a flight with a specific ID when getFlightById is called.
     */
    @Test
    public void flightDAO_GetFlightByIDTest2(){
        Flight flight = flightDAO.getFlightById(4);
        if(flight == null){
            Assert.fail();
        }else{
            Flight f9012 = new Flight(4, "morgantown", "dallas");
            Assert.assertTrue(flight.equals(f9012));
        }
    }

    /**
     * When there is one flight between two cities, getAllFlightsFromCityToCity should return a list containing
     * that flight. It should not contain other flights.
     */
    @Test
    public void flightDAO_GetFlightsFromCityToCityTest1(){
        List<Flight> flights = flightDAO.getAllFlightsFromCityToCity("reston", "morgantown");
        Flight f1 = new Flight(1, "tampa", "dallas");
        Flight f3 = new Flight(3, "reston", "morgantown");
        Flight f5 = new Flight(5, "tampa", "dallas");
        Assert.assertFalse(flights.contains(f1));
        Assert.assertTrue(flights.contains(f3));
        Assert.assertFalse(flights.contains(f5));
    }

    /**
     * When there are multiple flights between two cities, getAllFlightsFromCityToCity should return a list containing
     * both flights. It should not contain other flights.
     */
    @Test
    public void flightDAO_GetFlightsFromCityToCityTest2(){
        List<Flight> flights = flightDAO.getAllFlightsFromCityToCity("tampa", "dallas");
        Flight f1 = new Flight(1, "tampa", "dallas");
        Flight f3 = new Flight( 3, "reston", "morgantown");
        Flight f5 = new Flight( 5, "tampa", "dallas");
        Assert.assertTrue(flights.contains(f1));
        Assert.assertFalse(flights.contains(f3));
        Assert.assertTrue(flights.contains(f5));
    }

    /**
     * When a flight is added via the flightDAO, it should be retrievable by retrieving the flight by ID.
     */
    @Test
    public void flightDAO_InsertFlightCheckByIdTest1(){
        Flight f7 = new Flight( "tampa", "morgantown");
        flightDAO.insertFlight(f7);
        Flight f7expected = new Flight(7, "tampa", "morgantown");
        Flight f7actual = flightDAO.getFlightById(7);
        Assert.assertEquals(f7expected, f7actual);
    }

    /**
     * When a flight is added via the flightDAO, it should be retrievable by retrieving all flights.
     */
    @Test
    public void flightDAO_InsertFlightCheckAllFlightsTest1(){
        Flight f7 = new Flight( "tampa", "morgantown");
        flightDAO.insertFlight(f7);
        Flight f7expected = new Flight(7, "tampa", "morgantown");
        List<Flight> allFlights = flightDAO.getAllFlights();
        Assert.assertTrue(allFlights.contains(f7expected));
    }

    /**
     * When a flight is updated via the flightDAO, the updated values should be retrieved when the flight is next
     * accessed.
     */
    @Test
    public void flightDAO_UpdateFlightDAOTest1(){
        Flight f1updated = new Flight( "reston", "dallas");
        flightDAO.updateFlight(1, f1updated);
        Flight f1expected = new Flight(1, "reston", "dallas");
        Flight f1actual = flightDAO.getFlightById(1);
        Assert.assertEquals(f1expected, f1actual);
    }


    /**
     * THESE TESTS ARE FOR THE FLIGHTSERVICE CLASS
     */


    /**
     * when a flightDAO returns all flights, flightService.getAllFlights should return all flights.
     */
    @Test
    public void flightService_GetAllFlightsTest(){
        List<Flight> allFlightsReturned = new ArrayList<>();
        allFlightsReturned.add(new Flight(801, "tampa", "dallas"));
        allFlightsReturned.add(new Flight(802, "tampa", "morgantown"));
        allFlightsReturned.add(new Flight(803, "tampa", "reston"));
        allFlightsReturned.add(new Flight(804, "tampa", "dallas"));
        Mockito.when(mockFlightDAO.getAllFlights()).thenReturn(allFlightsReturned);
        Assert.assertEquals(allFlightsReturned, flightService.getAllFlights());
    }
    /**
     * When a flightAdded is called and the database does not already contain the flight,
     * the flight should be returned because the add should be successful. Subsequent attempts to add the flight
     * should return null as the flight already exists. Also, verify that the addFlight method of the flightDAO is
     * actually called.
     */
    @Test
    public void flightService_AddFlightTest(){
//        object represents new flight
        Flight newFlight = new Flight( "dallas", "morgantown");
        Flight persistedFlight = new Flight(1, "dallas", "morgantown");
        Mockito.when(mockFlightDAO.insertFlight(newFlight)).thenReturn(persistedFlight);
        Flight actualFlight = flightService.addFlight(newFlight);
        Assert.assertEquals(persistedFlight, actualFlight);
//        verify that addFlight was actually used (Mockito.any will accept any parameters)
        Mockito.verify(mockFlightDAO).insertFlight(Mockito.any());
    }


    /**
     * Regardless of if the flights are filtered with a SQL query or in the Java service class,
     * flightService.getAllFlightsFromCityToCity should return all the flights between two cities.
     */
    @Test
    public void flightService_GetFlightsFromCityToCityTest1(){
        List<Flight> allFlightsReturned = new ArrayList<>();
        allFlightsReturned.add(new Flight(801, "tampa", "dallas"));
        allFlightsReturned.add(new Flight(802, "tampa", "morgantown"));
        allFlightsReturned.add(new Flight(803, "tampa", "reston"));
        allFlightsReturned.add(new Flight(804, "tampa", "dallas"));
        allFlightsReturned.add(new Flight(805, "dallas", "morgantown"));
        Flight f801 = new Flight(801, "tampa", "dallas");
        Flight f804 = new Flight(804, "tampa", "dallas");
        List<Flight> cityToCityFlightsReturned = new ArrayList<>();
        cityToCityFlightsReturned.add(f801);
        cityToCityFlightsReturned.add(f804);
//        both getAllFlights and getAllFlightsFromCityToCity are mocked in case you want to develop a solution
//        that relies on filtering within the Service rather than in the SQL query. Filtering in Java
//        is less efficient than in a SQL query, but it still works.
        Mockito.when(mockFlightDAO.getAllFlightsFromCityToCity("tampa", "dallas"))
                .thenReturn(cityToCityFlightsReturned);
        Mockito.when(mockFlightDAO.getAllFlights()).thenReturn(allFlightsReturned);
        if(flightService.getAllFlightsFromCityToCity("tampa", "dallas") == null){
            Assert.fail();
        }else{
            Assert.assertTrue(flightService.getAllFlightsFromCityToCity("tampa", "dallas")
            .contains(f801));
            Assert.assertTrue(flightService.getAllFlightsFromCityToCity("tampa", "dallas")
            .contains(f804));
        }
        

    }

    /**
     * When a flight exists, attempting to update it should return the updated flight. Also, verify that the
     * updateFlight method of the mockFlightDAO was called.
     */
    @Test
    public void flightService_UpdateFlightTest1(){
        List<Flight> allFlightsReturned = new ArrayList<>();
        allFlightsReturned.add(new Flight(801, "tampa", "dallas"));
        allFlightsReturned.add(new Flight(802, "tampa", "morgantown"));
        allFlightsReturned.add(new Flight(803, "tampa", "reston"));
        allFlightsReturned.add(new Flight(804, "tampa", "dallas"));
        allFlightsReturned.add(new Flight(805, "dallas", "morgantown"));
        Flight f801 = new Flight( "dallas", "morgantown");
        Flight expectedFlight = new Flight(801, "dallas", "morgantown");
        Mockito.when(mockFlightDAO.getAllFlights()).thenReturn(allFlightsReturned);
        Mockito.when(mockFlightDAO.getFlightById(801)).thenReturn(expectedFlight);

        Flight actualFlight = flightService.updateFlight(801, f801);
        Assert.assertEquals(expectedFlight, actualFlight);
    }

    /**
     * When a flight does not exist, attempting to update it should return null. Also, verify that
     * flightDAO.updateFlight was never called.
     */
    @Test
    public void flightService_UpdateFlightTestNonExistent(){
        Flight f801 = new Flight( "tampa", "dallas");
        Assert.assertEquals(null, flightService.updateFlight(801, f801));
    }
}
