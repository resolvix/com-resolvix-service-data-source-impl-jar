package com.resolvix.lib.event.base;

import com.resolvix.lib.event.api.Listener;
import com.resolvix.lib.event.api.ListenerEnabled;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.resolvix.lib.reference.WeakReferenceUtils.compact;
import static com.resolvix.lib.reference.WeakReferenceUtils.find;

public class BaseListenerEnabledChangeHandlerImpl<P>
    extends BaseChangeHandlerImpl<P>
    implements ListenerEnabled<P>
{

    private List<WeakReference<Listener<P>>> listeners;

    public BaseListenerEnabledChangeHandlerImpl(P initialState) {
        super(initialState);
        this.listeners = new ArrayList<>();
    }

    @Override
    public synchronized void addListener(Listener<P> listener) {
        if (find(listeners, listener) == null) {
            listeners = compact(listeners);
            listeners.add(new WeakReference<>(listener));
        }
    }

    @Override
    public synchronized List<Listener<P>> getListeners() {
        return Collections.unmodifiableList(
            listeners.stream()
                .map(WeakReference::get)
                .collect(Collectors.toList()));
    }

    @Override
    public synchronized void removeListener(Listener<P> listener) {
        WeakReference<Listener<P>> weakReference
            = find(listeners, listener);
        if (weakReference != null)
            listeners.remove(weakReference);
        listeners = compact(listeners);
    }
}
