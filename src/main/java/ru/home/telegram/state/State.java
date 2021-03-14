package ru.home.telegram.state;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.inlinequery.ChosenInlineQuery;
import org.telegram.telegrambots.meta.api.objects.inlinequery.InlineQuery;
import org.telegram.telegrambots.meta.api.objects.payments.PreCheckoutQuery;
import org.telegram.telegrambots.meta.api.objects.payments.ShippingQuery;
import org.telegram.telegrambots.meta.api.objects.polls.Poll;
import org.telegram.telegrambots.meta.api.objects.polls.PollAnswer;
import ru.home.telegram.db.entity.User;

public interface State {

    BotApiMethod<?> handleCallBackQuery(User user, CallbackQuery callbackQuery);

    BotApiMethod<?> handleChannelPost(User user, Message message);

    BotApiMethod<?> handleChosenInlineQuery(User user, ChosenInlineQuery chosenInlineQuery);

    BotApiMethod<?> handleEditChannelPost(User user, Message message);

    BotApiMethod<?> handleEditedMessage(User user, Message message);

    BotApiMethod<?> handleInlineQuery(User user, InlineQuery inlineQuery);

    BotApiMethod<?> handleMessage(User user, Message message);

    BotApiMethod<?> handlePoll(Poll poll);

    BotApiMethod<?> handlePollAnswer(User user, PollAnswer pollAnswer);

    BotApiMethod<?> handlePreCheckoutQuery(User user, PreCheckoutQuery preCheckoutQuery);

    BotApiMethod<?> handleShippingQuery(User user, ShippingQuery shippingQuery);

}
