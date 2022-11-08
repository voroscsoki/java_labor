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
    List<Student> students = new ArrayList<>();

    public void addStudent(String name, String neptun) {
        students.add(new Student(name, neptun, false, 0));
        fireTableDataChanged();
    }

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
            case 0 -> "Név";
            case 1 -> "Neptun";
            case 2 -> "Aláírás";
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

    @Override
    public boolean isCellEditable(int row, int col){
        return col >= 2;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 2 -> students.get(rowIndex).setSignature((Boolean) aValue);
            case 3 -> students.get(rowIndex).setGrade((Integer) aValue);
        }
    }
}
