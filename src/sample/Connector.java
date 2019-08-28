package sample;

import javafx.scene.control.Control;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Connector implements Runnable {

    Socket socket;
    DataOutputStream out;
    DataInputStream in;

    String host = "localhost";
    int port = 1337;

    Controller controller;

    public Connector(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void run() {

        try {
            socket = new Socket(host, port);
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());

            while (true) {

                controller.chatHistoryText.appendText(in.readUTF() + "\n");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void send(String name, String text) {

        try {
            out.writeUTF(name + ": " + text);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
