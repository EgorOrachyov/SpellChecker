package Test;

import Common.LevenshteinDistanceMatrix;
import Common.LevenshteinDistanceNative;
import Common.PrefixTree;

import Interfaces.ISimilarityFactor;
import Interfaces.ISearchTree;

import Utils.DictionaryLoader;
import Utils.Range;
import Utils.CharacterArray;

import org.junit.Test;
import org.junit.Assert;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.io.FileNotFoundException;

public class TestCommon extends Assert {

    @Test
    public void dictionaryBuildTest() {
        HashMap<String, Long> dictionary = new HashMap<>();
        HashSet<String> userDefined = new HashSet<>();

        try {
            DictionaryLoader.loadDefaultDict("resource/dict-english-default.txt", dictionary);
            DictionaryLoader.loadCustomDict("resource/dict-english-custom.txt", dictionary, userDefined);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void dictionaryTest() {
        HashMap<String, Long> dictionary = new HashMap<>();
        HashSet<String> userDefined = new HashSet<>();

        try {
            DictionaryLoader.loadDefaultDict("resource/dict-english-default.txt", dictionary);
            DictionaryLoader.loadCustomDict("resource/dict-english-custom.txt", dictionary, userDefined);
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
            Long value = dictionary.get(word);
            if (value != null) {
                System.out.println("No: " + word);
            } else {
                System.out.println("Yes: " + word + " " + value);
            }
        }
    }

    @Test
    public void prefixTreeBuild() {
        HashMap<String, Long> dictionary = new HashMap<>();
        HashSet<String> userDefined = new HashSet<>();

        try {
            DictionaryLoader.loadDefaultDict("resource/dict-english-default.txt", dictionary);
            DictionaryLoader.loadCustomDict("resource/dict-english-custom.txt", dictionary, userDefined);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ISearchTree<Character, Long> tree = new PrefixTree<>();
        for (Map.Entry<String, Long> word : dictionary.entrySet()) {
            tree.put(CharacterArray.convert(word.getKey()), word.getValue());
        }
    }

    @Test
    public void prefixTreeTest() {
        HashMap<String, Long> dictionary = new HashMap<>();
        HashSet<String> userDefined = new HashSet<>();

        try {
            DictionaryLoader.loadDefaultDict("resource/dict-english-default.txt", dictionary);
            DictionaryLoader.loadCustomDict("resource/dict-english-custom.txt", dictionary, userDefined);
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
        for (Map.Entry<String, Long> word : dictionary.entrySet()) {
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

    @Test
    public void rangeTest() {
        Range<Integer> range = new Range<>(-10, 10);

        int[] data = new int[]{ -10, 4, 111, -190, 0, 10, 22, 1111, 5, -9 };
        int[] expected = new int[data.length];
        int[] actual = new int[data.length];

        for (int i = 0; i < data.length; i++) {
            expected[i] = (data[i] >= range.getLowerBorder() && data[i] <= range.getUpperBorder() ? 1 : 0);
            actual[i] = (range.inside(data[i]) ? 1 : 0);
        }

        assertArrayEquals(expected, actual);
    }

    @Test
    public void LevenshteinNativeTest() {
        ISimilarityFactor factor = new LevenshteinDistanceNative();

        String[] sources = { "egor", "the", "A", "blaBlaBla", "terraform", "kitten", "undertanging" };
        String[] targets = { "egor", "the", "A", "blaBlaBla", "terraform", "sitten", "understanding" };

        for (int i = 0; i < sources.length; i++) {
            System.out.println(sources[i] + " -> " + targets[i] + " " + factor.similarity(sources[i], targets[i]));
        }
    }

    @Test
    public void LevenshteinMatrixTest() {
        ISimilarityFactor factor = new LevenshteinDistanceMatrix();

        String[] sources = { "egor", "the", "A", "blaBlaBla", "terraform", "kitten", "undertanging" };
        String[] targets = { "egor", "the", "A", "blaBlaBla", "terraform", "sitten", "understanding"  };

        for (int i = 0; i < sources.length; i++) {
            System.out.println(sources[i] + " -> " + targets[i] + " " + factor.similarity(sources[i], targets[i]));
        }
    }

}
