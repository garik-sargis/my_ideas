package com.gs.android.mythoughts.dagger;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * A collection of qualifier-annotations.
 */
public final class Qualifiers {
    /**
     * Do not instantiate!
     */
    private Qualifiers() {
        // Empty
    }

    // TODO: Document

    /**
     * Intended for annotating  {@link rx.Scheduler} dependencies.
     */
    @Qualifier
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    public @interface IoScheduler {
    }

    // TODO: Document

    /**
     * Intended for annotating  {@link rx.Scheduler} dependencies.
     */
    @Qualifier
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    public @interface UiScheduler {
    }
}
