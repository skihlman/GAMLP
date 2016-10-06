/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 *
 * @author sebastian
 */
public class MainPanel extends JPanel {
    
    public MainPanel() {
        super();
        this.setLayout(new GridBagLayout());
        this.setMinimumSize(new Dimension(300, 300));
        
        this.setPreferredSize(new Dimension(600, 300));
        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
    }
}
