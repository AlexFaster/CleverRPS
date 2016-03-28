
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ComputerPlayer {
    
    private Map<Pattern, Integer> map;  //stores patterns
    
    public ComputerPlayer(Map<Pattern, Integer> map) {
        this.map = map;
    }

    //get patterns
    public Map<Pattern, Integer> getMap() {
        return map;
    }
    
    
    //computer make a choice
    public String makeChoice(List<String> listChoices, int tokens) {
        if (listChoices.size() >= tokens) {
            Pattern pattern = Util.listToPattern(listChoices.subList(1, listChoices.size()));   //create sublist of pattern without last symbol
            boolean isPatternFound = false;
            String theMostCoolestPattern = "";
            int theMostCoolestOccurences = 0;
            for (Map.Entry<Pattern, Integer> entry : map.entrySet()) {                      //find pattern in the conp map
                if (entry.getKey().getPattern().startsWith(pattern.getPattern())) {
                    isPatternFound = true;
                    if (entry.getValue() > theMostCoolestOccurences) {                      //compute the best next item to defeat the player
                        theMostCoolestPattern = entry.getKey().getPattern();
                        theMostCoolestOccurences = entry.getValue();
                    }
                }
            }
            if (isPatternFound) {                                                           //find counter item
                String[] patternTokens = theMostCoolestPattern.split(",");
                return getConter(patternTokens[patternTokens.length - 1]);
            }
        }
        
        return madeRandomChoice();                                                         //if map doesn't have pattern then random choice
        
    }
    
    //store new pattern in computer map
    public void storeNewPattern(Pattern pattern) {
        if (map.containsKey(pattern)) {
            map.put(pattern, map.get(pattern) + 1);
        } else {
            map.put(pattern, 1);
        }
    }
    
    //helper method for random choice
    private String madeRandomChoice() {
        Random r = new Random();
        return RPSArray.RPS_SYMBOLS.get(r.nextInt(3));
    }
    
    //compute counter item for each item
    private String getConter(String token) {
        if (token.equals("R")) {
            return "P";
        }
        if (token.equals("S")) {
            return "R";
        }
        return "S";
    }
}
