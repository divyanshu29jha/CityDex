package facilities.transports;

import Console.ConsoleColors;
import city.City;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;


public class TransportRunner {
    static ArrayList<Transport> transportArray=new ArrayList<>();
    public static void arr(City city)throws SQLException,IOException{
        transportArray.clear();
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "Sm.963258741m");
        Statement statement = connection.createStatement();


        String query = "select * from transport where city =" + "'" + city.getCity() + "'";
        ResultSet resultSet = statement.executeQuery(query);
        int id = 1;
        while (resultSet.next()) {
            Transport h = new Transport();
            h.setId(id);
            h.setName(resultSet.getString("name"));
            h.setCity(resultSet.getString("city"));
            h.setAddress(resultSet.getString("ADDRESS"));
            h.setContact(resultSet.getString("CONTACT"));
            h.setRating(Double.parseDouble(resultSet.getString("Rating")));
            h.setTransportType(resultSet.getString("transport_type"));
            h.setLocation(resultSet.getString("Location"));
            transportArray.add(h);
            id++;
        }
    }

    public static void gtRating(City city,double v) throws SQLException, IOException {
        arr(city);
        for (Transport i : transportArray) {
            if(i.getRating()>v)
            {
                System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT +"[ID: " + i.getId() +"]" + " Name: " + i.getName() +"  Transport Type: "+i.getTransportType()+ " Address: " + i.getAddress() + " [Rating: " + i.getRating()+"]"+ ConsoleColors.RESET);
            }
        }
    }

    public static void ltRating(City city,double v) throws SQLException, IOException {
        arr(city);
        for (Transport i : transportArray) {
            if(i.getRating()<v)
            {
                System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT +"[ID: " + i.getId() +"]" + " Name: " + i.getName() +"  Transport Type: "+i.getTransportType()+ " Address: " + i.getAddress() + " [Rating: " + i.getRating()+"]"+ ConsoleColors.RESET);
            }
        }
    }

    public static void eqRating(City city,double v) throws SQLException, IOException {
        arr(city);
        for (Transport i : transportArray) {
            if(i.getRating()==v)
            {
                System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT +"[ID: " + i.getId() +"]" + " Name: " + i.getName() +"  Transport Type: "+i.getTransportType()+ " Address: " + i.getAddress() + " [Rating: " + i.getRating()+"]"+ ConsoleColors.RESET);
            }
        }
    }

    public static void Searchname(City city,String name) throws SQLException, IOException {
        arr(city);
        for (Transport i : transportArray) {
            if(i.getName().contains(name))
            {
                System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT +"[ID: " + i.getId() +"]" + " Name: " + i.getName() +"  Transport Type: "+i.getTransportType()+ " Address: " + i.getAddress() + " [Rating: " + i.getRating()+"]"+ ConsoleColors.RESET);
            }
        }
    }

    public static void SearchID(City city,int ID) throws SQLException, IOException {
        arr(city);
        for (Transport i : transportArray) {
            if(i.getId()==ID)
            {
                System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT +"[ID: " + i.getId() +"]" + " Name: " + i.getName() +"  Transport Type: "+i.getTransportType()+ " Address: " + i.getAddress() + " [Rating: " + i.getRating()+"]"+ ConsoleColors.RESET);
            }
        }
    }

    public static void display(City city) throws SQLException,IOException{
        arr(city);
        for (Transport i : transportArray) {
            System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT +"[ID: " + i.getId() +"]" + " Name: " + i.getName() +"  Transport Type: "+i.getTransportType()+ " Address: " + i.getAddress() + " [Rating: " + i.getRating()+"]"+ ConsoleColors.RESET);
        }
    }

    public static void getWeb(City city,int idd) throws SQLException,IOException,URISyntaxException
    {
        arr(city);
        for (Transport j : transportArray) {
            if (j.getId() == idd) {
                try {

                    URI uri = new URI(j.getContact());
                    System.out.println(uri);

                    java.awt.Desktop.getDesktop().browse(uri);
                    System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT +"Web page opened in browser"+ ConsoleColors.RESET);

                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        }
    }

    public static void getloc(City city,int idf) throws SQLException,IOException, URISyntaxException
    {
        arr(city);
        for (Transport j : transportArray) {
            if (j.getId() == idf) {
                try {

                    URI uri = new URI(j.getLocation());
                    System.out.println(uri);

                    java.awt.Desktop.getDesktop().browse(uri);
                    System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT +"Web page opened in browser"+ ConsoleColors.RESET);

                } catch (Exception e) {

                    e.printStackTrace();
                }
            }

        }
    }

    public static void main(City city) throws SQLException, IOException,URISyntaxException {

//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "Sm.963258741m");
//        Statement statement = connection.createStatement();
//
//        display(city);
        boolean isTrue=true;
        do{
            System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter 1.To Display Transport stations 2.To search Transport by ID 3.To search Transport by name 4.Contact Transport 5.To go to google maps 6.Exit Transport"+ ConsoleColors.RESET);

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int c = Integer.parseInt(br.readLine());
            switch (c) {
                case 1 -> {

                    System.out.println(ConsoleColors.PURPLE_UNDERLINED+"The details of different transport modes and stations in " + city.getCity() + " are:"+ ConsoleColors.RESET);
                    display(city);
                }
                case 2 -> {
                    System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter the id of the Transport station"+ ConsoleColors.RESET);
                    int id = Integer.parseInt(br.readLine());
                    SearchID(city,id);
                }
                case 3 -> {
                    System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter the name of the Transport station"+ ConsoleColors.RESET);
                    String name = br.readLine();
                    Searchname(city,name);
                }
                case 4 -> {
                    System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter the id of the Transport station"+ ConsoleColors.RESET);
                    int idd = Integer.parseInt(br.readLine());
                    getWeb(city,idd);
                }
                case 5 ->
                {
                    System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter the id of the transport station"+ ConsoleColors.RESET);
                    int idf = Integer.parseInt(br.readLine());
                    getloc(city,idf);
                }
                case 6 -> {
                    isTrue = false;
                    transportArray.clear();
                }
            }

        }
        while (isTrue);

    }
}
