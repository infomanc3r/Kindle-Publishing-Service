package com.amazon.ata.kindlepublishingservice.publishing;

import com.amazon.ata.kindlepublishingservice.dao.CatalogDao;
import com.amazon.ata.kindlepublishingservice.dao.PublishingStatusDao;
import com.amazon.ata.kindlepublishingservice.dynamodb.models.CatalogItemVersion;
import com.amazon.ata.kindlepublishingservice.enums.PublishingRecordStatus;
import com.amazon.ata.kindlepublishingservice.exceptions.BookNotFoundException;

import javax.inject.Inject;

public class BookPublishTask implements Runnable {

    BookPublishRequestManager bookPublishRequestManager;
    PublishingStatusDao publishingStatusDao;
    CatalogDao catalogDao;

    @Inject
    public BookPublishTask(BookPublishRequestManager bookPublishRequestManager, PublishingStatusDao publishingStatusDao,
                           CatalogDao catalogDao) {
        this.bookPublishRequestManager = bookPublishRequestManager;
        this.publishingStatusDao = publishingStatusDao;
        this.catalogDao = catalogDao;
    }
    public void run() {

        BookPublishRequest request = bookPublishRequestManager.getBookPublishRequestToProcess();

        if (request == null) {
            return;
        }

        publishingStatusDao.setPublishingStatus(request.getPublishingRecordId(),
                PublishingRecordStatus.IN_PROGRESS, request.getBookId());

        KindleFormattedBook formattedBook = KindleFormatConverter.format(request);

//        try {
//            catalogDao.createOrUpdateBook(formattedBook);
//        } catch (BookNotFoundException e) {
//            System.out.println(e.getMessage());
//            publishingStatusDao.setPublishingStatus(request.getPublishingRecordId(),
//                    PublishingRecordStatus.FAILED, request.getBookId(), "Publish request failed.");
//        }

        CatalogItemVersion addedBook = catalogDao.createOrUpdateBook(formattedBook);

        publishingStatusDao.setPublishingStatus(request.getPublishingRecordId(), PublishingRecordStatus.SUCCESSFUL,
                addedBook.getBookId());

    }

}
