package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Controller {

    @FXML
    TextField chatText;
    @FXML
    TextArea chatHistoryText;
    @FXML
    TextField nameText;

    Connector connector;

    @FXML
    public void initialize(){
        connector = new Connector(this);
        (new Thread(connector)).start();
    }

    public void onKeyReleased(KeyEvent keyEvent) {

        if (keyEvent.getCode() == KeyCode.ENTER) {
            connector.send(nameText.getText(), chatText.getText());
            chatText.setText("");
        }
    }
}
