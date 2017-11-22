module matchers.kmp {

    requires matchers.api;

    provides net.mguenther.matchers.Matcher
        with net.mguenther.matchers.kmp.KnuthMorrisPrattMatcher;
}