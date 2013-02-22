package delicious.pos.ui.components.extensions;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import delicious.pos.App;
import delicious.pos.util.ImageLoader;

public class UIInfoDialog extends JDialog implements ActionListener {
   public UIInfoDialog(String msg, String closeBtnLabel) {
      super();
      
      ImageIcon myIcon = new ImageIcon(this.getClass().getResource("../icons/kostasicous-success.png"));

      getContentPane().setLayout(new BorderLayout());

      UILabel centerLabel = new UILabel(myIcon);
      UILabel name = new UILabel(msg);
      UIButton closeBtn = new UIButton(closeBtnLabel); 
      closeBtn.addActionListener((ActionListener) this);
      
      name.setFont(new Font("Palatino", Font.BOLD, 18));

      getContentPane().add(centerLabel, BorderLayout.CENTER);
      getContentPane().add(name, BorderLayout.NORTH);
      getContentPane().add(closeBtn, BorderLayout.SOUTH);
      
      setSize(new Dimension(400, 300));
      Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
      this.setLocation((dim.width/2) - (this.getSize().width/2), (dim.height/2) - (this.getSize().height/2));
      
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      
      pack();
      setVisible(true);
   }
   
   public void actionPerformed(ActionEvent e) {
	   setVisible(false); 
	   dispose(); 
   }
}