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

/**
 *
 * @author sebastian
 */
public class BottomPanel extends JPanel{
    
    public BottomPanel() {
        super();
        this.setPreferredSize(new Dimension(0, 100));
        this.setMinimumSize(new Dimension(0, 100));
        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
    }
}
