
package person.user;

import Console.ConsoleColors;
import city.City;
import requestr.Requester;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;

public class UserRunner {

    static ArrayList<User> arr= new ArrayList<>();
    static void displayUsers(){
        for (User i: arr) {

            System.out.println(i);

        }
    }

    public static void updateprofile(User i) throws SQLException, IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));

        //main();
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","Sm.963258741m");

        Statement statement=connection.createStatement();

        boolean isTrue=true;

        while (isTrue)
        {
            System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Select 1)Update username 2)Update password 3)Update Email 4)Update Name 5)Update City 6)Update Contact 7)Display userprofile 8)Exit"+ ConsoleColors.RESET);
            int selection = Integer.parseInt(br.readLine());
            switch(selection){
                case 1: {
                    try {
                        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter your new username:"+ ConsoleColors.RESET);
                        String k = i.getUsername();
                        i.setUsername(br.readLine());
                        String query1 = "UPDATE userprofiles SET Username =" + "\'" + i.getUsername() + "\'" + " WHERE Username=" + "\'" + k + "\'" + ";";
                        statement.executeUpdate(query1);
                    } catch (Exception e) {
                        System.out.println(e);
                    }

                    break;
                }
                case 2: {
                    try {
                        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter your old password"+ ConsoleColors.RESET);
                        String str = br.readLine();
                        if (str.equals(i.getPassword())) {
                            System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter your new password:"+ ConsoleColors.RESET);
                            i.setPassword(br.readLine());
                            System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Confirm your new password:"+ ConsoleColors.RESET);
                            String h = br.readLine();
                            if (h.equals(i.getPassword())) {
                                String query2 = "UPDATE userprofiles SET Password =" + "\'" + i.getPassword() + "\'" + " WHERE Username=" + "\'" + i.getUsername() + "\'" + ";";
                                statement.executeUpdate(query2);
                            }
                        }
                    }
                    catch (Exception e){
                        System.out.println(e);
                    }

                    break;
                }
                case 3: {
                    try {
                        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter your new email:"+ ConsoleColors.RESET);
                        i.setMail(br.readLine());
                        String query3 = "UPDATE userprofiles SET Email =" + "\'" + i.getMail() + "\'" + " WHERE Username=" + "\'" + i.getUsername() + "\'" + ";";
                        statement.executeUpdate(query3);
                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                    break;
                }
                case 4: {
                    try{  System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter a new name:"+ ConsoleColors.RESET);
                        i.setName(br.readLine());
                        String query4 = "UPDATE userprofiles SET Name =" + "\'" + i.getName() + "\'" + " WHERE Username=" + "\'" + i.getUsername() + "\'" + ";";
                        statement.executeUpdate(query4);
                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                    break;}
                case 5: {
                    try{System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter your city:"+ ConsoleColors.RESET);
                        i.setCity(br.readLine());
                        String query5 = "UPDATE userprofiles SET City =" + "\'" + i.getCity() + "\'" + " WHERE Username=" + "\'" + i.getUsername() + "\'" + ";";
                        statement.executeUpdate(query5);}
                    catch (Exception e){
                        System.out.println(e);
                    }
                    break;
                }
                case 6:
                {
                    try
                    {
                        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter your Contact no."+ ConsoleColors.RESET);
                        i.setContact(Long.parseLong(br.readLine()));
                        String query6 = "UPDATE userprofiles SET Contact =" +  + i.getContact()  + " WHERE Username=" + "\'" + i.getUsername() + "\'" + ";";
                        statement.executeUpdate(query6);
                    }
                    catch (Exception e)
                    {
                        System.out.println(e);
                    }
                }
                case 7:{
                    System.out.println("Name-"+i.getName());
                    System.out.println("Username-"+i.getUsername());
                    System.out.println("Email-"+i.getMail());
                    System.out.println("City-"+i.getCity());
                    System.out.println("User ID-"+i.getId());
                    System.out.println("Contact-"+i.getContact());
                }
                case 8: {
                    isTrue = false;
                    break;
                }

            }

        }

    }

   public static void signup() throws SQLException, IOException {
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","Sm.963258741m");

        Statement statement=connection.createStatement();



        ResultSet resultSet= statement.executeQuery("select * from userprofiles");
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter your name:"+ ConsoleColors.RESET);
            String name = br.readLine();
            System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter your username:"+ ConsoleColors.RESET);
            String unmae = br.readLine();
            System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter the password:"+ ConsoleColors.RESET);
            String pass = br.readLine();
            System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter your City:"+ ConsoleColors.RESET);
            String city = br.readLine();
            System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter your Contact no:"+ ConsoleColors.RESET);
            long contact=Long.parseLong(br.readLine());
            boolean ffl=true;
            do{
                ffl=true;
                System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter your mail:"+ ConsoleColors.RESET);
            String mail = br.readLine();

            User u = new User();
            u.setName(name);
            u.setUsername(unmae);
            u.setPassword(pass);
            u.setCity(city);
            u.setMail(mail);
            u.setContact(contact);
            if (mail.contains("@") && mail.contains(".com")) {
                String query = "INSERT INTO userprofiles (Name, UserName, Email, Password, City,Contact) Values(" + "\'" + name + "\'," + "\'" + unmae + "\'," + "\'" + mail + "\'," + "\'" + pass + "\'," + "\'" + city + "\'," + u.getContact() +");";
                ffl=false;
                statement.executeUpdate(query);
                System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT +"You have successfully signed up!!!"+ ConsoleColors.RESET);
                System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter 1 to Update your profile (or) 2 Login : "+ ConsoleColors.RESET);
                int crud = Integer.parseInt(br.readLine());

                switch (crud) {

                    case 1: {
                        System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT +"You are updating your profile!!"+ ConsoleColors.RESET);
                        updateprofile(u);
                        break;
                    }
                    case 2: {

                        break;

                    }
                }
            } else {
                System.out.println(ConsoleColors.RED_BOLD_BRIGHT +"Enter valid email!!!"+ ConsoleColors.RESET);
            }
        }while(ffl);
        }
        catch (Exception e){
            System.out.println(e);
        }


    }
    public static void login() throws IOException, SQLException, URISyntaxException {
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","Sm.963258741m");

        Statement statement=connection.createStatement();



        ResultSet resultSet= statement.executeQuery("select * from userprofiles");
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        Requester r=new Requester();
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter the Username: "+ ConsoleColors.RESET);

        String uname=br.readLine();
        boolean flag=false;
        for(User i:arr){
            if(i.getUsername().equals(uname)){
                flag=true;
            }
        }

        if(flag) {
            System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter the password : "+ ConsoleColors.RESET);
            String admin_password = br.readLine();
            boolean flaag = true;
            int kk=1;
            do {
                int jr = 1;
                for (User i : arr) {
                    boolean Istrue = false;

                    flaag = true;
                    if (i.getUsername().equals(uname)) {
                        jr = 1;
                        if (i.getPassword().equals(admin_password) && kk == 1) {
                            System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT +"You have logged in successfully!!"+ ConsoleColors.RESET);
                            Istrue = true;
                            flaag = false;
                            jr=0;

                            do {
                                System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter 1.To Update your profile (or) 2.Select a city to use CITYDEX (or) 3.Request for hosting (or) 4.Logout "+ ConsoleColors.RESET);
                                int crud = Integer.parseInt(br.readLine());

                                switch (crud) {
                                    case 1:
                                        System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT +"You are updating your profile..."+ ConsoleColors.RESET);
                                        updateprofile(i);

                                        break;
                                    case 2:
                                        City.main();
                                        break;
                                    case 3:
                                        r.request(i);
                                        break;
                                    case 4:
                                        System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT +"Logged out!"+ ConsoleColors.RESET);
                                        Istrue = false;
                                        kk = 0;
                                        break;

                                }
                            } while (Istrue);
                        }

                    }

                }

                if(jr==1){
                    System.out.println(ConsoleColors.RED_BOLD_BRIGHT +"Incorrect password"+ ConsoleColors.RESET);
                    flaag= false;
                }
                if (kk == 0) {
                    flaag = false;
                }
            }while (flaag);
        }
        else{
            System.out.println(ConsoleColors.RED_BOLD_BRIGHT +"Enter valid Username"+ ConsoleColors.RESET);
        }


    }

    public static void main() throws SQLException {
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","Sm.963258741m");

        Statement statement=connection.createStatement();


        //arr.clear();
        ResultSet resultSet= statement.executeQuery("select * from userprofiles");
        int i=1;

        while (resultSet.next()) {
            User u =new User();

            String k = resultSet.getString("Name");
            u.setId(i);
            u.setName(resultSet.getString("Name"));
            u.setCity(resultSet.getString("City"));
            u.setPassword(resultSet.getString("Password"));
            u.setUsername(resultSet.getString("Username"));
            u.setMail(resultSet.getString("Email"));
            u.setContact(resultSet.getLong("Contact"));

            i++;
            arr.add(u);


        }


    }
}