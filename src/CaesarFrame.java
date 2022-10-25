import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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
        upperTf.getDocument().addDocumentListener(new InputFieldKeyListener());
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
            char current = sb.charAt(i);
            if(current != ' ') {
                current = (char) (sb.charAt(i) + offset);
                while (current < 'A')
                    current += 26;
                while (current > 'Z')
                    current -= 26;
            }
            sb.setCharAt(i, current);
        }
        return sb.toString();
    }

    class OkButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            lowerTf.setText(caesarCode(upperTf.getText(), (char) selector.getSelectedItem() - 'A')); //casting here is fine
        }
    }
    class InputFieldKeyListener implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            lowerTf.setText(caesarCode(upperTf.getText(), (char) selector.getSelectedItem() - 'A'));
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            this.insertUpdate(e);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            this.insertUpdate(e);
        }
    }
}
