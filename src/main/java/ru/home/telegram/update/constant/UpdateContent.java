package ru.home.telegram.update.constant;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.telegram.exception.BotRoutingException;

public enum UpdateContent {
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

    public static UpdateContent getUpdateContent(Update update) {
        if (update.hasMessage()) {
            return UpdateContent.MESSAGE;
        } else if (update.hasInlineQuery()) {
            return UpdateContent.INLINE_QUERY;
        } else if (update.hasChosenInlineQuery()) {
            return UpdateContent.CHOSEN_INLINE_QUERY;
        } else if (update.hasCallbackQuery()) {
            return UpdateContent.CALL_BACK_QUERY;
        } else if (update.hasEditedMessage()) {
            return UpdateContent.EDITED_MESSAGE;
        } else if (update.hasChannelPost()) {
            return UpdateContent.CHANNEL_POST;
        } else if (update.hasEditedChannelPost()) {
            return UpdateContent.EDITED_CHANNEL_POST;
        } else if (update.hasShippingQuery()) {
            return UpdateContent.SHIPPING_QUERY;
        } else if (update.hasPreCheckoutQuery()) {
            return UpdateContent.PRE_CHECKOUT_QUERY;
        } else if (update.hasPoll()) {
            return UpdateContent.POLL;
        } else if (update.hasPollAnswer()) {
            return UpdateContent.POLL_ANSWER;
        } else {
            throw new BotRoutingException("Ошибка определения контекста запроса! Тип контекста не найден!");
        }
    }
}
