package ru.home.telegram.controller.intf;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Интерфейс Rest-контроллера запросов от Telegram Bot
 */

public interface IRestController {

    /**
     * Обработка входящего запроса от Telegram Bot
     * <p>
     * Нам необходимо отловить запрос, для это опишем необходимый нам метод контроллера
     * Анатация @PostMapping говорит о том, что данный метод контроллера будет отлавливать исключительно Post-запросы
     * Анатация @RequestBody преобразует тело входящего сообщения(JSON) в ожидаемый нами объект
     * Так как мы знаем, что на вход должен прийти JSON объекта {@link Update}, то он и будет входным параметром
     * </p>
     *
     * @param update - Входящий запрос от Telegram {@link Update}
     */
    @PostMapping
    void onUpdateReceived(@RequestBody Update update);
}
