package com.gs.android.myideas.domain;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class WithIdTest {

    // TODO: Break into 2 tests?
    @Test
    public void retains_construction_arguments() {
        final long id = 8;
        Object mockObj = mock(Object.class);
        WithId<Object> withId = new WithId<>(id, mockObj);
        assertThat(withId.id(), equalTo(id));
        assertThat(withId.content(), sameInstance(mockObj));
    }

    @Ignore
    @Test
    public void implements_hash_code() {

    }
}
