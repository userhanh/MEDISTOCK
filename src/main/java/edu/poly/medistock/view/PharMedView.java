/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package edu.poly.medistock.view;

import edu.poly.medistock.entity.PharMed;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.metal.MetalBorders;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author msi gameming
 */
public class PharMedView extends javax.swing.JFrame {

    //Định nghĩa các cột của bảng
    String[] columnNames = new String[]{
        "ID", "Tên sản phẩm", "NSX", "HSD", "Nguồn nhập", "Số hóa đơn", "Số lô", "Số lượng", "Giá sản phẩm" 
    };
    
    private  Object data = new Object[][]{};
    
    public PharMedView() {
        initComponents();
        setLocationRelativeTo(null);
    }
    
    //hiển thị thông báo 
    public void showMessage(String message){
        JOptionPane.showMessageDialog(this, message);
    }
    
    //đổ dữ liệu vào bảng pharMedTable
    public void showListPharMeds(List<PharMed> list){
        //mảng 2 chiều hàng và cột
        if(list == null || list.isEmpty()){
            DefaultTableModel model = (DefaultTableModel) tblPharMed.getModel();
            model.setRowCount(0);
        }
        int size = list.size();
        Object[][] pharMeds = new Object[size][9];
        for(int i = 0; i < size; i++){
            pharMeds[i][0] = list.get(i).getId();
            pharMeds[i][1] = list.get(i).getName();
            pharMeds[i][2] = list.get(i).getNSX();
            pharMeds[i][3] = list.get(i).getHSD();
            pharMeds[i][4] = list.get(i).getSource();
            pharMeds[i][5] = list.get(i).getBill();
            pharMeds[i][6] = list.get(i).getSet();
            pharMeds[i][7] = list.get(i).getNumber();
            pharMeds[i][8] = list.get(i).getPrice();
        }
        tblPharMed.setModel(new DefaultTableModel(pharMeds, columnNames));
    }
    //lấy thông tin từ ô được chọn trong bảng
    public void fillPharMedFromSelectedRow(){
        int row = tblPharMed.getSelectedRow();
        if(row >= 0){
            idField.setText(tblPharMed.getModel().getValueAt(row, 0).toString());
            nameField.setText(tblPharMed.getModel().getValueAt(row, 1).toString());
            nsxField.setText(tblPharMed.getModel().getValueAt(row, 2).toString());
            hsdField.setText(tblPharMed.getModel().getValueAt(row, 3).toString());
            sourceField.setText(tblPharMed.getModel().getValueAt(row, 4).toString());
            billField.setText(tblPharMed.getModel().getValueAt(row, 5).toString());
            setField.setText(tblPharMed.getModel().getValueAt(row, 6).toString());
            numberField.setText(tblPharMed.getModel().getValueAt(row, 7).toString());
            priceField.setText(tblPharMed.getModel().getValueAt(row, 8).toString());
            // enable Edit and Delete buttons
            editBtn.setEnabled(true);
            deleteBtn.setEnabled(true);
            // disable Add button
            addBtn.setEnabled(false);
        }
    }
    
    //xóa thông tin thuốc
        public void clearPharMedInfo() {
        idField.setText("");
        nameField.setText("");
        nsxField.setText("");
        hsdField.setText("");
        sourceField.setText("");
        billField.setText("");
        setField.setText("");
        numberField.setText("");
        priceField.setText("");

        // disable Edit and Delete buttons
        editBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
        // enable Add button
        addBtn.setEnabled(true);
    }
    
    //hiển thị thông tin thuốc
    public void showPharMedInfo(PharMed pharMed){
        idField.setText("" + pharMed.getId());
        nameField.setText("" + pharMed.getName());
        nsxField.setText("" + pharMed.getNSX());
        hsdField.setText("" + pharMed.getHSD());
        sourceField.setText("" + pharMed.getSource());
        billField.setText("" + pharMed.getBill());
        setField.setText("" + pharMed.getSet());
        numberField.setText("" + pharMed.getNumber());
        priceField.setText("" + pharMed.getPrice());
        
        // enable Edit and Delete buttons
        editBtn.setEnabled(true);
        deleteBtn.setEnabled(true);
        // disable Add button
        addBtn.setEnabled(false);
    }
    
    //xóa bảng khi không tìm thấy
    public void clearTable(){
    }
    
    //lấy thông tin đơn thuốc
    public PharMed getPharMedInfo() {
        // validate student
        if (!validateName() || !validateNSX() || !validateHSD() || !validatePrice()) {
            return null;
        }
        try {
            PharMed pharMed = new PharMed();
            if (idField.getText() != null && !"".equals(idField.getText())) {
                pharMed.setId(Integer.parseInt(idField.getText()));
            }
            pharMed.setName(nameField.getText().trim());
            pharMed.setNSX(nsxField.getText().trim());
            pharMed.setHSD(hsdField.getText().trim());
            pharMed.setSource(sourceField.getText().trim());
            pharMed.setBill(billField.getText().trim());
            pharMed.setSet(setField.getText().trim());
            pharMed.setNumber(Integer.parseInt(numberField.getText().trim()));
            pharMed.setPrice(Double.parseDouble(priceField.getText().trim()));
            return pharMed;
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
        return null;
    }    
    
    //các phương thức validate
     private boolean validateName() {
        String name = nameField.getText();
        if (name == null || "".equals(name.trim())) {
            nameField.requestFocus();
            showMessage("Tên không được trống.");
            return false;
        }
        return true;
    }
     
    //validate NSX
     LocalDate nsx = null; //khai báo bên ngoài để sau tiện so sánh
    public boolean validateNSX(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/yyyy");
         
        try{
             nsx = LocalDate.parse(nsxField.getText().trim(), format);
             if(nsx.isAfter(LocalDate.now())){
                 showMessage("Sản phẩm chưa được sản xuất!");
                 return false;
             }
             nsxField.requestFocus();
         }catch(Exception e){
             showMessage("NSX không đúng định dạng");
             return false;
         }
         return true;
     }
     
    //validate HSD: đúng kiểu ngày tháng và sau NSX
    public boolean validateHSD(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate hsd = null;
        try{
            hsd = LocalDate.parse(hsdField.getText().trim(), format);
            hsdField.requestFocus();
            
            if(hsd.isBefore(LocalDate.now())){
                showMessage("Chỉ được thêm hàng chưa hết hạn");
                return false;
            }
            
            if(!hsd.isAfter(nsx)){
                showMessage("HSD phải sau NSX");
                return false;
            }
        } catch(Exception e){
            showMessage("HSD không đúng định dạng");
        }
        return true;
    }
     
//    private boolean validateSource() {
//        String source = sourceField.getText();
//        if (source == null || "".equals(source.trim())) {
//            sourceField.requestFocus();
//            showMessage("Nguồn hàng không được trống.");
//            return false;
//        }
//        return true;
//    }
//    private boolean validateBill() {
//        String bill = billField.getText();
//        if (bill == null || "".equals(bill.trim())) {
//            billField.requestFocus();
//            showMessage("Số hóa đơn không được trống.");
//            return false;
//        }
//        return true;
//    }
//    private boolean validateSet() {
//        String set = setField.getText();
//        if (set == null || "".equals(set.trim())) {
//            setField.requestFocus();
//            showMessage("Số lô không được trống.");
//            return false;
//        }
//        return true;
//    }
//    
    private boolean validateNumber() {
        try {
            int number = Integer.parseInt(numberField.getText().trim());
            if (number <= 0) {
                priceField.requestFocus();
                showMessage("Số lượng phải > 0");
                return false;
            }
        } catch (Exception e) {
            priceField.requestFocus();
            showMessage("Số lượng không hợp lệ!");
            return false;
        }
        return true;
    }
    
    private boolean validatePrice() {
        try {
            Double price = Double.parseDouble(priceField.getText().trim());
            if (price < 0) {
                priceField.requestFocus();
                showMessage("Giá không được < 0");
                return false;
            }
        } catch (Exception e) {
            priceField.requestFocus();
            showMessage("Giá không hợp lệ!");
            return false;
        }
        return true;
    }
    
     public void valueChanged(ListSelectionEvent e) {
    }
     
      public void actionPerformed(ActionEvent e) {
    }
      
      public void addAddPharMedListener(ActionListener listener) {
        addBtn.addActionListener(listener);
    }
     
    public void addEditPharMedListener(ActionListener listener) {
        editBtn.addActionListener(listener);
    }
     
    public void addDeletePharMedListener(ActionListener listener) {
        deleteBtn.addActionListener(listener);
    }
     
    public void addClearPharMedListener(ActionListener listener) {
        clearBtn.addActionListener(listener);
    }
    

    public void getListPharMedSelectionListener(ListSelectionListener listener) {
        tblPharMed.getSelectionModel().addListSelectionListener(listener);
    }
    
    public void sortListPharMedListener(ActionListener listener){
        cbbSort.addActionListener(listener);
    }
    
    
    public String getTypeSort(){
        return (String) cbbSort.getSelectedItem();
    }
    
    public void searchPharMedListener(ActionListener listener){
        btnSearch1.addActionListener(listener);
    }
    
    public String getTypeSearch(){
       return (String) cbbSearchChoice1.getSelectedItem();
    }
    
    public String getSearchContent(){
        return (String) txtSearchInput.getText().trim();
    }
    
    
    //phục vụ tìm kiếm trong khoảng
    public void SearchPharMedInSegmentListener(ActionListener listener){
        btnSearch2.addActionListener(listener);
    }
    public String getTypeSearch2(){
        return (String) cbbSearchChoice2.getSelectedItem();
    }
    public String getSearchContent1(){
        return (String) txtSearchInput2.getText().trim();
    }
    
    public String getSearchContent2(){
        return (String) txtSearchInput3.getText().trim();
    }
    
    public void addStatisticsPharMedListener(ActionListener listener){
        btnStatistic.addActionListener(listener);
    }
    
    //hiển thị lại danh sách
    public void refresh(ActionListener listener){
        btnClear.addActionListener(listener);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPharMed = new javax.swing.JTable();
        btnClear = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        cbbSort = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbbSearchChoice1 = new javax.swing.JComboBox<>();
        cbbSearchChoice2 = new javax.swing.JComboBox<>();
        txtSearchInput = new javax.swing.JTextField();
        txtSearchInput2 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtSearchInput3 = new javax.swing.JTextField();
        btnSearch1 = new javax.swing.JButton();
        btnSearch2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btnStatistic = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        setField = new javax.swing.JTextField();
        billField = new javax.swing.JTextField();
        priceField = new javax.swing.JTextField();
        nameField = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        nsxField = new javax.swing.JTextField();
        idField = new javax.swing.JTextField();
        sourceField = new javax.swing.JTextField();
        hsdField = new javax.swing.JTextField();
        editBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        numberField = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.setForeground(new java.awt.Color(204, 255, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(963, 600));

        jPanel4.setBackground(new java.awt.Color(255, 204, 255));
        jPanel4.setForeground(new java.awt.Color(255, 204, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(885, 700));

        tblPharMed.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblPharMed.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tên sản phẩm", "NSX", "HSD", "Nguồn nhập", "Số hoá đơn", "Số lô", "Giá sản phẩm"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblPharMed.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPane1.setViewportView(tblPharMed);

        btnClear.setText("Hiển thị toàn bộ");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        jLabel42.setText("Sắp xếp theo: ");

        cbbSort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Tên sản phẩm", "NSX", "HSD", "Nguồn nhập", "Số hóa đơn", "Số lô", "Số lượng nhập", "Giá sản phẩm" }));

        jLabel1.setText("Tìm kiếm chính xác: ");

        jLabel5.setText("Tìm kiếm theo khoảng: ");

        cbbSearchChoice1.setMaximumRowCount(4);
        cbbSearchChoice1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Tên sản phẩm", "NSX", "HSD", "Nguồn nhập", "Số lô" }));

        cbbSearchChoice2.setMaximumRowCount(4);
        cbbSearchChoice2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Số lượng nhập", "Giá sản phẩm", "NSX", "HSD" }));

        jLabel7.setText("từ:");

        btnSearch1.setText("Tìm kiếm");
        btnSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch1ActionPerformed(evt);
            }
        });

        btnSearch2.setText("Tìm kiếm");

        jLabel6.setText("đến:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbbSearchChoice2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbbSearchChoice1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtSearchInput2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSearchInput3))
                            .addComponent(txtSearchInput, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSearch1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSearch2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(90, 90, 90))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(btnClear)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(535, 535, 535)
                                .addComponent(jLabel42)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(36, 36, 36))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbbSort, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 863, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbSort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel42)))
                    .addComponent(jLabel2)
                    .addComponent(btnClear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                .addGap(13, 13, 13)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbbSearchChoice1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearchInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbSearchChoice2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtSearchInput2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearchInput3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(btnSearch2))
                .addGap(16, 16, 16))
        );

        btnStatistic.setBackground(new java.awt.Color(0, 0, 204));
        btnStatistic.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnStatistic.setForeground(new java.awt.Color(255, 255, 255));
        btnStatistic.setText("Thống kê");
        btnStatistic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStatisticActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 255));
        jLabel3.setText("MEDISTOCK - Quản lý kho Thuốc và Vật tư y tế");

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));

        billField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                billFieldActionPerformed(evt);
            }
        });

        priceField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceFieldtxtPriceActionPerformed(evt);
            }
        });

        jLabel33.setText("ID:");

        jLabel34.setText("*Tên sản phẩm:");

        jLabel35.setText("*Ngày sản xuất:");

        jLabel36.setText("*Hạn sử dụng:");

        jLabel37.setText("Nguồn nhập:");

        jLabel38.setText("Số hóa đơn:");

        jLabel39.setText("Số lô: ");

        jLabel40.setText("*Giá sản phẩm:");

        idField.setEditable(false);

        sourceField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sourceFieldtxtSourceActionPerformed(evt);
            }
        });

        editBtn.setText("Sửa");

        clearBtn.setText("Làm mới");

        deleteBtn.setText("Xoá");

        addBtn.setText("Thêm");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        jLabel41.setText("*Số lượng:");

        numberField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberFieldtxtPriceActionPerformed(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel43.setText("(*): Thông tin bắt buộc");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel36)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel34)
                                    .addComponent(jLabel35)
                                    .addComponent(jLabel33)
                                    .addComponent(jLabel41)
                                    .addComponent(jLabel40))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nsxField, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(priceField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                                        .addComponent(numberField, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(hsdField, javax.swing.GroupLayout.Alignment.LEADING)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel39)
                            .addComponent(jLabel38)
                            .addComponent(jLabel37))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(setField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(billField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sourceField, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(clearBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deleteBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(editBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(23, 23, 23))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel43)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33)
                            .addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sourceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel37))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel39)
                            .addComponent(setField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34)
                            .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35)
                            .addComponent(nsxField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel38)
                            .addComponent(billField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel36)
                            .addComponent(hsdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel41)
                            .addComponent(numberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(addBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deleteBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(clearBtn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(priceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel43)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnStatistic))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 920, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStatistic, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );

        jPanel1.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void numberFieldtxtPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numberFieldtxtPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numberFieldtxtPriceActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addBtnActionPerformed

    private void sourceFieldtxtSourceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sourceFieldtxtSourceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sourceFieldtxtSourceActionPerformed

    private void priceFieldtxtPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceFieldtxtPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_priceFieldtxtPriceActionPerformed

    private void billFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_billFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_billFieldActionPerformed

    private void btnStatisticActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatisticActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnStatisticActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearch1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JTextField billField;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnSearch1;
    private javax.swing.JButton btnSearch2;
    private javax.swing.JButton btnStatistic;
    private javax.swing.JComboBox<String> cbbSearchChoice1;
    private javax.swing.JComboBox<String> cbbSearchChoice2;
    private javax.swing.JComboBox<String> cbbSort;
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JTextField hsdField;
    private javax.swing.JTextField idField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nameField;
    private javax.swing.JTextField nsxField;
    private javax.swing.JTextField numberField;
    private javax.swing.JTextField priceField;
    private javax.swing.JTextField setField;
    private javax.swing.JTextField sourceField;
    private javax.swing.JTable tblPharMed;
    private javax.swing.JTextField txtSearchInput;
    private javax.swing.JTextField txtSearchInput2;
    private javax.swing.JTextField txtSearchInput3;
    // End of variables declaration//GEN-END:variables
}
