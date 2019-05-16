package Common;

import Interfaces.ISimilarityFactor;
import org.junit.Test;

/**
 * Native recursive implementation of Levenshtein Distance metric
 * @warning This implementation is too slow (used only for fun)
 */
public class LevenshteinDistanceNative implements ISimilarityFactor {

    @Override
    public int similarity(String source, String target) {
        return evalInternal(source, source.length(), target, target.length());
    }

    private int evalInternal(String source, int srcLen, String target, int trgLen) {
        if (srcLen == 0) {
            return trgLen;
        }
        if (trgLen == 0) {
            return srcLen;
        }

        final int res1 = evalInternal(source, srcLen - 1, target, trgLen) + 1;
        final int res2 = evalInternal(source, srcLen, target, trgLen - 1) + 1;
        final int res3 = evalInternal(source, srcLen - 1, target, trgLen - 1);

        if (source.charAt(srcLen - 1) == target.charAt(trgLen - 1)) {
            return Math.min(res1, Math.min(res2, res3));
        }
        else {
            return Math.min(res1, Math.min(res2, res3 + 1));
        }
    }

}
