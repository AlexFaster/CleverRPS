
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileUtil {
    
    //read comp file
    public static Map<Pattern, Integer> readCompFile(File file) {
        Map<Pattern, Integer> map = new HashMap();
        try {
            Scanner input = new Scanner(file);
            while (input.hasNextLine()) {
                String[] currentLineSplitted = input.nextLine().split(" ");
                map.put(new Pattern(currentLineSplitted[0]), Integer.parseInt(currentLineSplitted[1]));
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Input file " + file.getName() + " not found");
            System.out.println("Start from scratch");
        }
        return map;
    }
    
    //write comp file
    public static void writeFile(Map<Pattern, Integer> map, File file) {
        try {
            FileWriter fw = new FileWriter(file);
            for (Map.Entry<Pattern, Integer> entry : map.entrySet()) {
                fw.write(entry.getKey().getPattern() + " " + entry.getValue() + System.getProperty("line.separator"));
            }
            fw.close();
        } catch (IOException ex) {
            System.out.println("Error while writing");
        }
    }
}
