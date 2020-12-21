package ru.home.telegram.service.facade;

import org.telegram.telegrambots.meta.api.objects.Update;

public enum UpdateEntity {
    MESSAGE,
    INLINE_QUERY,
    CHOSEN_INLINE_QUERY,
    CALL_BACK_QUERY,
    EDITED_MESSAGE,
    CHANNEL_POST,
    EDITED_CHANNEL_POST,
    SHIPPING_QUERY,
    PRE_CHECKOUT_QUERY,
    POLL,
    POLL_ANSWER;

    public static UpdateEntity getUpdateEntity(Update update) {
        if (update.hasMessage()) {
            return UpdateEntity.MESSAGE;
        } else if (update.hasInlineQuery()) {
            return UpdateEntity.INLINE_QUERY;
        } else if (update.hasChosenInlineQuery()) {
            return UpdateEntity.CHOSEN_INLINE_QUERY;
        } else if (update.hasCallbackQuery()) {
            return UpdateEntity.CALL_BACK_QUERY;
        } else if (update.hasEditedMessage()) {
            return UpdateEntity.EDITED_MESSAGE;
        } else if (update.hasChannelPost()) {
            return UpdateEntity.CHANNEL_POST;
        } else if (update.hasEditedChannelPost()) {
            return UpdateEntity.EDITED_CHANNEL_POST;
        } else if (update.hasShippingQuery()) {
            return UpdateEntity.SHIPPING_QUERY;
        } else if (update.hasPreCheckoutQuery()) {
            return UpdateEntity.PRE_CHECKOUT_QUERY;
        } else if (update.hasPoll()) {
            return UpdateEntity.POLL;
        } else if (update.hasPollAnswer()) {
            return UpdateEntity.POLL_ANSWER;
        } else {
            throw new IllegalStateException("Ошибка определения события! Тип события не найден!");
        }
    }
}
