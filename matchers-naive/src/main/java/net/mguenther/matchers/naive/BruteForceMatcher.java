package net.mguenther.matchers.naive;

import net.mguenther.matchers.Fast;
import net.mguenther.matchers.Matcher;
import net.mguenther.matchers.Stable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
@Stable
@Fast(value = false)
public class BruteForceMatcher implements Matcher {

    @Override
    public List<Integer> match(String haystack, String needle) {
        return match(haystack.toCharArray(), needle.toCharArray());
    }

    private List<Integer> match(final char[] haystack, final char[] needle) {

        final List<Integer> matchingPositions = new ArrayList<>();
        final int runUntil = haystack.length - needle.length;

        int i = 0;
        int j = 0;

        while (i <= runUntil) {

            while (haystack[i+j] == needle[j]) {

                if (j == needle.length - 1) {
                    matchingPositions.add(i);
                    break;
                }

                j++;
            }

            i++;
            j = 0;
        }

        return Collections.unmodifiableList(matchingPositions);
    }

    @Override
    public String getName() {
        return "Naive Brute-Force Matcher";
    }
}
