package ru.geekbrains.jdk.chat.server;

import ru.geekbrains.jdk.chat.client.Client;

import java.util.List;

public interface Server {

    /**
     * Возвращает историю сообщений
     * @return список сообщений
     */
    List<String> getHistory();

    /**
     * Получает и обрабатывает сообщение
     * @param message сообщение
     */
    void receiveMessage(String message);

    /**
     * Добавляет к чату клиента
     * @param client Client
     * @return результат операции
     */
    boolean connectUser(Client client);

    /**
     * Удаляет из чата клиента
     * @param client Client
     */
    void disconnectUser(Client client);

    /**
     * Запускает сервер
     */
    void startServer();

    /**
     * Останавливает сервер
      */
    void stopServer();

    /**
     * Проверяет запущен ли сервер
     * @return результат проверки
     */
    boolean status();
}
