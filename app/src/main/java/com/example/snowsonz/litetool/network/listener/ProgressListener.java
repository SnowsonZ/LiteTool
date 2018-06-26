package com.example.snowsonz.litetool.network.listener;

public interface ProgressListener {
    void updateProgress(int precent);

    void fileStart(int index);

    void fileEnd(int index);

    void completed();
}
