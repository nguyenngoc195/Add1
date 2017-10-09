/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Lan
 */
public class ClientRMI {

    private JButton calu;
    private JButton exit;
    private JLabel lblKilo;
    private JLabel lblMile;
    private JTextField txtKilo;
    private JTextField txtMile;
    private JLabel lblError;

    public ClientRMI() {

        JFrame frame = new JFrame("Caculator Clien");
        JPanel panel = new JPanel();

        calu = new JButton("CAlculating");
        exit = new JButton("Exit");

        frame.setSize(400, 400);

        panel.setBounds(0, 0, 400, 400);

        lblKilo = new JLabel("Kilometers");
        lblMile = new JLabel("Miles");

        txtKilo = new JTextField();
        txtMile = new JTextField();

        lblError = new JLabel();

        lblKilo.setBounds(50, 50, 70, 30);
        lblMile.setBounds(50, 100, 70, 30);
        txtKilo.setBounds(150, 50, 200, 30);
        txtMile.setBounds(150, 100, 200, 30);
        lblError.setBounds(150, 20, 200, 20);

        calu.setBounds(160, 300, 100, 30);
        exit.setBounds(280, 300, 70, 30);

        calu.addActionListener(new Calu());
        exit.addActionListener(new Exit());

        panel.add(lblKilo);
        panel.add(lblMile);
        panel.add(txtKilo);
        panel.add(txtMile);
        panel.add(calu);
        panel.add(lblError);

        panel.add(exit);
        frame.add(panel);
        panel.setLayout(null);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    class Calu implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String kilo = txtKilo.getText();
            HashMap<String, String> errors = new Handle().validateKilo(kilo);
            int result = 99999;
            if (errors.size() == 0) {
                lblError.setText("");

                try {
                    RemoteFormFar mold = (RemoteFormFar) Naming.lookup("rmi://localhost:5555/cong");
                    float mile = mold.TinhKM(Float.parseFloat(txtKilo.getText()));
                    txtMile.setText(mile + "");
                } catch (ConnectException ex) {
                    lblError.setText("The server has't connection, You must connect server!!!");
                    lblError.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, lblError);
                } catch (NotBoundException ex) {
                    Logger.getLogger(ClientRMI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(ClientRMI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RemoteException ex) {
                    Logger.getLogger(ClientRMI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NumberFormatException ex) {
                    lblError.setText(ex.getMessage());
                    lblError.setForeground(Color.red);
                }
            } else {
                showError(errors);
            }
        }
    }

    private void showError(HashMap<String, String> errors) {
        if (errors.containsKey("txt.kilo")) {
            lblError.setForeground(Color.red);
            lblError.setText(errors.get("txt.kilo"));
        }
    }

    class Exit implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }

    }

}
