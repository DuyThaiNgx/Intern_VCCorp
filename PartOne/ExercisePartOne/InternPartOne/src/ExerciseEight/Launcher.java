package ExerciseEight;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Launcher {
    static List<City> listCity = new ArrayList<>();
    static List<Country> listCountry = new ArrayList<>();
    public static void addToFile(){

        List<Country> countries = new ArrayList<>();
        Country country1 = new Country("VN", "Vietnam", "Asia", 331212.0, 97338579, 261921.9, 1);
        Country country2 = new Country("US", "United States", "North America", 9833517.0, 331002651, 21433225.0, 3);
        Country country3 = new Country("KR", "Korea", "Asia", 6000000.0, 5556968, 3030303.0, 2 );
        Country country4 = new Country("FR", "France", "Europe", 6900000.0, 30000000, 3030303.0, 5 );
        Country country5 = new Country("JP", "Japan", "Asia", 7500000.0, 45000000, 290000.0, 9 );
        Country country6 = new Country("EN", "England", "Europe", 9500000.0, 55000000, 290000.0, 10 );

        List<City> cities = new ArrayList<>();
        City city1 = new City(1, "Hanoi", 7781120,"VN");
        City city2 = new City(2, "Seoul", 335876,"KR");
        City city3 = new City(3, "WashingtonDC", 96897649, "US");
        City city4 = new City(4, "KangNam", 5000000, "KR");
        City city5 = new City(5, "Paris", 45000000, "FR");
        City city6 = new City(6, "HoChiMinh", 8000000, "VN");
        City city7 = new City(7, "Busan", 6800000, "KR");
        City city8 = new City(8, "NewYorkCity", 101000111, "US");
        City city9 = new City(9, "Tokyo", 234567888, "JP");
        City city10 = new City(10, "London", 45000001, "EN");
        City city11 = new City(11, "Manchester", 6800068, "EN");



        //File country
        countries.add(country1);
        countries.add(country2);
        countries.add(country3);
        countries.add(country4);
        countries.add(country5);
        countries.add(country6);
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
        cities.add(city4);
        cities.add(city5);
        cities.add(city6);
        cities.add(city7);
        cities.add(city8);
        cities.add(city9);
        cities.add(city10);
        cities.add(city11);



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
        addToFile();
        try {
            FileInputStream countryFile = new FileInputStream(countriesFile);
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
        mostPopulationOfEveryCountry();
        mostPopulationOfEveryContinent();
        mostPopulationOfCapital();
        mostPopulationCapitalOfEveryContinent();
        sortCountryByCityDESC();
        sortCountryByDensityDESC();

//        findMostPopulousCityPerCountry(listCountry, listCity);
//        findMostPopulousCityPerContinent(listCountry, listCity);
//        findMostPopulousCapitalCity(listCountry, listCity);
//        findMostPopulousCapitalCityPerContinent(listCountry, listCity);
    }
    public static void mostPopulationOfEveryCountry(){
        Map<String, Optional<City>> mostPopulousCityByCountry = listCity.stream()
                .collect(Collectors.groupingBy(City::getCode,
                        Collectors.maxBy(Comparator.comparingInt(City::getPopulation))));
        System.out.println("1.1 Most populous city by country: ");
        for(Map.Entry<String, Optional<City>> element : mostPopulousCityByCountry.entrySet()){
            System.out.println(element);
            System.out.println("Country: " + element.getValue().get().getName() + " Population: "+ element.getValue().get().getPopulation());
        }
    }

    public static void mostPopulationOfEveryContinent(){
        Map<String, Optional<City>> mostPopulousCityByContinent = listCity.stream()
                .collect(Collectors.groupingBy(city -> getCountryByCode(listCountry, city.getCode()).getContinent(),
                        Collectors.maxBy(Comparator.comparingInt(City::getPopulation))));
        System.out.println("1.2 Most populous city by continent: ");
        for(Map.Entry<String, Optional<City>> element : mostPopulousCityByContinent.entrySet()){
            System.out.println(element);
        }
    }
    public static void mostPopulationOfCapital(){
        Optional<City> mostPopulousCityByCappital = listCity.stream()
                .filter(city -> city.getId() == getCountryByCode(listCountry, city.getCode()).getCapital())
                .max(Comparator.comparingInt(City::getPopulation));
        System.out.println("1.3 Most populous capital: ");
        System.out.println(mostPopulousCityByCappital);
    }

    public static void mostPopulationCapitalOfEveryContinent(){
        Map<String, Optional<City>> mostPopulationCapitalByContinent = listCity.stream()
                .filter(city -> city.getId() == getCountryByCode(listCountry, city.getCode()).getCapital())
                .collect(Collectors.groupingBy(city -> getCountryByCode(listCountry, city.getCode()).getContinent(),
                        Collectors.maxBy(Comparator.comparingInt(City::getPopulation))));
        System.out.println("1.4 Most populous capital by continent: ");
        for(Map.Entry<String, Optional<City>> element : mostPopulationCapitalByContinent.entrySet()){
            System.out.println(element);
        }
    }

    public static void sortCountryByCityDESC(){
        List<Country> sortedCountriesByCityCount = listCountry.stream()
                .sorted(Comparator.comparingInt(country -> (int) listCity.stream()
                                .filter(city -> city.getCode().equals(country.getCode()))
                                .count()))
                .collect(Collectors.toList());
        Collections.reverse(sortedCountriesByCityCount);
        System.out.println("1.5: sortCountryByCityDESC: ");
//        for(Country ctr: sortedCountriesByCityCount){
//            System.out.println(ctr);
//        }
        sortedCountriesByCityCount.forEach(ctr-> System.out.println(ctr + " -- Number of city in the Country: " +
                listCity.stream().filter(city -> city.getCode().equals(ctr.getCode())).count()));
    }
    public static void sortCountryByDensityDESC(){
        List<Country> sortedCountriesByCityCount = listCountry.stream()
                        .filter(country -> country.getPopulation() > 0)
                        .sorted(Comparator.comparingDouble(country -> country.getPopulation()/country.getSurfaceArea()))
                .collect(Collectors.toList());
        Collections.reverse(sortedCountriesByCityCount);
        System.out.println("1.6: sortCountryByDensityDESC: ");
        sortedCountriesByCityCount.forEach(country -> System.out.println(country + " -- The density: " + country.getPopulation()/
                        country.getSurfaceArea()));
//                filter(country1 -> (double)country1.getPopulation() / country1.getSurfaceArea())));

    }


    private static Country getCountryByCode(List<Country> countries, String code) {
        return countries.stream()
                .filter(country -> country.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }
//    private static void findMostPopulousCityPerCountry(List<Country>listCountry, List<City> listCity) {
//        Map<String, City> mostPopulousCitiesPerCountry = listCity.stream()
//                .collect(Collectors.toMap(city -> city.getName(),
//                        city -> city,
//                        (existing, replacement) -> existing.getPopulation() > replacement.getPopulation() ? existing : replacement));
//
//        listCountry.forEach(country -> {
//            City mostPopulousCity = mostPopulousCitiesPerCountry.get(country.getName());
//            System.out.println("Most populous city in " + country.getName() + ": " + mostPopulousCity.getName());
//        });
//    }
//
//    private static void findMostPopulousCityPerContinent(List<Country> countries, List<City> cities) {
//        Map<String, City> mostPopulousCitiesPerContinent = countries.stream()
//                .collect(Collectors.toMap(country -> country.getContinent(),
//                        country -> cities.stream()
//                                .filter(city -> city.getName().equals(country.getName()))
//                                .max((c1, c2) -> Integer.compare(c1.getPopulation(), c2.getPopulation()))
//                                .orElse(null),
//                        (existing, replacement) -> existing.getPopulation() > replacement.getPopulation() ? existing : replacement));
//
//        mostPopulousCitiesPerContinent.forEach((continent, city) -> {
//            System.out.println("Most populous city in continent " + continent + ": " + city.getName());
//        });
//    }
//
//    private static void findMostPopulousCapitalCity(List<Country> countries, List<City> cities) {
//        Map<String, City> mostPopulousCapitalCities = countries.stream()
//                .collect(Collectors.toMap(country -> country.getName(),
//                        country -> cities.stream()
//                                .filter(city -> city.getId() == country.getCapital())
//                                .findFirst()
//                                .orElse(null),
//                        (existing, replacement) -> existing.getPopulation() > replacement.getPopulation() ? existing : replacement));
//
//        mostPopulousCapitalCities.forEach((country, city) -> {
//            System.out.println("Most populous capital city in " + country + ": " + city.getName());
//        });
//    }
//
//    private static void findMostPopulousCapitalCityPerContinent(List<Country> countries, List<City> cities) {
//        Map<String, City> mostPopulousCapitalCitiesPerContinent = countries.stream()
//                .collect(Collectors.toMap(country -> country.getContinent(),
//                        country -> cities.stream()
//                                .filter(city -> city.getId() == country.getCapital())
//                                .findFirst()
//                                .orElse(null),
//                        (existing, replacement) -> existing.getPopulation() > replacement.getPopulation() ? existing : replacement));
//
//        mostPopulousCapitalCitiesPerContinent.forEach((continent, city) -> {
//            System.out.println("Most populous capital city in continent " + continent + ": " + city.getName());
//        });
//    }

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

//    private static void sortCountriesByPopulationDensity(List<Country> countries) {
//        List<Country> sortedCountries = countries.stream()
//                .filter(country -> country.getPopulation() > 0)
//                .sorted((c1, c2) -> Double.compare(getPopulationDensity(c2), getPopulationDensity(c1)))
//                .collect(Collectors.toList());
//
//        System.out.println("Countries sorted by population density:");
//        sortedCountries.forEach(country -> System.out.println(country.getName() + ": " + getPopulationDensity(country)));
//    }
//
//    private static double getPopulationDensity(Country country) {
//        return country.getPopulation() / country.getSurfaceArea();
//    }
}
