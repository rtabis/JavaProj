package SQLConnectionPackage;


import java.sql.*;

public class ConnectionManager {


    private Connection conn;
    private Statement statement;
    private String connectionMessage;
    private String dbName = "AutoCarManagerDB.db";


//Metoda otwierajaca polaczenie do Bazy
    public void connectToDB(){

            try{
                conn = DriverManager.getConnection("jdbc:sqlite:"+dbName);
                statement = conn.createStatement();
                connectionMessage = "Session has been Open";

        }catch (SQLException e){
            connectionMessage="Cos poszlo nie tak :( : " +e.getMessage();
        }
    }
//Metoda zamykajaca polaczenie
    public void closeConnection(){
        try{
            statement.close();
            conn.close();
            connectionMessage = "Session has been closed";
        }catch (SQLException e){
            connectionMessage = "Blad przy zamykaniu sesji : " + e.getMessage();
        }

    }



    //Gettery do prywatnych pol
    public String getConnectionMessage(){
        return connectionMessage;
    }
    public String getDbName(){
        return dbName;
    }

    public Connection getConn() {
        return conn;
    }

    public Statement getStatement() {
        return statement;
    }

}
