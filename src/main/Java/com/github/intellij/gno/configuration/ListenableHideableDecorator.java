package com.github.intellij.gno.configuration;

import com.intellij.ui.HideableDecorator;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.Collection;

class ListenableHideableDecorator extends HideableDecorator {
    private final Collection<MyListener> myListeners = ContainerUtil.newSmartList();

    public ListenableHideableDecorator(@NotNull JPanel panel, @NotNull String displayName, @NotNull JComponent content) {
        super(panel, displayName, false);
        setContentComponent(content);
    }

    public void addListener(@NotNull MyListener listener) {
        myListeners.add(listener);
    }

    @Override
    protected void on() {
        for (MyListener listener : myListeners) {
            listener.on();
        }
        super.on();
    }

    @Override
    protected void off() {
        for (MyListener listener : myListeners) {
            listener.beforeOff();
        }
        super.off();
        for (MyListener listener : myListeners) {
            listener.afterOff();
        }
    }

    public static class MyListener {
        public void on() {

        }

        public void beforeOff() {

        }

        public void afterOff() {

        }
    }
}
