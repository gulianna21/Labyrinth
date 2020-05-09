package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnPathButtonStateChangeCallback {
    private TextView helloTv;
    private TextView textSolution;
    private TextView textViewPoints;

    Button buttonUpBtn1;
    Button buttonDownBtn3;
    Button buttonLeftBtn2;
    Button buttonRightBtn4;
    Button buttonQueue5;
    Button buttonHelp;
    Button buttonSolution;
    Button button1Forward;
    Button buttonTest;

    private long backPressedTime;
    private Toast backToast;

    GameLogicManager logicManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helloTv = findViewById(R.id.hello_tv);
        textSolution = findViewById(R.id.textSolution);
        textViewPoints = findViewById(R.id.textViewPoints);

        buttonUpBtn1 = findViewById(R.id.buttonUp);
        buttonDownBtn3 = findViewById(R.id.buttonDown);
        buttonLeftBtn2 = findViewById(R.id.buttonLeft);
        buttonRightBtn4 = findViewById(R.id.buttonRight);
        buttonQueue5 = findViewById(R.id.buttonQueue);
        buttonHelp = findViewById(R.id.buttonHelp);
        buttonSolution = findViewById(R.id.buttonSolution);
        button1Forward = findViewById(R.id.button1Forward);

        buttonUpBtn1.setOnClickListener(this);
        buttonDownBtn3.setOnClickListener(this);
        buttonLeftBtn2.setOnClickListener(this);
        buttonRightBtn4.setOnClickListener(this);
        buttonQueue5.setOnClickListener(this);
        buttonHelp.setOnClickListener(this);
        buttonSolution.setOnClickListener(this);
        button1Forward.setOnClickListener(this);
        buttonTest.setOnClickListener(this);

        logicManager = new GameLogicManager(10);
        logicManager.setCallback(this);

        logicManager.blockWay();
        logicManager.createVisibilityOption(View.INVISIBLE);
    }

    //Системная кнопка "Назад" начало
    @Override
    public void onBackPressed() {
        if(backPressedTime + 2000 > System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed(); // закрывает игру
            return;
        } else{
            backToast = Toast.makeText(getBaseContext(),"Нажмите еще раз, чтобы выйти", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis(); // засекли время нажатия на кнопку
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonUp:
                logicManager.onTopButtonClick();
                logicManager.blockWay();
                break;
            case R.id.buttonDown:
                logicManager.onDownButtonClick();
                break;
            case R.id.buttonLeft:
                logicManager.onLeftButtonClick();
                logicManager.blockWay();
                break;
            case R.id.buttonRight:
                logicManager.onRightButtonClick();
                logicManager.blockWay();
                break;
            case R.id.buttonQueue:
                logicManager.onQueueButtonClick();
                break;
            case R.id.buttonHelp:
                logicManager.createVisibilityHelp(View.INVISIBLE);
                logicManager.createVisibilityOption(View.VISIBLE);
                break;
            case R.id.buttonSolution:
                logicManager.onButtonSolutionClick();
                logicManager.createVisibilityOption(View.INVISIBLE);
                logicManager.createVisibilityHelp(View.VISIBLE);
                break;
            case R.id.button1Forward:
                logicManager.onButtonForwardClick();
                logicManager.createVisibilityOption(View.INVISIBLE);
                logicManager.createVisibilityHelp(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onButtonStateChanged(boolean enabled, int buttonResId) {
        switch (buttonResId) {
            case R.id.buttonUp:
                buttonUpBtn1.setEnabled(enabled);
                break;
            case R.id.buttonDown:
                buttonDownBtn3.setEnabled(enabled);
                break;
            case R.id.buttonLeft:
                buttonLeftBtn2.setEnabled(enabled);
                break;
            case R.id.buttonRight:
                buttonRightBtn4.setEnabled(enabled);
                break;
        }
    }

    @Override
    public void onTextUpdate(String text) {
        helloTv.setText(text);
    }

    @Override
    public void onTextUpdateStatic(String text) {
        textSolution.setText(text);
    }

    @Override
    public void buttonVisibilityHelp(int typeVisibility) {
        buttonHelp.setVisibility(typeVisibility);
    }

    @Override
    public void buttonVisibilityOption(int typeVisibility) {
        buttonSolution.setVisibility(typeVisibility);
        button1Forward.setVisibility(typeVisibility);
    }
}