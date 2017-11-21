module matchers.naive {

    requires matchers.api;

    provides net.mguenther.matchers.Matcher
        with net.mguenther.matchers.naive.BruteForceMatcher;
}