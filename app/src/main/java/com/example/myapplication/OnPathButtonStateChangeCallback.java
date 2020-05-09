package com.example.myapplication;

public interface OnPathButtonStateChangeCallback {
    void onButtonStateChanged(boolean enabled, int buttonResId);
    void onTextUpdate(String text);
    void onTextUpdateStatic(String text);
    void buttonVisibilityHelp(int typeVisibility);
    void buttonVisibilityOption(int typeVisibility);
}
