package ru.home.telegram.state.facade;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
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
import ru.home.telegram.state.constant.BotStateType;
import ru.home.telegram.state.start.StartState;

@RequiredArgsConstructor
public class StateFacadeImpl implements StateFacade {
    private static final Logger LOGGER = LoggerFactory.getLogger(StateFacadeImpl.class);
    private static final String STATE_FACADE_ROUTE = "Маршрутизация состояния StateType: {}";

    private final ApplicationContext context;

    @Override
    public BotApiMethod<?> handleCallBackQuery(BotStateType stateType, User user, CallbackQuery callbackQuery) {
        State currentState = route(stateType);
        if (currentState == null) {
            return null;
        }
        return currentState.handleCallBackQuery(user, callbackQuery);
    }

    @Override
    public BotApiMethod<?> handleChannelPost(BotStateType stateType, User user, Message message) {
        State currentState = route(stateType);
        if (currentState == null) {
            return null;
        }
        return currentState.handleChannelPost(user, message);
    }

    @Override
    public BotApiMethod<?> handleChosenInlineQuery(BotStateType stateType, User user, ChosenInlineQuery chosenInlineQuery) {
        State currentState = route(stateType);
        if (currentState == null) {
            return null;
        }
        return currentState.handleChosenInlineQuery(user, chosenInlineQuery);
    }

    @Override
    public BotApiMethod<?> handleEditChannelPost(BotStateType stateType, User user, Message message) {
        State currentState = route(stateType);
        if (currentState == null) {
            return null;
        }
        return currentState.handleEditChannelPost(user, message);
    }

    @Override
    public BotApiMethod<?> handleEditedMessage(BotStateType stateType, User user, Message message) {
        State currentState = route(stateType);
        if (currentState == null) {
            return null;
        }
        return currentState.handleEditedMessage(user, message);
    }

    @Override
    public BotApiMethod<?> handleInlineQuery(BotStateType stateType, User user, InlineQuery inlineQuery) {
        State currentState = route(stateType);
        if (currentState == null) {
            return null;
        }
        return currentState.handleInlineQuery(user, inlineQuery);
    }

    @Override
    public BotApiMethod<?> handleMessage(BotStateType stateType, User user, Message message) {
        State currentState = route(stateType);
        if (currentState == null) {
            return null;
        }
        return currentState.handleMessage(user, message);
    }

    @Override
    public BotApiMethod<?> handlePoll(BotStateType stateType, Poll poll) {
        State currentState = route(stateType);
        if (currentState == null) {
            return null;
        }
        return currentState.handlePoll(poll);
    }

    @Override
    public BotApiMethod<?> handlePollAnswer(BotStateType stateType, User user, PollAnswer pollAnswer) {
        State currentState = route(stateType);
        if (currentState == null) {
            return null;
        }
        return currentState.handlePollAnswer(user, pollAnswer);
    }

    @Override
    public BotApiMethod<?> handlePreCheckoutQuery(BotStateType stateType, User user, PreCheckoutQuery preCheckoutQuery) {
        State currentState = route(stateType);
        if (currentState == null) {
            return null;
        }
        return currentState.handlePreCheckoutQuery(user, preCheckoutQuery);
    }

    @Override
    public BotApiMethod<?> handleShippingQuery(BotStateType stateType, User user, ShippingQuery shippingQuery) {
        State currentState = route(stateType);
        if (currentState == null) {
            return null;
        }
        return currentState.handleShippingQuery(user, shippingQuery);
    }

    private State route(BotStateType stateType) {
        LOGGER.info(STATE_FACADE_ROUTE, stateType);

        switch (stateType) {
            case START:
                return context.getBean(StartState.class);
            default:
                return null;
        }
    }
}
