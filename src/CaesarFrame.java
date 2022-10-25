import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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

        confirmButton.addActionListener(new OkButtonActionListener());
        upperTf.addKeyListener(new InputFieldKeyListener());
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
    private static String caesarCode(String input, int offset){
        StringBuilder sb = new StringBuilder(input.toUpperCase());
        for (int i = 0; i < sb.length(); i++){
            char tmp = (char) (sb.charAt(i) + offset);
            while(tmp < 'A')
                tmp += 26;
            while(tmp > 'Z')
                tmp -= 26;
            sb.setCharAt(i, tmp);
        }
        return sb.toString();
    }

    class OkButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            lowerTf.setText(caesarCode(upperTf.getText(), (char) selector.getSelectedItem() - 'A')); //casting here is fine
        }
    }
    class InputFieldKeyListener extends KeyAdapter{
        @Override
        public void keyTyped(KeyEvent e) {
            lowerTf.setText(caesarCode(upperTf.getText(), (char) selector.getSelectedItem() - 'A'));
        }
    }
}
