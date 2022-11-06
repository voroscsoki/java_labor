package swingmvclab;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/*
 * A hallgat�k adatait t�rol� oszt�ly.
 */
public class StudentData extends AbstractTableModel {

    /*
     * Ez a tagv�ltoz� t�rolja a hallgat�i adatokat.
     * NE M�DOS�TSD!
     */
    List<Student> students = new ArrayList<>();

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

    @Override
    public String getColumnName(int columnIndex){
        return switch (columnIndex){
            case 0 -> "N�v";
            case 1 -> "Neptun";
            case 2 -> "Al��r�s";
            default -> "Jegy";
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex){
        return switch (columnIndex){
            case 2 -> Boolean.class;
            case 3 -> Integer.class;
            default -> String.class;
        };
    }
}
