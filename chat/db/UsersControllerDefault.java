package ru.geekbrains.jdk.chat.db;

import ru.geekbrains.jdk.chat.client.Client;

import java.util.ArrayList;
import java.util.List;

public class UsersControllerDefault implements UsersController{

    private List<Client> users;

    /**
     * Constructor
     */
    public UsersControllerDefault() {
        users = new ArrayList<>();
    }

    /**
     * Возвращает список участников чата
     *
     * @return список клиентов
     */
    @Override
    public List<Client> getAll() {
        return users;
    }

    /**
     * Добавляет участника чата
     *
     * @param client Client
     */
    @Override
    public void addUser(Client client) {
        users.add(client);
    }

    /**
     * Возвращает участника чата по индексу
     *
     * @param index индекс
     * @return Client
     */
    @Override
    public Client getUser(int index) {
        if (index >= 0 && index < users.size() ) {
            return users.get(index);
        }
        return null;
    }

    /**
     * Удаляет участника чата
     *
     * @param client Client
     */
    @Override
    public void removeUser(Client client) {
        if (!users.isEmpty()) {
            try {
                users.remove(client);
            } catch (RuntimeException e) {
                System.out.println(client.getLogin() + "нет в списке клиентов");
            }
        }

    }
}
