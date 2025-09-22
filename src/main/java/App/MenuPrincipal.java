package App;

public class MenuPrincipal extends javax.swing.JFrame {
    public MenuPrincipal() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jFrame2 = new javax.swing.JFrame();
        jFrame3 = new javax.swing.JFrame();
        desktopPane = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        comandasMenu = new javax.swing.JMenu();
        relatorioMenu = new javax.swing.JMenu();
        cardapioMenu = new javax.swing.JMenu();

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

        javax.swing.GroupLayout jFrame2Layout = new javax.swing.GroupLayout(jFrame2.getContentPane());
        jFrame2.getContentPane().setLayout(jFrame2Layout);
        jFrame2Layout.setHorizontalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame2Layout.setVerticalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame3Layout = new javax.swing.GroupLayout(jFrame3.getContentPane());
        jFrame3.getContentPane().setLayout(jFrame3Layout);
        jFrame3Layout.setHorizontalGroup(
            jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame3Layout.setVerticalGroup(
            jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        comandasMenu.setMnemonic('f');
        comandasMenu.setText("Comandas");
        comandasMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comandasMenuMouseClicked(evt);
            }
        });
        menuBar.add(comandasMenu);

        relatorioMenu.setMnemonic('e');
        relatorioMenu.setText("Relatório");
        relatorioMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                relatorioMenuMouseClicked(evt);
            }
        });
        menuBar.add(relatorioMenu);

        cardapioMenu.setMnemonic('h');
        cardapioMenu.setText("Cardápio");
        cardapioMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cardapioMenuMouseClicked(evt);
            }
        });
        menuBar.add(cardapioMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comandasMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comandasMenuMouseClicked
        boolean aberta = false;
        for (javax.swing.JInternalFrame frame : desktopPane.getAllFrames()) {
            if (frame instanceof MenuComandas) { 
                aberta = true;
                frame.toFront(); // traz para frente se já estiver aberta
                try {
                    frame.setSelected(true); // foca a janela
                } catch (java.beans.PropertyVetoException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        if (!aberta) {
            MenuComandas com = new MenuComandas();
            desktopPane.add(com);
            com.setVisible(true);
        }
    }//GEN-LAST:event_comandasMenuMouseClicked

    private void cardapioMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cardapioMenuMouseClicked
        boolean aberta = false;
        for (javax.swing.JInternalFrame frame : desktopPane.getAllFrames()) {
            if (frame instanceof Cardapio) { 
                aberta = true;
                frame.toFront(); // traz para frente se já estiver aberta
                try {
                    frame.setSelected(true); // foca a janela
                } catch (java.beans.PropertyVetoException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        if (!aberta) {
            Cardapio car = new Cardapio();
            desktopPane.add(car);
            car.setVisible(true);
        }                   
    }//GEN-LAST:event_cardapioMenuMouseClicked

    private void relatorioMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_relatorioMenuMouseClicked
        boolean aberta = false;
        for (javax.swing.JInternalFrame frame : desktopPane.getAllFrames()) {
            if (frame instanceof RelatorioMenu) { 
                aberta = true;
                frame.toFront(); 
                try {
                    frame.setSelected(true); 
                } catch (java.beans.PropertyVetoException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        if (!aberta) {
            RelatorioMenu relatorio = new RelatorioMenu();
            desktopPane.add(relatorio);
            relatorio.setVisible(true);
        }       
    }//GEN-LAST:event_relatorioMenuMouseClicked
    
   
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu cardapioMenu;
    private javax.swing.JMenu comandasMenu;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JFrame jFrame3;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu relatorioMenu;
    // End of variables declaration//GEN-END:variables

}
