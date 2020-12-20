package ru.home.telegram.service.facade.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.telegram.common.UpdateEntity;
import ru.home.telegram.service.facade.intf.IWebHookBotFacade;
import ru.home.telegram.service.hadler.imp.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class UpdateFacade implements IWebHookBotFacade<Update> {
    //Логгер
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateFacade.class);

    @Autowired
    @Lazy
    private MessageHandler messageHandler;
    @Autowired
    @Lazy
    private InlineQueryHandler inlineQueryHandler;
    @Autowired
    @Lazy
    private ChosenInlineQueryHandler chosenInlineQueryHandler;
    @Autowired
    @Lazy
    private CallBackQueryHandler callBackQueryHandler;
    @Autowired
    @Lazy
    private EditedMessageHandler editedMessageHandler;
    @Autowired
    @Lazy
    private ChannelPostHandler channelPostHandler;
    @Autowired
    @Lazy
    private EditedChannelPostHandler editedChannelPostHandler;
    @Autowired
    @Lazy
    private ShippingQueryHandler shippingQueryHandler;
    @Autowired
    @Lazy
    private PreCheckoutQueryHandler preCheckoutQueryHandler;
    @Autowired
    @Lazy
    private PollHandler pollHandler;
    @Autowired
    @Lazy
    private PollAnswerHandler pollAnswerHandler;


    @Override
    public void handle(Update update, TelegramWebhookBot telegramWebhookBot) {
        LOGGER.info("Обработка события запроса Update: {}", update);
        UpdateEntity updateEntity = UpdateEntity.getUpdateEntity(update);
        switch (updateEntity) {
            case MESSAGE:
                messageHandler.handle(update.getMessage(), telegramWebhookBot);
                break;
            case INLINE_QUERY:
                inlineQueryHandler.handle(update.getInlineQuery(), telegramWebhookBot);
                break;
            case CHOSEN_INLINE_QUERY:
                chosenInlineQueryHandler.handle(update.getChosenInlineQuery(), telegramWebhookBot);
                break;
            case CALL_BACK_QUERY:
                callBackQueryHandler.handle(update.getCallbackQuery(), telegramWebhookBot);
                break;
            case EDITED_MESSAGE:
                editedMessageHandler.handle(update.getEditedMessage(), telegramWebhookBot);
                break;
            case CHANNEL_POST:
                channelPostHandler.handle(update.getChannelPost(), telegramWebhookBot);
                break;
            case EDITED_CHANNEL_POST:
                editedChannelPostHandler.handle(update.getEditedChannelPost(), telegramWebhookBot);
                break;
            case SHIPPING_QUERY:
                shippingQueryHandler.handle(update.getShippingQuery(), telegramWebhookBot);
                break;
            case PRE_CHECKOUT_QUERY:
                preCheckoutQueryHandler.handle(update.getPreCheckoutQuery(), telegramWebhookBot);
                break;
            case POLL:
                pollHandler.handle(update.getPoll(), telegramWebhookBot);
                break;
            case POLL_ANSWER:
                pollAnswerHandler.handle(update.getPollAnswer(), telegramWebhookBot);
                break;
            default:
                throw new IllegalStateException("Не найден маршрут обработки объекта типа" + updateEntity);
        }
    }

    @PostConstruct
    private void initBean() {
        //Данный метод выполняется после инициализации бина
        LOGGER.info("UpdateFacade initialization");
    }

    @PreDestroy
    private void destroyBean() {
        //Данный метод выполняется перед удалением бина
        LOGGER.info("UpdateFacade destroy");
    }
}
