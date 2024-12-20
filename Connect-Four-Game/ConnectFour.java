import javax.swing.*;

public class ConnectFour {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ConnectFourGUI gameGUI = new ConnectFourGUI();
        });
    }
}
