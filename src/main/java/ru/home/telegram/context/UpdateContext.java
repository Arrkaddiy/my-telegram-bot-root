package ru.home.telegram.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.home.telegram.service.UserService;
import ru.home.telegram.state.facade.StateFacade;
import ru.home.telegram.update.facade.UpdateFacade;
import ru.home.telegram.update.facade.UpdateFacadeImpl;
import ru.home.telegram.update.handler.callbackquery.CallBackQueryHandler;
import ru.home.telegram.update.handler.callbackquery.CallBackQueryHandlerImpl;
import ru.home.telegram.update.handler.channelpost.ChannelPostHandler;
import ru.home.telegram.update.handler.channelpost.ChannelPostHandlerImpl;
import ru.home.telegram.update.handler.choseninlinequery.ChosenInlineQueryHandler;
import ru.home.telegram.update.handler.choseninlinequery.ChosenInlineQueryHandlerImpl;
import ru.home.telegram.update.handler.editedchannelpost.EditedChannelPostHandler;
import ru.home.telegram.update.handler.editedchannelpost.EditedChannelPostHandlerImpl;
import ru.home.telegram.update.handler.editedmessage.EditedMessageHandler;
import ru.home.telegram.update.handler.editedmessage.EditedMessageHandlerImpl;
import ru.home.telegram.update.handler.inlinequery.InlineQueryHandler;
import ru.home.telegram.update.handler.inlinequery.InlineQueryHandlerImpl;
import ru.home.telegram.update.handler.message.MessageHandler;
import ru.home.telegram.update.handler.message.MessageHandlerImpl;
import ru.home.telegram.update.handler.poll.PollHandler;
import ru.home.telegram.update.handler.poll.PollHandlerImpl;
import ru.home.telegram.update.handler.pollanswer.PollAnswerHandler;
import ru.home.telegram.update.handler.pollanswer.PollAnswerHandlerImpl;
import ru.home.telegram.update.handler.precheckoutquery.PreCheckoutQueryHandler;
import ru.home.telegram.update.handler.precheckoutquery.PreCheckoutQueryHandlerImpl;
import ru.home.telegram.update.handler.shippingquery.ShippingQueryHandler;
import ru.home.telegram.update.handler.shippingquery.ShippingQueryHandlerImpl;

/**
 * UpdateContext.
 */
@Configuration
public class UpdateContext {
    /**
     * Бин обработки контента CallBackQuery входящего запроса
     *
     * @param userService Бин серивиса для Entity User
     * @param stateFacade Бин маршрутизации состояния пользователя
     * @return {@link CallBackQueryHandler}
     */
    @Bean
    public CallBackQueryHandler callBackQueryHandler(UserService userService, StateFacade stateFacade) {
        return new CallBackQueryHandlerImpl(userService, stateFacade);
    }
    /**
     * Бин обработки контента ChannelPost входящего запроса
     *
     * @param userService Бин серивиса для Entity User
     * @param stateFacade Бин маршрутизации состояния пользователя
     * @return {@link ChannelPostHandlerImpl}
     */
    @Bean
    public ChannelPostHandler channelPostHandler(UserService userService, StateFacade stateFacade) {
        return new ChannelPostHandlerImpl(userService, stateFacade);
    }
    /**
     * Бин обработки контента ChosenInlineQuery входящего запроса
     *
     * @param userService Бин серивиса для Entity User
     * @param stateFacade Бин маршрутизации состояния пользователя
     * @return {@link ChosenInlineQueryHandlerImpl}
     */
    @Bean
    public ChosenInlineQueryHandler chosenInlineQueryHandler(UserService userService, StateFacade stateFacade) {
        return new ChosenInlineQueryHandlerImpl(userService, stateFacade);
    }
    /**
     * Бин обработки контента EditedChannelPost входящего запроса
     *
     * @param userService Бин серивиса для Entity User
     * @param stateFacade Бин маршрутизации состояния пользователя
     * @return {@link EditedChannelPostHandlerImpl}
     */
    @Bean
    public EditedChannelPostHandler editedChannelPostHandler(UserService userService, StateFacade stateFacade) {
        return new EditedChannelPostHandlerImpl(userService, stateFacade);
    }
    /**
     * Бин обработки контента EditedMessage входящего запроса
     *
     * @param userService Бин серивиса для Entity User
     * @param stateFacade Бин маршрутизации состояния пользователя
     * @return {@link EditedMessageHandlerImpl}
     */
    @Bean
    public EditedMessageHandler editedMessageHandler(UserService userService, StateFacade stateFacade) {
        return new EditedMessageHandlerImpl(userService, stateFacade);
    }
    /**
     * Бин обработки контента InlineQuery входящего запроса
     *
     * @param userService Бин серивиса для Entity User
     * @param stateFacade Бин маршрутизации состояния пользователя
     * @return {@link InlineQueryHandlerImpl}
     */
    @Bean
    public InlineQueryHandler inlineQueryHandler(UserService userService, StateFacade stateFacade) {
        return new InlineQueryHandlerImpl(userService, stateFacade);
    }
    /**
     * Бин обработки контента Message входящего запроса
     *
     * @param userService Бин серивиса для Entity User
     * @param stateFacade Бин маршрутизации состояния пользователя
     * @return {@link MessageHandlerImpl}
     */
    @Bean
    public MessageHandler messageHandler(UserService userService, StateFacade stateFacade) {
        return new MessageHandlerImpl(userService, stateFacade);
    }
    /**
     * Бин обработки контента Poll входящего запроса
     *
     * @return {@link PollHandlerImpl}
     */
    @Bean
    public PollHandler pollHandler() {
        return new PollHandlerImpl();
    }
    /**
     * Бин обработки контента PollAnswer входящего запроса
     *
     * @param userService Бин серивиса для Entity User
     * @param stateFacade Бин маршрутизации состояния пользователя
     * @return {@link PollAnswerHandlerImpl}
     */
    @Bean
    public PollAnswerHandler pollAnswerHandler(UserService userService, StateFacade stateFacade) {
        return new PollAnswerHandlerImpl(userService, stateFacade);
    }
    /**
     * Бин обработки контента PreCheckoutQuery входящего запроса
     *
     * @param userService Бин серивиса для Entity User
     * @param stateFacade Бин маршрутизации состояния пользователя
     * @return {@link PreCheckoutQueryHandlerImpl}
     */
    @Bean
    public PreCheckoutQueryHandler preCheckoutQueryHandler(UserService userService, StateFacade stateFacade) {
        return new PreCheckoutQueryHandlerImpl(userService, stateFacade);
    }
    /**
     * Бин обработки контента ShippingQuery входящего запроса
     *
     * @param userService Бин серивиса для Entity User
     * @param stateFacade Бин маршрутизации состояния пользователя
     * @return {@link ShippingQueryHandlerImpl}
     */
    @Bean
    public ShippingQueryHandler shippingQueryHandler(UserService userService, StateFacade stateFacade) {
        return new ShippingQueryHandlerImpl(userService, stateFacade);
    }
    /**
     * Бин маршрутизации контента входящего запроса
     *
     * @param messageHandler           Бин обработки контента Message входящего запроса
     * @param inlineQueryHandler       Бин обработки контента InlineQuery входящего запроса
     * @param chosenInlineQueryHandler Бин обработки контента ChosenInlineQuery входящего запроса
     * @param callBackQueryHandler     Бин обработки контента CallBackQuery входящего запроса
     * @param editedMessageHandler     Бин обработки контента EditedMessage входящего запроса
     * @param channelPostHandler       Бин обработки контента ChannelPost входящего запроса
     * @param editedChannelPostHandler Бин обработки контента EditedChannelPost входящего запроса
     * @param shippingQueryHandler     Бин обработки контента ShippingQuery входящего запроса
     * @param preCheckoutQueryHandler  Бин обработки контента PreCheckoutQuery входящего запроса
     * @param pollHandler              Бин обработки контента Poll входящего запроса
     * @param pollAnswerHandler        Бин обработки контента PollAnswer входящего запроса
     * @return {@link UpdateFacadeImpl}
     */
    @Bean
    public UpdateFacade updateFacade(MessageHandler messageHandler,
                                     InlineQueryHandler inlineQueryHandler,
                                     ChosenInlineQueryHandler chosenInlineQueryHandler,
                                     CallBackQueryHandler callBackQueryHandler,
                                     EditedMessageHandler editedMessageHandler,
                                     ChannelPostHandler channelPostHandler,
                                     EditedChannelPostHandler editedChannelPostHandler,
                                     ShippingQueryHandler shippingQueryHandler,
                                     PreCheckoutQueryHandler preCheckoutQueryHandler,
                                     PollHandler pollHandler,
                                     PollAnswerHandler pollAnswerHandler) {
        return new UpdateFacadeImpl(
                messageHandler,
                inlineQueryHandler,
                chosenInlineQueryHandler,
                callBackQueryHandler,
                editedMessageHandler,
                channelPostHandler,
                editedChannelPostHandler,
                shippingQueryHandler,
                preCheckoutQueryHandler,
                pollHandler,
                pollAnswerHandler);
    }
}
