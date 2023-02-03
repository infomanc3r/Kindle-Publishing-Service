package com.amazon.ata.kindlepublishingservice.converters;

import com.amazon.ata.coral.converter.CoralConverterUtil;
import com.amazon.ata.kindlepublishingservice.dynamodb.models.PublishingStatusItem;
import com.amazon.ata.kindlepublishingservice.models.PublishingStatusRecord;

import java.util.List;

public class PublishingStatusItemConverter {

    private PublishingStatusItemConverter(){}

    public static List<PublishingStatusRecord> toCoral(List<PublishingStatusItem> itemList) {
        return CoralConverterUtil.convertList(itemList, PublishingStatusItemConverter::toCoral);
    }

    public static PublishingStatusRecord toCoral(PublishingStatusItem item) {
        return PublishingStatusRecord.builder()
                .withBookId(item.getBookId())
                .withStatus(item.getStatus().toString())
                .withStatusMessage(item.getStatusMessage())
                .build();
    }
}
