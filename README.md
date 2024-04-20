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

## DEMO CHƯƠNG TRÌNH

#### Giao diện đăng nhập
![LogInTrue](https://github.com/userhanh/MEDISTOCK/blob/master/ImageMediStock/LoginTrue.png)
![LogInFalse](https://github.com/userhanh/MEDISTOCK/blob/master/ImageMediStock/LoginFalse.png)
#### Giao diện trang chủ
- Giao diện trang chủ:
  
- Xem thông tin thuốc:
  ![View](https://github.com/userhanh/MEDISTOCK/blob/master/ImageMediStock/Info.png)
- Thêm thuốc vào kho:
  ![Add1](https://github.com/userhanh/MEDISTOCK/blob/master/ImageMediStock/Add1.png)
  ![Add2](https://github.com/userhanh/MEDISTOCK/blob/master/ImageMediStock/Add2.png)

- Sửa thông tin thuốc:
  ![Edit]()
- Xóa thuốc khỏi kho

#### Demo tính năng sắp xếp
![Sort](https://github.com/userhanh/MEDISTOCK/blob/master/ImageMediStock/SortByName.png)
#### Demo tính năng tìm kiếm
##### Tìm kiếm chính xác
- Demo tìm kiếm chính xác theo tên:
  ![SBN](https://github.com/userhanh/MEDISTOCK/blob/master/ImageMediStock/SortByName.png)
##### Tìm kiếm trên đoạn
- Demo tìm kiếm trên đoạn theo số lượng:
  ![num](https://github.com/userhanh/MEDISTOCK/blob/master/ImageMediStock/SearchInRangeByNumber.png)
- Demo tìm kiếm trên đoạn theo HSD:
  ![hsd](https://github.com/userhanh/MEDISTOCK/blob/master/ImageMediStock/SearchInRangeByHSD.png)

#### Tính năng thống kê
##### Nút thống kê
![btnsta](https://github.com/userhanh/MEDISTOCK/blob/master/ImageMediStock/ButtonStatistics.png)
##### Kết quả thống kê
![sta](https://github.com/userhanh/MEDISTOCK/blob/master/ImageMediStock/Statistics.png)
##### Các mã nguồn mở được sử dụng
