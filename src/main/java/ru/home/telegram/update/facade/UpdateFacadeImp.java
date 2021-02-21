package ru.home.telegram.update.facade;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.telegram.exception.BotRoutingException;
import ru.home.telegram.update.constant.UpdateContext;
import ru.home.telegram.update.handler.callbackquery.CallBackQueryHandler;
import ru.home.telegram.update.handler.channelpost.ChannelPostHandler;
import ru.home.telegram.update.handler.choseninlinequery.ChosenInlineQueryHandler;
import ru.home.telegram.update.handler.editedchannelpost.EditedChannelPostHandler;
import ru.home.telegram.update.handler.editedmessage.EditedMessageHandler;
import ru.home.telegram.update.handler.inlinequery.InlineQueryHandler;
import ru.home.telegram.update.handler.message.MessageHandler;
import ru.home.telegram.update.handler.poll.PollHandler;
import ru.home.telegram.update.handler.pollanswer.PollAnswerHandler;
import ru.home.telegram.update.handler.precheckoutquery.PreCheckoutQueryHandler;
import ru.home.telegram.update.handler.shippingquery.ShippingQueryHandler;

@Service
@Qualifier(value = "updateFacade")
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class UpdateFacadeImp implements UpdateFacade {
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateFacadeImp.class);

    private MessageHandler messageHandler;
    private InlineQueryHandler inlineQueryHandler;
    private ChosenInlineQueryHandler chosenInlineQueryHandler;
    private CallBackQueryHandler callBackQueryHandler;
    private EditedMessageHandler editedMessageHandler;
    private ChannelPostHandler channelPostHandler;
    private EditedChannelPostHandler editedChannelPostHandler;
    private ShippingQueryHandler shippingQueryHandler;
    private PreCheckoutQueryHandler preCheckoutQueryHandler;
    private PollHandler pollHandler;
    private PollAnswerHandler pollAnswerHandler;

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
                    return messageHandler.handle(update.getMessage());
                case INLINE_QUERY:
                    return inlineQueryHandler.handle(update.getInlineQuery());
                case CHOSEN_INLINE_QUERY:
                    return chosenInlineQueryHandler.handle(update.getChosenInlineQuery());
                case CALL_BACK_QUERY:
                    return callBackQueryHandler.handle(update.getCallbackQuery());
                case EDITED_MESSAGE:
                    return editedMessageHandler.handle(update.getEditedMessage());
                case CHANNEL_POST:
                    return channelPostHandler.handle(update.getChannelPost());
                case EDITED_CHANNEL_POST:
                    return editedChannelPostHandler.handle(update.getEditedChannelPost());
                case SHIPPING_QUERY:
                    return shippingQueryHandler.handle(update.getShippingQuery());
                case PRE_CHECKOUT_QUERY:
                    return preCheckoutQueryHandler.handle(update.getPreCheckoutQuery());
                case POLL:
                    return pollHandler.handle(update.getPoll());
                case POLL_ANSWER:
                    return pollAnswerHandler.handle(update.getPollAnswer());
                default:
                    throw new BotRoutingException("Не найден маршрут обработки контекста: " + updateContext);
            }
        } catch (BotRoutingException bre) {
            LOGGER.error("Ошибка маршрутизации входящего запроса! Exception: {}", bre.getMessage(), bre);
            return null;
        }
    }
}
