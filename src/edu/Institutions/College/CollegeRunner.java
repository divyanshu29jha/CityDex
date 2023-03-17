package edu.Institutions.College;

import Console.ConsoleColors;
import city.City;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;

public class CollegeRunner {
    static ArrayList<College> Collegearray=new ArrayList<>();

    static double average;
    static int count;
    public static void arr(City city)throws SQLException,IOException {
        Collegearray.clear();
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "Sm.963258741m");

        Statement statement = connection.createStatement();

        String query = "select * from colleges where city =" + "'" + city.getCity() + "'";
        ResultSet resultSet = statement.executeQuery(query);
        int id = 1;
        while (resultSet.next()) {
            College h = new College();
            h.setId(id);
            h.setName(resultSet.getString("Name"));
            h.setCity(resultSet.getString("City"));
            h.setAddress(resultSet.getString("Address"));
            h.setWebsite(resultSet.getString("Website"));
            h.setRating(Double.parseDouble(resultSet.getString("Rating")));
            h.setFee(Integer.parseInt(resultSet.getString("Semester_Fee")));
            h.setLocation(resultSet.getString("Location"));
            Collegearray.add(h);
            id++;
        }

    }

    public static void gtRating(City city,double v) throws SQLException, IOException {
        arr(city);
        for (College i : Collegearray) {
            if(i.getRating()>v)
            {
                System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT +"[ID: " + i.getId() +"]" + " Name: " + i.getName() + " Address: " + i.getAddress() + " [Rating: " + i.getRating()+"]"+ ConsoleColors.RESET);

            }
        }

    }

    public static void ltRating(City city,double v) throws SQLException, IOException {
        arr(city);
        for (College i : Collegearray) {
            if(i.getRating()<v)
            {
                System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT +"[ID: " + i.getId() +"]" + " Name: " + i.getName() + " Address: " + i.getAddress() + " [Rating: " + i.getRating()+"]"+ ConsoleColors.RESET);

            }
        }

    }

    public static void eqRating(City city,double v) throws SQLException, IOException {
        arr(city);
        for (College i : Collegearray) {
            if(i.getRating()==v)
            {
                System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT +"[ID: " + i.getId() +"]" + " Name: " + i.getName() + " Address: " + i.getAddress() + " [Rating: " + i.getRating()+"]"+ ConsoleColors.RESET);

            }
        }

    }

    public static void Searchname(City city,String name) throws SQLException, IOException {
        arr(city);
        for (College i : Collegearray) {
            if(i.getName().contains(name))
            {
                System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT +"[ID: " + i.getId() +"]" + " Name: " + i.getName() + " Address: " + i.getAddress() + " [Rating: " + i.getRating()+"]"+ ConsoleColors.RESET);

            }
        }

    }

    public static void SearchID(City city,int ID) throws SQLException, IOException {
        //   Collegearray.clear();
        arr(city);
        for (College i : Collegearray) {
            if(i.getId()==ID)
            {
                System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT +"[ID: " + i.getId() +"]" + " Name: " + i.getName() + " Address: " + i.getAddress() + " [Rating: " + i.getRating()+"]"+ ConsoleColors.RESET);

            }
        }

    }

    public static void display(City city)throws SQLException,IOException {
        arr(city);
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "Sm.963258741m");

        Statement statement = connection.createStatement();

        String query2 = "select avg(Semester_Fee) as Average from colleges where city =" + "'" + city.getCity() + "'";
        ResultSet resultSet2 = statement.executeQuery(query2);

        while(resultSet2.next()) {
            average= Double.parseDouble(resultSet2.getString(1));
            System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT +"The average fee per semester for a college in this city is :"+ ConsoleColors.RESET + average);
        }
        String query3="SELECT count(Address) from colleges where city="+"'"+city.getCity()+"';";
        ResultSet resultSet3=statement.executeQuery(query3);
        while(resultSet3.next()) {
            count = Integer.parseInt(resultSet3.getString(1));
            System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT +"Total no. of colleges in this city are :"+ ConsoleColors.RESET + count);
        }
        for (College i : Collegearray) {
            System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT +"[ID: " + i.getId() +"]" + " Name: " + i.getName() + " Address: " + i.getAddress() + " [Rating: " + i.getRating()+"]"+ ConsoleColors.RESET);
        }

    }

    public static void getWeb(City city,int idd) throws SQLException,IOException,URISyntaxException
    {
        arr(city);
        for (College j : Collegearray) {
            if (j.getId() == idd) {
                try {

                    URI uri = new URI(j.getWebsite());
                    System.out.println(uri);

                    java.awt.Desktop.getDesktop().browse(uri);
                    System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT +"Web page opened in browser"+ ConsoleColors.RESET);

                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        }
    }

    public static void getloc(City city,int idf) throws SQLException,IOException,URISyntaxException
    {
        arr(city);
        for (College j : Collegearray) {
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


        boolean isTrue=true;
        do{
            System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter 1.To Display all the colleges 2.To Search colleges by ID 3.To Search colleges by name 4.To go to college's Webpage 5.To go to google maps 6.Exit College"+ ConsoleColors.RESET);

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int c = Integer.parseInt(br.readLine());
            switch (c) {
                case 1 -> {
                    System.out.println(ConsoleColors.PURPLE_UNDERLINED +"The details of all the Collges in " + city.getCity() + " are:"+ ConsoleColors.RESET);
                    display(city);
                }
                case 2->{
                    System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter ID of college"+ ConsoleColors.RESET);
                    int id=Integer.parseInt(br.readLine());
                    SearchID(city,id);
                }
                case 3->{
                    System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter name of college"+ ConsoleColors.RESET);
                    String name=br.readLine();
                    Searchname(city,name);
                }
                case 4 -> {
                    System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter the id of the College"+ ConsoleColors.RESET);
                    int idd = Integer.parseInt(br.readLine());
                    getWeb(city,idd);

                }
                case 5 -> {
                    System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter the id of the College"+ ConsoleColors.RESET);
                    int idf = Integer.parseInt(br.readLine());
                    getloc(city,idf);

                }
                case 6 -> {
                    isTrue = false;
                    Collegearray.clear();
                }
            }

        }
        while (isTrue);

    }
}

