package ru.geekbrains.jdk.chat.db;

import java.util.List;

public interface DataController {

    /**
     * Возвращает историю сообщений
     * @return список сообщений
     */
    List<String> getAll();

    /**
     * Добавляет к истории сообщение
     * @param message сообщение
     */
    void addMessage(String message);

    /**
     * Сохраняет изменения
     */
    void saveChanges();
}
