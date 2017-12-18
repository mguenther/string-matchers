package net.mguenther.matchers;

import java.util.function.Predicate;

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
public class MatcherCharacteristics {

    public static Predicate<Class<? extends Matcher>> isFast() {

        return clazz -> clazz.isAnnotationPresent(Fast.class) &&
                        clazz.getAnnotation(Fast.class).value();
    }

    public static Predicate<Class<? extends Matcher>> isStable() {

        return clazz -> clazz.isAnnotationPresent(Stable.class);
    }

    public static Predicate<Class<? extends Matcher>> isExperimental() {

        return clazz -> clazz.isAnnotationPresent(Experimental.class);
    }
}
