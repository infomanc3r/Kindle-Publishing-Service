package com.amazon.ata.kindlepublishingservice.publishing;

import dagger.Provides;

import java.util.LinkedList;
import java.util.Queue;

public class BookPublishRequestManager {

    private Queue<BookPublishRequest> queue;

    public BookPublishRequestManager () {
        queue = new LinkedList<>();
    }

    public void addBookPublishRequest(BookPublishRequest request) {
        queue.add(request);
    }

    public BookPublishRequest getBookPublishRequestToProcess() {
        return queue.poll();
    }
}
