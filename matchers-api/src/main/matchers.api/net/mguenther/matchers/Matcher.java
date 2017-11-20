package net.mguenther.matchers;

import java.util.List;

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
public interface Matcher {
    /**
     * Finds all the needles in the haystack and yields an unmodifiable
     * {@link java.util.List} which contains the starting indices of text
     * fragments (cf. {@code needle}) within the {@code haystack}.
     *
     * @param haystack
     *      The full text to search for {@code needle}s
     * @param needle
     *      The text fragment to search for inn {@code haystack}
     * @return
     *      unmodifiable {@link java.util.List} with starting indices
     *      of text fragments in the haystack, the empty list of
     *      no text fragments have been found
     */
    List<Integer> match(String haystack, String needle);
}
