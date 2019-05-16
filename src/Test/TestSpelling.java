package Test;

import Common.SpellChecker;
import Interfaces.ISpellChecker;
import org.junit.Assert;

public class TestSpelling extends Assert {

    public static final ISpellChecker spellchecker;

    static {
        spellchecker = new SpellChecker();
    }

}
