package ru.home.telegram.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;

import java.util.Arrays;

@AllArgsConstructor
public enum MessageEntityType {

    BOLD("bold"),
    BOT_COMMAND("bot_command"),
    CASH_TAG("cashtag"),
    CODE("code"),
    EMAIL("email"),
    HASH_TAG("hashtag"),
    ITALIC("italic"),
    MENTION("mention"),
    PHONE_NUMBER("phone_number"),
    PRE("pre"),
    STRIKE_THROUGH("strikethrough"),
    TEXT_LINK("text_link"),
    TEXT_MENTION("text_mention"),
    UNDERLINE("underline"),
    URL("url");

    @Getter
    private String type;

    public static MessageEntityType getMessageEntityTypeByMessageEntity(MessageEntity messageEntity) {
        return Arrays.stream(values())
                .filter(messageEntityType -> messageEntityType.getType().equalsIgnoreCase(messageEntity.getType()))
                .findFirst()
                .orElse(null);
    }
}
