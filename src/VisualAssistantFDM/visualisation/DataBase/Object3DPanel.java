/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Object3DPanel.java
 *
 * Created on 13 janv. 2011, 16:11:12
 */

package VisualAssistantFDM.visualisation.DataBase;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;

/**
 *
 * @author Abdelheq
 */
public class Object3DPanel extends javax.swing.JPanel {

    private ImageIcon imageIcon;
    private Color ChoixCouleur3 = null;
    private Color ChoixCouleur2 = null;
    private Color ChoixCouleur1 = null;
    private Color ChoixCouleur4 = null;

    /** Creates new form Object3DPanel */
    public Object3DPanel() {
        initComponents();
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        ButtonEchelleCouleursBas = new javax.swing.JButton();
        Pyr2Color2Label = new javax.swing.JLabel();
        Pyr2Color1Label = new javax.swing.JLabel();
        ButtonPyr2Color2 = new javax.swing.JButton();
        Color4 = new javax.swing.JTextField();
        Color3 = new javax.swing.JTextField();
        ButtonPyr2Color1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        ButtonEchelleCouleursHaut = new javax.swing.JButton();
        Pyr1Color2Label = new javax.swing.JLabel();
        Pyr1Color1Label = new javax.swing.JLabel();
        ButtonPyr1Color2 = new javax.swing.JButton();
        Color2 = new javax.swing.JTextField();
        Color1 = new javax.swing.JTextField();
        ButtonPyr1Color1 = new javax.swing.JButton();
        voix = new javax.swing.JComboBox();
        voixLabel = new javax.swing.JLabel();
        attSyntheseLabel = new javax.swing.JLabel();
        attSynthese = new javax.swing.JComboBox();
        objSize = new javax.swing.JSlider();
        objSizeLabel = new javax.swing.JLabel();
        size = new javax.swing.JLabel();
        JDrapeau = new javax.swing.JLabel();

        jPanel3.setPreferredSize(new java.awt.Dimension(195, 112));

        ButtonEchelleCouleursBas.setText("Echelle de couleurs...");

        Pyr2Color2Label.setText("Couleur Min");

        Pyr2Color1Label.setText("Couleur Max");

        ButtonPyr2Color2.setText("...");
        ButtonPyr2Color2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonPyr2Color2ActionPerformed(evt);
            }
        });

        Color4.setBackground(new java.awt.Color(204, 204, 255));

        Color3.setBackground(new java.awt.Color(0, 255, 255));
        Color3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Color3ActionPerformed(evt);
            }
        });

        ButtonPyr2Color1.setText("...");
        ButtonPyr2Color1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonPyr2Color1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ButtonEchelleCouleursBas, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(ButtonPyr2Color2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(Color4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Pyr2Color2Label))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(ButtonPyr2Color1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Color3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Pyr2Color1Label))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ButtonEchelleCouleursBas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Pyr2Color1Label)
                    .addComponent(Pyr2Color2Label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Color4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Color3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonPyr2Color1)
                    .addComponent(ButtonPyr2Color2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ButtonEchelleCouleursHaut.setText("Echelle de couleurs...");
        ButtonEchelleCouleursHaut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonEchelleCouleursHautActionPerformed(evt);
            }
        });

        Pyr1Color2Label.setText("Couleur Min");

        Pyr1Color1Label.setText("Couleur Max");

        ButtonPyr1Color2.setText("...");
        ButtonPyr1Color2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonPyr1Color2ActionPerformed(evt);
            }
        });

        Color2.setBackground(new java.awt.Color(204, 204, 255));

        Color1.setBackground(new java.awt.Color(0, 255, 255));
        Color1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Color1ActionPerformed(evt);
            }
        });

        ButtonPyr1Color1.setText("...");
        ButtonPyr1Color1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonPyr1Color1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ButtonEchelleCouleursHaut, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(ButtonPyr1Color2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(Color2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Pyr1Color2Label))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(ButtonPyr1Color1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Color1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Pyr1Color1Label))))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {ButtonPyr1Color1, ButtonPyr1Color2, Color1, Color2});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ButtonEchelleCouleursHaut)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Pyr1Color1Label)
                    .addComponent(Pyr1Color2Label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Color2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Color1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonPyr1Color1)
                    .addComponent(ButtonPyr1Color2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {ButtonPyr1Color1, ButtonPyr1Color2, Color1, Color2});

        voix.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pas de voix" }));
        voix.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                voixItemStateChanged(evt);
            }
        });

        voixLabel.setText("Voix :");

        attSyntheseLabel.setText("Attribut synthese vocale :");

        attSynthese.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ItemXXXXXXXX" }));

        objSize.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                objSizeStateChanged(evt);
            }
        });

        objSizeLabel.setText("Taille de l'objet 3D :");

        size.setText("137");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(objSizeLabel)
                        .addGap(4, 4, 4)
                        .addComponent(size)
                        .addGap(19, 19, 19)
                        .addComponent(attSyntheseLabel)
                        .addGap(15, 15, 15)
                        .addComponent(voixLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JDrapeau, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(objSize, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(attSynthese, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(voix, 0, 81, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jPanel2, jPanel3});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(objSizeLabel)
                    .addComponent(size)
                    .addComponent(attSyntheseLabel)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(voixLabel)
                        .addComponent(JDrapeau, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(objSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(attSynthese, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(voix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jPanel2, jPanel3});

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Pyramidion Haut ou Sphere"));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Pyramidion Haut ou Sphere"));
    }// </editor-fold>//GEN-END:initComponents

    private void Color1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Color1ActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_Color1ActionPerformed

    private void Color3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Color3ActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_Color3ActionPerformed

    private void objSizeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_objSizeStateChanged
         size.setText("" + objSize.getValue());
    }//GEN-LAST:event_objSizeStateChanged

    private void voixItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_voixItemStateChanged
        BufferedImage im = null;
        imageIcon = new ImageIcon();
        JDrapeau.setDisabledIcon(imageIcon);

    if (voix.getSelectedItem() == "Brian") {

        try {
            im = ImageIO.read(getClass().getResourceAsStream("/images/Drapeau1.png"));

        } catch (IOException e) {
        }
        imageIcon.setImage(im);

        JDrapeau.setIcon(imageIcon);

    } else {
        try {
            im = ImageIO.read(getClass().getResourceAsStream("/images/Drapeau.png"));

        } catch (IOException e) {
        }
        imageIcon.setImage(im);
        JDrapeau.setIcon(imageIcon);

    }
    }//GEN-LAST:event_voixItemStateChanged

    private void ButtonEchelleCouleursHautActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonEchelleCouleursHautActionPerformed
        
    }//GEN-LAST:event_ButtonEchelleCouleursHautActionPerformed

    private void ButtonPyr1Color2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonPyr1Color2ActionPerformed
        Color col = ButtonPyr1Color2.getBackground();

        Color color = JColorChooser.showDialog(
                this,
                "Couleur du pyramdian du haut",
                col);
        Color2.setBackground(color);

        ChoixCouleur2 = color;
    }//GEN-LAST:event_ButtonPyr1Color2ActionPerformed

    private void ButtonPyr1Color1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonPyr1Color1ActionPerformed
        Color col = ButtonPyr1Color2.getBackground();

        Color color = JColorChooser.showDialog(
                this,
                "Couleur du pyramdian du haut",
                col);
        Color2.setBackground(color);

        ChoixCouleur2 = color;
    }//GEN-LAST:event_ButtonPyr1Color1ActionPerformed

    private void ButtonPyr2Color2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonPyr2Color2ActionPerformed
        Color col = Color4.getBackground();

        Color color = JColorChooser.showDialog(
            this,
            "Couleur du pyramdian du haut",
            col);
        Color4.setBackground(color);

        ChoixCouleur4 = color;
    }//GEN-LAST:event_ButtonPyr2Color2ActionPerformed

    private void ButtonPyr2Color1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonPyr2Color1ActionPerformed
        Color col = Color3.getBackground();

        Color color = JColorChooser.showDialog(
                this,
                "Couleur du pyramdian du haut",
                col);
        Color3.setBackground(color);

        ChoixCouleur3 = color;
    }//GEN-LAST:event_ButtonPyr2Color1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonEchelleCouleursBas;
    private javax.swing.JButton ButtonEchelleCouleursHaut;
    private javax.swing.JButton ButtonPyr1Color1;
    private javax.swing.JButton ButtonPyr1Color2;
    private javax.swing.JButton ButtonPyr2Color1;
    private javax.swing.JButton ButtonPyr2Color2;
    private javax.swing.JTextField Color1;
    private javax.swing.JTextField Color2;
    private javax.swing.JTextField Color3;
    private javax.swing.JTextField Color4;
    private javax.swing.JLabel JDrapeau;
    private javax.swing.JLabel Pyr1Color1Label;
    private javax.swing.JLabel Pyr1Color2Label;
    private javax.swing.JLabel Pyr2Color1Label;
    private javax.swing.JLabel Pyr2Color2Label;
    private javax.swing.JComboBox attSynthese;
    private javax.swing.JLabel attSyntheseLabel;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSlider objSize;
    private javax.swing.JLabel objSizeLabel;
    private javax.swing.JLabel size;
    private javax.swing.JComboBox voix;
    private javax.swing.JLabel voixLabel;
    // End of variables declaration//GEN-END:variables

}
