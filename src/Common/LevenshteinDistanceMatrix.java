package Common;

import Interfaces.ISimilarityFactor;
import org.junit.Test;

import java.util.Arrays;

/**
 * Matrix based implementation of Levenshtein Distance metric
 * (has more acceptable performance then recursive implementation)
 */
public class LevenshteinDistanceMatrix implements ISimilarityFactor {

    @Override
    public int similarity(String source, String target) {
        final int srcLen = source.length();
        final int trgLen = target.length();
        final int[][] mat = new int[srcLen + 1][trgLen + 1];

        for (int[] ar : mat) {
            Arrays.fill(ar, 0);
        }

        for (int i = 1; i <= srcLen; i++) {
            mat[i][0] = i;
        }

        for (int j = 1; j <= trgLen; j++) {
            mat[0][j] = j;
        }

        for (int j = 1; j <= trgLen; j++) {
            for (int i = 1; i <= srcLen; i++) {
                final int cost = (source.charAt(i - 1) == target.charAt(j - 1) ? 0 : 1);
                mat[i][j] = Math.min(mat[i - 1][j] + 1,
                            Math.min(mat[i][j - 1] + 1,
                                     mat[i - 1][j - 1] + cost
                            )
                );
            }
        }

        return mat[srcLen][trgLen];
    }

    @Test
    public void test() {
        ISimilarityFactor factor = new LevenshteinDistanceMatrix();

        String[] sources = { "egor", "the", "A", "blaBlaBla", "terraform", "kitten", "undertanging" };
        String[] targets = { "egor", "the", "A", "blaBlaBla", "terraform", "sitten", "understanding"  };

        for (int i = 0; i < sources.length; i++) {
            System.out.println(sources[i] + " -> " + targets[i] + " " + factor.similarity(sources[i], targets[i]));
        }
    }

}
