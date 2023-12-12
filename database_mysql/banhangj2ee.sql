-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 12, 2023 lúc 03:43 PM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `banhangj2ee1`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cartitems`
--

CREATE TABLE `cartitems` (
  `Id` int(11) NOT NULL,
  `CartID` int(11) NOT NULL,
  `ProductID` int(11) NOT NULL,
  `ColorID` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `cartitems`
--

INSERT INTO `cartitems` (`Id`, `CartID`, `ProductID`, `ColorID`, `Quantity`) VALUES
(49, 1, 1, 1, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `carts`
--

CREATE TABLE `carts` (
  `CartID` int(11) NOT NULL,
  `CustomerID` int(11) NOT NULL,
  `create_at` datetime(6) NOT NULL,
  `total_price` decimal(38,0) NOT NULL,
  `total_quantity` int(11) NOT NULL,
  `update_at` datetime(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `carts`
--

INSERT INTO `carts` (`CartID`, `CustomerID`, `create_at`, `total_price`, `total_quantity`, `update_at`) VALUES
(1, 2, '2023-12-02 08:03:55.000000', 15990000, 1, '2023-12-02 08:03:55.000000'),
(10, 1, '2023-12-05 19:50:12.000000', 0, 0, '2023-12-05 19:50:12.000000'),
(12, 8, '2023-12-08 16:39:30.000000', 0, 0, '2023-12-08 16:39:30.000000');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `colors`
--

CREATE TABLE `colors` (
  `ColorID` int(11) NOT NULL,
  `Name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `colors`
--

INSERT INTO `colors` (`ColorID`, `Name`) VALUES
(1, 'Cool Silver'),
(2, 'Ponder Blue'),
(3, 'Classic Black'),
(4, 'Black');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `customers`
--

CREATE TABLE `customers` (
  `CustomerID` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Username` varchar(20) NOT NULL,
  `Password` varchar(20) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  `Address` varchar(150) DEFAULT NULL,
  `Status` int(11) NOT NULL,
  `search_latest` tinytext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `customers`
--

INSERT INTO `customers` (`CustomerID`, `Name`, `Username`, `Password`, `Email`, `phone_number`, `Address`, `Status`, `search_latest`) VALUES
(1, 'Đỗ Lê Hi', 'huydo', '123456', 'dolehuy222@gmail.com', '0981243434', '6 Nguyễn Trãi', 1, NULL),
(2, 'Đỗ Thị Lê Hồng', 'hongdo', '123456', 'dolehuy24082002@gmail.com', '0989843844', '', 1, ''),
(8, 'Võ Văn Vũ', 'vuvan', '123456', 'vuvan@gamail.com', '0987687878', '66 Nguyễn Trãi', 0, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `orderitems`
--

CREATE TABLE `orderitems` (
  `Id` int(11) NOT NULL,
  `OrderID` int(11) NOT NULL,
  `ProductID` int(11) NOT NULL,
  `ColorID` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `orderitems`
--

INSERT INTO `orderitems` (`Id`, `OrderID`, `ProductID`, `ColorID`, `Quantity`) VALUES
(1, 1, 2, 1, 1),
(2, 1, 4, 4, 1),
(5, 8, 3, 3, 2),
(6, 9, 3, 3, 1),
(7, 10, 3, 3, 1),
(8, 11, 1, 1, 1),
(9, 11, 2, 1, 1),
(10, 12, 1, 1, 2),
(11, 12, 2, 2, 1),
(12, 13, 2, 1, 1),
(13, 14, 1, 1, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `orders`
--

CREATE TABLE `orders` (
  `OrderID` int(11) NOT NULL,
  `CustomerID` int(11) NOT NULL,
  `seller` int(11) DEFAULT NULL,
  `date` datetime(6) NOT NULL,
  `total_price` decimal(38,0) NOT NULL,
  `Address` tinytext NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  `Status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `orders`
--

INSERT INTO `orders` (`OrderID`, `CustomerID`, `seller`, `date`, `total_price`, `Address`, `phone_number`, `Status`) VALUES
(1, 2, 2, '2023-10-27 05:30:12.000000', 64980000, '6 Nguyễn Trãi', '0981169511', 4),
(8, 1, 1, '2023-12-06 22:51:37.000000', 38780000, '6 Nguyễn Trãi', '0981169511', 4),
(9, 1, 2, '2023-12-06 23:10:24.000000', 19390000, '26 Nguyễn Trãi', '0999999999', 5),
(10, 1, 2, '2023-12-06 23:35:47.000000', 19390000, '66 Nguyễn Trãi', '0237272387', 3),
(11, 1, NULL, '2023-12-06 23:37:28.000000', 43980000, '76 Nguyễn Trãi', '0945645544', 1),
(12, 2, NULL, '2023-12-07 15:54:25.000000', 59970000, '66 Nguyễn Trãi', '0981169511', 1),
(13, 1, NULL, '2023-12-07 15:56:39.000000', 27990000, '6 Nguyễn Trãi', '0945645544', 1),
(14, 2, NULL, '2023-12-07 16:07:37.000000', 15990000, '66 Nguyễn Trãi', '0981169511', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `orderstatus`
--

CREATE TABLE `orderstatus` (
  `order_status_id` int(11) NOT NULL,
  `Name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `orderstatus`
--

INSERT INTO `orderstatus` (`order_status_id`, `Name`) VALUES
(1, 'Chờ xác nhận'),
(2, 'Đang xử lý'),
(3, 'Đang giao'),
(4, 'Đã hoàn thành'),
(5, 'Đã hủy');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `productcolors`
--

CREATE TABLE `productcolors` (
  `ProductID` int(11) NOT NULL,
  `ColorID` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL,
  `Status` int(11) NOT NULL,
  `Images` tinytext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `productcolors`
--

INSERT INTO `productcolors` (`ProductID`, `ColorID`, `Quantity`, `Status`, `Images`) VALUES
(1, 1, 30, 0, 'images/1-1.png'),
(1, 3, 20, 1, 'images/1-3.png'),
(1, 4, 5, 1, 'images/1-4.png'),
(2, 1, 4, 1, 'images/ASUS-Zenbook-14-OLED-cool-siliver.jpg'),
(2, 2, 4, 1, 'images/Laptop-ASUS-Zenbook-14-OLED-Ponder-Blue.jpg'),
(3, 3, 8, 1, 'images/Laptop MSI Modern 14 Classic Black.jpg'),
(4, 4, 10, 1, 'images/Laptop MSI Creator M16 Black.jpg'),
(13, 3, 50, 1, 'images/13-3.png');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `products`
--

CREATE TABLE `products` (
  `ProductID` int(11) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Price` decimal(38,0) NOT NULL,
  `Description` tinytext NOT NULL,
  `Specifications` tinytext NOT NULL,
  `SupplierID` int(11) NOT NULL,
  `Status` int(11) NOT NULL,
  `create_at` timestamp NULL DEFAULT NULL,
  `update_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `products`
--

INSERT INTO `products` (`ProductID`, `Name`, `Price`, `Description`, `Specifications`, `SupplierID`, `Status`, `create_at`, `update_at`) VALUES
(1, 'Laptop ASUS Vivobook 16', 15990000, 'Là một trong những dòng laptop mỏng nhẹ có hiệu năng cao, thiết kế đẹp mắt, laptop ASUS Vivobook 16 M1605YA MB303W từ khi ra mắt đã là một trong những mẫu laptop mà dân văn phòng không thể bỏ qua.', 'CPU: Ryzen 3 7320U (Up to 4.1GHz) 4 Cores 8 Threads;VGA: AMD Radeon Graphics;Ram: 8GB DDR5 onboard;Ổ cứng: 256GB SSD M.2 PCIe Gen 3.0;Màn hình: 14\" TN FHD;Bảo hành 2 năm.', 1, 1, '2023-11-26 12:18:44', '2023-12-12 13:57:04'),
(2, 'Laptop ASUS Zenbook 14 OLED', 27990000, 'ASUS Zenbook 14 OLED UX3402VA KM068W mang đến một siêu phẩm laptop học tập làm việc. Sở hữu sức mạnh từ con chip Intel Gen13 mới nhất để xử lý mọi công việc từ đơn giản đến phức tạp. Màn hình cảm ', 'CPU: Ryzen 3 7320U (Up to 4.1GHz) 4 Cores 8 Threads;VGA: AMD Radeon Graphics;Ram: 8GB DDR5 onboard;Ổ cứng: 256GB SSD M.2 PCIe Gen 3.0;Màn hình: 14\" TN FHD;Bảo hành 2 năm.', 1, 1, '2023-11-26 12:19:01', '2023-11-26 12:19:01'),
(3, 'Laptop MSI Modern 14', 19390000, 'Là sản phẩm dành cho cho phân khúc laptop văn phòng giá rẻ, MSI Modern 14 C13M 607VN được tận dụng mọi điều kiện vào khả năng làm việc để tạo nên một chiếc laptop lý tưởng nhất. Khoác lên vẻ ngoài đ', 'CPU: Ryzen 3 7320U (Up to 4.1GHz) 4 Cores 8 Threads;VGA: AMD Radeon Graphics;Ram: 8GB DDR5 onboard;Ổ cứng: 256GB SSD M.2 PCIe Gen 3.0;Màn hình: 14\" TN FHD;Bảo hành 2 năm.', 2, 1, '2023-11-26 12:19:12', '2023-11-26 12:19:12'),
(4, 'Laptop MSI Creator M16', 36990000, 'Laptop MSI Creator M16 B13VE 830VN dựa trên triết lý thiết kế lấy con người làm trọng tâm, áp dụng tỷ lệ vàng trong thiết kế sản phẩm với công nghệ tiên tiến hàng đầu. Nó là bước ngoặt mới của côn', 'CPU: Ryzen 3 7320U (Up to 4.1GHz) 4 Cores 8 Threads;VGA: AMD Radeon Graphics;Ram: 8GB DDR5 onboard;Ổ cứng: 256GB SSD M.2 PCIe Gen 3.0;Màn hình: 14\" TN FHD;Bảo hành 2 năm.', 2, 1, '2023-11-26 12:19:21', '2023-11-26 12:19:21'),
(13, 'Laptop Asus ExpertBook', 8999000, 'Laptop Asus Expert Book B1400CEAE BV3186W là một chiếc laptop văn phòng chất lượng cao với hiệu suất khủng từ CPU thế hệ thứ 11, ổ lưu trữ khủng cho khả năng xử lý ổn định. ', 'CPU: i3-1215U (Up to 4.4 GHz) 6 Cores 8 Threads;VGA: Intel UHD Graphics;Ram: 8GB DDR4 onboard;Ổ cứng: 256GB SSD M.2 NVMe PCIe 4.0;Màn hình: 14\" IPS FHD', 1, 1, '2023-12-11 05:01:49', '2023-12-12 11:58:03');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `reviews`
--

CREATE TABLE `reviews` (
  `ReviewID` int(11) NOT NULL,
  `ProductID` int(11) NOT NULL,
  `CutomerID` int(11) NOT NULL,
  `Substance` tinytext NOT NULL,
  `Star` float NOT NULL,
  `review_at` datetime(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `reviews`
--

INSERT INTO `reviews` (`ReviewID`, `ProductID`, `CutomerID`, `Substance`, `Star`, `review_at`) VALUES
(1, 1, 1, 'Sản phẩm này rất tốt!', 5, '2023-12-07 21:14:16.000000'),
(2, 1, 2, '10 điểm', 5, '2023-12-07 22:20:04.000000'),
(3, 1, 2, 'Không được tốt lắm', 3, '2023-12-07 22:21:08.000000');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `roles`
--

CREATE TABLE `roles` (
  `RoleID` int(11) NOT NULL,
  `Name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `roles`
--

INSERT INTO `roles` (`RoleID`, `Name`) VALUES
(1, 'Quản trị viên'),
(2, 'Bán hàng');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sellerreplyreview`
--

CREATE TABLE `sellerreplyreview` (
  `reply_review_id` int(11) NOT NULL,
  `ReviewID` int(11) NOT NULL,
  `UserID` int(11) NOT NULL,
  `Reply` tinytext NOT NULL,
  `reply_at` datetime(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `sellerreplyreview`
--

INSERT INTO `sellerreplyreview` (`reply_review_id`, `ReviewID`, `UserID`, `Reply`, `reply_at`) VALUES
(1, 1, 2, 'Cảm ơn quý khách đã ủng hộ ạ.', '2023-12-07 21:21:03.000000'),
(2, 2, 1, 'Cảm ơn quý khách', '2023-12-12 18:56:32.000000');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `slider`
--

CREATE TABLE `slider` (
  `ID` int(11) NOT NULL,
  `ProductID` int(11) NOT NULL,
  `image` tinytext NOT NULL,
  `Title` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `slider`
--

INSERT INTO `slider` (`ID`, `ProductID`, `image`, `Title`) VALUES
(1, 1, 'images/slider-laptop-asus-vivobook-16.png', 'Hàng mới về'),
(2, 4, 'images/slider-laptop-msi-creator-m16.png', 'Bán chạy');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `status`
--

CREATE TABLE `status` (
  `StatusID` int(11) NOT NULL,
  `Name` tinytext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `status`
--

INSERT INTO `status` (`StatusID`, `Name`) VALUES
(0, 'Không được sử dụng'),
(1, 'Bình thường');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `suppliers`
--

CREATE TABLE `suppliers` (
  `SupplierID` int(11) NOT NULL,
  `Name` varchar(20) NOT NULL,
  `Status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `suppliers`
--

INSERT INTO `suppliers` (`SupplierID`, `Name`, `Status`) VALUES
(1, 'ASUS', 1),
(2, 'MSI', 1),
(3, 'ACER', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `UserID` int(11) NOT NULL,
  `Name` varchar(20) NOT NULL,
  `Username` varchar(20) NOT NULL,
  `Password` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  `address` varchar(150) DEFAULT NULL,
  `RoleID` int(11) NOT NULL,
  `Status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`UserID`, `Name`, `Username`, `Password`, `email`, `phone_number`, `address`, `RoleID`, `Status`) VALUES
(1, 'Nguyễn Văn Tèo', 'teonguyen', '123456', 'teongguyen@gmail.com', '0989843844', '273 An Dương Vương', 1, 1),
(2, 'Huỳnh Văn Khoa', 'khoahuynh', '123456', 'dolehuy24082002@gmail.com', '0989843453', '273 An Dương Vương', 2, 1);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `cartitems`
--
ALTER TABLE `cartitems`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `FK_Cart_CartItem` (`CartID`),
  ADD KEY `FK_CartItem_Product` (`ProductID`),
  ADD KEY `FK_CartItem_Color` (`ColorID`),
  ADD KEY `FKm8wu5r5hctehc1mpo7se9fjkw` (`ColorID`,`ProductID`);

--
-- Chỉ mục cho bảng `carts`
--
ALTER TABLE `carts`
  ADD PRIMARY KEY (`CartID`),
  ADD KEY `FK_Cart_Customer` (`CustomerID`);

--
-- Chỉ mục cho bảng `colors`
--
ALTER TABLE `colors`
  ADD PRIMARY KEY (`ColorID`);

--
-- Chỉ mục cho bảng `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`CustomerID`),
  ADD KEY `FK_Customer_Status` (`Status`);

--
-- Chỉ mục cho bảng `orderitems`
--
ALTER TABLE `orderitems`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `FK_Order_OrderItem` (`OrderID`),
  ADD KEY `FK_OrderItem_Product` (`ProductID`),
  ADD KEY `FK_OrderItem_Color` (`ColorID`);

--
-- Chỉ mục cho bảng `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`OrderID`),
  ADD KEY `FK_Order_Customer` (`CustomerID`),
  ADD KEY `Status` (`Status`),
  ADD KEY `seller` (`seller`);

--
-- Chỉ mục cho bảng `orderstatus`
--
ALTER TABLE `orderstatus`
  ADD PRIMARY KEY (`order_status_id`);

--
-- Chỉ mục cho bảng `productcolors`
--
ALTER TABLE `productcolors`
  ADD PRIMARY KEY (`ProductID`,`ColorID`),
  ADD KEY `FK_Color_ProductColor` (`ColorID`),
  ADD KEY `Status` (`Status`);

--
-- Chỉ mục cho bảng `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`ProductID`),
  ADD KEY `FK_Product_Supplier` (`SupplierID`),
  ADD KEY `FK_Product_Status` (`Status`);

--
-- Chỉ mục cho bảng `reviews`
--
ALTER TABLE `reviews`
  ADD PRIMARY KEY (`ReviewID`),
  ADD KEY `ProductID` (`ProductID`),
  ADD KEY `CutomerID` (`CutomerID`);

--
-- Chỉ mục cho bảng `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`RoleID`);

--
-- Chỉ mục cho bảng `sellerreplyreview`
--
ALTER TABLE `sellerreplyreview`
  ADD PRIMARY KEY (`reply_review_id`),
  ADD KEY `UserID` (`UserID`),
  ADD KEY `sellerreplyreview_ibfk_1` (`ReviewID`);

--
-- Chỉ mục cho bảng `slider`
--
ALTER TABLE `slider`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_Slider_Product` (`ProductID`);

--
-- Chỉ mục cho bảng `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`StatusID`);

--
-- Chỉ mục cho bảng `suppliers`
--
ALTER TABLE `suppliers`
  ADD PRIMARY KEY (`SupplierID`),
  ADD KEY `Status` (`Status`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`UserID`),
  ADD KEY `FK_User_Role` (`RoleID`),
  ADD KEY `FK_User_Status` (`Status`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `cartitems`
--
ALTER TABLE `cartitems`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- AUTO_INCREMENT cho bảng `carts`
--
ALTER TABLE `carts`
  MODIFY `CartID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT cho bảng `customers`
--
ALTER TABLE `customers`
  MODIFY `CustomerID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT cho bảng `orderitems`
--
ALTER TABLE `orderitems`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT cho bảng `orders`
--
ALTER TABLE `orders`
  MODIFY `OrderID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT cho bảng `orderstatus`
--
ALTER TABLE `orderstatus`
  MODIFY `order_status_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `products`
--
ALTER TABLE `products`
  MODIFY `ProductID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT cho bảng `reviews`
--
ALTER TABLE `reviews`
  MODIFY `ReviewID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `roles`
--
ALTER TABLE `roles`
  MODIFY `RoleID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `sellerreplyreview`
--
ALTER TABLE `sellerreplyreview`
  MODIFY `reply_review_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `slider`
--
ALTER TABLE `slider`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `suppliers`
--
ALTER TABLE `suppliers`
  MODIFY `SupplierID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `users`
--
ALTER TABLE `users`
  MODIFY `UserID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `cartitems`
--
ALTER TABLE `cartitems`
  ADD CONSTRAINT `FK_CartItem_Color` FOREIGN KEY (`ColorID`) REFERENCES `colors` (`ColorID`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_CartItem_Product` FOREIGN KEY (`ProductID`) REFERENCES `products` (`ProductID`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_Cart_CartItem` FOREIGN KEY (`CartID`) REFERENCES `carts` (`CartID`) ON DELETE CASCADE,
  ADD CONSTRAINT `FKm8wu5r5hctehc1mpo7se9fjkw` FOREIGN KEY (`ColorID`,`ProductID`) REFERENCES `productcolors` (`ColorID`, `ProductID`);

--
-- Các ràng buộc cho bảng `carts`
--
ALTER TABLE `carts`
  ADD CONSTRAINT `FK_Cart_Customer` FOREIGN KEY (`CustomerID`) REFERENCES `customers` (`CustomerID`) ON DELETE CASCADE;

--
-- Các ràng buộc cho bảng `customers`
--
ALTER TABLE `customers`
  ADD CONSTRAINT `FK_Customer_Status` FOREIGN KEY (`Status`) REFERENCES `status` (`StatusID`);

--
-- Các ràng buộc cho bảng `orderitems`
--
ALTER TABLE `orderitems`
  ADD CONSTRAINT `FK_OrderItem_Color` FOREIGN KEY (`ColorID`) REFERENCES `colors` (`ColorID`),
  ADD CONSTRAINT `FK_OrderItem_Product` FOREIGN KEY (`ProductID`) REFERENCES `products` (`ProductID`),
  ADD CONSTRAINT `FK_Order_OrderItem` FOREIGN KEY (`OrderID`) REFERENCES `orders` (`OrderID`) ON DELETE CASCADE;

--
-- Các ràng buộc cho bảng `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `FK_Order_Customer` FOREIGN KEY (`CustomerID`) REFERENCES `customers` (`CustomerID`),
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`Status`) REFERENCES `orderstatus` (`order_status_id`),
  ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`seller`) REFERENCES `users` (`UserID`);

--
-- Các ràng buộc cho bảng `productcolors`
--
ALTER TABLE `productcolors`
  ADD CONSTRAINT `FK_Color_ProductColor` FOREIGN KEY (`ColorID`) REFERENCES `colors` (`ColorID`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_Product_ProductColor` FOREIGN KEY (`ProductID`) REFERENCES `products` (`ProductID`) ON DELETE CASCADE,
  ADD CONSTRAINT `productcolors_ibfk_1` FOREIGN KEY (`Status`) REFERENCES `status` (`StatusID`);

--
-- Các ràng buộc cho bảng `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `FK_Product_Status` FOREIGN KEY (`Status`) REFERENCES `status` (`StatusID`),
  ADD CONSTRAINT `FK_Product_Supplier` FOREIGN KEY (`SupplierID`) REFERENCES `suppliers` (`SupplierID`);

--
-- Các ràng buộc cho bảng `reviews`
--
ALTER TABLE `reviews`
  ADD CONSTRAINT `reviews_ibfk_1` FOREIGN KEY (`ProductID`) REFERENCES `products` (`ProductID`),
  ADD CONSTRAINT `reviews_ibfk_2` FOREIGN KEY (`CutomerID`) REFERENCES `customers` (`CustomerID`);

--
-- Các ràng buộc cho bảng `sellerreplyreview`
--
ALTER TABLE `sellerreplyreview`
  ADD CONSTRAINT `sellerreplyreview_ibfk_1` FOREIGN KEY (`ReviewID`) REFERENCES `reviews` (`ReviewID`) ON DELETE CASCADE,
  ADD CONSTRAINT `sellerreplyreview_ibfk_2` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`);

--
-- Các ràng buộc cho bảng `slider`
--
ALTER TABLE `slider`
  ADD CONSTRAINT `FK_Slider_Product` FOREIGN KEY (`ProductID`) REFERENCES `products` (`ProductID`);

--
-- Các ràng buộc cho bảng `suppliers`
--
ALTER TABLE `suppliers`
  ADD CONSTRAINT `suppliers_ibfk_1` FOREIGN KEY (`Status`) REFERENCES `status` (`StatusID`);

--
-- Các ràng buộc cho bảng `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `FK_User_Role` FOREIGN KEY (`RoleID`) REFERENCES `roles` (`RoleID`),
  ADD CONSTRAINT `FK_User_Status` FOREIGN KEY (`Status`) REFERENCES `status` (`StatusID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
