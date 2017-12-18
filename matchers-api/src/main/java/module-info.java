import net.mguenther.matchers.naive.BruteForceMatcher;

module matchers.api {

    exports net.mguenther.matchers;

    provides net.mguenther.matchers.Matcher
            with BruteForceMatcher;
}