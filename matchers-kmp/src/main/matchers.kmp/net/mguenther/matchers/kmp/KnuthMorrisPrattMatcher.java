package net.mguenther.matchers.kmp;

import net.mguenther.matchers.Matcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Integer.max;

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
public class KnuthMorrisPrattMatcher implements Matcher {

    @Override
    public List<Integer> match(final String haystack, final String needle) {
        return match(haystack.toCharArray(), needle.toCharArray());
    }

    private List<Integer> match(final char[] haystack, final char[] needle) {

        final List<Integer> matchingPositions = new ArrayList<>();
        final int n = haystack.length - 1;
        final int m = needle.length - 1;
        final int[] borders = computeBorders(needle);

        int j = 0;

        for (int i = 0; i <= n - m;) {

            while (haystack[i+j] == needle[j]) {
                if (j == m) {
                    matchingPositions.add(i);
                    break;
                }
                j++;
            }

            i = i + j - borders[j];
            j = max(0, borders[j]);
        }

        return Collections.unmodifiableList(matchingPositions);
    }

    private int[] computeBorders(final char[] needle) {

        final int[] borders = new int[needle.length];

        borders[0] = -1;
        borders[1] = 0;

        int i = 0;

        for (int j = 2; j < needle.length; j++) {
            while ((i >= 0) && (needle[i] != needle[j-1])) {
                i = borders[i];
            }
            i++;
            borders[j] = i;
        }

        return borders;
    }

    @Override
    public String getName() {
        return "kmp";
    }
}
