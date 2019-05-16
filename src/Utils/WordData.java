package Utils;

/**
 * Info about one suggested word. Supposed to
 * be used to sort all the suggestions it the order of
 * the similarity and then of the usage count
 */
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
