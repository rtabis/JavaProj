package CarManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class ElementManager {



    private Set<String> elementSet;
    private ResultSet elementS;
    private String elementChoice;
    private String queryElement = null;



    public void gatherElementSet(Statement statement){
        try{
            elementSet = new HashSet<>();
            queryElement = "SELECT Nazwa FROM Elementy";
            elementS = statement.executeQuery(queryElement);
            while(elementS.next())
                elementSet.add(elementS.getString(1));

        }catch (SQLException e){
            e.getMessage();
        }


    }


    public Set<String> getElementSet() {
        return elementSet;
    }

    public void setElementSet(Set<String> elementSet) {
        this.elementSet = elementSet;
    }

    public ResultSet getElementS() {
        return elementS;
    }

    public void setElementS(ResultSet elementS) {
        this.elementS = elementS;
    }

    public String getElementChoice() {
        return elementChoice;
    }

    public void setElementChoice(String elementChoice) {
        this.elementChoice = elementChoice;
    }

    public String getQueryElement() {
        return queryElement;
    }

    public void setQueryElement(String queryElement) {
        this.queryElement = queryElement;
    }

}
