package ru.home.telegram.state.scenario;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.telegram.constant.BotCommand;
import ru.home.telegram.db.entity.User;

import java.util.EnumMap;
import java.util.function.BiFunction;

public class ScenarioImp implements Scenario {
    @Getter
    private EnumMap<BotCommand, BiFunction<User, Message, BotApiMethod<?>>> scenarioMap = new EnumMap<>(BotCommand.class);

    public ScenarioImp onCommand(BotCommand botCommand, BiFunction<User, Message, BotApiMethod<?>> function) {
        scenarioMap.put(botCommand, function);
        return this;
    }
}
