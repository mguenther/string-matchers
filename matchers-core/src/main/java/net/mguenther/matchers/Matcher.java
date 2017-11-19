package net.mguenther.matchers;

import java.util.List;

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
public interface Matcher {

    List<Integer> match(String haystack, String needle);
}
