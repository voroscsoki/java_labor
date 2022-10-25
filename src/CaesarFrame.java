import org.w3c.dom.Text;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;

public class CaesarFrame extends JFrame {
    private final JTextField upperTf = new JTextField(20);
    private final JTextField lowerTf = new JTextField(20);
    private final JPanel upperJp = new JPanel();
    private final JPanel lowerJp = new JPanel();
    private final JButton confirmButton = new JButton("Code!");
    private final JComboBox<Object> selector;
    private boolean encoding = true;

    public CaesarFrame(){
        Object[] chars = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        selector = new JComboBox<>(chars);

        upperTf.getDocument().addDocumentListener(new InputFieldKeyListener());
        upperTf.addFocusListener(new TextFocusListener(true));
        lowerTf.addFocusListener(new TextFocusListener(false));
        confirmButton.addActionListener(new OkButtonActionListener());
        upperJp.add(selector);
        upperJp.add(upperTf);
        upperJp.add(confirmButton);

        lowerJp.add(new JLabel("Output: "));
        lowerJp.add(lowerTf);

        this.setTitle("SwingLab");
        this.setSize(400, 110);
        this.setResizable(true);
        this.add(upperJp, BorderLayout.NORTH);
        this.add(lowerJp, BorderLayout.SOUTH);


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    private static String caesarDeCode(String input, int offset){
        return caesarCode(input, -offset);
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

    class TextFocusListener implements FocusListener{
        private boolean onFocus;
        public TextFocusListener(boolean b){
            onFocus = b;
        }
        @Override
        public void focusGained(FocusEvent e) {
            encoding = onFocus;
        }

        @Override
        public void focusLost(FocusEvent e) {
            //do nothing
        }
    }

    class OkButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(encoding){
                lowerTf.setText(caesarCode(upperTf.getText(), (char) selector.getSelectedItem() - 'A')); //casting here is fine

            }
            else{
                upperTf.setText(caesarDeCode(lowerTf.getText(), (char) selector.getSelectedItem() - 'A')); //casting here is fine

            }
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
