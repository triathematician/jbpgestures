/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GTrainer.java
 *
 * Created on Oct 31, 2009, 7:31:19 PM
 */

package org.bm.firestorm.testing;

import java.util.NoSuchElementException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import org.bm.firestorm.functionspace.FunctionUtils;
import org.bm.firestorm.functionspace.ONLegendre;
import org.bm.firestorm.gestures.PolyReader;
import org.bm.firestorm.gestures.data.TrainContext;
import org.bm.firestorm.gestures.data.TrainGesture;

/**
 *
 * @author ae3263
 */
public class GTrainer extends javax.swing.JFrame {

    final PolyReader pr = new PolyReader();
    final ONLegendre onl = new ONLegendre();

    /** Creates new form GTrainer */
    public GTrainer() {
        initComponents();
        gestureTable.setModel(trainedGestures.getTableModel());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        trainedGestures = new org.bm.firestorm.gestures.data.CoefficientClassifier<String>();
        reader = new org.bm.firestorm.gestures.PolyReader();
        jToolBar1 = new javax.swing.JToolBar();
        jLabel1 = new javax.swing.JLabel();
        contextBox = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        trainString = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        trainingPanel = new org.bm.firestorm.gestures.GPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        gestureTable = new javax.swing.JTable();
        reflectPanel = new org.bm.firestorm.gestures.ParametricPathPanel();
        acceptButton = new javax.swing.JButton();
        rejectButton = new javax.swing.JButton();
        lookupPanel = new org.bm.firestorm.gestures.ParametricPathPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lookupString = new javax.swing.JLabel();
        lookupDist = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gesture Training");

        jToolBar1.setRollover(true);

        jLabel1.setText("Context:");
        jToolBar1.add(jLabel1);

        contextBox.setModel(new DefaultComboBoxModel(TrainContext.values()));
        jToolBar1.add(contextBox);

        jLabel2.setText("  String:");
        jToolBar1.add(jLabel2);

        trainString.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trainStringActionPerformed(evt);
            }
        });
        jToolBar1.add(trainString);

        getContentPane().add(jToolBar1, java.awt.BorderLayout.NORTH);

        trainingPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        trainingPanel.setMaxNumberOfPaths(3);
        trainingPanel.setMaximumSize(new java.awt.Dimension(200, 200));
        trainingPanel.setMinimumSize(new java.awt.Dimension(200, 200));
        trainingPanel.setPreferredSize(new java.awt.Dimension(200, 200));
        trainingPanel.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                trainingPanelStateChanged(evt);
            }
        });

        org.jdesktop.layout.GroupLayout trainingPanelLayout = new org.jdesktop.layout.GroupLayout(trainingPanel);
        trainingPanel.setLayout(trainingPanelLayout);
        trainingPanelLayout.setHorizontalGroup(
            trainingPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 196, Short.MAX_VALUE)
        );
        trainingPanelLayout.setVerticalGroup(
            trainingPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 196, Short.MAX_VALUE)
        );

        gestureTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(gestureTable);

        reflectPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        reflectPanel.setMaximumSize(new java.awt.Dimension(100, 100));

        org.jdesktop.layout.GroupLayout reflectPanelLayout = new org.jdesktop.layout.GroupLayout(reflectPanel);
        reflectPanel.setLayout(reflectPanelLayout);
        reflectPanelLayout.setHorizontalGroup(
            reflectPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 100, Short.MAX_VALUE)
        );
        reflectPanelLayout.setVerticalGroup(
            reflectPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 100, Short.MAX_VALUE)
        );

        acceptButton.setText("ACCEPT");
        acceptButton.setFocusable(false);
        acceptButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        acceptButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        acceptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptButtonActionPerformed(evt);
            }
        });

        rejectButton.setText("REJECT");
        rejectButton.setFocusable(false);
        rejectButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        rejectButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        rejectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rejectButtonActionPerformed(evt);
            }
        });

        lookupPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        lookupPanel.setMaximumSize(new java.awt.Dimension(100, 100));

        org.jdesktop.layout.GroupLayout lookupPanelLayout = new org.jdesktop.layout.GroupLayout(lookupPanel);
        lookupPanel.setLayout(lookupPanelLayout);
        lookupPanelLayout.setHorizontalGroup(
            lookupPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 100, Short.MAX_VALUE)
        );
        lookupPanelLayout.setVerticalGroup(
            lookupPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 100, Short.MAX_VALUE)
        );

        jLabel3.setText("Stored Gesture:");

        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Closest Gesture:");

        lookupString.setFont(new java.awt.Font("Tahoma", 1, 18));
        lookupString.setForeground(new java.awt.Color(204, 0, 51));
        lookupString.setText("NONE");

        lookupDist.setText("dist=0.0");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(trainingPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(lookupString)
                            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                .add(rejectButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(org.jdesktop.layout.GroupLayout.TRAILING, acceptButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel3))
                            .add(jLabel4)
                            .add(lookupDist))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(lookupPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(reflectPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane2)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(11, 11, 11)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(trainingPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jLabel3)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(acceptButton)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(rejectButton))
                            .add(reflectPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(lookupPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jLabel4)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(lookupString)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(lookupDist)))))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    TrainContext context() {
        return (TrainContext) contextBox.getModel().getSelectedItem();
    }

    private void acceptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptButtonActionPerformed
        double[][] coeffs = reader.convertPath(trainingPanel.getLastPath());
        trainedGestures.put(context(), coeffs, trainString.getText());
        trainingPanel.clearAllPaths();
        gestureTable.setModel(trainedGestures.getTableModel());
    }//GEN-LAST:event_acceptButtonActionPerformed

    private void trainStringActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trainStringActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_trainStringActionPerformed

    private void rejectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rejectButtonActionPerformed
        trainingPanel.clearAllPaths();
    }//GEN-LAST:event_rejectButtonActionPerformed

    private void trainingPanelStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_trainingPanelStateChanged
        double[][] coeffs = pr.convertPath(trainingPanel.getLastPath());
        reflectPanel.setFunctions(new FunctionUtils.CFunction(onl, coeffs[0]), new FunctionUtils.CFunction(onl, coeffs[1]));
        reflectPanel.repaint();
        try {
            TrainGesture tg = new TrainGesture(context(), coeffs);
            TrainGesture best = trainedGestures.closestTo(tg);
            lookupString.setText( (String) trainedGestures.get(best));
            lookupDist.setText( String.format("%.2f", best.distance(tg)) );
            lookupPanel.setFunctions(new FunctionUtils.CFunction(onl, best.getArrays()[0]), new FunctionUtils.CFunction(onl, best.getArrays()[1]));
            lookupPanel.repaint();
        } catch (NoSuchElementException e) {
        }
    }//GEN-LAST:event_trainingPanelStateChanged

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GTrainer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton acceptButton;
    private javax.swing.JComboBox contextBox;
    private javax.swing.JTable gestureTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lookupDist;
    private org.bm.firestorm.gestures.ParametricPathPanel lookupPanel;
    private javax.swing.JLabel lookupString;
    private org.bm.firestorm.gestures.PolyReader reader;
    private org.bm.firestorm.gestures.ParametricPathPanel reflectPanel;
    private javax.swing.JButton rejectButton;
    private javax.swing.JTextField trainString;
    private org.bm.firestorm.gestures.data.CoefficientClassifier trainedGestures;
    private org.bm.firestorm.gestures.GPanel trainingPanel;
    // End of variables declaration//GEN-END:variables

}
