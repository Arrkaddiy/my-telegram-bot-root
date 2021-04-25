package ru.home.telegram.state;

import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.inlinequery.ChosenInlineQuery;
import org.telegram.telegrambots.meta.api.objects.inlinequery.InlineQuery;
import org.telegram.telegrambots.meta.api.objects.payments.PreCheckoutQuery;
import org.telegram.telegrambots.meta.api.objects.payments.ShippingQuery;
import org.telegram.telegrambots.meta.api.objects.polls.Poll;
import org.telegram.telegrambots.meta.api.objects.polls.PollAnswer;
import ru.home.telegram.db.entity.User;
import ru.home.telegram.exception.BotRuntimeException;
import ru.home.telegram.update.constant.UpdateContent;

public abstract class AbstractState implements State {
    private static final String STATE_ROUTE_HANDLE_EXCEPTION =
            "Ошибка определения обработчика для данного типа входяшего сообщения! UpdateContent ";
    private static final String STATE_BOT_API_OBJECT_EXCEPTION =
            "Ошибка входящего объекта в обработчик! BotApiObject ";

    public BotApiMethod<?> routeHandle(UpdateContent updateContent, User user, BotApiObject botApiObject) {
        switch (updateContent) {
            case CALL_BACK_QUERY:
                return callBackQuery(user, botApiObject);
            case CHANNEL_POST:
                return channelPost(user, botApiObject);
            case CHOSEN_INLINE_QUERY:
                return chosenInlineQuery(user, botApiObject);
            case EDITED_CHANNEL_POST:
                return editChannelPost(user, botApiObject);
            case EDITED_MESSAGE:
                return editedMessage(user, botApiObject);
            case INLINE_QUERY:
                return inlineQuery(user, botApiObject);
            case MESSAGE:
                return message(user, botApiObject);
            case POLL:
                return poll(botApiObject);
            case POLL_ANSWER:
                return pollAnswer(user, botApiObject);
            case PRE_CHECKOUT_QUERY:
                return preCheckoutQuery(user, botApiObject);
            case SHIPPING_QUERY:
                return shippingQuery(user, botApiObject);
            default:
                throw new BotRuntimeException(STATE_ROUTE_HANDLE_EXCEPTION + updateContent);
        }
    }

    private BotApiMethod<?> callBackQuery(User user, BotApiObject botApiObject) {
        if (botApiObject instanceof CallbackQuery) {
            return handleCallBackQuery(user, (CallbackQuery) botApiObject);
        } else {
            throw new BotRuntimeException(STATE_BOT_API_OBJECT_EXCEPTION + botApiObject.getClass());
        }
    }

    private BotApiMethod<?> channelPost(User user, BotApiObject botApiObject) {
        if (botApiObject instanceof Message) {
            return handleChannelPost(user, (Message) botApiObject);
        } else {
            throw new BotRuntimeException(STATE_BOT_API_OBJECT_EXCEPTION + botApiObject.getClass());
        }
    }

    private BotApiMethod<?> chosenInlineQuery(User user, BotApiObject botApiObject) {
        if (botApiObject instanceof ChosenInlineQuery) {
            return handleChosenInlineQuery(user, (ChosenInlineQuery) botApiObject);
        } else {
            throw new BotRuntimeException(STATE_BOT_API_OBJECT_EXCEPTION + botApiObject.getClass());
        }
    }

    private BotApiMethod<?> editChannelPost(User user, BotApiObject botApiObject) {
        if (botApiObject instanceof Message) {
            return handleEditChannelPost(user, (Message) botApiObject);
        } else {
            throw new BotRuntimeException(STATE_BOT_API_OBJECT_EXCEPTION + botApiObject.getClass());
        }
    }

    private BotApiMethod<?> editedMessage(User user, BotApiObject botApiObject) {
        if (botApiObject instanceof Message) {
            return handleEditedMessage(user, (Message) botApiObject);
        } else {
            throw new BotRuntimeException(STATE_BOT_API_OBJECT_EXCEPTION + botApiObject.getClass());
        }
    }

    private BotApiMethod<?> inlineQuery(User user, BotApiObject botApiObject) {
        if (botApiObject instanceof InlineQuery) {
            return handleInlineQuery(user, (InlineQuery) botApiObject);
        } else {
            throw new BotRuntimeException(STATE_BOT_API_OBJECT_EXCEPTION + botApiObject.getClass());
        }
    }

    private BotApiMethod<?> message(User user, BotApiObject botApiObject) {
        if (botApiObject instanceof Message) {
            return handleMessage(user, (Message) botApiObject);
        } else {
            throw new BotRuntimeException(STATE_BOT_API_OBJECT_EXCEPTION + botApiObject.getClass());
        }
    }

    private BotApiMethod<?> poll(BotApiObject botApiObject) {
        if (botApiObject instanceof Poll) {
            return handlePoll((Poll) botApiObject);
        } else {
            throw new BotRuntimeException(STATE_BOT_API_OBJECT_EXCEPTION + botApiObject.getClass());
        }
    }

    private BotApiMethod<?> pollAnswer(User user, BotApiObject botApiObject) {
        if (botApiObject instanceof PollAnswer) {
            return handlePollAnswer(user, (PollAnswer) botApiObject);
        } else {
            throw new BotRuntimeException(STATE_BOT_API_OBJECT_EXCEPTION + botApiObject.getClass());
        }
    }

    private BotApiMethod<?> preCheckoutQuery(User user, BotApiObject botApiObject) {
        if (botApiObject instanceof PreCheckoutQuery) {
            return handlePreCheckoutQuery(user, (PreCheckoutQuery) botApiObject);
        } else {
            throw new BotRuntimeException(STATE_BOT_API_OBJECT_EXCEPTION + botApiObject.getClass());
        }
    }

    private BotApiMethod<?> shippingQuery(User user, BotApiObject botApiObject) {
        if (botApiObject instanceof ShippingQuery) {
            return handleShippingQuery(user, (ShippingQuery) botApiObject);
        } else {
            throw new BotRuntimeException(STATE_BOT_API_OBJECT_EXCEPTION + botApiObject.getClass());
        }
    }
}
