package Interfaces;

import java.util.Map;

/**
 * Search tree based on sequential key structures (or K[] key)
 * Allows to store pairs of (keys[], value)
 * Each leaf of this tree must store at least ONE NOT NULL value
 *
 * @param <K> Type of the symbols, which links the edges of the tree
 * @param <V> Type of the value, stored with each key
 */
public interface ISearchTree<K,V> {

    /**
     * Adds new pair (key[], value) to the tree.
     * @param keys Array of key sequence to check
     * @param value To store in the tree
     */
    void put(K[] keys, V value);

    /**
     * Finds value associated with the key sequence
     * @param keys Array of key sequence to check
     * @return Null if (key[], value) is not stored or
     *         value if it is presented
     */
    V find(K[] keys);

    /**
     * Shows whether this key are presented in the tree
     * @param keys Array of key sequence to check
     * @return True if with this key is associated with value
     */
    boolean contains(K[] keys);

    /**
     * @return Current nodes count in the tree
     */
    int getNodesCount();

    /**
     * A branch height is a nodes count in the way from the root to the leaf.
     * Tree height is the length of the longest branch in th tree
     * @return Max branch length
     */
    int getHeight();

    /**
     * @return Get the root of the tree
     */
    ITreeNode<K,V> getRoot();

    /**
     * Node interface for the tree
     * @param <V> Type of the value, stored with each key
     *            Could be null, if this one node is an intermediate node
     */
    interface ITreeNode<K,V> {

        /**
         * @return Stored value of null if it is not presented
         */
        V getValue();

        /**
         * @return True if this node stores value, otherwise
         *         False (if is is intermediate node)
         */
        boolean leaf();

        /**
         * @return All the sub-nodes of this in the map
         */
        Map<K, ? extends ITreeNode<K, V>> getChildNodes();

    }

}
