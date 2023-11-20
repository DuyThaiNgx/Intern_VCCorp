package ExerciseEight;

import java.io.Serializable;

public class City implements Serializable {
    private int id;
    private String name;
    private int population;
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public City(){

    }

    public City(int id, String name, int population, String code) {
        this.id = id;
        this.name = name;
        this.population = population;
        this.code=code;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", population=" + population +
                ", code='" + code + '\'' +
                '}';
    }
}
