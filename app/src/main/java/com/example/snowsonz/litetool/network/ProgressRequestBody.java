package com.example.snowsonz.litetool.network;

import android.support.annotation.Nullable;
import android.util.Log;

import com.example.snowsonz.litetool.network.listener.ProgressListener;

import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

public class ProgressRequestBody extends RequestBody {
    private List<File> tartFile;
    private MediaType mediaType;
//    private ProgressListener listener;

    private ProgressRequestBody(List<File> tartFile,
                                MediaType mediaType,
                                ProgressListener listener) {
        this.tartFile = tartFile;
        this.mediaType = mediaType;
//        this.listener = listener;
    }

    public static ProgressRequestBody create(List<File> tartFile,
                                             MediaType mediaType,
                                             ProgressListener listener) {
        return new ProgressRequestBody(tartFile, mediaType, listener);
    }

    @Nullable
    @Override
    public MediaType contentType() {
        return mediaType;
    }

    @Override
    public long contentLength() throws IOException {
        long totalSize = 0;
        for (File file : tartFile) {
            totalSize += file.length();
        }
        return totalSize;
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        long len = 0;
        long completed = 0;
        int fileIndex = 1;
        Buffer buffer = new Buffer();
        for (File file : tartFile) {
//            listener.fileStart(fileIndex);
            Source source = Okio.source(file);
            sink.writeAll(source);
            while ((len = source.read(buffer, 2048)) != -1) {
                sink.write(source, len);
                sink.flush();
                completed += len;
                Log.d("Progress", String.valueOf((int) completed * 100 / contentLength()));
//                listener.updateProgress((int) (completed * 100 / contentLength()));
            }
//            listener.fileEnd(fileIndex);
            fileIndex++;
        }
//        listener.completed();
    }
}
