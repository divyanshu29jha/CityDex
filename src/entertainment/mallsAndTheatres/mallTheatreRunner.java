package entertainment.mallsAndTheatres;
import city.City;
import Console.ConsoleColors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;


public class mallTheatreRunner {
    static ArrayList<mallTheatre> mallTheatreArray=new ArrayList<>();
    public static void arr(City city)throws SQLException,IOException{
        mallTheatreArray.clear();
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "Sm.963258741m");
        Statement statement = connection.createStatement();

        String query = "select * from mall_theatre where city =" + "'" + city.getCity() + "'";
        ResultSet resultSet = statement.executeQuery(query);
        int id = 1;
        while (resultSet.next()) {
            mallTheatre h = new mallTheatre();
            h.setId(id);
            h.setName(resultSet.getString("Name"));
            h.setCity(resultSet.getString("City"));
            h.setType(resultSet.getString("Type"));
            h.setAddress(resultSet.getString("Address"));
            h.setRating(Double.parseDouble(resultSet.getString("Rating")));
            h.setLocation(resultSet.getString("Location"));
            mallTheatreArray.add(h);
            id++;
        }

    }
    public static void gtRating(City city,double v) throws SQLException, IOException {
        arr(city);
        for (mallTheatre i : mallTheatreArray) {
            if(i.getRating()>v)
            {
                System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT +"[ID: " + i.getId() +"]" + " Name: " + i.getName() + " Type: "+ i.getType()+ " Address: " + i.getAddress() + " [Rating: " + i.getRating()+"]"+ ConsoleColors.RESET);
            }
        }
    }

    public static void ltRating(City city,double v) throws SQLException, IOException {
        arr(city);
        for (mallTheatre i : mallTheatreArray) {
            if(i.getRating()<v)
            {
                System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT +"[ID: " + i.getId() +"]" + " Name: " + i.getName() + " Type: "+ i.getType()+ " Address: " + i.getAddress() + " [Rating: " + i.getRating()+"]"+ ConsoleColors.RESET);
            }
        }
    }

    public static void eqRating(City city,double v) throws SQLException, IOException {
        arr(city);
        for (mallTheatre i : mallTheatreArray) {
            if(i.getRating()==v)
            {
                System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT +"[ID: " + i.getId() +"]" + " Name: " + i.getName() + " Type: "+ i.getType()+ " Address: " + i.getAddress() + " [Rating: " + i.getRating()+"]"+ ConsoleColors.RESET);
            }
        }
    }

    public static void Searchname(City city,String name) throws SQLException, IOException {
        arr(city);
        for (mallTheatre i : mallTheatreArray) {
            if(i.getName().contains(name))
            {
                System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT +"[ID: " + i.getId() +"]" + " Name: " + i.getName() + " Type: "+ i.getType()+ " Address: " + i.getAddress() + " [Rating: " + i.getRating()+"]"+ ConsoleColors.RESET);
            }
        }
    }

    public static void SearchID(City city,int ID) throws SQLException, IOException {
        arr(city);
        for (mallTheatre i : mallTheatreArray) {
            if(i.getId()==ID)
            {
                System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT +"[ID: " + i.getId() +"]" + " Name: " + i.getName() + " Type: "+ i.getType()+ " Address: " + i.getAddress() + " [Rating: " + i.getRating()+"]"+ ConsoleColors.RESET);
            }
        }

    }

    public static void display(City city) throws SQLException,IOException{
        arr(city);
        for (mallTheatre i : mallTheatreArray) {
            System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT +"[ID: " + i.getId() +"]" + " Name: " + i.getName() + " Type: "+ i.getType()+ " Address: " + i.getAddress() + " [Rating: " + i.getRating()+"]"+ ConsoleColors.RESET);
        }
    }

    public static void getloc(City city,int idf) throws SQLException,IOException
    {
        arr(city);
        for (mallTheatre j : mallTheatreArray) {
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


    public static void main(City city) throws SQLException, IOException, URISyntaxException
    {
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "Sm.963258741m");
//
//        Statement statement = connection.createStatement();

        //display(city);

        boolean isTrue=true;
        do{
            System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter 1.To Display all the Malls and Theatres 2.To search malls & Theatre by ID  3.To search malls & Theatre by name 4.To go to google maps 5.Exit Malls and Theatres"+ ConsoleColors.RESET);

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int c = Integer.parseInt(br.readLine());
            switch (c) {
                case 1 -> {

                    System.out.println(ConsoleColors.PURPLE_UNDERLINED +"The details of the Malls and Theatre in " + city.getCity() + " are:"+ ConsoleColors.RESET);
                    display(city);
                }
                case 2 -> {
                    System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter the id of the mall or theatre"+ ConsoleColors.RESET);
                    int id = Integer.parseInt(br.readLine());
                    SearchID(city,id);
                }
                case 3 -> {
                    System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter the name of the mall or theatre"+ ConsoleColors.RESET);
                    String name =br.readLine();
                    Searchname(city,name);
                }
                case 4 -> {
                    System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter the id of the mall or theatre"+ ConsoleColors.RESET);
                    int idf = Integer.parseInt(br.readLine());
                    getloc(city,idf);
                }
                case 5 -> {
                    isTrue = false;
                    mallTheatreArray.clear();
                }
            }

        }
        while (isTrue);

    }
}


