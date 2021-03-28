package ru.home.telegram.update.facade;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.telegram.update.constant.UpdateContent;
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

@RequiredArgsConstructor
public class UpdateFacadeImpl implements UpdateFacade {
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateFacadeImpl.class);
    private static final String ROUTE_UPDATE = "Маршрутизация входящего запроса Update: {}";
    private static final String ROUTE_UPDATE_ID = "Маршрутизация входящего запроса Update Id: {}";
    private static final String ROUTE_UPDATE_CONTENT = "Определен контент входящего запроса UpdateContent: {}";
    private static final String ROUTE_UPDATE_CONTENT_NULL_ERROR = "Ошибка определения контента входящего сообщения! Update: {}";
    private static final String ROUTE_UPDATE_CONTENT_HANDLER_ERROR = "Ошибка определения обработчика контента входящего сообщения! UpdateContent: {}";

    private final ApplicationContext context;

    /**
     * Маршрутизация входящего запроса
     *
     * @param update - Входящий запрос {@link Update}
     * @return Ответ пользователю
     */
    @Override
    public BotApiMethod<?> route(Update update) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(ROUTE_UPDATE, update);
        } else {
            LOGGER.info(ROUTE_UPDATE_ID, update.getUpdateId());
        }

        UpdateContent updateContent = UpdateContent.getUpdateContent(update);

        if (UpdateContent.NULL == updateContent) {
            LOGGER.error(ROUTE_UPDATE_CONTENT_NULL_ERROR, update);
            return null;
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(ROUTE_UPDATE_CONTENT, updateContent);
        }

        switch (updateContent) {
            case MESSAGE:
                return context.getBean(MessageHandler.class).handle(update.getMessage());
            case INLINE_QUERY:
                return context.getBean(InlineQueryHandler.class).handle(update.getInlineQuery());
            case CHOSEN_INLINE_QUERY:
                return context.getBean(ChosenInlineQueryHandler.class).handle(update.getChosenInlineQuery());
            case CALL_BACK_QUERY:
                return context.getBean(CallBackQueryHandler.class).handle(update.getCallbackQuery());
            case EDITED_MESSAGE:
                return context.getBean(EditedMessageHandler.class).handle(update.getEditedMessage());
            case CHANNEL_POST:
                return context.getBean(ChannelPostHandler.class).handle(update.getChannelPost());
            case EDITED_CHANNEL_POST:
                return context.getBean(EditedChannelPostHandler.class).handle(update.getEditedChannelPost());
            case SHIPPING_QUERY:
                return context.getBean(ShippingQueryHandler.class).handle(update.getShippingQuery());
            case PRE_CHECKOUT_QUERY:
                return context.getBean(PreCheckoutQueryHandler.class).handle(update.getPreCheckoutQuery());
            case POLL:
                return context.getBean(PollHandler.class).handle(update.getPoll());
            case POLL_ANSWER:
                return context.getBean(PollAnswerHandler.class).handle(update.getPollAnswer());
            default:
                LOGGER.error(ROUTE_UPDATE_CONTENT_HANDLER_ERROR, updateContent);
                return null;
        }
    }
}
