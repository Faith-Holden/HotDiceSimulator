import jdk.jshell.execution.Util;

import java.util.Scanner;

public class PlayGame {
    public static void main (String [] args){
        int [] gamePlayers = {};
        boolean testManyTimes = false;
        int numberOfTestGames = 0;

        System.out.println("Ultraconservative Player = 0");
        System.out.println("Conservative Player = 1");
        System.out.println("Moderate Player = 2");
        System.out.println("Aggressive Player = 3");
        System.out.println("Extremely aggressive Player = 4");
        System.out.println("Please type the number corresponding to your 1st player, or type 'done' when you have selected all your players");
        String completionStatus = "not done";

        while (!completionStatus.equals("done")){
            Scanner scanOfUserInput = new Scanner(System.in);
            String userInput = scanOfUserInput.nextLine();

            if (userInput.equals("done")){
                completionStatus = "done";
            }
            else if(userInput.equals("0") ||userInput.equals("1")||userInput.equals("2") || userInput.equals("3") ||userInput.equals("4")){
                int [] playerType = {Integer.parseInt(userInput)};
                gamePlayers = Utilities.concatenateArrays(gamePlayers, playerType);
                System.out.println("Thanks, your player choice was " + userInput +". Please choose another player, or type 'done'.");
            }
            else if (userInput.equals("test")){
                testManyTimes = true;
                System.out.println("How many games would you like to simulate?");
            }
            else if (testManyTimes){
                numberOfTestGames = Integer.parseInt(userInput);
                completionStatus = "done";
            }

            else {
                System.out.println("That was not a valid input. Please type a player number, or 'done'.");
            }
        }
        System.out.println("Your Players are " + Utilities.printArray(gamePlayers));



        if (testManyTimes){
            int playedTimes = 0;
            int[] playerAverages = new int[5];
            int[] gameTotals = new int[5];
            int[] gameWinsTotal = new int[5];
            while (playedTimes<numberOfTestGames){
                playedTimes++;
                int[] testPlayers = {0,1,2,3,4};
                int[] gameResults = playGame(testPlayers);

                for (int i = 0; i<playerAverages.length; i++){
                    gameTotals[i]+=gameResults[i];
                    playerAverages[i] = ((gameTotals[i])/playedTimes);
                    if (gameTotals[i]>=10000){
                        gameWinsTotal[i]++;
                    }
                }
            }
            System.out.println("The average scores were ");
            System.out.println(Utilities.printArray(playerAverages));
            System.out.println("The number of wins per player was ");
            System.out.println(Utilities.printArray(gameWinsTotal));
        }
        if (!testManyTimes){
            playGame(gamePlayers);
        }
    }

    //player types: ultraconservative, conservative, moderate, aggressive, extremely aggressive
    public static int[] playGame(int [] playerTypes){
        int highestScore = 0;
        int [] playersScores = new int [playerTypes.length];
        DiceContainer myContainer = Engine.myContainer();
        //boolean [] whethertoReroll = myContainer.diceWithScore;


        while (highestScore<10000){
            for (int i = 0; i<playerTypes.length; i++){
                boolean farkle = true;
                int tempDiceScore = 0;

                while (farkle) {

                    DiceContainer playerTurn = PlayGame.playerTurn(playerTypes[i]);
                    if (playerTurn.badFirstRoll){
                        playersScores[i]=0;
                    }
                    else if (playerTurn.handScore!=0){
                        tempDiceScore+=playerTurn.handScore;
                    }
                    else {
                        tempDiceScore = 0;
                    }
                    farkle = playerTurn.farkle;
                }
                playersScores[i]+=tempDiceScore;


                //System.out.println("player (" + i + ")'s current score is");
                //System.out.println(playersScores[i]);
                if (playersScores[i]>=highestScore){
                    highestScore=playersScores[i];
                }
                if (highestScore>=10000){
                    break;
                }
            }
        }

        System.out.println("the final scores were");
        System.out.println(Utilities.printArray(playersScores));
        return playersScores;
    }

    public static DiceContainer playerTurn(int playerIdentifier){
        int playerScore = 0;
        DiceContainer playerTurnDice = new DiceContainer();
        playerTurnDice.farkle = false;
        playerTurnDice.badFirstRoll = false;
        DiceContainer myContainer = Engine.myContainer();
        boolean [] whethertoReroll = myContainer.diceWithScore;
        DiceContainer rerolledDice = BasicPlayer.getFinalDiceHand(Engine.rollDice(6, myContainer.randomObject), whethertoReroll, playerIdentifier, myContainer.randomObject);
        playerScore += rerolledDice.handScore;
        if (rerolledDice.farkle){
            playerTurnDice.farkle = true;
        }
        if (rerolledDice.badFirstRoll){
            playerTurnDice.badFirstRoll = true;
        }
        playerTurnDice.handScore = playerScore;
        return playerTurnDice;
    }
}
