package ru.home.telegram.constant;

import org.telegram.telegrambots.meta.api.objects.Message;

public enum MessageContext {
    ANIMATION,
    AUDIO,
    CONTACT,
    DICE,
    DOCUMENT,
    INVOICE,
    LOCATION,
    PASSPORT_DATA,
    PHOTO,
    POLL,
    REPLY_MARKUP,
    STICKER,
    SUCCESSFUL_PAYMENT,
    TEXT,
    VIA_BOT,
    VIDEO,
    VIDEO_NOTE,
    VOICE;

    public static MessageContext getMessageEntity(Message message) {
        if (message.hasAnimation()) {
            return MessageContext.ANIMATION;
        } else if (message.hasAudio()) {
            return MessageContext.AUDIO;
        } else if (message.hasContact()) {
            return MessageContext.CONTACT;
        } else if (message.hasDice()) {
            return MessageContext.DICE;
        } else if (message.hasDocument()) {
            return MessageContext.DOCUMENT;
        } else if (message.hasInvoice()) {
            return MessageContext.INVOICE;
        } else if (message.hasLocation()) {
            return MessageContext.LOCATION;
        } else if (message.hasPassportData()) {
            return MessageContext.PASSPORT_DATA;
        } else if (message.hasPhoto()) {
            return MessageContext.PHOTO;
        } else if (message.hasPoll()) {
            return MessageContext.POLL;
        } else if (message.hasReplyMarkup()) {
            return MessageContext.REPLY_MARKUP;
        } else if (message.hasSticker()) {
            return MessageContext.STICKER;
        } else if (message.hasSuccessfulPayment()) {
            return MessageContext.SUCCESSFUL_PAYMENT;
        } else if (message.hasText()) {
            return MessageContext.TEXT;
        } else if (message.hasViaBot()) {
            return MessageContext.VIA_BOT;
        } else if (message.hasVideo()) {
            return MessageContext.VIDEO;
        } else if (message.hasVideoNote()) {
            return MessageContext.VIDEO_NOTE;
        } else if (message.hasVoice()) {
            return MessageContext.VOICE;
        } else {
            throw new IllegalStateException("Ошибка определения события! Тип события не найден!");
        }
    }
}
