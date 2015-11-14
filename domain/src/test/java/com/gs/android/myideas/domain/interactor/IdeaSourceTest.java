package com.gs.android.myideas.domain.interactor;

import org.junit.Ignore;
import org.junit.Test;

import rx.Subscriber;

import static org.mockito.Mockito.*;

public class IdeaSourceTest {
    @Test
    public void no_interactions_with_constructor_args() {
        com.gs.android.myideas.domain.repo.IdeaRepo mockIdeaRepo = mock(com.gs.android.myideas.domain.repo.IdeaRepo.class);
        Connector mockConnector = mock(Connector.class);
        new IdeaSource(mockIdeaRepo, mockConnector);
        verifyZeroInteractions(mockIdeaRepo, mockConnector);
    }

    @Ignore
    @Test
    public void fi() {
        final long id = 42L;
        com.gs.android.myideas.domain.repo.IdeaRepo mockIdeaRepo = mock(com.gs.android.myideas.domain.repo.IdeaRepo.class);
        when(mockIdeaRepo.query(id)).thenReturn(null);

        Connector mockConnector = mock(Connector.class);
        IdeaSource ideaSource = new IdeaSource(mockIdeaRepo, mockConnector);
        Subscriber mockSubscriber = mock(Subscriber.class);

        ideaSource.subscribe(mockSubscriber, id);
    }
}
