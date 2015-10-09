/**
 * Created by anas2605 on 10/8/2015.
 */

import java.util.Scanner;

class Pair {
    int start;                                      //starting position of snake or ladder
    int end;                                        //end position of snake or ladder

    public Pair(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class SnakeLadderGame {
    private int numberOfPlayers;
    private int numberOfSnakes;
    private int numberOfLadders;

    private int []board;                            //Array board represents board for snake ladder game.
    private int []playerPosition;                   //Array playerPosition represent player's position on board.

    private Pair []ladderPosition;
    private Pair []snakePosition;
    private String[]playerName;

    void input() {
        Scanner sc = new Scanner(System.in);

        //Input for snake
        System.out.println("Enter number of snakes");
        numberOfSnakes = sc.nextInt();

        snakePosition = new Pair[numberOfSnakes+1];
        System.out.println("Enter position of snakes");
        for (int i = 0; i < numberOfSnakes; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            snakePosition[i] = new Pair(start, end);
        }

        //Input for Ladder
        System.out.println("Enter number of Ladders");
        numberOfLadders = sc.nextInt();

        ladderPosition = new Pair[numberOfLadders+1];
        System.out.println("Enter position of ladders");
        for (int i = 0; i < numberOfLadders; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            ladderPosition[i] = new Pair(start, end);
        }

        //Input for player
        System.out.println("Enter number of players");
        numberOfPlayers = sc.nextInt();

        System.out.println("Enter Player names");
        playerName = new String[numberOfPlayers+2];

        sc.nextLine();                                      //consume newline leftover
        for (int i = 1; i <= numberOfPlayers; i++) {
            playerName[i] = sc.nextLine();
        }
    }

    void init() {

        //initialize board.
        //board[i] = 0 represent that at board[i] there is neither ladder nor snake.
        //board[i] != 0 represent that at board[i] there is either ladder or snake.

        board = new int[105];
        for (int i = 0; i < 105; i++) {
            this.board[i] = 0;
        }

        for (int i = 0; i < numberOfSnakes; i++) {
            this.board[snakePosition[i].start] = snakePosition[i].end;
        }


        for (int i = 0; i < numberOfLadders; i++) {
            this.board[ladderPosition[i].start] = ladderPosition[i].end;
        }


        //initialize players position.
        playerPosition = new int[numberOfPlayers+1];
        for (int i = 0; i <= numberOfPlayers; i++) {
            playerPosition[i]  = 0;                     //playerPosition[i] = 0 represent starting position of a player.
        }
    }

    void play() {

        Scanner sc = new Scanner(System.in);
        int player = 1;                                 //player 1 starts game and game continue in cyclic order.
        int roll;                                       //roll represents roll on dice.

        System.out.println("Game start");
        while (true) {
            System.out.println("\nEnter player " + playerName[player] + "'s turn\n");
            roll = sc.nextInt();

            //checks error caused by wrong input
            if (roll < 1 || roll > 6) {
                System.out.println("Enter correct roll");
                continue;
            }

            //if player's next position is not greater than 100 that is players position is not out of board then player moves accordingly
            //else stay at previous position and dice pass to next player.
            if (roll + playerPosition[player] <= 100) {

                roll += playerPosition[player];

                //checks whether next position have ladder or snake in it
                //else player next position on board would be player current position plus roll on dice.
                if (board[roll] != 0) {
                    playerPosition[player] = board[roll];
                } else {
                    playerPosition[player] = roll;
                }
            }

            if (playerPosition[player] == 100) {
                System.out.println("\nPlayer " + playerName[player] + " won\n");
                return;
            }

            System.out.println("\nPositions");
            for (int i = 1; i <= numberOfPlayers; i++) {
                System.out.println(playerName[i] + " -> " + playerPosition[i]);
            }
            System.out.println("\n");


            //next player turn.
            if (player == numberOfPlayers) {
                player = 1;
            } else {
                player++;
            }
        }
    }

    public static void main(String[] args) {
        SnakeLadderGame gameObject = new SnakeLadderGame();

        gameObject.input();

        //initializes the board for playing with snakes and ladders.
        gameObject.init();

        //play game
        gameObject.play();
    }
}
