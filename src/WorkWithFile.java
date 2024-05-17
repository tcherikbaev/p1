    import java.io.*;
    import java.util.List;

    public class WorkWithFile {
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
        public static void writeFile(Object data, String outputFilePath) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
                if (data instanceof List<?> dataList) {
                    for (Object item : dataList) {
                        if (item != null) {
                            writer.write(item + " ");
                        }
                    }
                } else if (data instanceof String) {
                    writer.write((String) data);
                } else {
                    throw new IllegalArgumentException("Unsupported data type for writing to file.");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

    }
