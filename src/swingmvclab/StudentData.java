package swingmvclab;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/*
 * A hallgatók adatait tároló osztály.
 */
public class StudentData extends AbstractTableModel {

    /*
     * Ez a tagváltozó tárolja a hallgatói adatokat.
     * NE MÓDOSÍTSD!
     */
    List<Student> students = new ArrayList<Student>();

    @Override
    public int getRowCount() {
        return students.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Student student = students.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> student.getName();
            case 1 -> student.getNeptun();
            case 2 -> student.hasSignature();
            default -> student.getGrade();
        };
    }
}
