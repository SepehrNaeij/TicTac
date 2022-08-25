import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Game.help();

        Scanner scanner1 = new Scanner(System.in);

        try {

            Label:
            while (true) {

                String input = scanner1.nextLine();
                String[] tokens = input.split(" ");

                switch (tokens[0]) {
                    case "/start":
                        System.out.println("| 1" + " | 2" + " | 3" + " |");
                        System.out.println("| 4" + " | 5" + " | 6" + " |");
                        System.out.println("| 7" + " | 8" + " | 9" + " |");
                        Scanner in = new Scanner(System.in);
                        String player1 = tokens[1];
                        String player2 = tokens[2];
                        Game.board = new String[12];
                        String winner = null;
                        String time = DateTimeFormatter.ofPattern("yyyy-MM-dd_._hh-mm-ss").format(LocalDateTime.now());
                        String fileName;
                        fileName = player1 + "." + player2 + ".(" + time + ").txt";
                        Game.turn = "X";
                        File tempFile = new File("F:\\TicTacProject\\" + fileName);
                        boolean exists = tempFile.exists();


                        if (!exists) {
                            for (int i = 0; i < 9; i++) {
                                Game.board[i] = String.valueOf(i + 1);
                            }
                            Game.board[9] = player1;
                            Game.board[10] = player2;

                        } else {
                            String[] s;
                            s = Game.readLines(fileName);
                            String[] strArray;
                            strArray = s[0].split("-");

                            for (int i = 0; i < strArray.length; i++) {
                                Game.board[i] = strArray[i];
                            }
                            player1 = Game.board[9];
                            player2 = Game.board[10];
                            winner = Game.checkWinner();
                            Game.printBoard();
                            if (Game.board[11].equals("X")) {
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
                            } else if (string[0].equals("/save")) {
                                Game.write(fileName, Game.board);
                                System.out.println("Your game is successfully saved in this this file ----> (" + fileName + ")");
                                System.exit(0);
                            } else {

                                if ("/put".equals(string[0])) {
                                    int number = Integer.parseInt(string[1]);

                                    if (!(number > 0 && number <= 9)) {
                                        System.out.println("The Number Input Invalid");
                                        continue;
                                    }


                                    if (Game.board[number - 1].equals(String.valueOf(number))) {
                                        Game.board[number - 1] = Game.turn;

                                        if (Game.turn.equals("X")) {
                                            Game.printBoard();
                                            Game.turn = "O";
                                            Game.board[11] = "O";
                                        } else {
                                            Game.printBoard();
                                            Game.turn = "X";
                                            Game.board[11] = "X";
                                        }

                                        winner = Game.checkWinner();

                                        if (winner == null) {
                                            if (Game.turn.equals("X")) {
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
                                    System.out.println("Wrong Input!!!");
                                }
                            }
                        }

                        if (winner.equalsIgnoreCase("draw")) {
                            System.out.println(
                                    "It's a draw! Thanks for playing.");
                            String string = scanner1.nextLine();
                            if (string.equals("/save")) {
                                Game.write(fileName, Game.board);
                                System.out.println("Your game is successfully saved in this this file ----> (" + fileName + ")");
                                System.exit(0);
                            } else if (string.equals("/exit")) {
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
                                Game.write(fileName, Game.board);
                                System.out.println("Your game is successfully saved in this this file ----> (" + fileName + ")");
                                System.exit(0);
                            } else if (string.equals("/exit")) {
                                System.exit(0);
                            }
                        }
                        break;

                    case "/load":
                        while (true) {
                            String string1 = tokens[1];
                            String[] result = string1.split("\\.");
                            String Player1 = result[0];
                            String Player2 = result[1];
                            Scanner scanner = new Scanner(System.in);
                            Game.board = new String[12];
                            String Winner = null;
                            String filename = tokens[1];

                            File TempFile = new File("F:\\TicTacProject\\" + filename);
                            boolean Exists = TempFile.exists();


                            if (!Exists) {
                                for (int i = 0; i < 9; i++) {
                                    Game.board[i] = String.valueOf(i + 1);
                                }
                                System.out.println("There isn't any file with this name!!!");
                            } else {
                                String[] s;
                                s = Game.readLines(filename);
                                String[] strArray = null;
                                strArray = s[0].split("-");

                                for (int i = 0; i < strArray.length; i++) {
                                    Game.board[i] = strArray[i];
                                }

                                Winner = Game.checkWinner();
                                Game.printBoard();
                                if (Winner == null) {
                                    if (Game.board[11].equals("X")) {
                                        System.out.println("Player " + Player1 + " It,s your turn.Please enter a number"
                                                + " for replacing 'X' with it:");
                                        Game.turn = "X";
                                    } else {
                                        System.out.println("Player " + Player2 + " It,s your turn.Please enter a number"
                                                + " for replacing 'O' with it:");
                                        Game.turn = "O";
                                    }
                                } else {
                                    System.out.println("This game was finished.");
                                }
                            }

                            while (Winner == null) {
                                String input1 = scanner.nextLine();
                                String[] string = input1.split(" ");
                                if (string[0].equals("/exit")) {
                                    System.exit(0);
                                } else if (string[0].equals("/save")) {
                                    Game.write(filename, Game.board);
                                    System.out.println("Your game is successfully saved in this this file ----> (" + filename + ")");
                                    System.exit(0);
                                } else {

                                    if ("/put".equals(string[0])) {
                                        int number = Integer.parseInt(string[1]);

                                        if (!(number > 0 && number <= 9)) {
                                            System.out.println("The Number Input Invalid");
                                            continue;
                                        }


                                        if (Game.board[number - 1].equals(String.valueOf(number))) {
                                            Game.board[number - 1] = Game.turn;

                                            if (Game.turn.equals("X")) {
                                                Game.printBoard();
                                                Game.turn = "O";
                                                Game.board[11] = "O";
                                            } else if (Game.turn.equals("O")) {
                                                Game.printBoard();
                                                Game.turn = "X";
                                                Game.board[11] = "X";
                                            }


                                            Winner = Game.checkWinner();

                                            if (Winner == null) {

                                                if (Game.turn.equals("X")) {
                                                    System.out.println("Player " + Player1 + " It,s your turn.Please enter a number"
                                                            + " for replacing 'X' with it:");
                                                } else if (Game.turn.equals("O")) {
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
                                    Game.write(filename, Game.board);
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
                                    Game.write(filename, Game.board);
                                    System.out.println("Your game is successfully saved in this this file ----> (" + filename + ")");
                                    System.exit(0);
                                } else if (string.equals("/exit")) {
                                    System.exit(0);
                                }
                            }
                        }
                    case "/exit":
                        System.exit(0);
                        break;
                    case "/help":
                        Game.help();
                        break;
                    default:
                        System.out.println("Incorrect value!!!");
                        break Label;
                }
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}