package fhku.appprojektmastermind;

import java.util.ArrayList;
import java.util.List;

public class ColorGuess {
    protected Integer[] colors;

    public ColorGuess(Integer[] colors) {
        this.colors = colors;
    }
    /** Constructor for a new <code>ColorGuess</code> containing all <code>null</code> values
     * @param guessLength indicates the length of the <code>ColorGuess</code>
     */
    public ColorGuess(int guessLength) {
        this.colors = new Integer[guessLength]; // the new array is filled with null by default
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

    public Integer getColorAtIndex(int index) {
        return colors[index];
    }

    public int getLength() {
        return colors.length;
    }

    /** Creates a <code>List</code> of <code>ColorGuess</code> objects containing only null values
     * @param guessLength indicates the length of each contained <code>ColorGuess</code>
     * @param listLength indicates the length of the returned <code>List</code>
     */
    public static List<ColorGuess> emptyGuessList(int guessLength, int listLength) {
        List<ColorGuess> list = new ArrayList<>();
        for (int i = 0; i < listLength; i++) {
            list.add(new ColorGuess(guessLength));
        }
        return list;
    }
}
