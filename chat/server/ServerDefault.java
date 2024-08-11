package ru.geekbrains.jdk.chat.server;

import ru.geekbrains.jdk.chat.client.Client;
import ru.geekbrains.jdk.chat.db.DataController;
import ru.geekbrains.jdk.chat.db.DataControllerDefault;
import ru.geekbrains.jdk.chat.db.UsersController;
import ru.geekbrains.jdk.chat.db.UsersControllerDefault;

import java.util.List;

public class ServerDefault implements Server{

    private UsersController users;
    private DataController messages;
    private ServerView view;
    private boolean status;

    /**
     * Constructor
     */
    public ServerDefault(ServerView view) {
        this.view = view;
        status =false;
        messages = new DataControllerDefault();
        users = new UsersControllerDefault();
    }

    /**
     * Возвращает историю сообщений
     *
     * @return список сообщений
     */
    @Override
    public List<String> getHistory() {
        return messages.getAll();
    }

    /**
     * Получает и обрабатывает сообщение
     *
     * @param message сообщение
     */
    @Override
    public void receiveMessage(String message) {
        broadcastMessage(message);
        view.showOnWindow(message);
        messages.addMessage(message);
        messages.saveChanges();
    }

    /**
     * Рассылает сообщение всем участникам чата
     * @param message сообщение
     */
    private void broadcastMessage(String message) {
        List<Client> list = users.getAll();
        if (!list.isEmpty()) {
            list.forEach(x -> x.answerFromServer(message));
        }
    }

    /**
     * Добавляет к чату клиента
     *
     * @param client Client
     * @return результат операции
     */
    @Override
    public boolean connectUser(Client client) {
        if (status) {
            users.addUser(client);
            view.showOnWindow(String.format("Server: %s присоединился к чату", client.getLogin()));
            broadcastMessage(String.format("Server: %s присоединился к чату", client.getLogin()));
            return true;
        } else {
            return false;
        }
    }

    /**
     * Удаляет из чата клиента
     *
     * @param client Client
     */
    @Override
    public void disconnectUser(Client client) {
        users.removeUser(client);
    }

    /**
     * Запускает сервер
     */
    @Override
    public void startServer() {
        if (!messages.getAll().isEmpty()) {
            loadMessages();
        } else {
            view.showOnWindow("История чата пуста");
        }
        view.showOnWindow("Server: Сервер запущен.");
        status = true;
    }

    /**
     * Останавливает сервер
     */
    @Override
    public void stopServer() {
        broadcastMessage("Server: Сервер остановлен.");

        while (!users.getAll().isEmpty()) {
            users.getUser(0).disconnectFromServer();
        }
        status = false;
    }

    /**
     * Проверяет запущен ли сервер
     *
     * @return результат проверки
     */
    @Override
    public boolean status() {
        return status;
    }

    /**
     * Обнавляет чат
     */
    private void loadMessages() {
        List<String> list = messages.getAll();
        for (String s : list) {
            view.showOnWindow(s);
        }
    }
}
