package com.amazon.ata.kindlepublishingservice.publishing;

import dagger.Provides;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BookPublishRequestManager {

    private Queue<BookPublishRequest> queue;

    public BookPublishRequestManager () {
        queue = new ConcurrentLinkedQueue<>();
    }

    public void addBookPublishRequest(BookPublishRequest request) {
        queue.add(request);
    }

    public BookPublishRequest getBookPublishRequestToProcess() {
        return queue.poll();
    }
}
