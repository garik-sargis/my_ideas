package com.gs.android.mythoughts.domain;

public class WithId<T> {

    private final long mId;

    private final T mData;

    public WithId(final long id, final T data) {
        mId = id;
        mData = data;
    }

    public long id() {
        return mId;
    }

    public T data() {
        return mData;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        final WithId<?> withId = (WithId<?>) obj;

        return mId == withId.mId && mData.equals(withId.mData);

    }

    @Override
    public int hashCode() {
        int result = (int) (mId ^ (mId >>> 32));
        result = 31 * result + mData.hashCode();
        return result;
    }
}
