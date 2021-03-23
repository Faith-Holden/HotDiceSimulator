public class StoppingConditions {

    public static boolean ultraconservativePlayerConditions(int [] keptDice){
        return true;
    }

    public static boolean conservativePlayerConditions(int [] keptDice){
        boolean stopConditionMet = false;
        if (keptDice.length >=2){
            stopConditionMet = true;
        }
        return stopConditionMet;
    }

    public static boolean moderatePlayerConditions(int [] keptDice){
        boolean stopConditionMet = false;
        if (keptDice.length >=3){
            stopConditionMet = true;
        }
        return stopConditionMet;
    }

    public static boolean aggressivePlayerConditions(int [] keptDice){
        boolean stopConditionMet = false;
        if (keptDice.length >=4){
            stopConditionMet = true;
        }
        return stopConditionMet;
    }

    public static boolean extremelyAggressivePlayerConditions(int [] keptDice){
        boolean stopConditionMet = false;
        if (keptDice.length >=5){
            stopConditionMet = true;
        }
        return stopConditionMet;
    }
}
