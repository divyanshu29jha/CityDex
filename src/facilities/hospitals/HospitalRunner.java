package facilities.hospitals;

import Console.ConsoleColors;
import city.City;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;

public class HospitalRunner {
    static ArrayList<Hospital> hospitalarray=new ArrayList<>();
    public static void arr(City city)throws SQLException,IOException{
        hospitalarray.clear();
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "Sm.963258741m");

        Statement statement = connection.createStatement();


        String query = "select * from hospitals where city =" + "'" + city.getCity() + "'";
        ResultSet resultSet = statement.executeQuery(query);
        int id = 1;
        while (resultSet.next()) {
            Hospital h = new Hospital();
            h.setId(id);
            h.setName(resultSet.getString("Name"));
            h.setCity(resultSet.getString("City"));
            h.setSpeciality(resultSet.getString("Speciality"));
            h.setAddress(resultSet.getString("Address"));
            h.setContact(resultSet.getString("Website"));
            h.setRating(Double.parseDouble(resultSet.getString("Rating")));
            h.setLocation(resultSet.getString("Location"));
            hospitalarray.add(h);
            id++;
        }
    }

    public static void gtRating(City city,double v) throws SQLException, IOException {
        arr(city);
        for (Hospital i : hospitalarray) {
            if(i.getRating()>v)
            {
                System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT +"[ID: " + i.getId() +"]" + " Name: " + i.getName() + " Address: " + i.getAddress() + " [Rating: " + i.getRating()+"]"+ ConsoleColors.RESET);
            }
        }
    }

    public static void ltRating(City city,double v) throws SQLException, IOException {
        arr(city);
        for (Hospital i : hospitalarray) {
            if(i.getRating()<v)
            {
                System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT +"[ID: " + i.getId() +"]" + " Name: " + i.getName() + " Address: " + i.getAddress() + " [Rating: " + i.getRating()+"]"+ ConsoleColors.RESET);
            }
        }
    }

    public static void eqRating(City city,double v) throws SQLException, IOException {
        arr(city);
        for (Hospital i : hospitalarray) {
            if(i.getRating()==v)
            {
                System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT +"[ID: " + i.getId() +"]" + " Name: " + i.getName() + " Address: " + i.getAddress() + " [Rating: " + i.getRating()+"]"+ ConsoleColors.RESET);
            }
        }
    }

    public static void Searchname(City city,String name) throws SQLException, IOException {
        arr(city);
        for (Hospital i : hospitalarray) {
            if(i.getName().contains(name))
            {
                System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT +"[ID: " + i.getId() +"]" + " Name: " + i.getName() + " Address: " + i.getAddress() + " [Rating: " + i.getRating()+"]"+ ConsoleColors.RESET);
            }
        }
    }

    public static void SearchID(City city,int ID) throws SQLException, IOException {
        arr(city);
        for (Hospital i : hospitalarray) {
            if(i.getId()==ID)
            {
                System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT +"[ID: " + i.getId() +"]" + " Name: " + i.getName() + " Address: " + i.getAddress() + " [Rating: " + i.getRating()+"]"+ ConsoleColors.RESET);
            }
        }
    }

    public static void display(City city)throws SQLException,IOException {
        arr(city);
        for (Hospital i : hospitalarray) {
            System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT +"[ID: " + i.getId() +"]" + " Name: " + i.getName() + " Address: " + i.getAddress() + " [Rating: " + i.getRating()+"]"+ ConsoleColors.RESET);
        }
    }

    public static void getWeb(City city,int idd) throws SQLException,IOException,URISyntaxException
    {
        arr(city);
        for (Hospital j : hospitalarray) {
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

    public static void getloc(City city,int idf) throws SQLException,IOException
    {
        arr(city);
        for (Hospital j : hospitalarray) {
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


    public static void main(City city) throws SQLException, IOException , URISyntaxException {
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "Sm.963258741m");
//
//        Statement statement = connection.createStatement();
//
//        display(city);
        boolean isTrue=true;
        do{
            System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter 1.To Display all the hospital 2.To search hospital by ID 3.To search hospital by name 4.To go to hospital's Webpage 5.To go to google maps 6.Exit hospital"+ ConsoleColors.RESET);

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int c = Integer.parseInt(br.readLine());
            switch (c) {
                case 1 -> {

                    System.out.println(ConsoleColors.PURPLE_UNDERLINED +"The details of all the hospital in " + city.getCity() + " are:"+ ConsoleColors.RESET);
                    display(city);
                }
                case 2 -> {
                    System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter the id of the hospital"+ ConsoleColors.RESET);
                    int id = Integer.parseInt(br.readLine());
                    SearchID(city,id);
                }
                case 3 -> {
                    System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter the name of the hospital"+ ConsoleColors.RESET);
                    String name =br.readLine();
                    Searchname(city,name);
                }
                case 4 -> {
                    System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter the id of the hospital"+ ConsoleColors.RESET);
                    int idd = Integer.parseInt(br.readLine());
                    getWeb(city,idd);
                }
                case 5 -> {
                    System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter the id of the hospital"+ ConsoleColors.RESET);
                    int idf = Integer.parseInt(br.readLine());
                    getloc(city,idf);
                }
                case 6 -> {
                    isTrue = false;
                    hospitalarray.clear();
                }
            }

        }
        while (isTrue);

    }
}


