package com.he180030.agentmanagement.controller;

import java.util.prefs.Preferences;

public class SessionManager {
    private static final String EMAIL_KEY = "email";
    private static final String USERNAME_KEY = "username";
    private static final String IS_LOGGED_IN = "isLoggedIn";
    private Preferences prefs;

    public SessionManager() {
        this.prefs = Preferences.userRoot().node(this.getClass().getName());
    }

    public void login(String email, String username) {
        prefs.put(EMAIL_KEY, email);
        prefs.put(USERNAME_KEY, username);
        prefs.putBoolean(IS_LOGGED_IN, true);
    }

    public void logout() {
        prefs.remove(EMAIL_KEY);
        prefs.remove(IS_LOGGED_IN);
    }

    public boolean isLoggedIn() {
        return prefs.getBoolean(IS_LOGGED_IN, false);
    }

    public String getUsername() {
        return prefs.get(USERNAME_KEY, "");
    }
}
