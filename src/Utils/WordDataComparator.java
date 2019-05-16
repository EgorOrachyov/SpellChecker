package Utils;

import java.util.Comparator;

public class WordDataComparator implements Comparator<WordData> {
    @Override
    public int compare(WordData o1, WordData o2) {
        if (o1.getEditsCount() != o2.getEditsCount()) {
            return o1.getEditsCount() - o2.getEditsCount();
        } else {
            return (int)(o2.getUsageCount() - o1.getUsageCount());
        }
    }
}
