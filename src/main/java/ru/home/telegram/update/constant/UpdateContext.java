package ru.home.telegram.update.constant;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.telegram.exception.BotRoutingException;

public enum UpdateContext {
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

    public static UpdateContext getUpdateContext(Update update) {
        if (update.hasMessage()) {
            return UpdateContext.MESSAGE;
        } else if (update.hasInlineQuery()) {
            return UpdateContext.INLINE_QUERY;
        } else if (update.hasChosenInlineQuery()) {
            return UpdateContext.CHOSEN_INLINE_QUERY;
        } else if (update.hasCallbackQuery()) {
            return UpdateContext.CALL_BACK_QUERY;
        } else if (update.hasEditedMessage()) {
            return UpdateContext.EDITED_MESSAGE;
        } else if (update.hasChannelPost()) {
            return UpdateContext.CHANNEL_POST;
        } else if (update.hasEditedChannelPost()) {
            return UpdateContext.EDITED_CHANNEL_POST;
        } else if (update.hasShippingQuery()) {
            return UpdateContext.SHIPPING_QUERY;
        } else if (update.hasPreCheckoutQuery()) {
            return UpdateContext.PRE_CHECKOUT_QUERY;
        } else if (update.hasPoll()) {
            return UpdateContext.POLL;
        } else if (update.hasPollAnswer()) {
            return UpdateContext.POLL_ANSWER;
        } else {
            throw new BotRoutingException("Ошибка определения контекста запроса! Тип контекста не найден!");
        }
    }
}
