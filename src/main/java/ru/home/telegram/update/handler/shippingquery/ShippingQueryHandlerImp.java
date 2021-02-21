package ru.home.telegram.update.handler.shippingquery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.payments.ShippingQuery;

@Component
@Qualifier(value = "shippingQueryHandler")
public class ShippingQueryHandlerImp implements ShippingQueryHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShippingQueryHandlerImp.class);

    @Override
    public BotApiMethod<?> handle(ShippingQuery shippingQuery) {
        LOGGER.info("Обработка события ShippingQuery, объект ShippingQuery: {}", shippingQuery);
        return null;
    }
}
