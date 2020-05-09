package com.example.myapplication;

import java.util.ArrayList;
import java.util.Arrays;

public class GameLogicManager {
    private OnPathButtonStateChangeCallback callback;
    private ArrayList<Integer> posledov;
    private ArrayList<Integer> block;
    private ArrayList<Integer> mainVector;
    private ArrayList<Integer> newblock;

    GameLogicManager(int count) {
        posledov = new ArrayList<>();
        mainVector = createVector(count);
        block = createMainVector(count);
    }

    void setCallback(OnPathButtonStateChangeCallback callback) {
        this.callback = callback;
    }

    private ArrayList<Integer> createVector(int count) {
        ArrayList<Integer> vector = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            vector.add(1 + (int) (Math.random() * 3));
        }
        return vector;
    }

    private ArrayList<Integer> createMainVector(int count) {
        ArrayList<Integer> vector = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            int number = (int) (Math.random() * 3);
            if (number != mainVector.get(i)) {
                vector.add(number);
            } else {
                i--;
            }
        }
        return vector;
    }

    public void onLeftButtonClick() {
        posledov.add(1);
        if (!isOver()) {
            callback.onTextUpdate("Hi Left!");
        }
    }

    public void onRightButtonClick() {
        posledov.add(3);
        if (!isOver()) {
            callback.onTextUpdate("Hi Right!");
        }
    }

    public void onTopButtonClick() {
        posledov.add(2);
        if (!isOver()) {
            callback.onTextUpdate("Hi Top!");
        }
    }

    public void onDownButtonClick() {
        if (posledov.size() != 0) {
            posledov.remove(posledov.size() - 1);
        }
        isOver();
        callback.onTextUpdate("Hi Down!");
    }

    public void onButtonSolutionClick() {
        callback.onTextUpdateStatic(Arrays.toString(mainVector.toArray()));
    }

    public void onButtonTestClick() {
        callback.onTextUpdateStatic(Arrays.toString(block.toArray()));
    }

    public void onButtonForwardClick() {
        if (posledov.size() < 10) {
            for (int i = 0; i < posledov.size(); i++) {
                if (posledov.get(i) != mainVector.get(i)) {
                    callback.onTextUpdateStatic("Down");
                    break;
                }
                switch (mainVector.get(posledov.size())) {
                    case 1:
                        callback.onTextUpdateStatic("Left");
                        break;
                    case 2:
                        callback.onTextUpdateStatic("Top");
                        break;
                    case 3:
                        callback.onTextUpdateStatic("Right");
                        break;
                }
            }
        }
    }

    private boolean isOver() {
        if (posledov.size() > 9) {
            callback.onButtonStateChanged(false, R.id.buttonLeft);
            callback.onButtonStateChanged(false, R.id.buttonUp);
            callback.onButtonStateChanged(false, R.id.buttonRight);
            callback.onTextUpdate("You win");
            return true;
        } else {
            callback.onButtonStateChanged(true, R.id.buttonLeft);
            callback.onButtonStateChanged(true, R.id.buttonUp);
            callback.onButtonStateChanged(true, R.id.buttonRight);
            return false;
        }
    }

    public void onQueueButtonClick() {
        callback.onTextUpdate(Arrays.toString(posledov.toArray()));
    }

    public void createVisibilityHelp(int visibl) {
        callback.buttonVisibilityHelp(visibl);
    }

    public void createVisibilityOption(int visibl) {
        callback.buttonVisibilityOption(visibl);
    }

    public void blockWay() {
        if (block.size() != 0) {
            newblock = block;
            switch (block.get(0)) {
                case 0:
                    callback.onButtonStateChanged(true, R.id.buttonLeft);
                    callback.onButtonStateChanged(true, R.id.buttonUp);
                    callback.onButtonStateChanged(true, R.id.buttonRight);
                    break;
                case 1:
                    callback.onButtonStateChanged(false, R.id.buttonLeft);
                    callback.onButtonStateChanged(true, R.id.buttonUp);
                    callback.onButtonStateChanged(true, R.id.buttonRight);
                    break;
                case 2:
                    callback.onButtonStateChanged(false, R.id.buttonUp);
                    callback.onButtonStateChanged(true, R.id.buttonLeft);
                    callback.onButtonStateChanged(true, R.id.buttonRight);
                    break;
                case 3:
                    callback.onButtonStateChanged(false, R.id.buttonRight);
                    callback.onButtonStateChanged(true, R.id.buttonLeft);
                    callback.onButtonStateChanged(true, R.id.buttonUp);
                    break;
            }
            block.remove(0);
        }
    }
}
