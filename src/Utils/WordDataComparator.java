package Utils;

import java.util.Comparator;

/**
 * Used to sort suggested words in the specified order:
 * First order param: the edits operations count
 * Second order param: the number of usages
 */
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
