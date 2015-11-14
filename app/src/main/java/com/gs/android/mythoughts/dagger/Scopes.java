package com.gs.android.mythoughts.dagger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * A collection of scope-annotations.
 */
public final class Scopes {
    /**
     * Do not instantiate!
     */
    private Scopes() {
        // Empty
    }

    // TODO: Document
    @Scope
    @Retention(RetentionPolicy.RUNTIME)
    public @interface PerActivity {
    }
}
