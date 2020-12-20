package ru.home.telegram.service.hadler.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.payments.ShippingQuery;
import ru.home.telegram.service.hadler.intf.IShippingQueryHandler;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class ShippingQueryHandler implements IShippingQueryHandler {
    //Логгер
    private static final Logger LOGGER = LoggerFactory.getLogger(ShippingQueryHandler.class);

    @Override
    public BotApiMethod<?> handle(ShippingQuery shippingQuery) {
        LOGGER.info("Обработка события ShippingQuery, объект ShippingQuery: {}", shippingQuery);
        return null;
    }

    @PostConstruct
    private void initBean() {
        //Данный метод выполняется после инициализации бина
        LOGGER.info("ShippingQueryHandler initialization");
    }

    @PreDestroy
    private void destroyBean() {
        //Данный метод выполняется перед удалением бина
        LOGGER.info("ShippingQueryHandler destroy");
    }
}
