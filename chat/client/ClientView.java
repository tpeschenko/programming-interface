package ru.geekbrains.jdk.chat.client;

public interface ClientView {

    /**
     * Добавляет сообщение на UI
     * @param message сообщение
     */
    void showOnWindow(String message);

    /**
     * Устанавливает доступность UI
     * @param status boolean
     */
    void setStatus(boolean status);
}
