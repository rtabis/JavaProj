package eraftab;

import java.util.List;
import java.util.Random;

public class QuestionGenerator {
    private List<String> questionList;
    private int questionIndex;

    public QuestionGenerator(List questionList){
        this.questionList = questionList;
    }

    public String getQuestion(){
        Random random = new Random();
        int questionIx = random.nextInt(20);

        if(questionIx == questionIndex) {
            questionIx = random.nextInt(20);
            this.questionIndex = questionIx;
        }else
            this.questionIndex = questionIx;

        return questionList.get(questionIndex);
    }

}
