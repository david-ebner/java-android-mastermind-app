package fhku.appprojektmastermind;

import java.util.ArrayList;
import java.util.List;

public class ColorGuess {
    //TODO: this Class has to contain a list (e.g. size 4) of Colors or empty fields
    protected int[] colors;

    //TODO: the int[] list is just for testing purposes!
    public ColorGuess(int[] colors) {
        this.colors = colors;
    }

    // toString is just for testing purposes!
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ColorGuess{");
        for (int i : colors) {
            sb.append(i).append(", ");
        }
        sb.delete(sb.length()-2, sb.length());
        sb.append("}");
        return sb.toString();
    }

    public int getColorAtIndex(int index) {
        return colors[index];
    }

    public int getLength() {
        return colors.length;
    }

    /** Creates a new <code>ColorGuess</code> containing all zeroes
     * @param guessLength indicates the length of the <code>ColorGuess</code> (= the number of zeroes)
     */
    public static ColorGuess emptyGuess(int guessLength) {
        int[] zeroes = new int[guessLength]; // the new array is filled with zeroes by default
        return new ColorGuess(zeroes);
    }

    /** Creates a <code>List</code> of <code>ColorGuess</code> objects containing only zeroes
     * @param guessLength indicates the length of each contained <code>ColorGuess</code>
     * @param listLength indicates the length of the returned <code>List</code>
     */
    public static List<ColorGuess> emptyGuessList(int guessLength, int listLength) {
        List<ColorGuess> list = new ArrayList<>();
        for (int i = 0; i < listLength; i++) {
            list.add(emptyGuess(guessLength));
        }
        return list;
    }
}
