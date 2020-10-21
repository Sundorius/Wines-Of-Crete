-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 22, 2018 at 08:06 PM
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

--
-- Indexes for dumped tables
--

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`pID`),
  ADD UNIQUE KEY `id` (`pID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `pID` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
