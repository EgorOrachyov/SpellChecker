package Utils;

import java.util.Comparator;

public class WordData {

    private final String word;
    private final int editsCount;
    private final long usageCount;

    public WordData(String word, int editsCount, long usageCount) {
        this.word = word;
        this.editsCount = editsCount;
        this.usageCount = usageCount;
    }

    public String getWord() {
        return word;
    }

    public int getEditsCount() {
        return editsCount;
    }

    public long getUsageCount() {
        return usageCount;
    }



}
