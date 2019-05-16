import Common.IDictionary;
import Common.ISearchTree;
import Utils.CharacterArray;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * Prefix search tree implementation (or Trie) based on hash map, used
 * for storing children (or sub-nodes) in the tree nodes
 * @param <K> Type of the symbols, which links the edges of the tree
 * @param <V> Type of the value, stored with each key
 */
public class PrefixTree<K,V> implements ISearchTree<K,V> {

    private final PrefixTreeNode root = new PrefixTreeNode(null);

    @Override
    public void put(K[] keys, V value) {
        PrefixTreeNode current = root;

        for (K key : keys) {
            PrefixTreeNode next = current.childNodes.get(key);
            if (next != null) {
                current = next;
            } else {
                next = new PrefixTreeNode(null);
                current.childNodes.put(key, next);
                current = next;
            }
        }

        current.value = value;
    }

    @Override
    public boolean contains(K[] keys) {
        PrefixTreeNode current = root;

        for (K key : keys) {
            PrefixTreeNode next = current.childNodes.get(key);
            if (next == null) {
                return false;
            } else {
                current = next;
            }
        }

        return current.leaf();
    }

    @Override
    public V find(K[] keys) {
        PrefixTreeNode current = root;

        for (K key : keys) {
            PrefixTreeNode next = current.childNodes.get(key);
            if (next == null) {
                return null;
            } else {
                current = next;
            }
        }

        return current.getValue();
    }

    @Override
    public void traverse() {

    }

    public class PrefixTreeNode implements ISearchTree.ITreeNode<V> {

        V value;
        HashMap<K, PrefixTreeNode> childNodes = new HashMap<>();

        PrefixTreeNode(V value) {
            this.value = value;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public boolean leaf() {
            return (value != null);
        }

    }

    @Test
    public void test() {

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

        for (String word : words) {
            Long stat = tree.find(CharacterArray.convert(word.toLowerCase()));
            if (stat != null) {
                System.out.println(word + " " + stat);
            }
        }

    }

}
