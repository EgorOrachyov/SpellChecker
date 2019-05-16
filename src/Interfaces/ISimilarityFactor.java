package Interfaces;

/**
 * Factor used in spell-checker
 */
public interface ISimilarityFactor {

    /**
     * String metric which is supposed to be used to get a non-negative
     * value, which will show how much to strings are similar to each other
     * Than less value, then strings more similar to each other
     *
     * similarity(a,b) = N
     * similarity(a,c) = D
     *
     * if (D < N) then a and c are more similar than a and b
     *
     * @param source Source string which is supposed to be check
     * @param target Another string which is available for us
     * @return Non-negative factor value
     */
    int similarity(String source, String target);

}
