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

--
-- Indexes for dumped tables
--

--
-- Indexes for table `consistsof`
--
ALTER TABLE `consistsof`
  ADD PRIMARY KEY (`productID`,`varietyID`),
  ADD KEY `consistsOf_varietyID_fk` (`varietyID`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `consistsof`
--
ALTER TABLE `consistsof`
  ADD CONSTRAINT `consistsOf_productID_fk` FOREIGN KEY (`productID`) REFERENCES `product` (`pID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `consistsOf_varietyID_fk` FOREIGN KEY (`varietyID`) REFERENCES `variety` (`vID`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
