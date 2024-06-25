package view;

import core.Helper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Layout extends JFrame {
    private ImageIcon icon;

    public void guiInitilaze(int width, int height) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Kapatma düğmesine basıldığında pencerenin kapatılmasını sağlar.
        this.setTitle("Tourism Agency System"); // Pencerenin başlığını "Tourism Agency System" olarak ayarlar.
        this.setSize(width, height); // Pencerenin boyutunu 600x500 piksel olarak ayarlar.
        this.setLocation(Helper.getLocationPoint("x", this.getSize()), Helper.getLocationPoint("y", this.getSize())); //Pencerenin ekranın ortasına yerleştirilmesini sağlar (Halper sınıfından getLocationPoint metodunu kullanır)
        this.setVisible(true); //LoginView sınıfından bir nesne oluşturulduğunda pencerenin görünür olmasını sağlar.

        //İcon değiştirme
        try {
            URL url = new URL("https://hodrimeydan.net/wp-content/sabai/File/thumbnails/7e1b9c264ed5995d8c5603b6d42ff538.png");
            icon = new ImageIcon(url); // İkonu yükleme

            this.setIconImage(icon.getImage()); // JFrame'in ikonunu ayarlama
        }catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    //tablo oluşturan metod
    public void createTable(DefaultTableModel model, JTable table, Object[] columns, ArrayList<Object[]> rows) {
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.getColumnModel().getColumn(0).setMaxWidth(75);
        table.setEnabled(false);

        DefaultTableModel clearModel = (DefaultTableModel) table.getModel();
        clearModel.setRowCount(0);

        if (rows == null) {
            rows = new ArrayList<>();
        }
        for (Object[] row : rows) {
            model.addRow(row);
        }
    }



    public int getTableSelectedRow(JTable table, int index) {
        return Integer.parseInt(table.getValueAt(table.getSelectedRow(), index).toString());
    }

    //Overloading
    public void tableRowSelect(JTable table) {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selected_row = table.rowAtPoint(e.getPoint());
                table.setRowSelectionInterval(selected_row, selected_row);
            }
        });
    }


}
