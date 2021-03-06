/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SearchDef.java
 *
 * Created on Oct 31, 2014, 2:25:43 PM
 */

package ui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import util.AppContext;
import static util.AppContext.* ;



public class SearchDVD extends javax.swing.JPanel {

    /** Creates new form SearchDef */
    public SearchDVD() {
        initComponents();
       buttonGroup1.add(rbtnName);
       buttonGroup1.add(rbtnYear);
       buttonGroup1.add(rbtnCategory);
       rbtnYear.setSelected(true);
       txtName.setEditable(false);
       txtCategory.setEditable(false);
    }

    DefaultTableModel dm = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int col) {
            if(col == 6 )
                return true;
            else
                return false;
        }
    };

    Vector dataRows = new Vector();
    Vector columnIdentifiers = new Vector();


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        rbtnName = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txtName = new javax.swing.JTextField();
        rbtnYear = new javax.swing.JRadioButton();
        txtYear = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        rbtnCategory = new javax.swing.JRadioButton();
        txtCategory = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 500));
        setPreferredSize(new java.awt.Dimension(800, 500));

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(0));

        rbtnName.setFont(new java.awt.Font("Tahoma", 1, 12));
        rbtnName.setText("by Name");
        rbtnName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnNameActionPerformed(evt);
            }
        });

        columnIdentifiers.add(0, "ID / REFERENCE");
        columnIdentifiers.add(1, " NAME ");
        columnIdentifiers.add(2, " CATEGORY ");
        columnIdentifiers.add(3, " DIRECTOR ");
        columnIdentifiers.add(4, " YEAR ");
        columnIdentifiers.add(5, " RENT ");

        dm.setDataVector(dataRows, columnIdentifiers);
        jTable1 = new JTable(dm) {
            public void tableChanged(TableModelEvent e) {
                super.tableChanged(e);
                repaint();
            }
        };
        jTable1.setFont(new java.awt.Font("Tahoma", 0, 12));
        jTable1.setModel(dm);
        jTable1.setRowHeight(25);
        jScrollPane1.setViewportView(jTable1);

        txtName.setFont(new java.awt.Font("Tahoma", 1, 12));

        rbtnYear.setFont(new java.awt.Font("Tahoma", 1, 12));
        rbtnYear.setText("After Year");
        rbtnYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnYearActionPerformed(evt);
            }
        });

        txtYear.setFont(new java.awt.Font("Tahoma", 1, 12));

        btnSearch.setFont(new java.awt.Font("Tahoma", 1, 12));
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        rbtnCategory.setFont(new java.awt.Font("Tahoma", 1, 12));
        rbtnCategory.setText("by Category");
        rbtnCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnCategoryActionPerformed(evt);
            }
        });

        txtCategory.setFont(new java.awt.Font("Tahoma", 1, 12));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbtnCategory)
                            .addComponent(rbtnName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rbtnYear)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnSearch)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 753, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnName)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbtnYear)
                    .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbtnCategory)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSearch)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setText("SEARCH DVD");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(339, 339, 339))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(27, 27, 27))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rbtnNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnNameActionPerformed
        // TODO add your handling code here:
        if(rbtnName.isSelected()){
            txtName.setEditable(true);
            txtYear.setEditable(false);
            txtCategory.setEditable(false);
        }else if(rbtnYear.isSelected()){
            txtName.setEditable(false);
            txtYear.setEditable(true);
            txtCategory.setEditable(false);
        }else if(rbtnCategory.isSelected()){
            txtName.setEditable(false);
            txtYear.setEditable(false);
            txtCategory.setEditable(true);
        }
        clearAllTextBox();
    }//GEN-LAST:event_rbtnNameActionPerformed

    private void rbtnYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnYearActionPerformed
        // TODO add your handling code here:
        if(rbtnName.isSelected()){
            txtName.setEditable(true);
            txtYear.setEditable(false);
            txtCategory.setEditable(false);
        }else if(rbtnYear.isSelected()){
            txtName.setEditable(false);
            txtYear.setEditable(true);
            txtCategory.setEditable(false);
        }else if(rbtnCategory.isSelected()){
            txtName.setEditable(false);
            txtYear.setEditable(false);
            txtCategory.setEditable(true);
        }
        clearAllTextBox();
    }//GEN-LAST:event_rbtnYearActionPerformed

    private void clearAllTextBox(){
        txtName.setText("");
        txtYear.setText("");
        txtCategory.setText("");
    }
    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void search(){
        if(rbtnName.isSelected()){
            getSearchByDVDName(txtName.getText());
        }else if(rbtnYear.isSelected()){
            getSearchByDVDYear(txtYear.getText());
        }else if(rbtnCategory.isSelected()){
            getSearchByServedby(txtCategory.getText());
        }
    }
    private void rbtnCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnCategoryActionPerformed
        // TODO add your handling code here:
        if(rbtnName.isSelected()){
            txtName.setEditable(true);
            txtYear.setEditable(false);
            txtCategory.setEditable(false);
        }else if(rbtnYear.isSelected()){
            txtName.setEditable(false);
            txtYear.setEditable(true);
            txtCategory.setEditable(false);
        }else if(rbtnCategory.isSelected()){
            txtName.setEditable(false);
            txtYear.setEditable(false);
            txtCategory.setEditable(true);
        }
        clearAllTextBox();
    }//GEN-LAST:event_rbtnCategoryActionPerformed

    private void getSearchByDVDName(String dvdName){

        if(dvdName == null || dvdName.length()==0){
            System.out.println("Invalid input value for Name");
            return;
        }
        ArrayList list = AppContext.dao.getDVDSearchByName(dvdName);
        fillTable(list);
    }
    
    private void getSearchByDVDYear(String strYear){
        if(strYear == null || strYear.length() == 0)
            return;
        int nYear = Integer.valueOf(strYear);
        ArrayList list = AppContext.dao.getDVDSearchByYear(nYear);
        fillTable(list);
    }

    private void getSearchByServedby(String strCategory){
        if(strCategory == null || strCategory.length() == 0)
            return;

        ArrayList list = AppContext.dao.getDVDSearchByCategory(strCategory);
        fillTable(list);
    }


    void fillTable(ArrayList list) {
        try{
        
            dataRows.clear();
            if(list == null || list.size()==0){
                //JOptionPane.showMessageDialog(null, " No record found.");
                dm.setDataVector(dataRows, columnIdentifiers);
                jTable1.setModel(dm);
                return;
            }
            for(int i=0; i<list.size(); i++){
                HashMap map = (HashMap) list.get(i);
                Vector row = new Vector();
                row.add(0, map.get(DVD_ID));
                row.add(1, map.get(DVD_NAME));
                row.add(2, map.get(DVD_CATEGORY));
                row.add(3, map.get(DVD_DIRECTOR));
                row.add(4, map.get(DVD_YEAR));
                row.add(5, map.get(DVD_RENT));
                dataRows.add(i, row);
            }// end for
            dm.setDataVector(dataRows, columnIdentifiers);
            jTable1.setModel(dm);

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Exception occured. \n" + e.getMessage() );
        }// end try-catch

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JRadioButton rbtnCategory;
    private javax.swing.JRadioButton rbtnName;
    private javax.swing.JRadioButton rbtnYear;
    private javax.swing.JTextField txtCategory;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtYear;
    // End of variables declaration//GEN-END:variables

}
