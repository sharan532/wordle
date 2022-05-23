
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Main {

     public static Scanner s = new Scanner(System.in);

     static  String ANSI_RESET = "\u001B[0m";
     static  String Yellow = "\u001B[33m";
     static  String Green = "\u001B[32m";

    public static void main(String[] args) throws IOException {

        String intro="¬Welcome to Wordle Game¬";
        System.out.println(String.format("%90s",intro));

        String Anyword = randomword();
        char[] answer = new char[5];
        for (int i = 0; i < 5; i++ )
        {
            answer[i] = Anyword.charAt(i);
        }
        char[] input = new char[5];
        boolean done = false;
        
        System.out.println("Enter A Five Letter Word");
        while (!done){

            String enter_word = s.nextLine();
            while (enter_word.length() < 5) {
                enter_word = s.nextLine().toLowerCase();
            }
            for (int i = 0; i < 5; i++ ) {
                answer[i] = Anyword.charAt(i);
                input[i] = enter_word.charAt(i);
            }
            if (changewordcolor(input, answer))
                done = true;
        }
    }

    public static String randomword() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("D:\\words.txt"));
        String line = reader.readLine();
        ArrayList<String> All_words = new ArrayList<String>();
        while(line != null)
        {
            String[] wordsLine = line.split(" ");
            for(String word : wordsLine)
            {
                All_words.add(word);
            }
            line = reader.readLine();
        }
        Random rand = new Random();
        String randomWord = All_words.get(rand.nextInt(All_words.size()));
        return randomWord;
    }


    public static boolean changewordcolor(char[] inputWord, char[] correctWord) {
        boolean correct = true;
        char[] answerTemp = correctWord;
        int[] color_Letter = new int[5];

        for (int i = 0; i < 5; i++)
        {
            if (inputWord[i] == answerTemp[i]) {
                answerTemp[i] = '-';
                color_Letter[i] = 2;
            } else correct = false;
        }

        for (int j = 0; j < 5; j++) {
            for (int k = 0; k < 5; k++){
                if (inputWord[j] == answerTemp[k] && color_Letter[j] != 2) {
                    color_Letter[j] = 1;
                    answerTemp[k] = '-';
                }
            }
        }

        for (int m = 0; m < 5; m++) {
            if (color_Letter[m] == 0) {
                System.out.print(inputWord[m]);
            }
            if (color_Letter[m] == 1){
                System.out.print(Yellow + inputWord[m] + ANSI_RESET);
            }
            if (color_Letter[m] == 2) {
                System.out.print(Green + inputWord[m] + ANSI_RESET);
            }
        }
        System.out.println("");
        return correct;
    }

}
