package mattresssalesapp;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MattressSalesGUI extends JFrame {

    private JTextField txtCustomerName;
    private JComboBox<String> cmbProduct;
    private JTextField txtPrice;
    private JTextField txtVat;
    private JTextField txtCommission;
    private JTextField txtTotal;

    public MattressSalesGUI() {
        setTitle("Mattress Sales Application - Question 2");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Setup Menu Bar (File -> Exit, Tools -> Process, Clear)
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        JMenuItem itemExit = new JMenuItem("Exit");
        itemExit.addActionListener(e -> System.exit(0));
        menuFile.add(itemExit);

        JMenu menuTools = new JMenu("Tools");
        JMenuItem itemProcess = new JMenuItem("Process");
        itemProcess.addActionListener(e -> processSale());
        JMenuItem itemClear = new JMenuItem("Clear");
        itemClear.addActionListener(e -> clearFields());
        menuTools.add(itemProcess);
        menuTools.add(itemClear);

        menuBar.add(menuFile);
        menuBar.add(menuTools);
        setJMenuBar(menuBar);

        // Setup Form Layout
        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        panel.add(new JLabel("Customer Name:"));
        txtCustomerName = new JTextField();
        panel.add(txtCustomerName);

        panel.add(new JLabel("Product Name:"));
        String[] products = {"Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"};
        cmbProduct = new JComboBox<>(products);
        panel.add(cmbProduct);

        panel.add(new JLabel("Product Price (R):"));
        txtPrice = new JTextField();
        panel.add(txtPrice);

        panel.add(new JLabel("VAT (15%):"));
        txtVat = new JTextField();
        txtVat.setEditable(false);
        panel.add(txtVat);

        panel.add(new JLabel("Commission (5%):"));
        txtCommission = new JTextField();
        txtCommission.setEditable(false);
        panel.add(txtCommission);

        panel.add(new JLabel("Total Amount (R):"));
        txtTotal = new JTextField();
        txtTotal.setEditable(false);
        panel.add(txtTotal);

        JButton btnProcess = new JButton("Process & Save");
        btnProcess.addActionListener(e -> processSale());
        panel.add(btnProcess);

        JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(e -> clearFields());
        panel.add(btnClear);

        add(panel);
    }

    private void processSale() {
        try {
            String customer = txtCustomerName.getText().trim();
            String product = (String) cmbProduct.getSelectedItem();

            if (customer.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter customer name.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double price = Double.parseDouble(txtPrice.getText().trim());

            ProductCalculations calc = new ProductCalculations(price);
            txtVat.setText(String.format("%.2f", calc.GetVat()));
            txtCommission.setText(String.format("%.2f", calc.GetCommission()));
            txtTotal.setText(String.format("%.2f", calc.GetTotal()));

            // Save data to data.txt
            try (PrintWriter writer = new PrintWriter(new FileWriter("data.txt", true))) {
                writer.println("Customer: " + customer + " | Product: " + product + " | Price: " + price + " | Total: " + String.format("%.2f", calc.GetTotal()));
                JOptionPane.showMessageDialog(this, "Sale processed and saved to data.txt successfully!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving to file: " + ex.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric price.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        txtCustomerName.setText("");
        txtPrice.setText("");
        txtVat.setText("");
        txtCommission.setText("");
        txtTotal.setText("");
        cmbProduct.setSelectedIndex(0);
        txtCustomerName.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MattressSalesGUI().setVisible(true);
        });
    }
}
