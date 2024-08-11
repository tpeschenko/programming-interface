package ru.geekbrains.jdk.chat.db;

import ru.geekbrains.jdk.chat.client.Client;

import java.util.List;

public interface UsersController {

    /**
     * Возвращает список участников чата
     * @return список клиентов
     */
    List<Client> getAll();

    /**
     * Возвращает участника чата по индексу
     * @param index индекс
     * @return Client
     */
    Client getUser(int index);

    /**
     * Добавляет участника чата
     * @param client Client
     */
    void addUser(Client client);


    /**
     * Удаляет участника чата
     * @param client Client
     */
    void removeUser(Client client);
}
