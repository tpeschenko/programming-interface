package ru.geekbrains.jdk.chat.server;

public interface ServerView {

    /**
     * Запускает сервер чата
     */
    void startServer();

    /**
     * Останавливает сервер
     */
    void stopServer();

    /**
     * Добавляет сообщение на UI
     */
    void showOnWindow(String message);

    }
