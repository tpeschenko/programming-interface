package ru.geekbrains.jdk.chat.db;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DataControllerDefault implements DataController {

    private List<String> messages;
    private IOController ioController;
    private File logFile;

    /**
     * Constructor
     */
    public DataControllerDefault() {
        messages = new ArrayList<>();
        ioController = new IOControllerDefault();
        logFile = new File("./log.txt");
        loadMessages();

    }
    /**
     * Возвращает историю сообщений
     *
     * @return список сообщений
     */
    @Override
    public List<String> getAll() {
        return messages;
    }

    /**
     * Добавляет к истории сообщение
     *
     * @param message сообщение
     */
    @Override
    public void addMessage(String message) {
        messages.add(message);
    }

    /**
     * Сохраняет изменения
     */
    @Override
    public void saveChanges() {
        ioController.writeFile(messages, logFile);
    }

    /**
     * Обнавляет чат
     */
    private void loadMessages() {
        if (logFile.exists()) {
            messages = ioController.readFile(logFile);
        }
        }

}
