package App;

import Model.Comandas;
import BO.ComandasBO;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JDialog;
import javax.swing.JTable;

public class MenuComandas extends javax.swing.JInternalFrame {
    public MenuComandas() {
        initComponents();
        carregarTabelaComandas();
      
    }
    
    public void carregarTabelaComandas() {
        ComandasBO cbo = new ComandasBO();
        List<Comandas> listaComandasAbertas = cbo.listarAbertas();
        DefaultTableModel model = (DefaultTableModel) listaComandas.getModel();
        model.setRowCount(0);
        for (Comandas comanda : listaComandasAbertas){
            Object[] novaLinha = {comanda};
            model.addRow(novaLinha);
        }
    }
    
    public void recarregarTabela() {
        carregarTabelaComandas(); 
        listaComandas.revalidate();
        listaComandas.repaint();
    }
    
    ComandasBO cbo = new ComandasBO();
    List<Comandas> listaComandasAbertas = cbo.listarAbertas();
    
    DefaultTableModel model = new DefaultTableModel();
    JTable JTable1 = new JTable(model);
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        criarComandaButton = new javax.swing.JButton();
        pedidos = new javax.swing.JButton();
        excluirComanda = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaComandas = new javax.swing.JTable();
        fecharComanda = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jTextField1 = new javax.swing.JTextField();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        jMenu3.setText("jMenu3");

        jMenuItem1.setText("jMenuItem1");

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        setClosable(true);

        criarComandaButton.setText("Criar nova comanda");
        criarComandaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                criarComandaButtonActionPerformed(evt);
            }
        });

        pedidos.setText("Pedidos");
        pedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pedidosActionPerformed(evt);
            }
        });

        excluirComanda.setText("Excluir comanda existente");
        excluirComanda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excluirComandaActionPerformed(evt);
            }
        });

        listaComandas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Lista de comandas"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(listaComandas);

        fecharComanda.setText("Fechar comanda existente");
        fecharComanda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fecharComandaActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(criarComandaButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fecharComanda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(excluirComanda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pedidos)
                        .addGap(0, 15, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(criarComandaButton)
                    .addComponent(pedidos)
                    .addComponent(excluirComanda)
                    .addComponent(fecharComanda)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void criarComandaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_criarComandaButtonActionPerformed
        java.awt.Window window = javax.swing.SwingUtilities.getWindowAncestor(this);
        CriarComandas dialog = new CriarComandas((java.awt.Frame) window, true);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
        Comandas nova = dialog.getComanda();
        if(nova != null){
            recarregarTabela();
        }
    }//GEN-LAST:event_criarComandaButtonActionPerformed

    private void pedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pedidosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pedidosActionPerformed

    private void excluirComandaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excluirComandaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_excluirComandaActionPerformed

    private void fecharComandaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fecharComandaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fecharComandaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton criarComandaButton;
    private javax.swing.JButton excluirComanda;
    private javax.swing.JButton fecharComanda;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable listaComandas;
    private javax.swing.JButton pedidos;
    // End of variables declaration//GEN-END:variables
}
