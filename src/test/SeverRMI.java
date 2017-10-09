/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Lan
 */
public class SeverRMI {

    private JTextArea textArea;

    public SeverRMI() {
        JFrame frame = new JFrame("Sever RMI");
        JPanel panel = new JPanel();
        frame.setLayout(null);
        panel.setLayout(null);
        textArea = new JTextArea();
        JButton start = new JButton("Start");
        JButton stop = new JButton("Stop");
        JButton exit = new JButton("Exit");

        frame.setSize(400, 400);
        textArea.setBounds(0, 0, 400, 250);
        panel.setBounds(0, 250, 400, 150);
        start.setBounds(80, 50, 70, 30);
        stop.setBounds(160, 50, 70, 30);
        exit.setBounds(240, 50, 70, 30);

        start.addActionListener(new Start());
        stop.addActionListener(new Stop());
        exit.addActionListener(new Exit());
        

        panel.add(stop);
        panel.add(start);
        panel.add(exit);
        frame.add(panel);

        frame.add(textArea);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    class Start implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {
               LocateRegistry.createRegistry(5555);
                MethodFromFar methor = new MethodFromFar();
                Naming.rebind("rmi://localhost:5555/cong", methor);
                textArea.setText("Sever runing ...");
            } catch (RemoteException | MalformedURLException ex) {
                Logger.getLogger(SeverRMI.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    class Stop implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
             
             
              
             
            
             
             
           textArea.setText("Sever not run:");
        }
    
    
    
    
    }
    class Exit implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
         System.exit(0);
        }
    }

 
}
