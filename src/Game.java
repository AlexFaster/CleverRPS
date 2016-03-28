
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    public static final int VETERAN_TOKENS = 4;
    public static final int BEGINNER_TOKENS = 3;
    private ComputerPlayer comp;    //comp player
    private Scanner input;      
    private int playerPoints;   //compute player points
    private int compPoints;     //compute comp points 
    private int tokens;         //how many consequtive tokens save in file
    private File file;          //game input file

    public Game(int tokens, File file) {
        this.file = file;
        comp = new ComputerPlayer(FileUtil.readCompFile(file));
        input = new Scanner(System.in);
        this.tokens = tokens;
    }

    
    //full game loop
    public void start() {
        String userChoice;
        List<String> listChoices = new ArrayList();
        System.out.println("Game started");
        do {
            String computerChoice = comp.makeChoice(listChoices, tokens);
            System.out.println("Hi I made a prediction. Try to beat me!");
            userChoice = userInput();
            if (!userChoice.equals("E")) {
                listChoices.add(userChoice);
                System.out.println("My choice is " + computerChoice);
                int roundResult = roundResults(userChoice, computerChoice);
                switch (roundResult) {
                    case 1:
                        System.out.println("Not bad! You win!");
                        playerPoints++;
                        break;
                    case -1:
                        System.out.println("I win");
                        compPoints++;
                        break;
                    default:
                        System.out.println("Round draw!");
                        break;
                }
                if (listChoices.size() > tokens) {
                    handleUserChoicesStack(listChoices);
                }
                if (listChoices.size() == tokens) {
                    comp.storeNewPattern(Util.listToPattern(listChoices));
                }
                System.out.println("You: " + playerPoints + "    Me " + compPoints);

                System.out.println();
            } else {
                FileUtil.writeFile(comp.getMap(), file);
            }

        } while (!userChoice.equals("E"));

    }

    //always adapt stack for tokens size
    private void handleUserChoicesStack(List<String> list) {
        list.remove(0);
    }

    //handle user item selection
    private String userInput() {
        String option;
        while (true) {
            System.out.print("Input desire operation in a range R-rock S-scissors P-paper E-exit: ");
            option = input.nextLine();
            if (option.equalsIgnoreCase("R") || option.equalsIgnoreCase("S") || option.equalsIgnoreCase("P") || option.equalsIgnoreCase("E")) {
                break;
            } else {
                System.out.println("Bad choice! Try again");
            }

        }
        return option.toUpperCase();
    }

    //compute who is the winner
    private int roundResults(String userChoice, String compChoice) {
        if (userChoice.equals(compChoice)) {
            return 0;
        }
        if (userChoice.equals("R") && compChoice.equals("S")) {
            return 1;
        }
        if (userChoice.equals("S") && compChoice.equals("P")) {
            return 1;
        }
        if (userChoice.equals("P") && compChoice.equals("S")) {
            return 1;
        }
        return -1;
    }
}
