package ru.home.telegram.state.authorizephone;

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
import ru.home.telegram.state.State;

public interface AuthorizePhoneState extends State {

    @Override
    default BotApiMethod<?> handleCallBackQuery(User user, CallbackQuery callbackQuery) {
        return null;
    }

    @Override
    default BotApiMethod<?> handleChannelPost(User user, Message message) {
        return null;
    }

    @Override
    default BotApiMethod<?> handleChosenInlineQuery(User user, ChosenInlineQuery chosenInlineQuery) {
        return null;
    }

    @Override
    default BotApiMethod<?> handleEditChannelPost(User user, Message message) {
        return null;
    }

    @Override
    default BotApiMethod<?> handleEditedMessage(User user, Message message) {
        return null;
    }

    @Override
    default BotApiMethod<?> handleInlineQuery(User user, InlineQuery inlineQuery) {
        return null;
    }

    @Override
    default BotApiMethod<?> handlePoll(Poll poll) {
        return null;
    }

    @Override
    default BotApiMethod<?> handlePollAnswer(User user, PollAnswer pollAnswer) {
        return null;
    }

    @Override
    default BotApiMethod<?> handlePreCheckoutQuery(User user, PreCheckoutQuery preCheckoutQuery) {
        return null;
    }

    @Override
    default BotApiMethod<?> handleShippingQuery(User user, ShippingQuery shippingQuery) {
        return null;
    }
}
