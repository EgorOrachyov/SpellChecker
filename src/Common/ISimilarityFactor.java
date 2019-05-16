package Common;

/**
 * Factor used in spell-checker
 */
public interface ISimilarityFactor {

    /**
     * String metric which is supposed to be used to get a non-negative
     * value, which will show how much to strings are similar to each other
     *
     * @param source Source string which is supposed to be check
     * @param target Another string which is available for us
     * @return Non-negative factor value
     */
    int similarity(String source, String target);

}
