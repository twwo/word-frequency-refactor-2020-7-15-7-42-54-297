import java.util.*;

public class WordFrequencyGame {

    public static final String SPACE_PATTERN = "\\s+";
    public static final String SPACE_STRING = " ";
    public static final String WRAP_STRING = "\n";
    public static final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String sentence) {
        if (sentence.split(SPACE_PATTERN).length == 1) {
            return sentence + " 1";
        } else {
            try {
                String[] words = sentence.split(SPACE_PATTERN);
                List<WordInfo> wordInfos = new ArrayList<>();
                for (String word : words) {
                    WordInfo wordInfo = new WordInfo(word, 1);
                    wordInfos.add(wordInfo);
                }
                Map<String, List<WordInfo>> map = getWordInfoMap(wordInfos);
                List<WordInfo> list = new ArrayList<>();
                for (Map.Entry<String, List<WordInfo>> entry : map.entrySet()) {
                    WordInfo input = new WordInfo(entry.getKey(), entry.getValue().size());
                    list.add(input);
                }
                wordInfos = list;
                wordInfos.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());
                StringJoiner joiner = new StringJoiner(WRAP_STRING);
                for (WordInfo w : wordInfos) {
                    String s = w.getValue() + SPACE_STRING + w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    private Map<String, List<WordInfo>> getWordInfoMap(List<WordInfo> wordInfos) {
        Map<String, List<WordInfo>> wordInfoMap = new HashMap<>();
        for (WordInfo wordInfo : wordInfos) {
            if (!wordInfoMap.containsKey(wordInfo.getValue())) {
                List<WordInfo> newWordInfo = new ArrayList<>();
                newWordInfo.add(wordInfo);
                wordInfoMap.put(wordInfo.getValue(), newWordInfo);
            } else {
                wordInfoMap.get(wordInfo.getValue()).add(wordInfo);
            }
        }
        return wordInfoMap;
    }
}
