package net.mguenther.matchers.cli;

import net.mguenther.matchers.Matcher;
import net.mguenther.matchers.MatcherCharacteristics;

import java.util.List;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
public class MatchersCli {

    public static void main(String[] args) {

        if (args.length != 2) {
            System.err.println("Wrong number of arguments. Expected: <algorithm> <haystack> <needle>");
            System.exit(1);
        }

        final String haystack = args[0];
        final String needle = args[1];

        final Optional<Matcher> optionalMatcher = ServiceLoader
                .load(Matcher.class)
                .stream()
                .filter(provider -> MatcherCharacteristics.isStable().test(provider.type()))
                .findFirst()
                .map(ServiceLoader.Provider::get);

        if (!optionalMatcher.isPresent()) {
            System.out.println("Unable to find a suitable matcher with the given quality characteristics.");
            System.exit(1);
        }

        final Matcher matcher = optionalMatcher.get();

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
