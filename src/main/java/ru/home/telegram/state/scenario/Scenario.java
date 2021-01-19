package ru.home.telegram.state.scenario;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.telegram.constant.BotCommand;
import ru.home.telegram.db.entity.User;

import java.util.EnumMap;
import java.util.function.BiFunction;

public interface Scenario {

    EnumMap<BotCommand, BiFunction<User, Message, BotApiMethod<?>>> getScenarioMap();
}
