package by.benikov.citybookbot.citybookbot.bot;

import by.benikov.citybookbot.citybookbot.entity.City;
import by.benikov.citybookbot.citybookbot.service.CityService;
import by.benikov.citybookbot.citybookbot.utils.ResponseBuild;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@PropertySource("classpath:telegram.properties")
public class TelegramBot extends TelegramLongPollingBot {

    @Autowired
    public CityService cityService;

    @Value("CityBookBot")
    private String botName;

    @Value("1067789389:AAE1otvH9HIS7wjA9Muj5i-cuoYP_FijoW8")
    private String botToken;

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        streamMessage(update);
        Message message = update.getMessage();
        if(message != null && message.hasText()){
            switch (message.getText()) {
                case "/start":
                    sendMsg(message, "Введите город по котору хотите посмотреть информацию: ");
                    break;
                default:
                    if (isAvailable(message.getText())) {
                        try {
                            City city = cityService.getCityByName(message.getText());
                            sendMsg(message, ResponseBuild.getMessage(city));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else{
                        sendMsg(message, "К сожалению у нас нет информации по этому городу.");
                    }
            }
        }
    }

    private void sendMsg(Message message, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(s);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void streamMessage(Update update){
        System.out.println(update.getUpdateId()+ " " + update.getMessage().getText());
    }

    private boolean isAvailable(String cityName){
        City city = cityService.getCityByName(cityName);
        if (city == null) return false;
        return true;
    }
}
