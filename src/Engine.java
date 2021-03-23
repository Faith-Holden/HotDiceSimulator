import java.util.Arrays;
import java.util.Random;

public class Engine {

    public static DiceContainer myContainer() {
        int startingDiceHandSize = 6;
        Random RandomObject = new Random();
        DiceContainer myDiceContainer = new DiceContainer();
        myDiceContainer.randomObject = RandomObject;
        myDiceContainer.diceHand = rollDice(startingDiceHandSize, RandomObject);
        myDiceContainer.diceTally = tallyDice(myDiceContainer.diceHand);
        myDiceContainer.scoringDice = findScoringDice(myDiceContainer.diceTally);
        myDiceContainer.diceWithScore = checkWhetherDiceareScoring(myDiceContainer.scoringDice);
        myDiceContainer.handScore = scoreHand(myDiceContainer.diceTally);

        return myDiceContainer;
    }

    public static int [] rollDice (int numberofdice, Random randomObject){
        //this function takes in an integer (number of new dice to be rolled) and outputs a hand of dice of that size.
        int[] diceresults;
        diceresults = new int[numberofdice];

        for(int i = 0; i < numberofdice; i++){
            diceresults[i]=randomObject.nextInt(6)+1;
        }

        //System.out.println("the rolled dice were");
        //System.out.println(Utilities.printArray(diceresults));
        return diceresults;
    }

    public static int[] tallyDice(int [] diceresults){
        int tally1 = 0;
        int tally2 = 0;
        int tally3 = 0;
        int tally4 = 0;
        int tally5 = 0;
        int tally6 = 0;

        for (int diceResult : diceresults) {
            if (diceResult == 1) {
                tally1++;
            }
            if (diceResult == 2) {
                tally2++;
            }
            if (diceResult == 3) {
                tally3++;
            }
            if (diceResult == 4) {
                tally4++;
            }
            if (diceResult == 5) {
                tally5++;
            }
            if (diceResult == 6) {
                tally6++;
            }
        }

        return new int[]{tally1, tally2, tally3, tally4, tally5, tally6};
    }

    public static int[] tallyTallies(int[] diceTally){
        int Singles = 0;
        int Doubles = 0;
        int Triples = 0;
        int FourOfAKind = 0;
        int FiveOfAKind = 0;
        int SixOfAKind = 0;

        for (int diceTallyValue : diceTally) {
            if (diceTallyValue == 1) {
                Singles++;
            }
            if (diceTallyValue == 2) {
                Doubles++;
            }
            if (diceTallyValue == 3) {
                Triples++;
            }
            if (diceTallyValue == 4) {
                FourOfAKind++;
            }
            if (diceTallyValue == 5) {
                FiveOfAKind++;
            }
            if (diceTallyValue == 6) {
                SixOfAKind++;
            }
        }
        return new int[]{Singles, Doubles, Triples, FourOfAKind, FiveOfAKind, SixOfAKind};
    }

    public static int[] findScoringDice (int[] diceTally){
        int[] tallyTallies = tallyTallies(diceTally);
        int[] diceTallies = diceTally.clone();
        int[] scoredDice=new int[diceTallies.length];
        scoredDice[0]=diceTallies[0];
        scoredDice[4]=diceTallies[4];

        if (tallyTallies[0] == 6){
            Arrays.fill(scoredDice, 1);
        }

        else if (tallyTallies[1] == 3){
            for (int i=0; i<diceTallies.length; i++){
                if (diceTallies[i]==2){
                    scoredDice[i]=2;
                }
            }
        }

        else if (tallyTallies[2]==2) {
            for (int i = 0; i < diceTallies.length; i++) {
                if (diceTallies[i] == 3) {
                    scoredDice[i] = 3;
                }
            }
        }

        else if (tallyTallies[5]==1){
            for (int i = 0; i < diceTallies.length; i++) {
                if (diceTallies[i] == 6) {
                    scoredDice[i] = 6;
                }
            }
        }

        //standard cases
        else if (tallyTallies[4] ==1){
            for (int i= 0; i<diceTallies.length; i++){
                if (diceTallies[i]==5){
                    scoredDice[i]=diceTallies[i];
                }
            }
        }

        else if (tallyTallies[3] ==1){
            for (int i= 0; i<diceTallies.length; i++){
                if (diceTallies[i]==4){
                    scoredDice[i]=diceTallies[i];
                }
            }
        }

        else if (tallyTallies[2] ==1){
            for (int i= 0; i<diceTallies.length; i++){
                if (diceTallies[i]==3){
                    scoredDice[i]=diceTallies[i];
                }
            }
        }
        //System.out.println("the scored dice are");
        //System.out.println(Utilities.printArray(scoredDice));

        return scoredDice;
    }

    public static int scoreHand(int[] diceTally){
        //takes in the tally of the dice and scores the hand, outputting that score.
        int[] scoreddice = findScoringDice(diceTally);
        int[] diceTallies = tallyTallies(diceTally);
        int score = 0;

        if (diceTallies[5]==1 || diceTallies[2]==2 || diceTallies[1]==3 || diceTallies[0]==6) {
            score = 2600;
        }

        else if (diceTallies[4]==1){
            if (scoreddice[0]==5){
                score+=3000;
                score+=(scoreddice[4]*50);
            }
            if (scoreddice[1]==5){
                score+=600;
                score+=(scoreddice[0]*100);
                score+=(scoreddice[4]*50);
            }
            if (scoreddice[2]==5){
                score+=900;
                score+=(scoreddice[0]*100);
                score+=(scoreddice[4]*50);
            }
            if (scoreddice[3]==5){
                score+=1200;
                score+=(scoreddice[0]*100);
                score+=(scoreddice[4]*50);
            }
            if (scoreddice[4]==5){
                score+=1500;
                score+=(scoreddice[0]*100);
            }
            if (scoreddice[5]==5){
                score+=1800;
                score+=(scoreddice[0]*100);
                score+=(scoreddice[4]*50);
            }
        }

        else if (diceTallies[3]==1){
            if (scoreddice[0]==4){
                score+=2000;
                score+=(scoreddice[4]*50);
            }
            if (scoreddice[1]==4){
                score+=400;
                score+=(scoreddice[0]*100);
                score+=(scoreddice[4]*50);
            }
            if (scoreddice[2]==4){
                score+=600;
                score+=(scoreddice[0]*100);
                score+=(scoreddice[4]*50);
            }
            if (scoreddice[3]==4){
                score+=800;
                score+=(scoreddice[0]*100);
                score+=(scoreddice[4]*50);
            }
            if (scoreddice[4]==4){
                score+=1000;
                score+=(scoreddice[0]*100);
            }
            if (scoreddice[5]==4){
                score+=1200;
                score+=(scoreddice[0]*100);
                score+=(scoreddice[4]*50);
            }
        }

        else if (diceTallies[2]==1){
            if (scoreddice[0]==3){
                score+=1000;
                score+=(scoreddice[4]*50);
            }
            if (scoreddice[1]==3){
                score+=200;
                score+=(scoreddice[0]*100);
                score+=(scoreddice[4]*50);
            }
            if (scoreddice[2]==3){
                score+=300;
                score+=(scoreddice[0]*100);
                score+=(scoreddice[4]*50);
            }
            if (scoreddice[3]==3){
                score+=400;
                score+=(scoreddice[0]*100);
                score+=(scoreddice[4]*50);
            }
            if (scoreddice[4]==3){
                score+=500;
                score+=(scoreddice[0]*100);
            }
            if (scoreddice[5]==3){
                score+=600;
                score+=(scoreddice[0]*100);
                score+=(scoreddice[4]*50);
            }
        }

        else{
            score+=(scoreddice[0]*100);
            score+=(scoreddice[4]*50);
        }
        //System.out.println("the score for this hand is");
        //System.out.println(score);

        return score;

        /*scoring options: tally of 1s, tally of 5s, stb, 3 of a kind, 4 of a kind, 5 of a kind,
        boxcars (3 pair, 2 triples, straight, 6 of a kind)
         */

    }

    public static boolean[] checkWhetherDiceareScoring(int[] scoreddice){
        boolean scored1s=false;
        boolean scored2s=false;
        boolean scored3s=false;
        boolean scored4s=false;
        boolean scored5s=false;
        boolean scored6s=false;

        if (scoreddice[0]!=0){
            scored1s=true;
        }
        if (scoreddice[1]!=0){
            scored2s=true;
        }
        if (scoreddice[2]!=0){
            scored3s=true;
        }
        if (scoreddice[3]!=0){
            scored4s=true;
        }
        if (scoreddice[4]!=0){
            scored5s=true;
        }
        if (scoreddice[5]!=0){
            scored6s=true;
        }

        return new boolean[]{scored1s, scored2s, scored3s, scored4s, scored5s, scored6s};

    }


}


