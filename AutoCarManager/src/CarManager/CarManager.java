package CarManager;

import java.util.HashSet;
import java.util.Set;
import java.sql.*;

public class CarManager {

    private Set<String> brandSet;
    private Set<String> modelSet;
    private Set<String> engineSet;
    private ResultSet brandS;
    private ResultSet modelS;
    private ResultSet engineS;
    private ResultSet valueS;

    private String brandChoice;
    private String modelChoice;
    private String engineChoice;
    private double carValue;
    private double carPrize;



    private String queryCarBrand = null;
    private String queryCarModel = null;
    private String queryCarEngine = null;
    private String queryCarValue = null;
    private Integer yearChoice;



    public void gatherBrandSet(Statement statement){
        try{
            brandSet = new HashSet<>();
            queryCarBrand = "SELECT Marka FROM Samochody";
            brandS = statement.executeQuery(queryCarBrand);
            while(brandS.next()){
                brandSet.add(brandS.getString(1));
            }


        }catch (SQLException e){
            e.getMessage();
        }
    }


    public void gatherModelSet(Statement statement){
        try{
            modelSet= new HashSet<>();
            queryCarModel = "SELECT Model FROM Samochody WHERE Marka=\""+brandChoice+"\"";
            modelS = statement.executeQuery(queryCarModel);
            //System.out.println(modelS.getString(1));
            while (modelS.next())
                modelSet.add(modelS.getString(1));

        }catch (SQLException e){
            e.getMessage();
        }

    }

    public void gatherEngineSet(Statement statement){
        try{
            engineSet = new HashSet<>();
            queryCarEngine = "SELECT Silnik FROM Samochody WHERE Marka=\""+brandChoice+"\"" + " AND Model=\""+modelChoice+"\"";
            engineS = statement.executeQuery(queryCarEngine);

            while (engineS.next())
                engineSet.add(engineS.getString(1));

        }catch(SQLException e){
            e.getMessage();
        }
    }

    public void gatherCarValue(Statement statement){
        try{
            queryCarValue = "SELECT Wartosc FROM Samochody WHERE Marka=\""+brandChoice+"\"" + " AND Model=\""+modelChoice+"\""+" AND Silnik=\""+engineChoice+"\"";
            valueS = statement.executeQuery(queryCarValue);

            while(valueS.next())
               carValue = valueS.getDouble(1);

        }catch(SQLException e){
            e.getMessage();
        }


    }

    public Set<String> getBrandSet() {
        return brandSet;
    }

    public Set<String> getModelSet() {
        return modelSet;
    }
    public Set<String> getEngineSet(){ return engineSet; }

    public String getBrandChoice() {
        return brandChoice;
    }
    public String getModelChoice() { return modelChoice; }
    public String getEngineChoice() { return engineChoice; }
    public Integer getYearChoice() { return yearChoice; }
    public double getCarValue() { return carValue; }


    public void setBrandChoice(String brandChoice) {
        this.brandChoice = brandChoice;
    }

    public void setMarkaChoice(String markaChoice) {
        this.modelChoice = markaChoice;
    }

    public void setEngineChoice(String engineChoice) {
        this.engineChoice = engineChoice;
    }

    public void setYearChoice(Integer yearChoice) { this.yearChoice = yearChoice; }



    public void setCarValue(double carValue) { this.carValue = carValue; }


    public double getCarPrize() {
        return carPrize;
    }

    public void setCarPrize(double carPrize) {
        this.carPrize = carPrize;
    }
}
