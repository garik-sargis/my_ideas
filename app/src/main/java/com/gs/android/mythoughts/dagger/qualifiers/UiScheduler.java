package com.gs.android.mythoughts.dagger.qualifiers;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

// TODO: Document
@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface UiScheduler {
}
