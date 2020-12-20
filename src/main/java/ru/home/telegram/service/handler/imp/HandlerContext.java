package ru.home.telegram.service.handler.imp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.home.telegram.service.handler.intf.*;

@Getter
@Component
@AllArgsConstructor
public class HandlerContext implements IHandlerContext {

    private IMessageHandler messageHandler;
    private IInlineQueryHandler inlineQueryHandler;
    private IChosenInlineQueryHandler chosenInlineQueryHandler;
    private ICallBackQueryHandler callBackQueryHandler;
    private IEditedMessageHandler editedMessageHandler;
    private IChannelPostHandler channelPostHandler;
    private IEditedChannelPostHandler editedChannelPostHandler;
    private IShippingQueryHandler shippingQueryHandler;
    private IPreCheckoutQueryHandler preCheckoutQueryHandler;
    private IPollHandler pollHandler;
    private IPollAnswerHandler pollAnswerHandler;

}
