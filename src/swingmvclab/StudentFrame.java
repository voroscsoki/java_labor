package swingmvclab;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/*
 * A megjelenítendõ ablakunk osztálya.
 */
public class StudentFrame extends JFrame {
    
    /*
     * Ebben az objektumban vannak a hallgatói adatok.
     * A program indulás után betölti az adatokat fájlból, bezáráskor pedig kimenti oda.
     * 
     * NE MÓDOSÍTSD!
     */
    private StudentData data;
    private final JTextField nameField = new JTextField(20);
    private final JTextField neptunField = new JTextField(6);
    /*
     * Itt hozzuk létre és adjuk hozzá az ablakunkhoz a különbözõ komponenseket
     * (táblázat, beviteli mezõ, gomb).
     */
    private void initComponents() {
        this.setLayout(new BorderLayout());
        JTable table = new JTable(data);

        JPanel bottomPanel = new JPanel();
        JLabel label1 = new JLabel("Név:");
        JLabel label2 = new JLabel("Neptun:");
        JButton confirmButton = new JButton("Felvesz");
        confirmButton.addActionListener(e -> data.addStudent(nameField.getText(), neptunField.getText()));

        bottomPanel.add(label1);
        bottomPanel.add(nameField);
        bottomPanel.add(label2);
        bottomPanel.add(neptunField);
        bottomPanel.add(confirmButton);

        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        table.setDefaultRenderer(String.class, new StudentTableCellRenderer(table.getDefaultRenderer(String.class)));
        table.setDefaultRenderer(Boolean.class, new StudentTableCellRenderer(table.getDefaultRenderer(Boolean.class)));
        table.setDefaultRenderer(Integer.class, new StudentTableCellRenderer(table.getDefaultRenderer(Integer.class)));

        this.add(bottomPanel, BorderLayout.SOUTH);
        this.add(new JScrollPane(table), BorderLayout.CENTER);
    }

    /*
     * Az ablak konstruktora.
     * 
     * NE MÓDOSÍTSD!
     */
    @SuppressWarnings("unchecked")
    public StudentFrame() {
        super("Hallgatói nyilvántartás");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // Induláskor betöltjük az adatokat
        try {
            data = new StudentData();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("students.dat"));
            data.students = (List<Student>)ois.readObject();
            ois.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        // Bezáráskor mentjük az adatokat
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students.dat"));
                    oos.writeObject(data.students);
                    oos.close();
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Felépítjük az ablakot
        setMinimumSize(new Dimension(500, 200));
        initComponents();
    }

    class StudentTableCellRenderer implements TableCellRenderer {
        private final TableCellRenderer renderer;

        public StudentTableCellRenderer(TableCellRenderer defRenderer) {
            this.renderer = defRenderer;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = renderer.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, column);
            Student currentStudent = data.students.get(table.getRowSorter().convertRowIndexToModel(row));
            component.setBackground((currentStudent.getGrade() > 1 && currentStudent.hasSignature()) ? Color.GREEN : Color.RED);
            return component;
        }
    }

    /*
     * A program belépési pontja.
     * 
     * NE MÓDOSÍTSD!
     */
    public static void main(String[] args) {
        // Megjelenítjük az ablakot
        StudentFrame sf = new StudentFrame();
        sf.setVisible(true);
    }
}
