package ru.geekbrains.jdk.chat.client;

import ru.geekbrains.jdk.chat.server.ServerUI;

import javax.swing.*;
import java.awt.*;

public class ClientUI extends JFrame implements ClientView{


    private Client client;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private JButton btnSend, btnLogin;
    private JTextField tfLogin;
    private JTextField tfMessage;
    private String login;
    private JTextArea taMessages;

    /**
     * Constructor
     */
    public ClientUI(ServerUI server) {
        client = new ClientDefault(server.getServer(), this);
        taMessages = new JTextArea();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setTitle("Unknown user");
        setResizable(false);
        createPanel();
        setVisible(true);
    }

    /**
     * Наполняет панель верхнего уровня
     */
    private void createPanel() {
        add(createPnlLogin(), BorderLayout.NORTH);
        add(createPnlMessage(), BorderLayout.SOUTH);
        JScrollPane scrollPane = new JScrollPane(taMessages);
        add(scrollPane, BorderLayout.CENTER);

    }

    /**
     * Creates pnlLogin
     * @return Component
     */
    private Component createPnlLogin() {
        JPanel pnlLogin = new JPanel(new GridLayout(1, 2));

        tfLogin = new JTextField();
        tfLogin.addActionListener(e -> logIn());

        btnLogin = new JButton("Login");
        btnLogin.setToolTipText("Введите имя");
        btnLogin.addActionListener(e -> logIn());

        pnlLogin.add(tfLogin);
        pnlLogin.add(btnLogin);

        return pnlLogin;
    }

    /**
     * Creates pnlMessage
     * @return Component
     */
    private Component createPnlMessage() {
        JPanel pnlMessage = new JPanel(new GridLayout(1,2));

        tfMessage = new JTextField();
        tfMessage.setEnabled(false);
        tfMessage.addActionListener(e -> sendMessage());

        btnSend = new JButton("Send");
        btnSend.setToolTipText("Нажмите, чтобы отослать сообщение");
        btnSend.setEnabled(false);
        btnSend.addActionListener(e -> sendMessage());

        pnlMessage.add(tfMessage);
        pnlMessage.add(btnSend);

        return pnlMessage;
    }

    /**
     * Авторизует пользователя
     */
    private void logIn() {
        if (!tfLogin.getText().isEmpty()) {
            client.connectToServer(tfLogin.getText());
            setTitle(client.getLogin());
        }
    }

    /**
     * Отправляет сообщение
     */
    private void sendMessage() {
        if (!tfMessage.getText().isEmpty()) {
            client.sendMessage(tfMessage.getText());
        }
    }

    /**
     * Добавляет сообщение на UI
     *
     * @param message сообщение
     */
    @Override
    public void showOnWindow(String message) {
     taMessages.append(message);
        taMessages.append("\n");
    }

    /**
     * Устанавливает доступность UI
     *
     * @param status boolean
     */
    @Override
    public void setStatus(boolean status) {
        if (status) {
            tfMessage.setEnabled(true);
            btnSend.setEnabled(true);
        } else {
            tfMessage.setEnabled(false);
            btnSend.setEnabled(false);
        }
    }
}
