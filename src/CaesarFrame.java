import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("ConstantConditions")
public class CaesarFrame extends JFrame {
    private final JTextField upperTf = new JTextField(20);
    private final JTextField lowerTf = new JTextField(20);
    private final JComboBox<Object> selector;
    private boolean encoding = true;

    public CaesarFrame(){
        JButton confirmButton = new JButton("Code!");
        JPanel upperJp = new JPanel();
        JPanel lowerJp = new JPanel();


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
    private static String caesarDeCode(String input, char offset){
        return caesarCode(input, (char) ('A' - (offset - 'A')));
    }
    private static String caesarCode(String input, char offset){
        int offsetNumber = offset - 'A';
        StringBuilder sb = new StringBuilder(input.toUpperCase());
        for (int i = 0; i < sb.length(); i++){
            char current = sb.charAt(i);
            if(current != ' ') {
                current = (char) (sb.charAt(i) + offsetNumber);
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
        private final boolean onFocus;
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
                lowerTf.setText(caesarCode(upperTf.getText(), (char) selector.getSelectedItem())); //casting here is fine

            }
            else{
                upperTf.setText(caesarDeCode(lowerTf.getText(), (char) selector.getSelectedItem()));

            }
        }
    }
    class InputFieldKeyListener implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            lowerTf.setText(caesarCode(upperTf.getText(), (char) selector.getSelectedItem()));
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
