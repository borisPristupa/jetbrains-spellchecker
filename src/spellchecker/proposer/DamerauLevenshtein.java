package spellchecker.proposer;


public class DamerauLevenshtein {

    private static int neq(String s1, int pos1, String s2, int pos2) {
        return s1.charAt(pos1) == s2.charAt(pos2) ? 0 : 1;
    }

    private static int ntranspos(String s1, int pos1, String s2, int pos2) {
        if (s1.charAt(pos1) == s2.charAt(pos2 + 1))
            if (s1.charAt(pos1 + 1) == s2.charAt(pos2))
                return 1;
        return 2;
    }

    public static int distance(String s1, String s2) {
        if (s1.length() > s2.length()) {
            String tmp = s1;
            s1 = s2;
            s2 = tmp;
        }

        int[][] lastColumns = new int[3][s1.length() + 1];
        final int CURR = 0, PREV = 1, BEFORE_PREV = 2;

        for (int i = 0; i < s2.length() + 1; i++) {
            for (int j = 0; j < s1.length() + 1; j++) {
                if (0 == (i | j)) {
                    lastColumns[CURR][0] = 0;
                    continue;
                }

                if (0 == j) {
                    lastColumns[CURR][j] = lastColumns[PREV][j] + 1;
                    continue;
                }

                if (0 == i) {
                    lastColumns[CURR][j] = lastColumns[CURR][j-1] + 1;
                    continue;
                }

                lastColumns[CURR][j] = Math.min(lastColumns[CURR][j - 1] + 1, lastColumns[PREV][j] + 1);

                lastColumns[CURR][j] = Math.min(lastColumns[PREV][j - 1] + neq(s1, j - 1, s2, i - 1),
                                                lastColumns[CURR][j]);

                if (i > 1 && j > 1)
                    lastColumns[CURR][j] = Math.min(lastColumns[BEFORE_PREV][j - 2]
                                                            + ntranspos(s1, j - 2, s2, i - 2),
                                                    lastColumns[CURR][j]);
            }

            for (int j = 0; j < s1.length() + 1; j++) {
                lastColumns[BEFORE_PREV][j] = lastColumns[PREV][j];
                lastColumns[PREV][j] = lastColumns[CURR][j];
            }
        }

        return lastColumns[CURR][s1.length()];
    }
}
