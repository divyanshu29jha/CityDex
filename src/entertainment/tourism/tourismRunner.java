package entertainment.tourism;
import city.City;
import Console.ConsoleColors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;


public class tourismRunner {
    static ArrayList<tourism> tourismArray =new ArrayList<>();
    public static void arr(City city)throws SQLException,IOException{
        tourismArray.clear();
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "Sm.963258741m");

        Statement statement = connection.createStatement();


        String query = "select * from tourism where city =" + "'" + city.getCity() + "'";
        ResultSet resultSet = statement.executeQuery(query);
        int id = 1;
        while (resultSet.next()) {
            tourism h = new tourism();
            h.setId(id);
            h.setName(resultSet.getString("Name"));
            h.setCity(resultSet.getString("City"));
            h.setType(resultSet.getString("Type"));
            h.setAddress(resultSet.getString("Address"));
            h.setRating(Double.parseDouble(resultSet.getString("Rating")));
            h.setLocation(resultSet.getString("Location"));
            tourismArray.add(h);
            id++;
        }
    }
    public static void gtRating(City city,double v) throws SQLException, IOException {
        arr(city);
        for (tourism i : tourismArray) {
            if(i.getRating()>v)
            {
                System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT +"[ID: " + i.getId() +"]" + " Name: " + i.getName() + " Type: "+ i.getType()+ " Address: " + i.getAddress() + " [Rating: " + i.getRating()+"]"+ ConsoleColors.RESET);
            }
        }

    }

    public static void ltRating(City city,double v) throws SQLException, IOException {
        arr(city);
        for (tourism i : tourismArray) {
            if(i.getRating()<v)
            {
                System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT +"[ID: " + i.getId() +"]" + " Name: " + i.getName() + " Type: "+ i.getType()+ " Address: " + i.getAddress() + " [Rating: " + i.getRating()+"]"+ ConsoleColors.RESET);
            }
        }

    }

    public static void eqRating(City city,double v) throws SQLException, IOException {
        arr(city);
        for (tourism i : tourismArray) {
            if(i.getRating()==v)
            {
                System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT +"[ID: " + i.getId() +"]" + " Name: " + i.getName() + " Type: "+ i.getType()+ " Address: " + i.getAddress() + " [Rating: " + i.getRating()+"]"+ ConsoleColors.RESET);
            }
        }

    }

    public static void Searchname(City city,String name) throws SQLException, IOException {
        arr(city);
        for (tourism i : tourismArray) {
            if(i.getName().contains(name))
            {
                System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT +"[ID: " + i.getId() +"]" + " Name: " + i.getName() + " Type: "+ i.getType()+ " Address: " + i.getAddress() + " [Rating: " + i.getRating()+"]"+ ConsoleColors.RESET);
            }
        }

    }

    public static void SearchID(City city,int ID) throws SQLException, IOException {
        arr(city);
        for (tourism i : tourismArray) {
            if(i.getId()==ID)
            {
                System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT +"[ID: " + i.getId() +"]" + " Name: " + i.getName() + " Type: "+ i.getType()+ " Address: " + i.getAddress() + " [Rating: " + i.getRating()+"]"+ ConsoleColors.RESET);
            }
        }

    }

    public static void display(City city)throws SQLException,IOException {

        arr(city);
        for (tourism i : tourismArray) {
            System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT +"[ID: " + i.getId() +"]" + " Name: " + i.getName() + " Type: "+ i.getType()+ " Address: " + i.getAddress() + " [Rating: " + i.getRating()+"]"+ ConsoleColors.RESET);
        }
    }

    public static void getloc(City city,int idf) throws SQLException,IOException, URISyntaxException
    {
        arr(city);
        for (tourism j : tourismArray) {
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

    public static void main(City city) throws SQLException, IOException ,URISyntaxException
    {
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "Sm.963258741m");
//
//        Statement statement = connection.createStatement();
//
//        //display(city);

        boolean isTrue=true;
        do{
            System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter 1.To Display all the Toursism 2.To search Tourist places by ID  3.To search tourist places by name 4.To go to google maps 5.Exit Tourism"+ ConsoleColors.RESET);

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int c = Integer.parseInt(br.readLine());
            switch (c) {
                case 1 -> {

                    System.out.println(ConsoleColors.PURPLE_UNDERLINED +"The details of all the tourism places in " + city.getCity() + " are:"+ ConsoleColors.RESET);
                    display(city);
                }
                case 2 -> {
                    System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter the id of the tourism place"+ ConsoleColors.RESET);
                    int id = Integer.parseInt(br.readLine());
                    SearchID(city,id);
                }
                case 3 -> {
                    System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter the name of the tourism place"+ ConsoleColors.RESET);
                    String name = br.readLine();
                    Searchname(city,name);
                }
                case 4 -> {
                    System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter the id of the tourism place"+ ConsoleColors.RESET);
                    int idf = Integer.parseInt(br.readLine());
                    getloc(city,idf);
                }
                case 5 -> {
                    isTrue = false;
                    tourismArray.clear();
                }
            }

        }
        while (isTrue);

    }
}


