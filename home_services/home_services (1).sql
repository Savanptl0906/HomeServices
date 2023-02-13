-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 28, 2019 at 05:08 AM
-- Server version: 5.6.12-log
-- PHP Version: 5.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `home_services`
--
CREATE DATABASE IF NOT EXISTS `home_services` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `home_services`;

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE IF NOT EXISTS `customer` (
  `name` varchar(50) NOT NULL,
  `mobile` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `address` varchar(100) NOT NULL,
  `pincode` varchar(6) NOT NULL,
  PRIMARY KEY (`mobile`,`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`name`, `mobile`, `email`, `password`, `address`, `pincode`) VALUES
('Rahul kholwala ', '+918200385990', 'rahulkholwala@gmail.com', '2020', 'Surat', '394210');

-- --------------------------------------------------------

--
-- Table structure for table `customer_location`
--

CREATE TABLE IF NOT EXISTS `customer_location` (
  `mobile` varchar(255) NOT NULL,
  `lat` double NOT NULL,
  `lng` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer_location`
--

INSERT INTO `customer_location` (`mobile`, `lat`, `lng`) VALUES
('empty', 21.1324468, 72.7187825),
('+919904662900', 21.1325151, 72.7187534),
('+919904662900', 21.1324831, 72.7187854),
('+919904662900', 21.1333505, 72.719876);

-- --------------------------------------------------------

--
-- Table structure for table `customer_tokens`
--

CREATE TABLE IF NOT EXISTS `customer_tokens` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `token` varchar(300) NOT NULL,
  `mobile` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `customer_tokens`
--

INSERT INTO `customer_tokens` (`id`, `token`, `mobile`) VALUES
(3, 'fnoRcO-GTLk:APA91bE0p_rtLCyTREdRpxBqZv6hWN2mnIQpsyKxH3UdK9JvhfspwqL-0G4c-p-qDo7DVn1zOeaDllmjtmu47ClcHnarIJQSEbijO6NNIGpRFqUCMwaPyfnd-NH0_ce-haiS2TuXneLS', '+918200385990');

-- --------------------------------------------------------

--
-- Table structure for table `provider`
--

CREATE TABLE IF NOT EXISTS `provider` (
  `shopname` varchar(50) NOT NULL,
  `name` varchar(255) NOT NULL,
  `mobile` varchar(20) NOT NULL,
  `registration` varchar(255) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `address` varchar(100) NOT NULL,
  `pincode` varchar(6) NOT NULL,
  `category` varchar(50) NOT NULL,
  `visitcharge` varchar(255) NOT NULL,
  PRIMARY KEY (`mobile`,`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `provider`
--

INSERT INTO `provider` (`shopname`, `name`, `mobile`, `registration`, `email`, `password`, `address`, `pincode`, `category`, `visitcharge`) VALUES
('Radhe Electronics', 'Kamlesh Mehta', '+918200385990', '123456789', 'kamlesh@gmail.com', '2020', 'Surat', '394210', 'Electricians', '100');

-- --------------------------------------------------------

--
-- Table structure for table `provider_location`
--

CREATE TABLE IF NOT EXISTS `provider_location` (
  `mobile` varchar(20) NOT NULL,
  `lat` double NOT NULL,
  `lng` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `provider_token`
--

CREATE TABLE IF NOT EXISTS `provider_token` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `token` varchar(300) NOT NULL,
  `mobile` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `provider_token`
--

INSERT INTO `provider_token` (`id`, `token`, `mobile`) VALUES
(2, 'fnoRcO-GTLk:APA91bE0p_rtLCyTREdRpxBqZv6hWN2mnIQpsyKxH3UdK9JvhfspwqL-0G4c-p-qDo7DVn1zOeaDllmjtmu47ClcHnarIJQSEbijO6NNIGpRFqUCMwaPyfnd-NH0_ce-haiS2TuXneLS', '+918200385990');

-- --------------------------------------------------------

--
-- Table structure for table `ratting`
--

CREATE TABLE IF NOT EXISTS `ratting` (
  `pname` varchar(255) NOT NULL,
  `cname` varchar(255) NOT NULL,
  `rate` float NOT NULL,
  `review` text NOT NULL,
  `category` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ratting`
--

INSERT INTO `ratting` (`pname`, `cname`, `rate`, `review`, `category`) VALUES
('Rajesh Geysers', 'Rahul Kholwala', 4.5, 'Nice Service', 'Geyser'),
('Rajesh Geysers', 'Monil Shah', 5, 'Nice Service', 'Geyser');

-- --------------------------------------------------------

--
-- Table structure for table `registration`
--

CREATE TABLE IF NOT EXISTS `registration` (
  `registrationno` varchar(255) NOT NULL,
  PRIMARY KEY (`registrationno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `registration`
--

INSERT INTO `registration` (`registrationno`) VALUES
('123456789'),
('987654321');

-- --------------------------------------------------------

--
-- Table structure for table `request`
--

CREATE TABLE IF NOT EXISTS `request` (
  `shopname` varchar(255) NOT NULL,
  `providername` varchar(255) NOT NULL,
  `providermobile` varchar(255) NOT NULL,
  `provideraddress` varchar(255) NOT NULL,
  `providercharge` varchar(20) NOT NULL,
  `customername` varchar(255) NOT NULL,
  `customermobile` varchar(255) NOT NULL,
  `customeraddress` varchar(255) NOT NULL,
  `model` varchar(255) NOT NULL,
  `issue` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `request`
--

INSERT INTO `request` (`shopname`, `providername`, `providermobile`, `provideraddress`, `providercharge`, `customername`, `customermobile`, `customeraddress`, `model`, `issue`) VALUES
('', 'Rajubhai Jariwala', '+918945685210', 'Nanpura,Surat', '50', 'Rahul Kholwala', '+919904662900', 'harinagar-2,udhna,surat', 'hij', ''),
('', 'Raj Shah', '+919898635698', 'Nanpura,Surat', '100', 'Rahul Kholwala', '+919904662900', 'harinagar-2,udhna,surat', 'fan', 'not working'),
('', 'Kamlesh Mehta', '+919904662900', 'Athwalines,surat', '100', 'Rahul Kholwala', '+919904662900', 'harinagar-2,udhna,surat', 'bajaj fan', 'fan is not working'),
('', 'Raj Shah', '+919898635698', 'Nanpura,Surat', '100', 'Rahul Kholwala', '+919904662900', 'harinagar-2,udhna,surat', 'lg fan', ''),
('', 'Raj Shah', '+919898635698', 'Nanpura,Surat', '100', 'Rahul Kholwala', '+919904662900', 'harinagar-2,udhna,surat', '', ''),
('', 'Kamlesh Mehta', '+919904662900', 'Athwalines,surat', '100', 'Rahul Kholwala', '+919904662900', 'harinagar-2,udhna,surat', 'Bajaj Fan ', ''),
('', 'Mayur Patel', '+919879881864', 'Udhna', '150', 'savan', '+918200385990', 'surat', 'Ushana Fan', ''),
('', 'Mayur Patel', '+919879881864', 'Udhna', '150', 'Raj kapadiya', '+919825141864', 'Adajan,Surat', 'usha fan', ''),
('Mayur Patel ', 'Mayur Electronics', '+919879881864', 'Udhna, Surat', '150', 'Raj kapadiya', '+919825141864', 'Adajan,Surat', 'Switch Board Problem', ''),
('Radhe Electronics', 'Kamlesh Mehta', '+919904662900', 'Athwalines,surat', '100', 'Raj kapadiya', '+919825141864', 'Adajan,Surat', 'Switch Board Problem ', '');

-- --------------------------------------------------------

--
-- Table structure for table `serviceman`
--

CREATE TABLE IF NOT EXISTS `serviceman` (
  `name` varchar(255) NOT NULL,
  `serviceman_mobile` varchar(255) NOT NULL,
  `provider_mobile` varchar(255) NOT NULL,
  `shopname` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `serviceman`
--

INSERT INTO `serviceman` (`name`, `serviceman_mobile`, `provider_mobile`, `shopname`, `email`, `password`) VALUES
('+919904662900', 'Radhe', 'Raj', '+919825636980', 'raj007@gmail.com', '2020'),
('bipin ', '+913698527410', '+919904662900', 'Radhe Electronics', 'b@gmail.com', '2020'),
('Rajesh ', '+913698527419', '+919904662900', 'Radhe Electronics', 'Rajesh@gmail.com', '2020');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
