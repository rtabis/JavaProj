package eraftab;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuestionReader {

private File file;
private String word;
private List<String> wordList = new ArrayList<>();

public void generateQuestion(String questionPath){

    file = new File(questionPath);
    wordLine(file);
}

public void wordLine(File file) {
    // Read Lines the file with IOException
    try{

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        while ((word = bufferedReader.readLine()) != null)
            wordList.add(word);

    }catch (IOException ex){

        System.out.println(ex.getMessage());

    }
}

public List getWordList(){
    return wordList;
}

}
