package com.gs.android.mythoughts.domain.interactor;

import com.gs.android.mythoughts.domain.repo.IdeaRepo;

import org.junit.Ignore;
import org.junit.Test;

import rx.Subscriber;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class IdeaSourceTest {
    @Test
    public void no_interactions_with_constructor_args() {
        IdeaRepo mockIdeaRepo = mock(IdeaRepo.class);
        Connector mockConnector = mock(Connector.class);
        new IdeaSource(mockIdeaRepo, mockConnector);
        verifyZeroInteractions(mockIdeaRepo, mockConnector);
    }

    @Ignore
    @Test
    public void fi() {
        final long id = 42L;
        IdeaRepo mockIdeaRepo = mock(IdeaRepo.class);
        when(mockIdeaRepo.query(id)).thenReturn(null);

        Connector mockConnector = mock(Connector.class);
        IdeaSource ideaSource = new IdeaSource(mockIdeaRepo, mockConnector);
        Subscriber mockSubscriber = mock(Subscriber.class);

        ideaSource.getIdea(mockSubscriber, id);
    }
}
