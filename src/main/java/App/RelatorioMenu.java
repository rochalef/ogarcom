package App;

import BO.RelatorioBO;
import Model.Relatorio;
import java.util.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */

/**
 *
 * @author Joao victor
 */
public class RelatorioMenu extends javax.swing.JInternalFrame {
    private javax.swing.JLabel lblTotalPedidos;
    private javax.swing.JLabel lblValorArrecadado;
    private javax.swing.JLabel lblPratoMaisVendido;
   

    /**
     * Creates new form Relatorio
     */
    public RelatorioMenu() {
        initComponents();
        carregarRelatorio();
    }
    
    
    // Método que chama o BO e coloca os dados nos labels
    private void carregarRelatorio() {
        RelatorioBO relatorioBO = new RelatorioBO();
        Model.Relatorio relatorio = relatorioBO.gerarRelatorioMensal();

        if (relatorio != null) {
            total_pedidos.setText("Total de Pedidos: " + relatorio.getTotalPedidos());
            valor_arrecadado.setText("Valor Arrecadado: R$ " + relatorio.getFaturamentoMensal());
            pratomaisvendido.setText("Prato Mais Vendido: " + relatorio.getPratoMaisVendido());
        } else {
            total_pedidos.setText("Nenhum dado encontrado");
            valor_arrecadado.setText("");
            pratomaisvendido.setText("");
        }
    }
    

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        total_pedidos = new javax.swing.JLabel();
        valor_arrecadado = new javax.swing.JLabel();
        pratomaisvendido = new javax.swing.JLabel();

        setTitle("Relatório");

        total_pedidos.setText("Total de pedidos:");

        valor_arrecadado.setText("Valor Arrecadado:");

        pratomaisvendido.setText("Prato mais vendido:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(pratomaisvendido)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(valor_arrecadado)
                            .addComponent(total_pedidos))
                        .addGap(46, 276, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(total_pedidos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(valor_arrecadado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pratomaisvendido)
                .addContainerGap(188, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel pratomaisvendido;
    private javax.swing.JLabel total_pedidos;
    private javax.swing.JLabel valor_arrecadado;
    // End of variables declaration//GEN-END:variables
}
