package ru.home.telegram.service.hadler;

import ru.home.telegram.service.hadler.intf.*;

public interface IHandlerContext {

    IMessageHandler getMessageHandler();

    IInlineQueryHandler getInlineQueryHandler();

    IChosenInlineQueryHandler getChosenInlineQueryHandler();

    ICallBackQueryHandler getCallBackQueryHandler();

    IEditedMessageHandler getEditedMessageHandler();

    IChannelPostHandler getChannelPostHandler();

    IEditedChannelPostHandler getEditedChannelPostHandler();

    IShippingQueryHandler getShippingQueryHandler();

    IPreCheckoutQueryHandler getPreCheckoutQueryHandler();

    IPollHandler getPollHandler();

    IPollAnswerHandler getPollAnswerHandler();
}
