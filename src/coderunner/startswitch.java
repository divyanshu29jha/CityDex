package coderunner;
import Csv2DbInserter.CollegeInserter;
import Csv2DbInserter.HospitalInserter;
import Csv2DbInserter.SchoolInserter;
import CsvUpdater.HospitalUpdater;
import CsvUpdater.SchoolUpdater;
import person.admin.AdminRunner;
import person.user.UserRunner;
import Console.ConsoleColors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.Scanner;

public class startswitch {

    public static void main() throws IOException, SQLException, URISyntaxException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "Sm.963258741m");

        Statement statement = connection.createStatement();


//        String query1 = "UPDATE people SET name = 'Lord' WHERE name='Alfred Schmidt';";
//        statement.executeUpdate(query1);

        ResultSet resultSet = statement.executeQuery("select * from adminprofiles");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        UserRunner q = new UserRunner();
        AdminRunner r=new AdminRunner();
        boolean istrue = true;

        do {
            System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter 1 if you are User (or) 2 if you are Admin  (or) 3 to Exit"+ ConsoleColors.RESET);
            int profile = Integer.parseInt(br.readLine());
            boolean real = true;
            boolean real2=true;

            switch (profile) {
                case 1:
                    do {
                        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter 1 to Login (or) 2 to Sign Up 3.Exit"+ ConsoleColors.RESET);
                        int action = Integer.parseInt(br.readLine());
                        switch (action) {
                            case 1 -> {
                                q.main();
                                q.login();
                            }
                            case 2 -> {
                                System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT +"You are trying to signup"+ ConsoleColors.RESET);
                                q.main();
                                q.signup();
                            }
                            case 3 -> real = false;
                        }
                    } while (real);


                    break;

                case 2: {
                    do{
                        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter 1 to Login (or) 2.Exit"+ ConsoleColors.RESET);
                        int action = Integer.parseInt(br.readLine());
                        switch(action) {
                            case 1: {
                                System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT +"You are Admin..."+ ConsoleColors.RESET);
                               // r.main();
                                String Adminuser,Pass;
                                System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter Username of Admin :"+ ConsoleColors.RESET);
                                Adminuser=br.readLine();
                                System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter password :"+ ConsoleColors.RESET);
                                Pass= br.readLine();
                                r.login(Adminuser,Pass);
                                System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter 1.To check requests (or) 2.To upload CSV into database (or) 3.To update hospitals (or) 4.To update schools (or) 5.Delete userprofiles (or) 6.Delete facility 7.Exit"+ ConsoleColors.RESET);
                                int admin = Integer.parseInt(br.readLine());
                                boolean real3 = true;
                                do{
                                    switch(admin){
                                        case 1:
                                            r.requestManager();
                                            real3=false;
                                            break;
                                        case 2:
                                            CollegeInserter clg = new CollegeInserter();
                                            HospitalInserter h2 = new HospitalInserter();
                                            SchoolInserter s = new SchoolInserter();

                                            h2.main();
                                            clg.main();
                                            s.main();
                                            real3 = false;
                                           // main();
                                            break;

                                        case 3:
                                            Scanner sc = new Scanner(System.in);
                                            System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter the name of hospital: "+ ConsoleColors.RESET);
                                            String nm = sc.nextLine();
                                            System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter the address: "+ ConsoleColors.RESET);
                                            String add = sc.nextLine();
                                            System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter the speciality: "+ ConsoleColors.RESET);
                                            String spl = sc.nextLine();
                                            System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter the website: "+ ConsoleColors.RESET);
                                            String web = sc.nextLine();
                                            HospitalUpdater.load_from_hospitals_csv(nm,add,spl,web);
                                            real3=false;
                                            //main();
                                            break;

                                        case 4:
                                            Scanner sc2 = new Scanner(System.in);
                                            System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter the name of school: "+ ConsoleColors.RESET);
                                            String nm2 = sc2.nextLine();
                                            System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter the address: "+ ConsoleColors.RESET);
                                            String add2 = sc2.nextLine();
                                            System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter the name of principal: "+ ConsoleColors.RESET);
                                            String prncpl = sc2.nextLine();
                                            System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Enter the website: "+ ConsoleColors.RESET);
                                            String web2 = sc2.nextLine();
                                            SchoolUpdater.load_from_schools_csv(nm2,add2,prncpl,web2);
                                            real3=false;
                                            break;

                                        case 5:
                                            r.usermanager();
                                            real3=false;
                                            break;
                                        case 6:
                                        {   System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"TO delete data from database \n 1.Hotels 2.Hospital 3.Transport 4.Exit"+ ConsoleColors.RESET);
                                            int delin = Integer.parseInt(br.readLine());
                                            boolean delbreak = true;
                                            do {
                                                boolean boo = true,bob = true,bol=true;
                                                switch (delin) {
                                                    case 1 -> {
                                                        bob = AdminRunner.hotelmanager();
                                                        break;
                                                    }
                                                    case 4 -> {
                                                        delbreak = false;
                                                        break;
                                                    }
                                                    case 2 -> {
                                                        boo = AdminRunner.hospitalmanager();
                                                        break;
                                                    }
                                                    case 3 ->{
                                                        bol = AdminRunner.transportmanager();
                                                        break;
                                                    }

                                                }
                                                if (!boo || !bob || !bol) {
                                                    delbreak=false;
                                                }
                                            } while (delbreak) ;
                                            real3=false;
                                            break;
                                        }

                                        case 7:
                                            real3 = false;
                                            break;
                                    }
                                } while (real3);
                                break;
                            }
                            case 2:real=false;
                                break;
                        }
                    }while(real);
                    break;
                }
                case 3: {
                    istrue = false;
                    break;
                }


            }


        } while (istrue);
    }
}


