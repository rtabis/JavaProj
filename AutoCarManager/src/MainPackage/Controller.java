package MainPackage;




import CarManager.CarManager;
import CarManager.ElementManager;
import Mechanic.Mechanic;
import SQLConnectionPackage.ConnectionManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.*;



public class Controller{

    @FXML Label connectionStatus;
    @FXML Button disconnectButton;
    @FXML ChoiceBox brandCBox;
    @FXML ChoiceBox modelCBox;
    @FXML ChoiceBox engineCBox;
    @FXML ChoiceBox servActivities;
    @FXML ChoiceBox elementActivities;
    @FXML ChoiceBox yearCBox;
    @FXML Button executeButton;
    @FXML TextArea resultArea;






    //Nowy obiekt ConnectionManager -> polaczenie do Bazy
    ConnectionManager connectionManager = new ConnectionManager();
    //Definicja Alertow
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    //Status polaczenia
    boolean connState = false;

//Mechanizm laczenia z baza danych
    @FXML
    public void dbConnect(){
        try{
        connectionManager.connectToDB();
        connectionStatus.setText(connectionManager.getDbName());
        disconnectButton.setVisible(true);
        alert.setTitle("Polaczenie z baza: " + connectionManager.getConn().getMetaData().getDatabaseProductName() + " " + connectionManager.getConn().getMetaData().getDatabaseProductVersion());
        alert.setContentText(connectionManager.getConnectionMessage());
        alert.showAndWait();

            if(connectionManager.getConn().getMetaData().getDatabaseProductName().equals("SQLite"));
                connState = true;
            if(connState == true)
                initComponents();

        }catch (SQLException e){
            e.getMessage();
        }

        executeButton.setDisable(false);
    }
    //Rozlaczanie z baza danych
    @FXML
    public void dbDisconnect(){
        try{
            connectionStatus.setText("DBConnection");
            disconnectButton.setVisible(false);
            alert.setTitle("Rozlaczenie z baza: " + connectionManager.getConn().getMetaData().getDatabaseProductName() + " " + connectionManager.getConn().getMetaData().getDatabaseProductVersion());
            connectionManager.closeConnection();
            alert.setContentText(connectionManager.getConnectionMessage());
            alert.showAndWait();
            carModelList.removeAll(carModelList);
            carBrandList.removeAll(carBrandList);
            carEngineList.removeAll(carEngineList);
            carElementList.removeAll(carElementList);
            executeButton.setDisable(true);
            System.exit(0);

        }catch (SQLException e){
            e.getMessage();
        }
    }

// Nowy obiekt CarManager
    CarManager carManager = new CarManager();
    ElementManager elementManager = new ElementManager();
    //Listy wyboru: Marka, Model, Silnik, Czynnosci
    ObservableList<String> carBrandList = FXCollections.observableArrayList();
    ObservableList<String> carModelList = FXCollections.observableArrayList();
    ObservableList<String> carEngineList = FXCollections.observableArrayList();
    ObservableList<String> carElementList = FXCollections.observableArrayList();
    ObservableList<String> carActivityList = FXCollections.observableArrayList("Przeglad", "Wymiana" , "Naprawa");
    ObservableList<Integer> yearList = FXCollections.observableArrayList();


//inicjalizacja innych komponentow programu
    public void initComponents() {
//metoda gatherBrandSet z klasy CarManager przyjmuje polaczenie do bazy
    carManager.gatherBrandSet(connectionManager.getStatement());
//petla iterujaca po elementach zbioru marek i dodajaca elementy do listy
        carBrandList.removeAll(carBrandList);
        for(String o: carManager.getBrandSet()){

        carBrandList.add(o);
    }

//ustawianie listy jako ChoiceBox w GUI
    brandCBox.setItems(carBrandList);

//Marka Box wrazliwa na zmiany
    brandCBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
        @Override
        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
//przesylanie wyboru Marki do CarManager
             carManager.setBrandChoice((String) brandCBox.getValue());
            carManager.gatherModelSet(connectionManager.getStatement());
//Czyszczenie listy i dodawanie nowych obiektow z  ModelSet do ModelList
                        carModelList.removeAll(carModelList);
                        for (String p : carManager.getModelSet())
                            carModelList.add(p);

            modelCBox.setItems(carModelList);
// Listener do zmiany Modelu
            modelCBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
//Ustawianie wartosci wybranej z przycisku
                    carManager.setMarkaChoice((String) modelCBox.getValue());
// Mechaniz wyboru silnika
                    carManager.gatherEngineSet(connectionManager.getStatement());


                        carEngineList.removeAll(carEngineList);
                        for(String p: carManager.getEngineSet())
                            carEngineList.add(p);

                        engineCBox.setItems(carEngineList);
                        engineCBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                            @Override
                            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                carManager.setEngineChoice(String.valueOf(engineCBox.getValue()));
                            }
                        });


                }
            });

        }
    });
//Pobieranie Elementow z bazy
        elementManager.gatherElementSet(connectionManager.getStatement());

        servActivities.setItems(carActivityList);
        servActivities.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {


            }
        });

        carElementList.removeAll(carModelList);
        for(String p: elementManager.getElementSet())
            carElementList.add(p);

        elementActivities.setItems(carElementList);
        elementActivities.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                elementManager.setElementChoice(String.valueOf(elementActivities.getValue()));

            }
        });
//Ustawianie lat
        yearList.removeAll(yearList);
        for(int i = 2017; i> 1970; i--){
            yearList.add(i);
        }
        yearCBox.setItems(yearList);
 //Przypisywanie do zmiennej
        yearCBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                carManager.setYearChoice(0);
                carManager.setYearChoice(Integer.valueOf(String.valueOf(yearCBox.getValue())));
            }
        });

}
   //Przycisk "Wykonaj
    @FXML
    public void executeActivity(){
        carManager.gatherCarValue(connectionManager.getStatement());

            //(String brand, String model, String engine, int year, double value, String service, String element
        Mechanic mechanic = new Mechanic(carManager.getBrandChoice(),carManager.getModelChoice(),carManager.getEngineChoice(),
                carManager.getYearChoice(),carManager.getCarValue(),String.valueOf(servActivities.getValue()),elementManager.getElementChoice());
        mechanic.work();




        resultArea.setText("Marka : " + mechanic.getBrand()+
                "\n"+ "Model : " + mechanic.getModel()+
                "\n"+ "Silnik: " + mechanic.getEngine()+
                "\n"+ "Rok prod: " + mechanic.getYear()+
                "\n"+ "Wartosc: " + mechanic.getValue()+
                "\n"+"==============================="+
                "\n"+ "Usluga: "+"      ||"+"   " +mechanic.getService()+
                "\n"+ "Element: "+"    ||"+"   " + mechanic.getElement()+
                "\n"+"==============================="+
                "\n"+"Cena:  +"+"         ||"+ mechanic.getPrize());





    }


}








