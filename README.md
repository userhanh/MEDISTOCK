# MEDISTOCK

Ứng dụng dùng cho quản lý kho thuốc và vật tư y tế.

#### Tác giả:

- Chu Thị Hồng Anh D53ATB9
- Nguyễn Hữu Nguyên D53ATB9

## Tính năng:
- Xem thông tin thuốc
- Thêm thuốc vào kho
- Sửa thông tin thuốc
- Xóa thuốc khỏi kho
- Sắp xếp thuốc
- Tìm kiếm thuốc trong kho:
  - Tìm kiếm chính xác các trường: ID, Tên sản phẩm, NSX, HSD, Nguồn nhập, số lô
  - Tìm kiếm trên đoạn các trường: Số lượng nhập, Giá sản phẩm, NSX, HSD
- Thống kê:
  - Biểu đồ tròn: thống kê về số lượng theo tỉ lệ
  - Biểu đồ cột: thống kê số lượng theo tầm giá

## Cài đặt:

### Cài đặt môi trường:

- Cài đặt [JDK21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
- Cài đặt [Apache Netbeans IDE 21](https://netbeans.apache.org/front/main/download/nb21/)

### Tải mã nguồn project:

- Link mã nguồn [MediStock](https://github.com/userhanh/MEDISTOCK)
- Clone project

  ```bash
      git clone https://github.com/userhanh/MEDISTOCK
  ```

### Chạy project với mã nguồn:

- Mở project trong Apache Netbeans 16
- Chạy ứng dụng bằng cách chạy file **MediStock.java**
  ![Run](https://github.com/userhanh/MEDISTOCK/blob/master/ImageMediStock/Add/Main.png)
## DEMO CHƯƠNG TRÌNH

#### Giao diện đăng nhập
![LogInTrue](https://github.com/userhanh/MEDISTOCK/blob/master/ImageMediStock/LoginTrue.png)
![LogInFalse](https://github.com/userhanh/MEDISTOCK/blob/master/ImageMediStock/LoginFalse.png)
#### Giao diện trang chủ
- Giao diện trang chủ:
  ![Enter](https://github.com/userhanh/MEDISTOCK/blob/master/ImageMediStock/Add/PharMedView.png)
- Xem thông tin thuốc: Click vào hàng muốn xem
  ![View](https://github.com/userhanh/MEDISTOCK/blob/master/ImageMediStock/Info.png)
- Thêm thuốc vào kho: Nhập các trường thông tin (một số trường không bắt buộc) -> Click "Thêm"
- Lưu ý: Phải điền đủ các trường thông tin bắt buộc, nhập đúng định dạng NSX và HSD, NSX <= nay, HSD >= nay  
  ![Add1](https://github.com/userhanh/MEDISTOCK/blob/master/ImageMediStock/Add1.png)
  ![Add2](https://github.com/userhanh/MEDISTOCK/blob/master/ImageMediStock/Add2.png)

- Sửa thông tin thuốc: Lựa chọn thuốc bạn muốn sửa -> Sửa thông tin được hiển thị -> Click "Sửa"
  ![Edit](https://github.com/userhanh/MEDISTOCK/blob/master/ImageMediStock/Add/Edit.png)
- Xóa thuốc khỏi kho: Lựa chọn thuốc bạn muốn xóa -> Click "Xóa"
  ![Del](https://github.com/userhanh/MEDISTOCK/blob/master/ImageMediStock/Delelete.png)
- Ngoài ra còn nút làm mới sẽ xóa những thông tin đang hiển thị trên các trường  
#### Demo tính năng sắp xếp
Lựa chọn trường thông tin bạn muốn sắp xếp:
![Sort](https://github.com/userhanh/MEDISTOCK/blob/master/ImageMediStock/SortByName.png)
#### Demo tính năng tìm kiếm
##### Tìm kiếm chính xác, 
- Demo tìm kiếm chính xác theo tên:
  Ở phần tìm kiếm chính xác, lựa chọn theo Tên sản Phẩm -> Nhập nội dung muốn tìm (có dấu/không dấu/hoa/thường) -> Click nút Tìm kiếm bên cạnh
  ![SBN](https://github.com/userhanh/MEDISTOCK/blob/master/ImageMediStock/SortByName.png)
##### Tìm kiếm trên đoạn
- Demo tìm kiếm trên đoạn theo số lượng:
  Ở phần tìm kiếm theo khoảng (cho kết quả trên đoạn theo yêu cầu của cô), lựa chọn theo Số lượng -> Nhập khoảng -> Click nút Tìm kiếm
  ![num](https://github.com/userhanh/MEDISTOCK/blob/master/ImageMediStock/SearchInRangeByNumber.png)
- Demo tìm kiếm trên đoạn theo HSD:
  Ở phần tìm kiếm theo khoảng (cho kết quả trên đoạn theo yêu cầu của cô), lựa chọn theo HSD -> Nhập khoảng -> Click nút Tìm kiếm
  ![hsd](https://github.com/userhanh/MEDISTOCK/blob/master/ImageMediStock/SearchInRangeByHSD.png)

#### Tính năng thống kê
Click vào nút thông kê ở bên góc phải màn hình
![btnsta](https://github.com/userhanh/MEDISTOCK/blob/master/ImageMediStock/ButtonStatistics.png)
##### Kết quả thống kê
Một cửa sổ mới biểu thị kết quả thống kê sẽ hiện ra
![sta](https://github.com/userhanh/MEDISTOCK/blob/master/ImageMediStock/Statistics.png)
##### Các mã nguồn mở được sử dụng
- jaxb-api
- jaxb ri
- jfreechart
