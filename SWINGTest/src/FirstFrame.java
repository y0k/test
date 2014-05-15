
import java.net.DatagramSocket;
import java.net.SocketException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.awt.event.*;

public class FirstFrame extends JFrame implements ActionListener{

    public void actionPerformed(ActionEvent ev){
        new MyFrame().setVisible(true);


    }

    public static void main(String[] args) {
		FirstFrame fr = new FirstFrame();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        fr.setBackground(Color.YELLOW);
        fr.setSize(new Dimension(320, 380));
        fr.setLocation(600, 250);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton Browse1 = new JButton("SOURCE_DIR");
        JButton Browse2 = new JButton("FINAL_DIR");
        JButton Start = new JButton("START");
        JPanel buttonsPannel = new JPanel(new FlowLayout());
        fr.add(Browse1, BorderLayout.NORTH);
        fr.add(Browse2, BorderLayout.SOUTH);
        fr.add(Start, BorderLayout.CENTER);
        Browse1.addActionListener(fr);
        Start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {



            }
        });
        fr.setVisible(true);
    }
}
































