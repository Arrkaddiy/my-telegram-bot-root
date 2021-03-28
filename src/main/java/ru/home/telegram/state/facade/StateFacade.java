package ru.home.telegram.state.facade;

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
import ru.home.telegram.state.constant.BotStateType;

public interface StateFacade {

    BotApiMethod<?> handleCallBackQuery(BotStateType stateType, User user, CallbackQuery callbackQuery);

    BotApiMethod<?> handleChannelPost(BotStateType stateType, User user, Message message);

    BotApiMethod<?> handleChosenInlineQuery(BotStateType stateType, User user, ChosenInlineQuery chosenInlineQuery);

    BotApiMethod<?> handleEditChannelPost(BotStateType stateType, User user, Message message);

    BotApiMethod<?> handleEditedMessage(BotStateType stateType, User user, Message message);

    BotApiMethod<?> handleInlineQuery(BotStateType stateType, User user, InlineQuery inlineQuery);

    BotApiMethod<?> handleMessage(BotStateType stateType, User user, Message message);

    BotApiMethod<?> handlePoll(BotStateType stateType, Poll poll);

    BotApiMethod<?> handlePollAnswer(BotStateType stateType, User user, PollAnswer pollAnswer);

    BotApiMethod<?> handlePreCheckoutQuery(BotStateType stateType, User user, PreCheckoutQuery preCheckoutQuery);

    BotApiMethod<?> handleShippingQuery(BotStateType stateType, User user, ShippingQuery shippingQuery);
}
