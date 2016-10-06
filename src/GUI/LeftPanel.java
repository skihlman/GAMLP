/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

/**
 *
 * @author sebastian
 */
public class LeftPanel extends JPanel {
    
    
    public LeftPanel() {
        super();
        //this.setPreferredSize(new Dimension(300, 0));
        this.setMinimumSize(new Dimension(200, 0));
        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
    }
}
