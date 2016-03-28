
import java.util.List;

public class Util {

    public static Pattern listToPattern(List<String> list) {
        String listAsString = list.toString();
        return new Pattern(listAsString.substring(1, listAsString.length() - 1).replaceAll(" ", ""));
    }

}
