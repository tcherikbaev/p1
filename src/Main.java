import java.io.IOException;
import java.util.List;

public class Main {
    public static final String INPUT_FILE_PATH = "wwwroot/Input File.txt";
    public static final String OUTPUT_FILE_PATH = "wwwroot/Compressed.txt";

    public static final String DECOMPRESSED_FILE_PATH = "wwwroot/Decompressed.txt";

    public static void main(String[] args) {
        try {
            String fileContent = WorkWithFile.readFile(INPUT_FILE_PATH);
            List<Integer> compressedData = Compressor.compress(fileContent);
            WorkWithFile.writeFile(compressedData,OUTPUT_FILE_PATH);
            System.out.println("File compressed successfully.");

            String decompressed = Decompressor.decompress(compressedData);
            WorkWithFile.writeFile(decompressed,DECOMPRESSED_FILE_PATH);
            System.out.println("File decompressed successfully.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
