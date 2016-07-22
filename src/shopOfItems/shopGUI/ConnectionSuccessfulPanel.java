package shopOfItems.shopGUI;

import javax.swing.*;
import java.awt.*;

public class ConnectionSuccessfulPanel extends JPanel{

    public ConnectionSuccessfulPanel() {
        setLayout(new GridBagLayout());

        JLabel connectionLabel = new JLabel("Connection Successful");
        JLabel canWorkLabel = new JLabel("You can work now");

        add(connectionLabel, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0, 29, 20, 0), 0, 0));
        add(canWorkLabel, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0, 42, 100, 0), 0, 0));
    }
}
