package ru.home.telegram.update.facade.imp;

import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.telegram.constant.UpdateContext;
import ru.home.telegram.exception.BotRoutingException;
import ru.home.telegram.update.facade.intf.UpdateFacade;
import ru.home.telegram.update.handler.intf.*;

@Service
@Qualifier(value = "updateFacade")
public class UpdateFacadeImp implements UpdateFacade {
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateFacadeImp.class);
    @Setter(onMethod_ = {@Autowired})
    private MessageUpdateHandler messageUpdateHandler;
    @Setter(onMethod_ = {@Autowired})
    private InlineQueryUpdateHandler inlineQueryUpdateHandler;
    @Setter(onMethod_ = {@Autowired})
    private ChosenInlineQueryUpdateHandler chosenInlineQueryUpdateHandler;
    @Setter(onMethod_ = {@Autowired})
    private CallBackQueryUpdateHandler callBackQueryUpdateHandler;
    @Setter(onMethod_ = {@Autowired})
    private EditedMessageUpdateHandler editedMessageUpdateHandler;
    @Setter(onMethod_ = {@Autowired})
    private ChannelPostUpdateHandler channelPostUpdateHandler;
    @Setter(onMethod_ = {@Autowired})
    private EditedChannelPostUpdateHandler editedChannelPostUpdateHandler;
    @Setter(onMethod_ = {@Autowired})
    private ShippingQueryUpdateHandler shippingQueryUpdateHandler;
    @Setter(onMethod_ = {@Autowired})
    private PreCheckoutQueryUpdateHandler preCheckoutQueryUpdateHandler;
    @Setter(onMethod_ = {@Autowired})
    private PollUpdateHandler pollUpdateHandler;
    @Setter(onMethod_ = {@Autowired})
    private PollAnswerUpdateHandler pollAnswerUpdateHandler;

    /**
     * Маршрутизация входящего запроса
     *
     * @param update - Входящий запрос {@link Update}
     * @return Ответ пользователю
     */
    @Override
    public BotApiMethod<?> route(Update update) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Маршрутизация входящего запроса Update Id: {}", update.getUpdateId());
        }

        try {
            UpdateContext updateContext = UpdateContext.getUpdateContext(update);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Определен тип входящего запроса UpdateContext: {}", updateContext);
            }

            switch (updateContext) {
                case MESSAGE:
                    return messageUpdateHandler.handle(update.getMessage());
                case INLINE_QUERY:
                    return inlineQueryUpdateHandler.handle(update.getInlineQuery());
                case CHOSEN_INLINE_QUERY:
                    return chosenInlineQueryUpdateHandler.handle(update.getChosenInlineQuery());
                case CALL_BACK_QUERY:
                    return callBackQueryUpdateHandler.handle(update.getCallbackQuery());
                case EDITED_MESSAGE:
                    return editedMessageUpdateHandler.handle(update.getEditedMessage());
                case CHANNEL_POST:
                    return channelPostUpdateHandler.handle(update.getChannelPost());
                case EDITED_CHANNEL_POST:
                    return editedChannelPostUpdateHandler.handle(update.getEditedChannelPost());
                case SHIPPING_QUERY:
                    return shippingQueryUpdateHandler.handle(update.getShippingQuery());
                case PRE_CHECKOUT_QUERY:
                    return preCheckoutQueryUpdateHandler.handle(update.getPreCheckoutQuery());
                case POLL:
                    return pollUpdateHandler.handle(update.getPoll());
                case POLL_ANSWER:
                    return pollAnswerUpdateHandler.handle(update.getPollAnswer());
                default:
                    throw new BotRoutingException("Не найден маршрут обработки объекта типа " + updateContext);
            }
        } catch (BotRoutingException bre) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("Ошибка маршрутизации входящего запроса! Exception: {}", bre.getMessage());
            }

            bre.printStackTrace();
            return null;
        }
    }
}
