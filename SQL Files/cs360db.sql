-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 22, 2018 at 08:05 PM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 7.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cs360db`
--

DELIMITER $$
--
-- Functions
--
CREATE DEFINER=`root`@`localhost` FUNCTION `findDiscount` (`amount` DOUBLE) RETURNS DOUBLE BEGIN
IF (amount < 100) THEN
	RETURN 0.0;
ELSEIF 	amount < 200 THEN
	RETURN 0.05;
ELSE
	RETURN 0.1;
END IF;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `username` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `phoneNumber` varchar(10) NOT NULL,
  `debitCardNumber` varchar(16) NOT NULL,
  `debt` decimal(12,3) NOT NULL DEFAULT '0.000',
  `accountType` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`username`, `email`, `password`, `name`, `address`, `phoneNumber`, `debitCardNumber`, `debt`, `accountType`) VALUES
('Sundorius', 'testemail@gmail.com', 'qweasd!1', 'John', 'Lasaias 45', '6955555555', '1484861468148648', '35.000', 'customer'),
('TestUser', 'test@gmail.com', 'qweasd!1', 'Tester', 'nowhere', '6955234124', '4532198745632145', '0.000', 'merchant');

--
-- Triggers `client`
--
DELIMITER $$
CREATE TRIGGER `onDeletionDeleteDebitCard` BEFORE DELETE ON `client` FOR EACH ROW BEGIN
DELETE FROM debitcard WHERE debitcard.number = 'debitCardNumber';
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `consistsof`
--

CREATE TABLE `consistsof` (
  `productID` int(4) NOT NULL,
  `varietyID` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `consistsof`
--

INSERT INTO `consistsof` (`productID`, `varietyID`) VALUES
(1, 1),
(1, 2),
(2, 8),
(2, 9),
(3, 11),
(4, 1),
(5, 1),
(6, 3),
(7, 10),
(8, 3),
(8, 4),
(8, 5),
(9, 7),
(10, 7),
(10, 14),
(10, 15),
(11, 1),
(11, 2),
(12, 2),
(12, 8),
(13, 1),
(13, 2),
(14, 2),
(14, 8),
(15, 1),
(15, 8),
(16, 1),
(17, 7),
(18, 1),
(18, 2),
(18, 9),
(19, 13),
(20, 1),
(20, 6);

-- --------------------------------------------------------

--
-- Table structure for table `contains`
--

CREATE TABLE `contains` (
  `orderID` int(4) NOT NULL,
  `productID` int(4) NOT NULL,
  `quantity` int(4) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `contains`
--

INSERT INTO `contains` (`orderID`, `productID`, `quantity`) VALUES
(8, 5, 1),
(8, 8, 1),
(8, 12, 3);

-- --------------------------------------------------------

--
-- Table structure for table `debitcard`
--

CREATE TABLE `debitcard` (
  `number` varchar(16) NOT NULL,
  `verificationCode` int(4) NOT NULL,
  `expirationDate` date NOT NULL,
  `type` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `debitcard`
--

INSERT INTO `debitcard` (`number`, `verificationCode`, `expirationDate`, `type`) VALUES
('1484861468148648', 5657, '2024-01-01', 'Maestro'),
('4532198745632145', 5324, '2024-01-22', 'Visa');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `oID` int(4) NOT NULL,
  `status` int(2) NOT NULL,
  `amount` decimal(12,3) NOT NULL,
  `amountDue` decimal(12,3) NOT NULL DEFAULT '0.000',
  `discount` decimal(3,2) NOT NULL DEFAULT '0.00',
  `date` date NOT NULL,
  `clientName` varchar(45) NOT NULL,
  `daysLeftToPay` int(12) NOT NULL DEFAULT '10',
  `clientUsername` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`oID`, `status`, `amount`, `amountDue`, `discount`, `date`, `clientName`, `daysLeftToPay`, `clientUsername`) VALUES
(8, 1, '77.000', '35.000', '0.00', '2018-01-22', 'Manos', 10, 'Sundorius');

-- --------------------------------------------------------

--
-- Table structure for table `payments`
--

CREATE TABLE `payments` (
  `pID` int(4) NOT NULL,
  `clientUsername` varchar(45) NOT NULL,
  `amountPaid` decimal(65,3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `payments`
--

INSERT INTO `payments` (`pID`, `clientUsername`, `amountPaid`) VALUES
(28, 'Sundorius', '12.000'),
(29, 'Sundorius', '30.000'),
(30, 'Sundorius', '15.000');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `pID` int(4) NOT NULL,
  `name` varchar(45) NOT NULL,
  `year` int(4) NOT NULL,
  `color` varchar(45) NOT NULL,
  `price` decimal(10,3) NOT NULL,
  `winery` varchar(45) NOT NULL,
  `quantity` int(20) NOT NULL,
  `image` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`pID`, `name`, `year`, `color`, `price`, `winery`, `quantity`, `image`) VALUES
(1, 'Kotsifali-Syrah', 2011, 'red', '12.200', 'Alexakis Winery', 50, 'https://www.botilia.gr/Uploads/Media/products/product_352/%CE%9A%CE%BF%CF%84%CF%83%CE%B9%CF%86%CE%B1%CE%BB%CE%B9_Syrah-%CE%91%CE%BB%CE%B5%CE%BE%CE%B1%CE%BA%CE%B7%CF%82_.media-1943.jpg'),
(2, 'Emphasis', 2010, 'red', '11.400', 'Silva Daskalaki', 100, 'https://www.silvawines.gr/files/wines/001_new.jpg'),
(3, 'Merlot', 2006, 'red', '17.900', 'Mixalakis Estate', 100, 'http://www.winesofcrete.gr/cretewines_files/wineries/winery_857/products/138xNxlogo_merlot_2007.jpg.pagespeed.ic.kLKgxv0XDq.webp'),
(4, 'Prinos Syrah', 2015, 'red', '7.650', 'Diamantakis Winery', 100, 'https://www.akros.gr/media/4312.jpg'),
(5, 'Kudos Syrah', 2015, 'red', '9.100', 'Dourakis Winery', 100, 'https://www.botilia.gr/Uploads/Media/products/product_258/kudos_carignan_%CE%9D%CF%84%CE%BF%CF%85%CF%81%CE%B1%CE%BA%CE%B7%CF%82_.media-1464.jpg'),
(6, 'Chardonnay', 2016, 'white', '10.300', 'Douloufakis Winery', 100, 'http://www.kondor.com.gr/img/p/141-976-thickbox.jpg'),
(7, 'Lithos', 2011, 'white', '12.100', 'Karavitakis Vineyards', 100, 'http://www.winesofcrete.gr/cretewines_files/wineries/winery_820/products/138xNxlogo_lithos_leukos_mpoukali.png.pagespeed.ic.bunZ7jLHVS.webp'),
(8, 'Aeriko', 2014, 'white', '11.500', 'Digenakis Wines', 100, 'https://www.botilia.gr/Uploads/Media/products/product_502/Aeriko_Digenakis_.media-2801.jpg'),
(9, 'Vidiano', 2013, 'white', '7.400', 'Strataridaki Winery', 100, 'http://www.winesofcrete.gr/cretewines_files/wineries/winery_855/products/logo_vidiano.jpg'),
(10, 'Theon Dora', 2016, 'white', '9.900', 'Stylianou Winery', 100, 'https://www.mycretangoods.com/Images/Products/Theon-dora.jpg'),
(11, 'Enotria', 2016, 'rose', '5.500', 'Douloufakis Winery', 100, 'https://www.cretanwines.gr/images/winery/news/2016-enotria-rose/enotria-rose-douloufaki-2016-2.jpg'),
(12, 'Le Manoir', 2008, 'rose', '18.800', 'Mixalakis Estate', 100, 'http://www.winesofcrete.gr/cretewines_files/wineries/winery_857/products/logo_le_manoir_roze.jpg'),
(13, 'Melissinos', 2015, 'rose', '8.200', 'Paterianaki Winery', 100, 'http://www.winesofcrete.gr/cretewines_files/wineries/winery_852/products/melissinos_roze_bottle.jpg'),
(14, 'Rizitis', 2015, 'rose', '3.780', 'Ntourakis Winery', 100, 'https://dourakiswinery.gr/newsite/wp-content/uploads/2015/10/red_rizitis.png?x51099'),
(15, 'Oneirikon', 2016, 'rose', '5.400', 'Eurosyni Winery', 100, 'http://bakalikocrete.com/webshop/329-tonytheme_cloudzoom_big/efrosini-oneirikon-750-ml-mandilari-syrah.jpg'),
(16, 'Alargo Syrah', 2013, 'red', '9.110', 'Douloufakis Winery', 100, 'https://www.greeceandgrapes.com/image/cache/catalog/Douloufakis/alargo-syrah-douloufakis-600x600.png'),
(17, 'Dafnios', 2017, 'white', '7.300', 'Douloufakis Winery', 100, 'http://www.foodsngoods.com/media/catalog/product/cache/1/thumbnail/600x600/9df78eab33525d08d6e5fb8d27136e95/d/a/dafnios-white.png'),
(18, 'Efivos', 2016, 'rose', '7.850', 'Gavalas Wines', 100, 'http://www.foodsngoods.com/media/catalog/product/cache/1/thumbnail/600x600/9df78eab33525d08d6e5fb8d27136e95/_/2/_2016.jpg'),
(19, 'Liastos', 2009, 'red', '15.700', 'Silva Daskalaki', 100, 'https://www.mycretangoods.com/Images/Products/Liastos-red-wine-silva-Daskalaki.jpg'),
(20, 'Ypopsia', 2016, 'rose', '11.100', 'Digenakis Wines', 100, 'http://www.winesofcrete.gr/cretewines_files/wineries/winery_2532/products/138xNxlogo_ipopsia_roze.png.pagespeed.ic.iGEyyPhPnB.png');

-- --------------------------------------------------------

--
-- Table structure for table `variety`
--

CREATE TABLE `variety` (
  `vID` int(4) NOT NULL,
  `name` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `variety`
--

INSERT INTO `variety` (`vID`, `name`) VALUES
(1, 'Syrah '),
(2, 'Kotsifali'),
(3, 'Chardonnay'),
(4, 'Malvasia Aromatica'),
(5, 'Dafni'),
(6, 'Grenache Rouge'),
(7, 'Bidiano'),
(8, 'Mantilari'),
(9, 'Cabernet Sauvignon'),
(10, 'Soultanina'),
(11, 'Merlot'),
(13, 'Liatiko'),
(14, 'Trapsathiri'),
(15, 'Bilana');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`username`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `consistsof`
--
ALTER TABLE `consistsof`
  ADD PRIMARY KEY (`productID`,`varietyID`),
  ADD KEY `consistsOf_varietyID_fk` (`varietyID`);

--
-- Indexes for table `contains`
--
ALTER TABLE `contains`
  ADD PRIMARY KEY (`orderID`,`productID`),
  ADD KEY `orderID_fk` (`orderID`),
  ADD KEY `contains_productID_fk` (`productID`);

--
-- Indexes for table `debitcard`
--
ALTER TABLE `debitcard`
  ADD PRIMARY KEY (`number`),
  ADD UNIQUE KEY `number` (`number`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`oID`),
  ADD UNIQUE KEY `id` (`oID`),
  ADD KEY `order_buyerUsername_fk` (`clientUsername`);

--
-- Indexes for table `payments`
--
ALTER TABLE `payments`
  ADD PRIMARY KEY (`pID`) USING BTREE,
  ADD UNIQUE KEY `pID` (`pID`),
  ADD KEY `payments_buyerUsername_fk` (`clientUsername`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`pID`),
  ADD UNIQUE KEY `id` (`pID`);

--
-- Indexes for table `variety`
--
ALTER TABLE `variety`
  ADD PRIMARY KEY (`vID`),
  ADD UNIQUE KEY `vID` (`vID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `oID` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `payments`
--
ALTER TABLE `payments`
  MODIFY `pID` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `pID` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `variety`
--
ALTER TABLE `variety`
  MODIFY `vID` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `consistsof`
--
ALTER TABLE `consistsof`
  ADD CONSTRAINT `consistsOf_productID_fk` FOREIGN KEY (`productID`) REFERENCES `product` (`pID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `consistsOf_varietyID_fk` FOREIGN KEY (`varietyID`) REFERENCES `variety` (`vID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `contains`
--
ALTER TABLE `contains`
  ADD CONSTRAINT `contains_orderID_fk` FOREIGN KEY (`orderID`) REFERENCES `orders` (`oID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `contains_productID_fk` FOREIGN KEY (`productID`) REFERENCES `product` (`pID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `order_buyerUsername_fk` FOREIGN KEY (`clientUsername`) REFERENCES `client` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `payments`
--
ALTER TABLE `payments`
  ADD CONSTRAINT `payments_buyerUsername_fk` FOREIGN KEY (`clientUsername`) REFERENCES `client` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
