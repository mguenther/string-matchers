package net.mguenther.matchers.cli;

import net.mguenther.matchers.Matcher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
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

        final Map<String, Matcher> matchers = new HashMap<>();
        Iterable<Matcher> availableMatchers = ServiceLoader.load(Matcher.class);
        for (Matcher matcher : availableMatchers) {
            System.out.println("Found matcher '" + matcher.getName() + "' provided by '" + matcher.getClass().getName() + "'.");
            matchers.put(matcher.getName(), matcher);
        }

        final Matcher matcher = matchers.getOrDefault(algorithm, matchers.get("naive"));
        System.out.println("Using '" + matcher.getName() + "'.");
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
