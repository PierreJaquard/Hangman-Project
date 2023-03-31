import org.w3c.dom.ls.LSOutput;

import javax.xml.stream.events.StartDocument;
import java.lang.reflect.Array;
import java.util.*;
import java.io.*;
public class Main {
    //static Scanner input;
    public static void main(String[] args) throws Exception {

        game();

    }

    private static void game() throws FileNotFoundException {
        System.out.println("");

        System.out.print("Choose a game difficulty; 'E' for EASY, 'M' for MEDIUM and 'H' for HARD: ");
        while(true) {
            Scanner scan2 = new Scanner(System.in);
            String StartInput4 = scan2.next().toUpperCase();
            if (StartInput4.equals("E")) {
                easyMode();
            } else if(StartInput4.equals("H")) {
                hardMode();
            } else if(StartInput4.equals("EXIT")) {
                System.exit(0);
            } else if(StartInput4.equals("MODE")) {
                game();
            } else if(StartInput4.equals("M")){
                mediumMode();
            } else {
                System.out.println("");
                System.out.print("Enter 'E' for EASY, 'M' for MEDIUM and 'H' for HARD:  ");
            }


        }
    }

    private static void easyMode() throws FileNotFoundException {

        while (true){
            int lives = 0;

            startInput();

            String random_word = getEASYRandomWord();

            List<Character> playerGuesses = new ArrayList<>();
            while(true) {

                printEASYHangMan(lives);

                if (lives>= 9){
                    System.out.println("     YOU LOST !!!");
                    System.out.println("The word was: " + random_word);
                    System.out.println(" ");
                    gameEnds();
                    break;}

                printUnderscores(random_word, playerGuesses);

                if (!getPlayerGuess(random_word, playerGuesses)){
                    lives++;}
                if (playerGuesses.equals(random_word)){
                    System.out.println("\n" +"    YOU WON !!!");
                    gameEnds();
                }

                if (printUnderscores(random_word, playerGuesses)){
                    System.out.println("\n" +"    YOU WON !!!");
                    gameEnds();
                }
                System.out.println(" ");

            }
        }
    }

    private static String getEASYRandomWord() throws FileNotFoundException {
        File file = new File("src/dictionary.txt");
        Scanner dictionary = new Scanner(file);
        List<String> words = new ArrayList<>();

        while(true) {
            while (dictionary.hasNext()) {
                words.add(dictionary.nextLine());
            }
            Random r = new Random();
            String random_word = words.get(r.nextInt(words.size()));
            while (random_word.length() < 6) {
                //System.out.println(random_word);
                return random_word;
            }
        }
    }

    private static void hardMode() throws FileNotFoundException {

        while (true){
            int lives = 0;

            startInput();

            String random_word = getHARDRandomWord();


            List<Character> playerGuesses = new ArrayList<>();
            while(true) {

                printHARDHangMan(lives);

                if (lives>= 6){
                    System.out.println("     YOU LOST !!!");
                    System.out.println("The word was: " + random_word);
                    System.out.println(" ");
                    gameEnds();
                    break;}

                printUnderscores(random_word, playerGuesses);

                if (!getPlayerGuess(random_word, playerGuesses)){
                    lives++;}
                if (playerGuesses.equals(random_word)){
                    System.out.println("\n" +"    YOU WON !!!");
                    gameEnds();
                }

                if (printUnderscores(random_word, playerGuesses)){
                    System.out.println("\n" +"    YOU WON !!!");
                    gameEnds();
                }
                System.out.println(" ");

            }
        }
    }

    private static void mediumMode() throws FileNotFoundException {

        while (true){
            int lives = 0;

            startInput();

            String random_word = getHARDRandomWord();


            List<Character> playerGuesses = new ArrayList<>();
            while(true) {

                printHARDHangMan(lives);

               if (lives==3){
                    giveHint(random_word);
                    lives+=1;}


                if (lives>= 6){
                    System.out.println("     YOU LOST !!!");
                    System.out.println("The word was: " + random_word);
                    System.out.println(" ");
                    gameEnds();
                    break;}

                printUnderscores(random_word, playerGuesses);

                if (!getPlayerGuess(random_word, playerGuesses)){
                    lives++;}
                if (playerGuesses.equals(random_word)){
                    System.out.println("\n" +"    YOU WON !!!");
                    gameEnds();
                }

                if (printUnderscores(random_word, playerGuesses)){
                    System.out.println("\n" +"    YOU WON !!!");
                    gameEnds();
                }
                System.out.println(" ");

            }
        }
    }

    private static String getHARDRandomWord() throws FileNotFoundException {
        File file = new File("src/dictionary.txt");
        Scanner dictionary = new Scanner(file);
        List<String> words = new ArrayList<>();

        while(true) {
            while (dictionary.hasNext()) {
                words.add(dictionary.nextLine());
            }
            Random r = new Random();
            String random_word = words.get(r.nextInt(words.size()));
            while (random_word.length() > 5 && random_word.length()<20) {
                //
                // System.out.println(random_word);
                return random_word;
            }
        }
    }

    private static void giveHint(String random_word) {
        System.out.print("You only have two more lives/tries! Would you like a hint? THIS WILL COST YOU ONE LIFE! Type 'HINT' for a hint, or 'N' for no:");
        while(true) {
            Scanner scan6 = new Scanner(System.in);
            String StartInput1 = scan6.next().toUpperCase();
            int randint = (int)(Math.random()*random_word.length());

            char [] itterator = random_word.toCharArray();
            if (StartInput1.equals("HINT")) {
                System.out.println("The word you're guessing has the letter '"+ itterator[randint]+"'");
                break;
            } else if (StartInput1.equals("N")) {
                break;
            } else if (StartInput1.equals("EXIT")) {
                System.exit(0);
                break;
            } else {
                System.out.print("Please type 'HINT' for a hint or 'N' for no: ");
                continue;
            }
        }
    }

    private static void gameEnds() throws FileNotFoundException {
        System.out.print("Do you want to play again?   Press 'Y' for yes, 'N' for no or 'RULES' for rules: ");
        while(true) {
            Scanner scan3 = new Scanner(System.in);
            String StartInput3 = scan3.next().toUpperCase();
            if (StartInput3.equals("Y")) {
                game();
            } else if (StartInput3.equals("N")) {
                System.exit(0);
            } else if (StartInput3.equals("RULES")) {
                printRules();
                game();
            } else if(StartInput3.equals("MODE")) {
                game();
            } else if (StartInput3.equals("EXIT")) {
                System.exit(0);
            } else {
                System.out.println("Please enter 'N', 'Y' or 'RULES': ");
            }
        }
    }

    private static void startInput() {
        System.out.println("");
        System.out.print("\n" + "You are about to start a new game of Hangman. To see the rules press 'R', to start press 'S': ");
        while(true) {
            Scanner scan1 = new Scanner(System.in);
            String StartInput1 = scan1.next().toUpperCase();
            if (StartInput1.equals("R")) {
                printRules();
                break;
            } else if (StartInput1.equals("S")) {
                System.out.println("\n" + "                      LET THE GAME BEGIN!" + "\n");
                System.out.println("---------------------------------------------------------------------------------------------------------------");
                break;
            } else if (StartInput1.equals("EXIT")) {
                System.exit(0);
            } else {
                System.out.println("Please enter 'R' for the rules or 'S' to start.");
                }
            }

    }

    private static void printRules(){
        System.out.println("The rules of the game are as follows;");
        System.out.println("   -The computer chooses a random word which you have to guess");
        System.out.println("   -You can only input letters");
        System.out.println("   -You can only input one letter at a time");
        System.out.println("   -If you wish to quit the game, type 'EXIT'");
        System.out.println("    -If you wish to change the game mode/difficulty, type 'MODE'");
        System.out.println(                     "LET THE GAME BEGIN!");
        System.out.println("------------------------------------------------------------");
    }

    private static void printHARDHangMan(int lives) {
        if (lives==0){
            System.out.println(
                            "  +---+\n" +
                            "  |   |\n" +
                            "      |\n" +
                            "      |\n" +
                            "      |\n" +
                            "      |\n" +
                            "========= ");
        }

        if (lives == 1) {
            System.out.println(
                            "  +---+\n" +
                            "  |   |\n" +
                            "  O   |\n" +
                            "      |\n" +
                            "      |\n" +
                            "      |\n" +
                            "=========");
        }
        if (lives == 2) {
            System.out.println(
                            "  +---+\n" +
                            "  |   |\n" +
                            "  O   |\n" +
                            "  |   |\n" +
                            "      |\n" +
                            "      |\n" +
                            "=========");
        }
        if (lives == 3) {
            System.out.println(
                            "  +---+\n" +
                            "  |   |\n" +
                            "  O   |\n" +
                            " /|   |\n" +
                            "      |\n" +
                            "      |\n" +
                            "=========");}


        if (lives == 4) {
            System.out.println(
                            "  +---+\n" +
                            "  |   |\n" +
                            "  O   |\n" +
                            " /|\\  |\n" +
                            "      |\n" +
                            "      |\n" +
                            "========= ");
        }

        if (lives == 5) {
            System.out.println(
                         "  +---+\n" +
                            "  |   |\n" +
                            "  O   |\n" +
                            " /|\\  |\n" +
                            " /    |\n" +
                            "      |\n" +
                            "========= ");
        }
        if (lives >= 6) {
            System.out.println(
                            "  +---+\n" +
                            "  |   |\n" +
                            "  O   |\n" +
                            " /|\\  |\n" +
                            " / \\  |\n" +
                            "      |\n" +
                            "========= ");
        }

    }

    private static void printEASYHangMan(int lives) {
        if (lives == 0){
            System.out.println(
                            "      \n" +
                            "      \n" +
                            "      \n" +
                            "      \n" +
                            "      \n" +
                            "      \n" +
                            "========= ");
        }
        if (lives == 1){
            System.out.println(
                            "       \n" +
                            "      |\n" +
                            "      |\n" +
                            "      |\n" +
                            "      |\n" +
                            "      |\n" +
                            "========= ");
        }
        if (lives == 2){
            System.out.println(
                            "  +---+\n" +
                            "      |\n" +
                            "      |\n" +
                            "      |\n" +
                            "      |\n" +
                            "      |\n" +
                            "========= ");
        }
        if (lives == 3){
            System.out.println(
                            "  +---+\n" +
                            "  |   |\n" +
                            "      |\n" +
                            "      |\n" +
                            "      |\n" +
                            "      |\n" +
                            "========= ");
        }
        if (lives == 4) {
            System.out.println(
                            "  +---+\n" +
                            "  |   |\n" +
                            "  O   |\n" +
                            "      |\n" +
                            "      |\n" +
                            "      |\n" +
                            "=========");
        }
        if (lives == 5) {
            System.out.println(
                            "  +---+\n" +
                            "  |   |\n" +
                            "  O   |\n" +
                            "  |   |\n" +
                            "      |\n" +
                            "      |\n" +
                            "=========");
        }
        if (lives == 6) {
            System.out.println(
                            "  +---+\n" +
                            "  |   |\n" +
                            "  O   |\n" +
                            " /|   |\n" +
                            "      |\n" +
                            "      |\n" +
                            "=========");}

        if (lives == 7) {
            System.out.println(
                            "  +---+\n" +
                            "  |   |\n" +
                            "  O   |\n" +
                            " /|\\  |\n" +
                            "      |\n" +
                            "      |\n" +
                            "========= ");
        }

        if (lives == 8) {
            System.out.println(
                            "  +---+\n" +
                            "  |   |\n" +
                            "  O   |\n" +
                            " /|\\  |\n" +
                            " /    |\n" +
                            "      |\n" +
                            "========= ");
        }
        if (lives >= 9) {
            System.out.println(
                            "  +---+\n" +
                            "  |   |\n" +
                            "  O   |\n" +
                            " /|\\  |\n" +
                            " / \\  |\n" +
                            "      |\n" +
                            "========= ");
        }

    }

    private static boolean getPlayerGuess(String random_word, List<Character> playerGuesses) throws FileNotFoundException {
        System.out.println(" ");
        System.out.print("Please enter a letter: ");

        while(true){

            Scanner guess = new Scanner(System.in);
            String letterGuess = guess.next().toUpperCase();

            for (int q=0;q<letterGuess.length();q++) {

                String[] splitLetterGuess = letterGuess.split("");

                if (Character.isLetter(letterGuess.charAt(q)) == false) {
                    System.out.print("You must input a letter: ");
                    break;
                } else if (letterGuess.equals("EXIT")) {System.exit(0);
                } else if(letterGuess.equals("MODE")) {
                    game();
                }else if(letterGuess.equals(random_word.toUpperCase())) {
                    System.out.println("\n" + "    YOU WON !!!");
                    System.out.println("");
                    gameEnds();
                }else if ( !splitLetterGuess[q].equals(splitLetterGuess[0])){
                        System.out.print("You must input one letter only: ");
                        break;

                } else{
                    playerGuesses.add(letterGuess.charAt(0));
                    System.out.println(" ");
                    return random_word.contains(letterGuess);}
            }

        }}

    private static boolean printUnderscores(String random_word, List<Character> playerGuesses) {
        int count = 0;
        for (int s = 0; s< random_word.length(); s++) {
            if (playerGuesses.contains(random_word.charAt(s))) {
                System.out.print(random_word.charAt(s));
                count++;
            } else {
                System.out.print("_");
            }
        }

        return (random_word.length() == count);
    }
}
