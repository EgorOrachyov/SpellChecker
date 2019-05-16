package Test;

import org.junit.Test;

import Common.Dictionary;
import Common.PrefixTree;
import Interfaces.IDictionary;
import Interfaces.ISearchTree;
import Utils.CharacterArray;

import java.io.FileNotFoundException;
import java.util.Map;

public class TestCommon {

    @Test
    public void dictionaryBuildTest() {

        IDictionary dictionary = new Dictionary();
        try {
            dictionary.loadDefaultDict("resource/dict-english-default.txt");
            dictionary.loadCustomDict("resource/dict-english-custom.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String[] words = new String[]{ "egor", "the", "A", "blaBlaBla", "terraform" };

        for (String word : words) {
            System.out.println(dictionary.containsWord(word) + " " + word);
            System.out.println(dictionary.isUserWord(word) + " " + word);
        }

    }

    @Test
    public void dictionaryTest() {

        IDictionary dictionary = new Dictionary();
        try {
            dictionary.loadDefaultDict("resource/dict-english-default.txt");
            dictionary.loadCustomDict("resource/dict-english-custom.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String[] words = new String[]{
                "egor", "the", "A", "blaBlaBla",
                "terraform", "soil", "house", "horse",
                "label", "list", "map", "array", "shader",
                "cpu", "graphics", "engine", "gog", "lol",
                "heee", "ddddd", ""
        };

        for (String word : words) {
            System.out.println(dictionary.containsWord(word) + " " + word);
            System.out.println(dictionary.isUserWord(word) + " " + word);
        }

    }

    @Test
    public void prefixTreeBuild() {

        IDictionary dictionary = new Dictionary();
        try {
            dictionary.loadDefaultDict("resource/dict-english-default.txt");
            dictionary.loadCustomDict("resource/dict-english-custom.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String[] words = new String[]{ "egor", "the", "A", "blaBlaBla", "terraform" };

        ISearchTree<Character, Long> tree = new PrefixTree<>();
        for (Map.Entry<String, Long> word : dictionary.getRawData()) {
            tree.put(CharacterArray.convert(word.getKey()), word.getValue());
        }

    }

    @Test
    public void prefixTreeTest() {

        IDictionary dictionary = new Dictionary();
        try {
            dictionary.loadDefaultDict("resource/dict-english-default.txt");
            dictionary.loadCustomDict("resource/dict-english-custom.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String[] words = new String[]{
                "egor", "the", "A", "blaBlaBla",
                "terraform", "soil", "house", "horse",
                "label", "list", "map", "array", "shader",
                "cpu", "graphics", "engine", "gog", "lol",
                "heee", "ddddd", ""
        };

        ISearchTree<Character, Long> tree = new PrefixTree<>();
        for (Map.Entry<String, Long> word : dictionary.getRawData()) {
            tree.put(CharacterArray.convert(word.getKey()), word.getValue());
        }

        for (String word : words) {
            Long stat = tree.find(CharacterArray.convert(word.toLowerCase()));
            if (stat != null) {
                System.out.println(word + " " + stat);
            }
        }

        System.out.println("Height: " + tree.getHeight());
        System.out.println("Nodes count: " + tree.getNodesCount());

    }

}
