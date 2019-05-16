package Utils;

public class CharacterArray {

    public static Character[] convert(String s) {
        Character[] result = new Character[s.length()];
        for (int i = 0; i < s.length(); i++) {
            result[i] = s.charAt(i);
        }
        return result;
    }

}
