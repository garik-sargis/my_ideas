package com.gs.android.mythoughts.log;

import timber.log.Timber;

// TODO: Don't subclass
public class ThreadDebugTree extends Timber.DebugTree {

    private static String currentThreadName() {
        return Thread.currentThread().getName();
    }

    @Override
    protected void log(final int priority, final String tag, final String message, final Throwable
            throwable) {
        final String transformedMessage = String.format("[%s] %s", currentThreadName(), message);

        super.log(priority, tag, transformedMessage, throwable);
    }
}
