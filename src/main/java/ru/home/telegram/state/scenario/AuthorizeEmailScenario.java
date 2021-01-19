package ru.home.telegram.state.scenario;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.home.telegram.constant.BotCommand;
import ru.home.telegram.state.intf.AuthorizationEmailBotState;
import ru.home.telegram.state.intf.AuthorizationPhoneBotState;
import ru.home.telegram.state.intf.StartBotState;

import javax.annotation.PostConstruct;

@Component
public class AuthorizeEmailScenario implements StateScenario {

    @Setter(onMethod_ = {@Autowired})
    private StartBotState startBotState;
    @Setter(onMethod_ = {@Autowired})
    private AuthorizationPhoneBotState authorizationPhoneBotState;
    @Setter(onMethod_ = {@Autowired})
    private AuthorizationEmailBotState authorizationEmailBotState;
    @Getter
    private Scenario scenario;

    @PostConstruct
    private void init() {
        scenario = new ScenarioImp()
                .onCommand(BotCommand.START, startBotState::execute)
                .onCommand(BotCommand.BACK, authorizationPhoneBotState::execute)
                .onCommand(BotCommand.HELP, startBotState::execute)
                .onCommand(BotCommand.NONE, authorizationEmailBotState::execute);
    }
}