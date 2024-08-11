package ru.geekbrains.jdk.chat.client;

import ru.geekbrains.jdk.chat.server.Server;

public class ClientDefault implements Client{

    private String login;
    private Server server;
    private ClientView view;

    public ClientDefault(Server server, ClientView view) {
        this.server = server;
        this.view = view;
    }
    /**
     * Слушает сервер
     */
    @Override
    public void answerFromServer(String message) {
        view.showOnWindow(message);
    }

    /**
     * Пересылает сообщение
     *
     * @param message Сообщение
     */
    @Override
    public void sendMessage(String message) {
        if (server.status()) {
            server.receiveMessage(String.format("%s: %s", login, message));
        } else {
            view.showOnWindow("Нет подключения");
            view.setStatus(false);
        }
    }

    /**
     * Устанавливает соединение с сервером
     *
     * @param login login
     * @return результат
     */
    @Override
    public boolean connectToServer(String login) {
        this.login = login;
        if (server.status()) {
            view.showOnWindow("Есть соединение с сервером.");
            server.connectUser(this);
            view.setStatus(true);
            if (!server.getHistory().isEmpty()) {
                server.getHistory().forEach(x -> view.showOnWindow(x));
            }
        } else {
            view.showOnWindow("Нет соединения с сервером");
            view.setStatus(false);
        }

        return false;
    }

    /**
     * Разрывает соединение с сервером
     */
    @Override
    public void disconnectFromServer() {
        server.disconnectUser(this);
        view.showOnWindow("Нет соединения с сервером");
        view.setStatus(false);
    }

    /**
     * Возвращает логин клиента
     *
     * @return String
     */
    @Override
    public String getLogin() {
        return login;
    }
}
