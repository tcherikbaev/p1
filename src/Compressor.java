import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Compressor {

    public static final String INPUT_FILE_PATH = "wwwroot/Input File.txt";
    public static final String OUTPUT_FILE_PATH = "wwwroot/Compressed.txt";

    public static void main(String[] args) {
        try {
            String fileContent = WorkWithFile.readFile(INPUT_FILE_PATH);
            List<Integer> compressedData = compress(fileContent);
            writeCompressedData(compressedData,OUTPUT_FILE_PATH);
            System.out.println("File compressed successfully.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


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
    public static void writeCompressedData(List<Integer> compressedData, String outputFilePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            for (int code : compressedData) {
                writer.write(code + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
