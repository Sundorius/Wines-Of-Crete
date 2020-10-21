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
('Sundorius', 'manos_katsif@gmail.com', 'qweasd!1', 'Manos', 'Lasaias 45', '6955555555', '1484861468148648', '35.000', 'customer'),
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
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
