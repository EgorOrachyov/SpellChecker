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

    public static void main(String[] args) {
        ISimilarityFactor factor = new LevenshteinDistanceNative();

        String[] sources = { "egor", "the", "A", "blaBlaBla", "terraform", "kitten", "undertanging" };
        String[] targets = { "egor", "the", "A", "blaBlaBla", "terraform", "sitten", "understanding" };

        for (int i = 0; i < sources.length; i++) {
            System.out.println(sources[i] + " -> " + targets[i] + " " + factor.similarity(sources[i], targets[i]));
        }
    }

}
