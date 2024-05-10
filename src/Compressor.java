import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Compressor {

    public static final String INPUT_FILE_PATH = "wwwroot/test.txt";
    public static final String OUTPUT_FILE_PATH = "wwwroot/result.zip";

    public static void main(String[] args) {
        try {
            String fileContent = readFile(INPUT_FILE_PATH);
            compressAndWriteFile(fileContent, OUTPUT_FILE_PATH);
            System.out.println("File compressed successfully.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readFile(String filePath) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    public static void compressAndWriteFile(String inputContent, String outputPath) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(outputPath);
             ZipOutputStream zipOS = new ZipOutputStream(fos)) {
            ZipEntry zipEntry = new ZipEntry("result.txt");
            zipOS.putNextEntry(zipEntry);
            zipOS.write(inputContent.getBytes());
            zipOS.closeEntry();
        }
    }
}
