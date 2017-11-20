package net.mguenther.matchers.cli;

import net.mguenther.matchers.Matcher;
import net.mguenther.matchers.impl.BruteForceMatcher;
import net.mguenther.matchers.impl.KnuthMorrisPrattMatcher;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
public class MatchersCli {

    public static void main(String[] args) {

        if (args.length != 3) {
            System.err.println("Wrong number of arguments. Expected: <algorithm> <haystack> <needle>");
            System.exit(1);
        }

        final String algorithm = args[0];
        final String haystack = args[1];
        final String needle = args[2];

        Matcher matcher = null;

        switch (algorithm) {
            case "kmp":
                System.out.println("Using Knuth-Morris-Pratt matching algorithm");
                matcher = new KnuthMorrisPrattMatcher();
                break;
            case "brute":
            default:
                System.out.println("Using Brute-Force matching algorithm");
                matcher = new BruteForceMatcher();
        }

        final List<Integer> matchingPositions = matcher.match(haystack, needle);

        if (matchingPositions.isEmpty()) {
            System.out.println("Found no match.");
        } else if (matchingPositions.size() == 1) {
            System.out.println("Found matching position at index: " + matchingPositions.get(0));
        } else {

            final String result = String.join(", ", matchingPositions.stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList()));
            System.out.println("Found matching positions at indices: " + result);
        }
        System.exit(0);
    }
}
