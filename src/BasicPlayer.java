import java.util.Random;

public class BasicPlayer {

    public static int [] dicetoKeep(int [] diceHand, boolean [] whethertoReroll){
        int keptDiceArrayLength = 0;
        int [] holdDiceTemporarily = new int [6];

        for (int i = 0; i<diceHand.length; i++){
            if (whethertoReroll[diceHand[i]-1]){
                holdDiceTemporarily[i]=diceHand[i];
                keptDiceArrayLength++;
            }
        }

        int [] keptDice= new int [keptDiceArrayLength];
        int positionCounter = 0;

        for (int value : holdDiceTemporarily) {
            if (value != 0) {
                for (int j = 0; j < keptDice.length; j++) {
                    if (keptDice[positionCounter] == 0) {
                        keptDice[positionCounter] = value;
                        break;
                    } else {
                        positionCounter++;
                    }
                }
            }
        }
        //System.out.println("the dice to keep are");
        //System.out.println(Utilities.printArray(keptDice));

        return keptDice;
    }

    public static int [] dicetoReroll(int [] diceHand, boolean [] whethertoReroll){
        int dicetoRerollArrayLength = 0;
        int [] holdDiceTemporarily = new int [6];

        for (int i = 0; i<diceHand.length; i++){
            if (!whethertoReroll[diceHand[i]-1]){
                holdDiceTemporarily[i]=diceHand[i];
                dicetoRerollArrayLength++;
            }
        }

        int [] rerolledDice= new int [dicetoRerollArrayLength];
        int positionCounter = 0;

        for (int value : holdDiceTemporarily) {
            if (value != 0) {
                for (int j = 0; j < rerolledDice.length; j++) {
                    if (rerolledDice[positionCounter] == 0) {
                        rerolledDice[positionCounter] = value;
                        break;
                    } else {
                        positionCounter++;
                    }
                }
            }
        }

        //System.out.println("the dice to reroll are");
        //System.out.println(Utilities.printArray(rerolledDice));
        return rerolledDice;
    }

    public static DiceContainer getFinalDiceHand(int [] diceHand, boolean [] whethertoReroll, int playerIdentifier, Random randomNumber){
        int [] handofDice = diceHand.clone();
        boolean stopConditionMet = false;
        int handScore = Engine.scoreHand(Engine.tallyDice(handofDice));
        DiceContainer rerolledDice = new DiceContainer();
        boolean farkle = false;

        if (Engine.scoreHand(Engine.tallyDice(handofDice))!=0){



            while (!stopConditionMet){
                int [] keptDice = BasicPlayer.dicetoKeep(handofDice, whethertoReroll);


                if (keptDice.length == 6) {
                    farkle = true;
                }

                stopConditionMet = checkStopCondition(keptDice, playerIdentifier);
                if (stopConditionMet){
                    break;
                }

                int [] dicetoReroll = BasicPlayer.dicetoReroll(handofDice, whethertoReroll);
                int [] rerolledDiceHand = Engine.rollDice(dicetoReroll.length, randomNumber);

                handScore += Engine.scoreHand(Engine.tallyDice(rerolledDiceHand));
                if (Engine.scoreHand(Engine.tallyDice(rerolledDiceHand))==0){
                    handScore = 0;
                    break;
                }
                handofDice = Utilities.concatenateArrays(keptDice, rerolledDiceHand);
                keptDice = BasicPlayer.dicetoKeep(handofDice, whethertoReroll);
                if (keptDice.length == 6){
                    farkle = true;
                }
                stopConditionMet = checkStopCondition(keptDice, playerIdentifier);
            }



        }

        else if (Engine.scoreHand(Engine.tallyDice(handofDice))==0){
            rerolledDice.badFirstRoll = true;
        }

        rerolledDice.handScore = handScore;
        rerolledDice.diceHand = handofDice;
        //System.out.println("the final hand is");
        //System.out.println(Utilities.printArray(handofDice));

        rerolledDice.farkle = farkle;

        return rerolledDice;
    }

    public static boolean checkStopCondition(int[] dicetoCheck, int playerIdentifier){
        boolean stopConditionMet = false;

        if (playerIdentifier == 0){
            stopConditionMet = StoppingConditions.ultraconservativePlayerConditions(dicetoCheck);
        }

        else if (playerIdentifier == 1){
            stopConditionMet = StoppingConditions.conservativePlayerConditions(dicetoCheck);
        }

        else if (playerIdentifier == 2){
            stopConditionMet = StoppingConditions.moderatePlayerConditions(dicetoCheck);
        }

        else if (playerIdentifier == 3){
            stopConditionMet = StoppingConditions.aggressivePlayerConditions(dicetoCheck);
        }

        else if (playerIdentifier == 4){
            stopConditionMet = StoppingConditions.extremelyAggressivePlayerConditions(dicetoCheck);
        }

        return stopConditionMet;
    }

}