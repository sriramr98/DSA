package me.the10xdev.dsa.types;

import java.util.Arrays;

public enum Language {

    PYTHON3("python"),
    PYTHON2("python2");

    private final String language;

    Language(String language) {
       this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public static Language parse(String language) {
        return Arrays.stream(Language.values()).filter(lang -> lang.getLanguage().equals(language)).findFirst().orElseThrow();
    }
}
