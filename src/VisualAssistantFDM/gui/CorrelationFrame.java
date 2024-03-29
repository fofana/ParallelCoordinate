/*

Copyright (c) 2001, 2002, 2003 Flo Ledermann <flo@subnet.at>

This file is part of parvis - a parallel coordiante based data visualisation
tool written in java. You find parvis and additional information on its
website at http://www.mediavirus.org/parvis.

parvis is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License, or
(at your option) any later version.

parvis is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with parvis (in the file LICENSE.txt); if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

*/

package VisualAssistantFDM.gui;

import javax.swing.*;
import VisualAssistantFDM.model.Brush;

/**
 *
 * @author  flo
 */
public class CorrelationFrame extends javax.swing.JFrame {
    
    /** The ParallelDisplay Component we are working for. */
    ParallelDisplay parent;
    
    /** the current brush */
    Brush brush = null;
    
    /** Creates new form CorrelationFrame */
    public CorrelationFrame(ParallelDisplay parent) {
        this.parent = parent;
        parent.setCorrelationFrame(this);
        
        initComponents();
        this.getContentPane().setSize(160,160);
        pack();            
    }
    
    /**
     * Initializes the two dropdown lists with the current array of axes.
     */
    public void updateAxes(){

        ((DefaultComboBoxModel)(axis1Select.getModel())).removeAllElements();
        ((DefaultComboBoxModel)(axis2Select.getModel())).removeAllElements();

        System.out.println("adding axes...");
        for (int i=0; i<parent.getNumAxes(); i++){
            ((DefaultComboBoxModel)(axis1Select.getModel())).addElement(parent.getAxisLabel(i));
            ((DefaultComboBoxModel)(axis2Select.getModel())).addElement(parent.getAxisLabel(i));
        }
        
        axis1Select.setSelectedIndex(0);
        axis2Select.setSelectedIndex(1);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        axis1Select = new javax.swing.JComboBox();
        axis2Select = new javax.swing.JComboBox();
        slider1 = new javax.swing.JSlider();
        slider2 = new javax.swing.JSlider();

        setTitle("Correlation Brush");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        axis1Select.setFont(new java.awt.Font("Dialog", 0, 10));
        axis1Select.setMinimumSize(new java.awt.Dimension(31, 20));
        axis1Select.setPreferredSize(new java.awt.Dimension(160, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(axis1Select, gridBagConstraints);

        axis2Select.setFont(new java.awt.Font("Dialog", 0, 10));
        axis2Select.setMinimumSize(new java.awt.Dimension(31, 20));
        axis2Select.setPreferredSize(new java.awt.Dimension(160, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(axis2Select, gridBagConstraints);

        slider1.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        slider1.setMinorTickSpacing(10);
        slider1.setPaintTicks(true);
        slider1.setPreferredSize(new java.awt.Dimension(160, 27));
        slider1.setOpaque(false);
        slider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                slider1StateChanged(evt);
            }
        });
        slider1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                sliderMousePressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(slider1, gridBagConstraints);

        slider2.setPreferredSize(new java.awt.Dimension(160, 27));
        slider2.setOpaque(false);
        slider2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                slider2StateChanged(evt);
            }
        });
        slider2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                sliderMousePressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(slider2, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Callback for mousedown on the sliders.
     */
    private void sliderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sliderMousePressed
        //System.out.println("new correlation brush...");
        brush = new Brush(parent.getNumRecords());
    }//GEN-LAST:event_sliderMousePressed

    /**
     * Callback for dragging of the upper slider.
     */
    private void slider2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_slider2StateChanged
        System.out.println("maxSlider: " + slider2.getValue());
        updateBrush();
    }//GEN-LAST:event_slider2StateChanged

    /**
     * Callback for dragging of the lower slider.
     */
    private void slider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_slider1StateChanged
        System.out.println("maxSlider: " + slider2.getValue());
        updateBrush();
    }//GEN-LAST:event_slider1StateChanged
 
    /**
     * Calculates the brush from the slider settings.
     */
    private void updateBrush(){
        int axis1 = axis1Select.getSelectedIndex();
        int axis2 = axis2Select.getSelectedIndex();
        
        if ((axis1 == -1) || (axis2 == -1)) return;

        if ((brush == null) || (brush.getNumValues() != parent.getNumRecords())){
            brush = new Brush(parent.getNumRecords());
        }
                
        int s1 = slider1.getValue();
        int s2 = slider2.getValue();
        float smin = (Math.min(s1,s2) - 50) / 50.0f;
        float smax = (Math.max(s1,s2) - 50) / 50.0f;
        
        for (int i=0; i<brush.getNumValues(); i++){
            float val1 = (parent.getValue(i, axis1) - parent.getAxisOffset(axis1)) / parent.getAxisScale(axis1);
            float val2 = (parent.getValue(i, axis2) - parent.getAxisOffset(axis2)) / parent.getAxisScale(axis2);
            
            float bval = 0.0f;
            if (((val1 - val2) >= smin) && ((val1 - val2) <= smax)) bval = 1.0f;

            //System.out.println("val1: " + val1 + " val2: " + val2 + " (val2-val1): " + (val2-val1) + " smin: " + smin + " smax: " + smax + " bval: " + bval);

            brush.setBrushValue(i, bval);
        }
        
        parent.setCurrentBrush(brush);
    }
    
    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
    }//GEN-LAST:event_exitForm
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox axis1Select;
    private javax.swing.JComboBox axis2Select;
    private javax.swing.JSlider slider1;
    private javax.swing.JSlider slider2;
    // End of variables declaration//GEN-END:variables
    
}