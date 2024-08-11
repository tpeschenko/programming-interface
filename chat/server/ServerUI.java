package ru.geekbrains.jdk.chat.server;

import javax.swing.*;
import java.awt.*;

public class ServerUI extends JFrame implements ServerView{

    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private JTextArea taMessages;
    private JButton btnStart, btnStop;
    private Server server;

    /**
     * Constructor
     */
    public ServerUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setTitle("Server is stopped");
        setResizable(false);
        taMessages = new JTextArea();
        server = new ServerDefault(this);

        createPanel();
        setVisible(true);
    }

    /**
     * Creates content panel
     */
    private void createPanel() {
        add(createPanelAdmin(), BorderLayout.SOUTH);
        JScrollPane scrollPane = new JScrollPane(taMessages);
        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Creates pnlAdmin
     * @return Component
     */
    private Component createPanelAdmin() {
        JPanel pnlAdmin = new JPanel(new GridLayout(1, 2));

        btnStart = new JButton("Start");
        btnStart.addActionListener(e -> startServer());

        btnStop = new JButton("Stop");
        btnStop.addActionListener(e -> stopServer());

        pnlAdmin.add(btnStart);
        pnlAdmin.add(btnStop);

        return pnlAdmin;
    }


    /**
     * Запускает сервер чата
     */
    public void startServer() {
        server.startServer();
        this.setTitle("Server is running");
    }

    /**
     * Останавливает сервер
     */
    public void stopServer() {
        server.stopServer();
        this.setTitle("Server is stopped");

    }

    /**
     * Добавляет сообщение на UI
     */
    @Override
    public void showOnWindow(String message) {
        taMessages.append(message);
        taMessages.append("\n");
    }

    public Server getServer() {
        return server;
    }
}
