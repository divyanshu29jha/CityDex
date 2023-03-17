package edu.Institutions.school;

import Console.ConsoleColors;
import city.City;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;

public class SchoolRunner {
    static ArrayList<school> schoolarray=new ArrayList<>();


    static double average;
    static int count;
    public static void arr(City city)throws SQLException,IOException {
        schoolarray.clear();
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "Sm.963258741m");

        Statement statement = connection.createStatement();
        String query = "select * from schools where city =" + "'" + city.getCity() + "'";
        ResultSet resultSet = statement.executeQuery(query);
        int id = 1;
        while (resultSet.next()) {
            school h = new school();
            h.setId(id);
            h.setName(resultSet.getString("NAME"));
            h.setCity(resultSet.getString("CITY"));
            h.setAddress(resultSet.getString("ADDRESS"));
            h.setWebsite(resultSet.getString("Website"));
            h.setRating(Double.parseDouble(resultSet.getString("Rating")));
            h.setFee(Integer.parseInt(resultSet.getString("Monthly_Fee")));
            h.setLocation(resultSet.getString("Location"));
            schoolarray.add(h);
            id++;
        }
    }

   public static void gtRating(City city,double v) throws SQLException, IOException {
        arr(city);
       for (school i : schoolarray) {
           if(i.getRating()>v)
           {
               System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT +"[ID: " + i.getId() + "]" + " Name: " + i.getName() + " Address: " + i.getAddress() + " [Rating: " + i.getRating() + "]"+ ConsoleColors.RESET);
           }
       }

    }

    public static void ltRating(City city,double v) throws SQLException, IOException {
        arr(city);
        for (school i : schoolarray) {
            if(i.getRating()<v)
            {
                System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT +"[ID: " + i.getId() + "]" + " Name: " + i.getName() + " Address: " + i.getAddress() + " [Rating: " + i.getRating() + "]"+ ConsoleColors.RESET);
            }
        }

    }

    public static void eqRating(City city,double v) throws SQLException, IOException {
        arr(city);
        for (school i : schoolarray) {
            if(i.getRating()==v)
            {
                System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT +"[ID: " + i.getId() + "]" + " Name: " + i.getName() + " Address: " + i.getAddress() + " [Rating: " + i.getRating() + "]"+ ConsoleColors.RESET);
            }
        }

    }

    public static void Searchname(City city,String name) throws SQLException, IOException {
        arr(city);
        for (school i : schoolarray) {
            if(i.getName().contains(name))
            {
                System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT +"[ID: " + i.getId() + "]" + " Name: " + i.getName() + " Address: " + i.getAddress() + " [Rating: " + i.getRating() + "]"+ ConsoleColors.RESET);
            }
        }

    }

    public static void SearchID(City city,int ID) throws SQLException, IOException {
        arr(city);
        for (school i : schoolarray) {
            if(i.getId()==ID)
            {
                System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT +"[ID: " + i.getId() + "]" + " Name: " + i.getName() + " Address: " + i.getAddress() + " [Rating: " + i.getRating() + "]"+ ConsoleColors.RESET);
            }
        }

    }

    public static void display(City city)throws SQLException,IOException{
        arr(city);
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "Sm.963258741m");

        Statement statement = connection.createStatement();

        String query2 = "select avg(Monthly_Fee) as Average from schools where city =" + "'" + city.getCity() + "'";
        ResultSet resultSet2 = statement.executeQuery(query2);

        while(resultSet2.next()) {
            average= Double.parseDouble(resultSet2.getString(1));
            System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT +"The average fee per month for a school in this city is :"+ ConsoleColors.RESET + average);
        }

        String query3="SELECT count(Address) from schools where city="+"'"+city.getCity()+"';";
        ResultSet resultSet3=statement.executeQuery(query3);
        while(resultSet3.next()) {
            count = Integer.parseInt(resultSet3.getString(1));
            System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT +"Total no. of schools in this city are :"+ ConsoleColors.RESET + count);
        }


        for (school i : schoolarray) {
            System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT +"[ID: " + i.getId() +"]" + " Name: " + i.getName() + " Address: " + i.getAddress() + " [Rating: " + i.getRating()+"]"+ ConsoleColors.RESET);
        }


    }

    public static void getWeb(City city,int idd) throws SQLException,IOException,URISyntaxException
    {
        arr(city);
        for (school j : schoolarray) {
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

    public static void getloc(City city,int idf) throws SQLException,IOException, URISyntaxException
    {
        arr(city);
        for (school j : schoolarray) {
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


    public static void main(City city) throws SQLException, IOException,URISyntaxException{


        boolean isTrue=true;
        do{
            System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter 1.To Display all the schools 2.To search schools by ID 3.To search school by name  4.To go to school's Webpage 5.To go to google maps 6.Exit schools"+ ConsoleColors.RESET);

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int c = Integer.parseInt(br.readLine());
            switch (c) {
                case 1 -> {

                    System.out.println(ConsoleColors.PURPLE_UNDERLINED +"The details of all the schools in " + city.getCity() + " are:"+ ConsoleColors.RESET);
                    display(city);
                }
                case 2->{
                    System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter ID of school"+ ConsoleColors.RESET);
                    int id=Integer.parseInt(br.readLine());
                    SearchID(city,id);
                }
                case 3->{
                    System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter name of school"+ ConsoleColors.RESET);
                    String name=br.readLine();
                    Searchname(city,name);
                }
                case 4 -> {
                    System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter the id of the school"+ ConsoleColors.RESET);
                    int idd = Integer.parseInt(br.readLine());
                    getWeb(city,idd);
                }
                case 5 ->
                {
                    System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter the id of the school"+ ConsoleColors.RESET);
                    int idf = Integer.parseInt(br.readLine());
                    getloc(city,idf);
                }
                case 6 -> {
                    isTrue = false;
                    schoolarray.clear();
                }
            }

        }
        while (isTrue);

    }
}

