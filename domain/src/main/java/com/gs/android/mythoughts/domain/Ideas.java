package com.gs.android.mythoughts.domain;

public class Ideas {
    public static WithId<Idea> withId(final long id, final String text) {
        return new WithId<>(id, new Idea(text));
    }
}
