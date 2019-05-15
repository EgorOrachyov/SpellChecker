import java.io.FileNotFoundException;
import java.util.List;

public interface IDictionary {

    void loadDefaultDict(String path) throws FileNotFoundException;

    void loadCustomDict(String path) throws FileNotFoundException;

    boolean containsWord(String word);

    boolean isUserWord(String word);

}
