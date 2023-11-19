package ExerciseEight;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Launcher {
    public static void addToFile(){
        List<Country> countries = new ArrayList<>();
        Country country1 = new Country("VN", "Vietnam", "Asia", 331212.0, 97338579, 261921.9, 1);
        Country country2 = new Country("US", "United States", "North America", 9833517.0, 331002651, 21433225.0, 2);
        Country country3 = new Country("KR", "Korea", "Asia", 6000000.0, 5556968, 3030303.0, 1 );

        List<City> cities = new ArrayList<>();
        City city1 = new City(1, "Hanoi", 7781120);
        City city2 = new City(2, "Seoul", 335876);
        City city3 = new City(3, "WashingtonDC", 96897649);

        //File country
        countries.add(country1);
        countries.add(country2);
        countries.add(country3);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/ExerciseEight/countries.dat"))) {
            for (Country country : countries){
                oos.writeObject(country);
            }
            System.out.println("Saved to countries.dat" );
        } catch (IOException e) {
            e.printStackTrace();
        }
        //File City
        cities.add(city1);
        cities.add(city2);
        cities.add(city3);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/ExerciseEight/cities.dat"))) {
            for (City city : cities){
                oos.writeObject(city);
            }
            System.out.println("Saved to cities.dat" );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        addToFile();
        List<City> listCity = new ArrayList<>();
        List<Country> listCountry = new ArrayList<>();

        try {
            FileInputStream countryFile = new FileInputStream("src/ExerciseEight/countries.dat");
            ObjectInputStream countryInput = new ObjectInputStream(countryFile);

            FileInputStream cityFile = new FileInputStream("src/ExerciseEight/cities.dat");
            ObjectInputStream cityInput = new ObjectInputStream(cityFile);

            // Đọc danh sách quốc gia từ file
            while (true) {
                try {
                    Country country = (Country) countryInput.readObject();
                    listCountry.add(country);
                    // Thực hiện xử lý với đối tượng quốc gia đọc được
                    System.out.println(country.toString());
                } catch (EOFException | ClassNotFoundException e) {
                    break;
                }
            }

            // Đọc danh sách thành phố từ file
            while (true) {
                try {
                    City city = (City) cityInput.readObject();
                    // Thực hiện xử lý với đối tượng thành phố đọc được
                    listCity.add(city);
                    System.out.println(city.toString());
                } catch (EOFException | ClassNotFoundException e) {
                    break;
                }
            }
            countryInput.close();
            cityInput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
