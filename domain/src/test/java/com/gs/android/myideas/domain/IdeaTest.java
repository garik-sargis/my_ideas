package com.gs.android.myideas.domain;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class IdeaTest {

    @Test
    public void correctInit() {
        final String text = "Some text";
        final Idea idea = new Idea(text);
        assertThat(idea.text(), sameInstance(text));
    }
}
