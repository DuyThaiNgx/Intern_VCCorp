package ExerciseEight;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Launcher {
    public static void addToFile(String filePath){
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
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
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
        String countriesFile = "src/ExerciseEight/countries.dat";
        String citiesFile = "src/ExerciseEight/cities.dat";
        addToFile(countriesFile);
        addToFile(citiesFile);

        List<City> listCity = new ArrayList<>();
        List<Country> listCountry = new ArrayList<>();

        try {
            FileInputStream countryFile = new FileInputStream(citiesFile);
            ObjectInputStream countryInput = new ObjectInputStream(countryFile);

            FileInputStream cityFile = new FileInputStream(citiesFile);
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

        findMostPopulousCityPerCountry(listCountry, listCity);
        findMostPopulousCityPerContinent(listCountry, listCity);
        findMostPopulousCapitalCity(listCountry, listCity);
        findMostPopulousCapitalCityPerContinent(listCountry, listCity);
    }
    private static void findMostPopulousCityPerCountry(List<Country>listCountry, List<City> listCity) {
        Map<String, City> mostPopulousCitiesPerCountry = listCity.stream()
                .collect(Collectors.toMap(city -> city.getName(),
                        city -> city,
                        (existing, replacement) -> existing.getPopulation() > replacement.getPopulation() ? existing : replacement));

        listCountry.forEach(country -> {
            City mostPopulousCity = mostPopulousCitiesPerCountry.get(country.getName());
            System.out.println("Most populous city in " + country.getName() + ": " + mostPopulousCity.getName());
        });
    }

    private static void findMostPopulousCityPerContinent(List<Country> countries, List<City> cities) {
        Map<String, City> mostPopulousCitiesPerContinent = countries.stream()
                .collect(Collectors.toMap(country -> country.getContinent(),
                        country -> cities.stream()
                                .filter(city -> city.getName().equals(country.getName()))
                                .max((c1, c2) -> Integer.compare(c1.getPopulation(), c2.getPopulation()))
                                .orElse(null),
                        (existing, replacement) -> existing.getPopulation() > replacement.getPopulation() ? existing : replacement));

        mostPopulousCitiesPerContinent.forEach((continent, city) -> {
            System.out.println("Most populous city in continent " + continent + ": " + city.getName());
        });
    }

    private static void findMostPopulousCapitalCity(List<Country> countries, List<City> cities) {
        Map<String, City> mostPopulousCapitalCities = countries.stream()
                .collect(Collectors.toMap(country -> country.getName(),
                        country -> cities.stream()
                                .filter(city -> city.getId() == country.getCapital())
                                .findFirst()
                                .orElse(null),
                        (existing, replacement) -> existing.getPopulation() > replacement.getPopulation() ? existing : replacement));

        mostPopulousCapitalCities.forEach((country, city) -> {
            System.out.println("Most populous capital city in " + country + ": " + city.getName());
        });
    }

    private static void findMostPopulousCapitalCityPerContinent(List<Country> countries, List<City> cities) {
        Map<String, City> mostPopulousCapitalCitiesPerContinent = countries.stream()
                .collect(Collectors.toMap(country -> country.getContinent(),
                        country -> cities.stream()
                                .filter(city -> city.getId() == country.getCapital())
                                .findFirst()
                                .orElse(null),
                        (existing, replacement) -> existing.getPopulation() > replacement.getPopulation() ? existing : replacement));

        mostPopulousCapitalCitiesPerContinent.forEach((continent, city) -> {
            System.out.println("Most populous capital city in continent " + continent + ": " + city.getName());
        });
    }

//    private static void sortCountriesByNumberOfCities(List<Country> countries) {
//        List<Country> sortedCountries = countries.stream()
//                .sorted((c1, c2) -> Integer.compare(getCityCount(c2), getCityCount(c1)))
//                .collect(Collectors.toList());
//
//        System.out.println("Countries sorted by number of cities:");
//        sortedCountries.forEach(country -> System.out.println(country.getName() + ": " + getCityCount(country)));
//    }
//
//    private static int getCityCount(Country country, List<City> cities) {
//        // Assume cities is a list of City objects
//        return (int) cities.stream().filter(city -> city.getName().equals(country.getName())).count();
//    }

    private static void sortCountriesByPopulationDensity(List<Country> countries) {
        List<Country> sortedCountries = countries.stream()
                .filter(country -> country.getPopulation() > 0)
                .sorted((c1, c2) -> Double.compare(getPopulationDensity(c2), getPopulationDensity(c1)))
                .collect(Collectors.toList());

        System.out.println("Countries sorted by population density:");
        sortedCountries.forEach(country -> System.out.println(country.getName() + ": " + getPopulationDensity(country)));
    }

    private static double getPopulationDensity(Country country) {
        return country.getPopulation() / country.getSurfaceArea();
    }
}
