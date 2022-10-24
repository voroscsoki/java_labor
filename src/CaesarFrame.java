import javax.swing.*;
import java.awt.*;

public class CaesarFrame extends JFrame {
    private final JTextField upperTf = new JTextField(20);
    private final JTextField lowerTf = new JTextField(20);
    private final JPanel upperJp = new JPanel();
    private final JPanel lowerJp = new JPanel();
    private final JButton confirmButton = new JButton("Code!");
    private final JComboBox<Object> selector;

    public CaesarFrame(){
        Object[] chars = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        selector = new JComboBox<>(chars);

        upperJp.add(selector);
        upperJp.add(upperTf);
        upperJp.add(confirmButton);

        lowerJp.add(new JLabel("Output: "));
        lowerTf.setFocusable(false);
        lowerJp.add(lowerTf);

        this.setTitle("SwingLab");
        this.setSize(400, 110);
        this.setResizable(true);
        this.add(upperJp, BorderLayout.NORTH);
        this.add(lowerJp, BorderLayout.SOUTH);


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
