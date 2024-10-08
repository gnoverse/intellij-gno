package com.github.intellij.gno.services;

import com.intellij.lang.Language;

public class GnoLanguage extends Language {

    public static final GnoLanguage INSTANCE = new GnoLanguage();

    GnoLanguage() {
        super("Gno");
    }
}