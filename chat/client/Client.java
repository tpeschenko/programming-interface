package ru.geekbrains.jdk.chat.client;

public interface Client {

    /**
     * Слушает сервер
     */
    void answerFromServer(String message);

    /**
     * Пересылает сообщение
     * @param message Сообщение
     */
    void sendMessage(String message);

    /**
     * Устанавливает соединение с сервером
     * @param login
     * @return
     */
    boolean connectToServer(String login);

    /**
     * Разрывает соединение с сервером
     */
    void disconnectFromServer();

    /**
     * Возвращает логин клиента
     * @return String
     */
    String getLogin();

}
