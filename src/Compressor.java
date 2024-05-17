import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Compressor {

    public static List<Integer> compress(String inputContent)
    {
        Map<String,Integer> dictionary =new HashMap<>();
        for(int i=0;i<256;i++)
        {
            dictionary.put("" + (char) i,i);
        }
        StringBuilder sb=new StringBuilder();
        List<Integer> compressData=new ArrayList<>();
        for(int i=0;i<inputContent.length();i++)
        {
            char currentCharacter=inputContent.charAt(i);
            String nextCharacter=sb.toString()+ currentCharacter;
            if(dictionary.containsKey(nextCharacter))
            {
                sb.append(currentCharacter);
            }
            else
            {
                compressData.add(dictionary.get(sb.toString()));
                dictionary.put(nextCharacter,dictionary.size());
                sb = new StringBuilder("" + currentCharacter);
            }
        }
        if(!sb.isEmpty())
        {
            compressData.add(dictionary.get(sb.toString()));
        }

       return compressData;

    }
}
