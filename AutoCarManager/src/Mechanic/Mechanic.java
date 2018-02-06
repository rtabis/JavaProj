package Mechanic;

public class Mechanic {
    private String brand;
    private String model;
    private String engine;
    private int year;
    private double value;
    private String service;
    private String element;
    private double prize;

    public Mechanic(String brand, String model, String engine, int year, double value, String service, String element){
        this.brand = brand;
        this.model = model;
        this.engine = engine;
        this.year = year;
        this.value = value;
        this.service = service;
        this.element = element;
    }


    public void work(){

        if(service.equals("Przeglad")){

            prize = (int) (500 + value/(year/10));


        }else if (service.equals("Wymiana")){
            if(element.equals("Piasta"))
                prize = (int) (70 + value/(year/10));
            if(element.equals("Klapa"))
                prize = (int) (100 + value/(year/10));
            if(element.equals("Listwa"))
                prize = (int) (50 + value/(year/10));
            if(element.equals("Maska"))
                prize = (int) (120 + value/(year/10));
            if(element.equals("Blotnik"))
                prize = (int) (80 + value/(year/10));
            if(element.equals("Klocki Hamulcowe"))
                prize = (int) (50 + value/(year/10));
            if(element.equals("Drzwi"))
                prize = (int) (100 + value/(year/10));
            if(element.equals("Wahacz"))
                prize = (int) (110 + value/(year/10));
            if(element.equals("Reflektor"))
                prize = (int) (70 + value/(year/10));
            if(element.equals("Zderzak"))
                prize = (int) (40 + value/(year/10));
            if(element.equals("Lusterko"))
                prize = (int) (30 + value/(year/10));
            if(element.equals("Szyba"))
                prize = (int) (80 + value/(year/10));
            if(element.equals("Filtry"))
                prize = (int) (20 + value/(year/10));

        }else if (service.equals("Naprawa")){
            if(element.equals("Piasta"))
                prize = (int) (35 + value/(year/10));
            if(element.equals("Klapa"))
                prize = (int) (50 + value/(year/10));
            if(element.equals("Listwa"))
                prize = (int) (25 + value/(year/10));
            if(element.equals("Maska"))
                prize = (int) (60 + value/(year/10));
            if(element.equals("Blotnik"))
                prize = (int) (40 + value/(year/10));
            if(element.equals("Klocki Hamulcowe"))
                prize = (int) (25 + value/(year/10));
            if(element.equals("Drzwi"))
                prize = (int) (50 + value/(year/10));
            if(element.equals("Wahacz"))
                prize = (int) (55+ value/(year/10));
            if(element.equals("Reflektor"))
                prize = (int) (35 + value/(year/10));
            if(element.equals("Zderzak"))
                prize = (int) (20 + value/(year/10));
            if(element.equals("Lusterko"))
                prize = (int) (15 + value/(year/10));
            if(element.equals("Szyba"))
                prize = (int) (40 + value/(year/10));
            if(element.equals("Filtry"))
                prize = (int) (10 + value/(year/10));


        }
    }


    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getEngine() {
        return engine;
    }

    public int getYear() {
        return year;
    }

    public double getValue() {
        return value;
    }

    public String getService() {
        return service;
    }

    public double getPrize() {
        return prize;
    }
    public String getElement(){ return element;}
}
