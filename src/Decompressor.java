import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Decompressor {

    public static String decompress(List<Integer> encodedText) {
        int dictSize = 256;
        Map<Integer, String> dictionary = new HashMap<>();
        for (int i = 0; i < dictSize; i++) {
            dictionary.put(i, String.valueOf((char) i));
        }

        StringBuilder result = new StringBuilder();
        if (!encodedText.isEmpty()) {
            String characters = String.valueOf((char) encodedText.removeFirst().intValue());
            result.append(characters);
            for (Integer code : encodedText) {
                String entry = dictionary.containsKey(code)
                        ? dictionary.get(code)
                        : characters + characters.charAt(0);
                result.append(entry);
                dictionary.put(dictSize++, characters + entry.charAt(0));
                characters = entry;
            }
        }
        return result.toString();
    }

}
