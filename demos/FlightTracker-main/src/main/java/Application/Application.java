package Application;

import Application.Controller.FlightController;
import Application.Util.ConnectionUtil;
import io.javalin.Javalin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * There is no need to modify anything in this class.
 * The main method will start a new Javalin API on the console at localhost:8080.
 * Take a look at the FlightController class for API documentation as well as instructions for how to
 * access the API endpoints.
 */
public class Application {
    /**
     * You can run this main method to run the API.
     * @param args
     */
    public static void main(String[] args) {
        databaseSetup();
        FlightController flightController = new FlightController();
        Javalin app = flightController.startAPI();
        app.start(8080);
    }
    /**
     * For the purpose of this short exercise, this method will destroy and set up a new flight table.
     * This is not a normal way to set up your tables, in real projects you should set up your database
     * schema in a SQL editor such as DBeaver or DataGrip. Do not change anything in this method.
     */
    public static void databaseSetup(){
        try {
            Connection conn = ConnectionUtil.getConnection();
            PreparedStatement ps1 = conn.prepareStatement("drop table if exists flight");
            ps1.executeUpdate();
            PreparedStatement ps2 = conn.prepareStatement("create table flight(" +
                    "flight_id int primary key auto_increment, " +
                    "departure_city varchar(255), " +
                    "arrival_city varchar(255));");
            ps2.executeUpdate();
            PreparedStatement ps3 = conn.prepareStatement("insert into flight " +
                    "(departure_city, arrival_city) values " +
                    "('tampa', 'dallas')," +
                    "('tampa', 'reston')," +
                    "('reston', 'morgantown')," +
                    "('morgantown', 'dallas')," +
                    "('tampa', 'dallas')," +
                    "('dallas', 'tampa');");
            ps3.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
