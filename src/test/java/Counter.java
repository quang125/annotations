import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author QuangNN
 */
public class Counter {
    public static void main(String[] args) {
        String filePath = "41588106-1c06-4523-9c7c-1c1f6ffac4c7.txt";

        try {
            int count = countStringOccurrences(filePath, "data");
            System.out.println("The string 'data' appears " + count + " times in the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int countStringOccurrences(String filePath, String targetString) throws IOException {
        int count = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                count += countSubstringOccurrences(line, targetString);
            }
        }

        return count;
    }

    public static int countSubstringOccurrences(String source, String target) {
        int count = 0;
        int index = source.indexOf(target);

        while (index != -1) {
            count++;
            index = source.indexOf(target, index + 1);
        }

        return count;
    }
}
