package com.example.mobilnakasa;

import android.app.Application;

public class GlobalVariables extends Application {

    private OperatorItem loggedOperator;

    public OperatorItem getLoggedOperator() {
        return loggedOperator;
    }

    public void setLoggedOperator(OperatorItem loggedOperator) {
        this.loggedOperator = loggedOperator;
    }
}
