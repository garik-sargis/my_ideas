package com.gs.android.mythoughts.ui.util;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;
import rx.functions.Action1;
import rx.observers.Subscribers;
import timber.log.Timber;

public class SubscriberPool<K, V> {

    public interface Listener<K, V> {
        void onDataReceived(K id, V data);
    }

    public static <K, V> SubscriberPool<K, V> create(final Listener<K, V> listener) {
        return new SubscriberPool<>(listener);
    }

    private final Map<K, Subscriber<V>> mActiveSubscribers;
    private final Listener<K, V> mListener;

    private SubscriberPool(final Listener<K, V> listener) {
        mActiveSubscribers = new HashMap<>();
        mListener = listener;
    }

    // TODO: Inline?
    private void notifyListener(final K id, final V data) {
        mListener.onDataReceived(id, data);
    }

    // TODO: Violates encapsulation?
    // TODO: Two calls with the same id?
    public Subscriber<V> get(final K id) {
        // Create a subscriber
        Subscriber<V> subscriber = Subscribers.create(new Action1<V>() {
            @Override public void call(final V v) {
                SubscriberPool.this.notifyListener(id, v);
            }
        });
        // Register as active
        mActiveSubscribers.put(id, subscriber);

        return subscriber;
    }

    /**
     * Unsubscribe subscriber with the supplied ID.
     */
    public void unsubscribe(final K id) {
        if (mActiveSubscribers.containsKey(id)) {
            // Unregister as active and unsubscribe
            Subscriber<V> subscriber = mActiveSubscribers.remove(id);
            subscriber.unsubscribe();
        } else {
            Timber.w("Already unsubscriber");
        }
    }

    /**
     * Unsubscribe all subscribers.
     */
    public void unsubscribeAll() {
        for (final K id : mActiveSubscribers.keySet()) {
            unsubscribe(id);
        }
    }
}
