package ru.home.telegram.service.facade.imp;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.telegram.service.facade.UpdateEntity;
import ru.home.telegram.service.facade.intf.IUpdateFacade;
import ru.home.telegram.service.hadler.IHandlerContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@AllArgsConstructor
public class UpdateFacade implements IUpdateFacade {
    //Логгер
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateFacade.class);

    private IHandlerContext handlerContext;

    @Override
    public BotApiMethod<?> handle(Update update) {
        LOGGER.info("Обработка события запроса Update: {}", update);
        UpdateEntity updateEntity = UpdateEntity.getUpdateEntity(update);
        switch (updateEntity) {
            case MESSAGE:
                return handlerContext.getMessageHandler().handle(update.getMessage());
            case INLINE_QUERY:
                return handlerContext.getInlineQueryHandler().handle(update.getInlineQuery());
            case CHOSEN_INLINE_QUERY:
                return handlerContext.getChosenInlineQueryHandler().handle(update.getChosenInlineQuery());
            case CALL_BACK_QUERY:
                return handlerContext.getCallBackQueryHandler().handle(update.getCallbackQuery());
            case EDITED_MESSAGE:
                return handlerContext.getEditedMessageHandler().handle(update.getEditedMessage());
            case CHANNEL_POST:
                return handlerContext.getChannelPostHandler().handle(update.getChannelPost());
            case EDITED_CHANNEL_POST:
                return handlerContext.getEditedChannelPostHandler().handle(update.getEditedChannelPost());
            case SHIPPING_QUERY:
                return handlerContext.getShippingQueryHandler().handle(update.getShippingQuery());
            case PRE_CHECKOUT_QUERY:
                return handlerContext.getPreCheckoutQueryHandler().handle(update.getPreCheckoutQuery());
            case POLL:
                return handlerContext.getPollHandler().handle(update.getPoll());
            case POLL_ANSWER:
                return handlerContext.getPollAnswerHandler().handle(update.getPollAnswer());
            default:
                throw new IllegalStateException("Не найден маршрут обработки объекта типа " + updateEntity);
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
