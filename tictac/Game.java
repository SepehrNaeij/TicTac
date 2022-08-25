// This project is written by *Sepehr_Naeij*

import java.io.*;
import java.util.*;
import java.util.List;

public class Game {
    public static String[] board;
    public static String turn;

    public static void help() {
        System.out.println("/start ----> It,s for beginning a game between two player.");
        System.out.println("/save  ----> It,s for saving a game.");
        System.out.println("/load  ----> It,s for having access to a saved game.");
        System.out.println("/put   ----> It,s for adding a number in the TicTac game.");
        System.out.println("/help  ----> It,s for showing us how can we work with this game.");
        System.out.println("/exit  ----> It,s for closing the game.");
    }

    public static String checkWinner() {
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

    public static void printBoard() {
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

        return lines.toArray(new String[0]);
    }

    public static void write(String fileName, String[] x) throws Exception {
        FileWriter writer = new FileWriter(fileName);
        for (int i = 0; i < x.length; i++) {
            writer.write(x[i] + "-");
        }
        writer.close();
    }
}



