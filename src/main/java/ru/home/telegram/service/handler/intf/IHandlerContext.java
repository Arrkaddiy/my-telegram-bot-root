package ru.home.telegram.service.handler.intf;

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
