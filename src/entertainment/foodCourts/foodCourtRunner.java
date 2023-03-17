package entertainment.foodCourts;

import Console.ConsoleColors;
import city.City;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;

public class foodCourtRunner {
    static ArrayList<foodCourt>foodCourtArray=new ArrayList<>();
    public static void arr(City city)throws SQLException,IOException {
        foodCourtArray.clear();
        Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","Sm.963258741m");

        Statement statement= connection.createStatement();
        String query="select * from fastfood where city ="+"'" + city.getCity() + "'";
        ResultSet resultSet=statement.executeQuery(query);
        int id=1;
        while(resultSet.next()) {
            foodCourt h = new foodCourt();
            h.setId(id);
            h.setName(resultSet.getString("Name"));
            h.setCity(resultSet.getString("City"));
            h.setAddress(resultSet.getString("address"));
            h.setLocation(resultSet.getString("location"));
            h.setRating(Double.parseDouble(resultSet.getString("rating")));
            foodCourtArray.add(h);
            id++;
        }
    }
    public static void gtRating(City city,double v) throws SQLException, IOException {
        arr(city);
        for (foodCourt i : foodCourtArray) {
            if(i.getRating()>v)
            {
                System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT +"[ID: " + i.getId() +"]" + " Name: " + i.getName() + " Address: " + i.getAddress() + " [Rating: " + i.getRating()+"]"+ ConsoleColors.RESET);
            }
        }
    }

    public static void ltRating(City city,double v) throws SQLException, IOException {
        arr(city);
        for (foodCourt i : foodCourtArray) {
            if(i.getRating()<v)
            {
                System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT +"[ID: " + i.getId() +"]" + " Name: " + i.getName() + " Address: " + i.getAddress() + " [Rating: " + i.getRating()+"]"+ ConsoleColors.RESET);
            }
        }
    }

    public static void eqRating(City city,double v) throws SQLException, IOException {
        arr(city);
        for (foodCourt i : foodCourtArray) {
            if(i.getRating()==v)
            {
                System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT +"[ID: " + i.getId() +"]" + " Name: " + i.getName() + " Address: " + i.getAddress() + " [Rating: " + i.getRating()+"]"+ ConsoleColors.RESET);
            }
        }
    }

    public static void Searchname(City city,String name) throws SQLException, IOException {
        arr(city);
        for (foodCourt i : foodCourtArray) {
            if(i.getName().contains(name))
            {
                System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT +"[ID: " + i.getId() +"]" + " Name: " + i.getName() + " Address: " + i.getAddress() + " [Rating: " + i.getRating()+"]"+ ConsoleColors.RESET);
            }
        }
    }

    public static void SearchID(City city,int ID) throws SQLException, IOException {
        arr(city);
        for (foodCourt i : foodCourtArray) {
            if(i.getId()==ID)
            {
                System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT +"[ID: " + i.getId() +"]" + " Name: " + i.getName() + " Address: " + i.getAddress() + " [Rating: " + i.getRating()+"]"+ ConsoleColors.RESET);
            }
        }
    }

    public static void display(City city) throws SQLException,IOException
    {
            arr(city);
        for (foodCourt i : foodCourtArray) {
            System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT +"[ID: " + i.getId() +"]" + " Name: " + i.getName() + " Address: " + i.getAddress() + " [Rating: " + i.getRating()+"]"+ ConsoleColors.RESET);
        }
    }

    public static void getloc(City city,int idf) throws SQLException,IOException, URISyntaxException
    {
        arr(city);
        for (foodCourt j : foodCourtArray) {
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


    public static void main(City city) throws SQLException, IOException,URISyntaxException
    {
//        Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","Sm.963258741m");
//
//        Statement statement= connection.createStatement();
        boolean isTrue=true;
        do{
            System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter 1.To Display all the Food Courts 2.To search food court by ID 3.To search food court by name 4.To go to google maps 5.Exit Food Courts"+ ConsoleColors.RESET);

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int c = Integer.parseInt(br.readLine());
            switch (c) {
                case 1 -> {

                    System.out.println(ConsoleColors.PURPLE_UNDERLINED +"The details of the Food Courts in " + city.getCity() + " are:"+ ConsoleColors.RESET);
                    display(city);
                }
                case 2->{
                    System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter ID of Food Court"+ ConsoleColors.RESET);
                    int id=Integer.parseInt(br.readLine());
                    SearchID(city,id);
                }
                case 3->{
                    System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter name of Food Court"+ ConsoleColors.RESET);
                    String name=br.readLine();
                    Searchname(city,name);
                }
                case 4 -> {
                    System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter the id of Food Court"+ ConsoleColors.RESET);
                    int idf = Integer.parseInt(br.readLine());
                    getloc(city,idf);
                }
                case 5 -> {
                    isTrue = false;
                    foodCourtArray.clear();
                }
            }

        }
        while (isTrue);

    }
}
