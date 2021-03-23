import java.util.Arrays;
import java.util.Random;

public class Utilities {

    public static String printArray(int [] thingtoprint){
        //This method simply takes in an integer array and outputs a string with the values of that array.
        StringBuilder stringtoprint = new StringBuilder();
        for (int value : thingtoprint) {
            String stringinstance = String.valueOf(value);
            stringtoprint.append(stringinstance);
            stringtoprint.append(",");
        }

        return stringtoprint.toString();
    }

    /* public static String printBooleanArray (boolean [] thingtoprint){
         //Takes in a boolean array and outputs a string with values of that array.
         StringBuilder stringtoprint = new StringBuilder();
         for (boolean value : thingtoprint) {
             String stringinstance = String.valueOf(value);
             stringtoprint.append(stringinstance).append(", ");
         }
         return stringtoprint.toString();
     }
     */
    public static int [] concatenateArrays(int [] firstArray, int [] arraytoAdd){
        int[] newarray = new int[firstArray.length + arraytoAdd.length];
        System.arraycopy(firstArray, 0, newarray, 0, firstArray.length);
        System.arraycopy(arraytoAdd, 0, newarray, firstArray.length, arraytoAdd.length);
        return newarray;
    }
}
