package eraftab;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static int loseCounter = 0;
    private static String wordMarks;
    private static String question;
    public static HangmanCompare hangmanCompare = new HangmanCompare();


    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);


        QuestionReader questionReader = new QuestionReader();
        questionReader.generateQuestion("C:\\Users\\Rafał\\Desktop\\Movies.txt");

        List<String> wordList = questionReader.getWordList();
        QuestionGenerator questionGenerator = new QuestionGenerator(wordList);


        for (int roundCounter = 0; roundCounter <= 5; roundCounter++) {
            question = questionGenerator.getQuestion();
            wordMarks = new String(new char[question.length()]).replace("\0", "*");

            while (loseCounter < 7 && wordMarks.contains("*")) {

                System.out.println("Guess any letter in a movie title");
                System.out.println(wordMarks);
                String shoot = in.next();
                hangman(shoot);
                System.out.println(loseCounter);
            }


        }
    }

    public static void hangman(String shoot) {
        String newMark = "";
        for (int i = 0; i < question.length(); i++) {
            if (question.charAt(i) == shoot.charAt(0)) {
                newMark += shoot.charAt(0);
            } else if (wordMarks.charAt(i) != '*') {
                newMark += question.charAt(i);
            } else {
                newMark += "*";
            }
        }

        if (wordMarks.equals(newMark)) {
            loseCounter ++;
            hangmanCompare.hangmanImage(question, loseCounter);
        } else {
            wordMarks = newMark;
        }
        if (wordMarks.equals(question)) {
            System.out.println("Correct! You win! The word was " + question);
        }
    }

}