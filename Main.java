// This project is written by *Sepehr_Naeij*

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

public class Main {
    static String[] board;
    static String turn;

    public static void help() {
        System.out.println("/start ----> It,s for beginning a game between two person.");
        System.out.println("/save  ----> It,s for saving a game.");
        System.out.println("/load  ----> It,s for having access to a saved game.");
        System.out.println("/put   ----> It,s for adding a number in the TicTac game.");
        System.out.println("/help  ----> It,s for showing us how can we work with this game.");
        System.out.println("/exit  ----> It,s for exiting from the game.");
    }

    static String checkWinner() {
        for (int a = 0; a < 8; a++) {
            String line = null;

            switch (a) {
                case 0:
                    line = board[0] + board[1] + board[2];
                    break;
                case 1:
                    line = board[3] + board[4] + board[5];
                    break;
                case 2:
                    line = board[6] + board[7] + board[8];
                    break;
                case 3:
                    line = board[0] + board[3] + board[6];
                    break;
                case 4:
                    line = board[1] + board[4] + board[7];
                    break;
                case 5:
                    line = board[2] + board[5] + board[8];
                    break;
                case 6:
                    line = board[0] + board[4] + board[8];
                    break;
                case 7:
                    line = board[2] + board[4] + board[6];
                    break;
            }

            if (line.equals("XXX")) {
                return "X";
            } else if (line.equals("OOO")) {
                return "O";
            }
        }

        for (int a = 0; a < 9; a++) {
            if (Arrays.asList(board).contains(
                    String.valueOf(a + 1))) {
                break;
            } else if (a == 8) {
                return "draw";
            }
        }

        return null;
    }

    static void printBoard() {
        System.out.println("| " + board[0] + " | " + board[1] + " | " + board[2] + " |");
        System.out.println("| " + board[3] + " | " + board[4] + " | " + board[5] + " |");
        System.out.println("| " + board[6] + " | " + board[7] + " | " + board[8] + " |");
    }

    public static String[] readLines(String fileName)
            throws IOException {
        FileReader fileReader = new FileReader(fileName);

        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> lines = new ArrayList<>();
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }

        bufferedReader.close();

        return lines.toArray(new String[lines.size()]);
    }

    public static void write(String fileName, String[] x) throws Exception {
        FileWriter writer = new FileWriter(fileName);
        for (int i = 0; i < x.length; i++) {
            writer.write(x[i] + "-");
        }
        writer.close();
    }


    public static void main(String[] args) {

// cli
        Scanner scanner1 = new Scanner(System.in);
        String input = scanner1.nextLine();
        String[] tokens = input.split(" ");

        try {

            Label:
            while (true) {
                switch (tokens[0]) {
                    case "/start":
                        System.out.println("| 1" + " | 2" + " | 3" + " |");
                        System.out.println("| 4" + " | 5" + " | 6" + " |");
                        System.out.println("| 7" + " | 8" + " | 9" + " |");
                        Scanner in = new Scanner(System.in);
                        String player1 = tokens[1];
                        String player2 = tokens[2];
                        board = new String[12];
                        String winner = null;
                        String time = DateTimeFormatter.ofPattern("yyyy-MM-dd_._hh-mm-ss").format(LocalDateTime.now());
                        String fileName;
                        fileName = player1 + "." + player2 + ".(" + time + ").txt";
                        turn = "X";
                        File tempFile = new File("F:\\TicTacProject\\" + fileName);
                        boolean exists = tempFile.exists();


                        if (!exists) {
                            for (int i = 0; i < 9; i++) {
                                board[i] = String.valueOf(i + 1);
                            }
                            board[9] = player1;
                            board[10] = player2;

                        } else {
                            String[] s;
                            s = readLines(fileName);
                            String[] strArray;
                            strArray = s[0].split("-");

                            for (int i = 0; i < strArray.length; i++) {
                                board[i] = strArray[i];
                            }
                            player1 = board[9];
                            player2 = board[10];
                            winner = checkWinner();
                            printBoard();
                            if (board[11].equals("X")) {
                                System.out.println("Player " + player1 + " It,s your turn.Please enter a number"
                                        + " for replacing 'X' with it:");
                            } else {
                                System.out.println("Player " + player2 + " It,s your turn.Please enter a number"
                                        + " for replacing 'O' with it:");
                            }
                        }


                        System.out.println("Player " + player1 + " it,s your turn.Please enter a number:");

                        while (winner == null) {
                            String input1 = in.nextLine();
                            String[] string = input1.split(" ");
                            if (string[0].equals("/exit")) {
                                System.exit(0);
                            }else if (string[0].equals("/save")) {
                                write(fileName, board);
                                System.out.println("Your game is successfully saved in this this file ----> (" + fileName + ")");
                                System.exit(0);
                            } else {

                                if ("/put".equals(string[0])) {
                                    int number = Integer.parseInt(string[1]);

                                    if (!(number > 0 && number <= 9)) {
                                        System.out.println("The Number Input Invalid");
                                        continue;
                                    }


                                    if (board[number - 1].equals(String.valueOf(number))) {
                                        board[number - 1] = turn;

                                        if (turn.equals("X")) {
                                            printBoard();
                                            turn = "O";
                                            board[11] = "O";
                                        } else {
                                            printBoard();
                                            turn = "X";
                                            board[11] = "X";
                                        }

                                        winner = checkWinner();

                                        if (winner == null) {
                                            if (turn.equals("X")) {
                                                System.out.println("Player " + player1 + " It,s your turn.Please enter a number"
                                                        + " for replacing 'X' with it:");
                                            } else {
                                                System.out.println("Player " + player2 + " It,s your turn.Please enter a number"
                                                        + " for replacing 'O' with it:");
                                            }
                                        }
                                    } else {
                                        System.out.println("this number already taken");
                                    }
                                } else {
                                    System.out.println("Wrong!!!");
                                }
                            }
                        }

                        if (winner.equalsIgnoreCase("draw")) {
                            System.out.println(
                                    "It's a draw! Thanks for playing.");
                            String string = scanner1.nextLine();
                            if (string.equals("/save")) {
                                write(fileName, board);
                                System.out.println("Your game is successfully saved in this this file ----> (" + fileName + ")");
                                System.exit(0);
                            }
                            else if (string.equals("/exit")) {
                                System.exit(0);
                            }
                            break;

                        } else {
                            if (winner.equals("X")) {
                                System.out.println(
                                        "Congratulations " + player1
                                                + "! You won the game,Thanks for playing.");
                            } else if (winner.equals("O")) {
                                System.out.println(
                                        "Congratulations " + player2
                                                + "! You won the game,Thanks for playing.");

                            }

                            String string = scanner1.nextLine();
                            if (string.equals("/save")) {
                                write(fileName, board);
                                System.out.println("Your game is successfully saved in this this file ----> (" + fileName + ")");
                                System.exit(0);
                            }
                            else if (string.equals("/exit")) {
                                System.exit(0);
                            }
                        }
                        break;

                    case "/load":
                        String string1 = tokens[1];
                        String[] result = string1.split("\\.");
                        String Player1 = result[0];
                        String Player2 = result[1];
                        Scanner scanner = new Scanner(System.in);
                        board = new String[12];
                        String Winner = null;
                        String filename = tokens[1];

                        File TempFile = new File("F:\\TicTacProject\\" + filename);
                        boolean Exists = TempFile.exists();


                        if (!Exists) {
                            for (int i = 0; i < 9; i++) {
                                board[i] = String.valueOf(i + 1);
                            }
                            System.out.println("There isn't any file with this name!!!");
                        } else {
                            String[] s;
                            s = readLines(filename);
                            String[] strArray = null;
                            strArray = s[0].split("-");

                            for (int i = 0; i < strArray.length; i++) {
                                board[i] = strArray[i];
                            }

                            Winner = checkWinner();
                            printBoard();
                            if ( Winner == null ) {
                                if (board[11].equals("X")) {
                                    System.out.println("Player " + Player1 + " It,s your turn.Please enter a number"
                                            + " for replacing 'X' with it:");
                                    turn = "X";
                                } else {
                                    System.out.println("Player " + Player2 + " It,s your turn.Please enter a number"
                                            + " for replacing 'O' with it:");
                                    turn = "O";
                                }
                            }else{
                                System.out.println("This game was finished.");
                            }
                        }

                        while (Winner == null) {
                            String input1 = scanner.nextLine();
                            String[] string = input1.split(" ");
                            if (string[0].equals("/exit")) {
                                System.exit(0);
                            } else
                                if (string[0].equals("/save")) {
                                write(filename, board);
                                System.out.println("Your game is successfully saved in this this file ----> (" + filename + ")");
                                System.exit(0);
                            } else {

                                    if ("/put".equals(string[0])) {
                                        int number = Integer.parseInt(string[1]);

                                        if (!(number > 0 && number <= 9)) {
                                            System.out.println("The Number Input Invalid");
                                            continue;
                                        }


                                        if (board[number - 1].equals(String.valueOf(number))) {
                                            board[number - 1] = turn;

                                            if (turn.equals("X")) {
                                                printBoard();
                                                turn = "O";
                                                board[11] = "O";
                                            } else if (turn.equals("O")) {
                                                printBoard();
                                                turn = "X";
                                                board[11] = "X";
                                            }


                                            Winner = checkWinner();

                                            if (Winner == null) {

                                                if (turn.equals("X")) {
                                                    System.out.println("Player " + Player1 + " It,s your turn.Please enter a number"
                                                            + " for replacing 'X' with it:");
                                                } else if (turn.equals("O")) {
                                                    System.out.println("Player " + Player2 + " It,s your turn.Please enter a number"
                                                            + " for replacing 'O' with it:");
                                                }
                                            }
                                        } else {
                                            System.out.println("this number already taken");
                                        }
                                    } else {
                                        System.out.println("Incorrect value!!!");
                                    }
                            }
                        }

                        if (Winner.equalsIgnoreCase("draw")) {
                            System.out.println(
                                    "It's a draw! Thanks for playing.");
                            String string = scanner1.nextLine();
                            if (string.equals("/save")) {
                                write(filename, board);
                                System.out.println("Your game is successfully saved in this this file ----> (" + filename + ")");
                                System.exit(0);

                            } else if (string.equals("/exit")) {
                                System.exit(0);
                            }

                        } else {
                            if (Winner.equals("X")) {
                                System.out.println(
                                        "Congratulations " + Player1
                                                + "! You won the game,Thanks for playing.");
                            } else if (Winner.equals("O")) {
                                System.out.println(
                                        "Congratulations " + Player2
                                                + "! You won the game,Thanks for playing.");

                            }
                            String string = scanner1.nextLine();
                            if (string.equals("/save")) {
                                write(filename, board);
                                System.out.println("Your game is successfully saved in this this file ----> (" + filename + ")");
                                System.exit(0);
                            } else if (string.equals("/exit")) {
                                System.exit(0);
                            }
                        }
                        break;
                    case "/exit":
                        System.exit(0);
                        break;
                    case "/help":
                        help();
                        break;
                    default:
                        System.out.println("Incorrect value!!!");
                        break Label;
                }
                input = scanner1.nextLine();
                tokens = input.split(" ");
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}

