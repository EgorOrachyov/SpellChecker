package Common;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Set;

public interface IDictionary {

    void loadDefaultDict(String path) throws FileNotFoundException;

    void loadCustomDict(String path) throws FileNotFoundException;

    boolean containsWord(String word);

    boolean isUserWord(String word);

    Set<Map.Entry<String, Long>> getRawData();

}
