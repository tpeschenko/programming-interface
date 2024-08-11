package ru.geekbrains.jdk.chat;

import ru.geekbrains.jdk.chat.client.ClientUI;
import ru.geekbrains.jdk.chat.server.Server;
import ru.geekbrains.jdk.chat.server.ServerUI;

public class Main {
    public static void main(String[] args) {
        ServerUI serverUI = new ServerUI();
        new ClientUI(serverUI);
        new ClientUI(serverUI);
    }
}
