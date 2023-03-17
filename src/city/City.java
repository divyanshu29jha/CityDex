package city;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.sql.SQLException;
import Console.ConsoleColors;

public class City {

    private String city;

    public City(String city) {
        this.city = city;
    }

    public City() {

    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return
                "city='" + city ;
    }

    public static void main() throws IOException, SQLException, URISyntaxException {

        boolean istrue=true;
        do{
            City city = new City();
            CityRunner cityRunner = new CityRunner(city);
            System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT +"Select a city"+ ConsoleColors.RESET);
            System.out.println(ConsoleColors.PURPLE +"1."+ConsoleColors.CYAN_BOLD_BRIGHT + "Kolkata"+ ConsoleColors.RESET);
            System.out.println(ConsoleColors.PURPLE +"2."+ConsoleColors.CYAN_BOLD_BRIGHT + "Mumbai"+ ConsoleColors.RESET);
            System.out.println(ConsoleColors.PURPLE +"3."+ConsoleColors.CYAN_BOLD_BRIGHT + "Delhi"+ ConsoleColors.RESET);
            System.out.println(ConsoleColors.PURPLE +"4."+ConsoleColors.CYAN_BOLD_BRIGHT + "Chennai"+ ConsoleColors.RESET);
            System.out.println(ConsoleColors.PURPLE +"5."+ConsoleColors.CYAN_BOLD_BRIGHT + "Exit"+ ConsoleColors.RESET);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int s = Integer.parseInt(br.readLine());
            switch (s) {
                case 1 -> {
                    city.setCity("Kolkata");
                    cityRunner.runner();
                }
                case 2 -> {
                    city.setCity("Mumbai");
                    cityRunner.runner();
                }
                case 3 -> {
                    city.setCity("Delhi");
                    cityRunner.runner();
                }
                case 4 -> {
                    city.setCity("Chennai");
                    cityRunner.runner();
                }
                case 5 -> {
                    istrue = false;
                }
            }
    }while (istrue);
    }
}
