package ru.home.telegram.update.facade;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.telegram.update.constant.UpdateContext;
import ru.home.telegram.exception.BotRoutingException;
import ru.home.telegram.update.handler.callbackquery.CallBackQueryUpdateHandler;
import ru.home.telegram.update.handler.channelpost.ChannelPostUpdateHandler;
import ru.home.telegram.update.handler.choseninlinequery.ChosenInlineQueryUpdateHandler;
import ru.home.telegram.update.handler.editedchannelpost.EditedChannelPostUpdateHandler;
import ru.home.telegram.update.handler.editedmessageupdate.EditedMessageUpdateHandler;
import ru.home.telegram.update.handler.inlinequery.InlineQueryUpdateHandler;
import ru.home.telegram.update.handler.message.MessageUpdateHandler;
import ru.home.telegram.update.handler.poll.PollUpdateHandler;
import ru.home.telegram.update.handler.pollanswer.PollAnswerUpdateHandler;
import ru.home.telegram.update.handler.precheckoutquery.PreCheckoutQueryUpdateHandler;
import ru.home.telegram.update.handler.shippingquery.ShippingQueryUpdateHandler;

@Service
@Qualifier(value = "updateFacade")
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class UpdateFacadeImp implements UpdateFacade {
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateFacadeImp.class);

    private MessageUpdateHandler messageUpdateHandler;
    private InlineQueryUpdateHandler inlineQueryUpdateHandler;
    private ChosenInlineQueryUpdateHandler chosenInlineQueryUpdateHandler;
    private CallBackQueryUpdateHandler callBackQueryUpdateHandler;
    private EditedMessageUpdateHandler editedMessageUpdateHandler;
    private ChannelPostUpdateHandler channelPostUpdateHandler;
    private EditedChannelPostUpdateHandler editedChannelPostUpdateHandler;
    private ShippingQueryUpdateHandler shippingQueryUpdateHandler;
    private PreCheckoutQueryUpdateHandler preCheckoutQueryUpdateHandler;
    private PollUpdateHandler pollUpdateHandler;
    private PollAnswerUpdateHandler pollAnswerUpdateHandler;

    /**
     * Маршрутизация входящего запроса
     *
     * @param update - Входящий запрос {@link Update}
     * @return Ответ пользователю
     */
    @Override
    public BotApiMethod<?> route(Update update) {
        LOGGER.info("Маршрутизация входящего запроса Update Id: {}", update.getUpdateId());

        try {
            UpdateContext updateContext = UpdateContext.getUpdateContext(update);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Определен контекст входящего запроса UpdateContext: {}", updateContext);
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
                    throw new BotRoutingException("Не найден маршрут обработки контекста: " + updateContext);
            }
        } catch (BotRoutingException bre) {
            LOGGER.error("Ошибка маршрутизации входящего запроса! Exception: {}", bre.getMessage(), bre);
            return null;
        }
    }
}
