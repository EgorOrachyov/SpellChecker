package Common;

import Interfaces.ISearchTree;

import java.util.HashMap;

/**
 * Prefix search tree implementation (or Trie) based on hash map, used
 * for storing children (or sub-nodes) in the tree nodes
 * @param <K> Type of the symbols, which links the edges of the tree
 * @param <V> Type of the value, stored with each key
 */
public class PrefixTree<K,V> implements ISearchTree<K,V> {

    /** Suppose, that root could not be a leaf node
     * (however, if empty key[] arrays ar not prohibited, there can be stored value) */
    private final PrefixTreeNode root = new PrefixTreeNode(null);
    private int height = 1;
    private int nodesCount = 1;

    @Override
    public void put(K[] keys, V value) {
        PrefixTreeNode current = root;
        int currentHeight = 1;

        for (K key : keys) {
            PrefixTreeNode next = current.childNodes.get(key);
            if (next != null) {
                current = next;
            } else {
                next = new PrefixTreeNode(null);
                current.childNodes.put(key, next);
                current = next;
                nodesCount += 1;
            }
            currentHeight += 1;
        }

        current.value = value;
        height = Math.max(height, currentHeight);
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

    @Override
    public int getNodesCount() {
        return nodesCount;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
