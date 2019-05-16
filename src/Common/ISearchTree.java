package Common;

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
     * Node interface for the tree
     * @param <V> Type of the value, stored with each key
     *            Could be null, if this one node is an intermediate node
     */
    interface ITreeNode<V> {

        /**
         * @return Stored value of null if it is not presented
         */
        V getValue();

        /**
         * @return True if this node stores value, otherwise
         *         False (if is is intermediate node)
         */
        boolean leaf();

    }

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

    void traverse();

}
