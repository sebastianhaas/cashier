-- phpMyAdmin SQL Dump
-- version 3.2.0.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 27, 2010 at 09:29 PM
-- Server version: 5.1.37
-- PHP Version: 5.3.0
--
-- ORIGINAL CASHIER2 DB, 01/27/2010 21:28
--

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `cashier2`
--

-- --------------------------------------------------------

--
-- Table structure for table `accounts`
--
-- Creation: Oct 15, 2009 at 10:31 PM
-- Last update: Oct 15, 2009 at 09:31 PM
--

CREATE TABLE IF NOT EXISTS `accounts` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `nbr` double NOT NULL,
  `bin` varchar(50) NOT NULL,
  `iban` varchar(255) NOT NULL,
  `identifier` varchar(255) NOT NULL,
  `holder` int(11) NOT NULL,
  `limit` double NOT NULL,
  `created` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `accounts`
--

INSERT INTO `accounts` (`ID`, `nbr`, `bin`, `iban`, `identifier`, `holder`, `limit`, `created`) VALUES
(1, 15000006143, '55000', '1093635142', 'Sebastian Hypo Konto', 1, 250, '2009-10-03 10:22:15'),
(2, 1231231231, '11020', 'AT-976251422', 'Nix besonderes', 3, 10, '2009-10-03 12:02:05'),
(3, 123455644, '33111', 'AT-976251422', 'Nix besonderes', 2, 24, '2009-10-03 12:02:05');

-- --------------------------------------------------------

--
-- Table structure for table `data`
--
-- Creation: Oct 15, 2009 at 11:26 PM
-- Last update: Oct 15, 2009 at 10:26 PM
--

CREATE TABLE IF NOT EXISTS `data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `price` double NOT NULL,
  `shop` varchar(255) NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `data`
--


-- --------------------------------------------------------

--
-- Table structure for table `standing_orders`
--
-- Creation: Oct 15, 2009 at 10:31 PM
-- Last update: Oct 15, 2009 at 09:31 PM
--

CREATE TABLE IF NOT EXISTS `standing_orders` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `identifier` varchar(100) NOT NULL,
  `descr` varchar(255) NOT NULL,
  `beyond` double NOT NULL,
  `by` int(11) NOT NULL,
  `to` varchar(255) NOT NULL,
  `internal` int(11) NOT NULL,
  `startdate` date NOT NULL,
  `enddate` date NOT NULL,
  `interval` varchar(10) NOT NULL,
  `created` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `standing_orders`
--


-- --------------------------------------------------------

--
-- Table structure for table `storeslist`
--
-- Creation: Oct 15, 2009 at 09:31 PM
--

CREATE TABLE IF NOT EXISTS `storeslist` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Dumping data for table `storeslist`
--

INSERT INTO `storeslist` (`ID`, `name`) VALUES
(1, 'BILLA'),
(2, 'SATURN'),
(3, 'SPAR GOURMET'),
(4, 'LIDL'),
(5, 'COSMOS'),
(6, 'OBI'),
(7, 'WIENER LINIEN'),
(8, 'AMAZON'),
(9, 'MERKUR'),
(10, 'STRÖCK'),
(11, 'DM');

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--
-- Creation: Oct 15, 2009 at 11:26 PM
-- Last update: Jan 27, 2010 at 03:37 AM
--

CREATE TABLE IF NOT EXISTS `transactions` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `by` int(11) NOT NULL,
  `to` varchar(255) NOT NULL,
  `beyond` double NOT NULL,
  `private` int(11) NOT NULL,
  `internal` int(11) NOT NULL,
  `standing_order` int(11) NOT NULL,
  `datetime` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=57 ;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`ID`, `by`, `to`, `beyond`, `private`, `internal`, `standing_order`, `datetime`) VALUES
(1, 2, 'BILLA', 22.28, 0, 0, 0, '2009-10-02 13:42:00'),
(2, 2, 'BILLA', 34.98, 0, 0, 0, '2009-10-13 18:00:00'),
(3, 1, 'BILLA', 12.78, 0, 0, 0, '2009-10-06 15:16:00'),
(4, 1, 'BILLA', 13.35, 0, 0, 0, '2009-10-12 18:56:00'),
(5, 1, 'BILLA', 0.98, 0, 0, 0, '2009-09-30 11:32:00'),
(6, 1, 'BILLA', 31.09, 0, 0, 0, '2009-09-29 19:30:00'),
(7, 1, 'BILLA', 38.24, 0, 0, 0, '2009-10-16 18:33:00'),
(8, 1, 'COSMOS', 3.99, 0, 0, 0, '2009-10-01 17:47:00'),
(9, 1, 'COSMOS', 14.98, 0, 0, 0, '2009-09-30 11:05:00'),
(10, 1, 'COSMOS', 13.99, 1, 0, 0, '2009-09-30 11:05:00'),
(11, 1, 'WIENER LINIEN', 50.5, 1, 0, 0, '2009-10-02 13:20:00'),
(12, 3, 'BILLA', 11.69, 0, 0, 0, '2009-10-15 19:08:00'),
(13, 3, 'BILLA', 13.11, 0, 0, 0, '2009-10-19 18:49:00'),
(14, 1, 'AMAZON', 17.66, 1, 0, 0, '2009-10-20 11:47:00'),
(15, 2, 'BILLA', 12.86, 0, 0, 0, '2009-10-24 17:21:00'),
(16, 2, 'BILLA', 9.66, 0, 0, 0, '2009-10-19 10:01:00'),
(17, 2, 'BILLA', 28.26, 0, 0, 0, '2009-10-21 18:01:00'),
(18, 2, 'BILLA', 5.42, 0, 0, 0, '2009-10-21 18:30:00'),
(19, 3, 'BILLA', 11.41, 0, 0, 0, '2009-10-29 17:11:00'),
(20, 1, 'SATURN', 79.9, 1, 0, 0, '2009-10-15 16:04:00'),
(21, 1, 'BILLA', 37.95, 0, 0, 0, '2009-10-28 12:32:00'),
(22, 2, 'BILLA', 27.6, 0, 0, 0, '2009-11-05 19:26:00'),
(23, 3, 'BILLA', 8.95, 0, 0, 0, '2009-11-06 14:29:00'),
(24, 3, 'BILLA', 18.23, 0, 0, 0, '2009-11-06 14:29:00'),
(25, 3, 'BILLA', 2.79, 0, 0, 0, '2009-10-31 16:30:00'),
(26, 3, 'BILLA', 40.02, 0, 0, 0, '2009-10-05 16:51:00'),
(27, 3, 'BILLA', 12.78, 0, 0, 0, '2009-10-10 14:35:00'),
(28, 3, 'BILLA', 11.28, 0, 0, 0, '2009-11-09 18:03:00'),
(29, 2, 'BILLA', 17.31, 0, 0, 0, '2009-11-09 17:10:00'),
(30, 2, 'BILLA', 12.1, 0, 0, 0, '2009-11-09 11:38:00'),
(31, 3, 'BILLA', 25.72, 0, 0, 0, '2009-11-24 17:25:00'),
(32, 3, 'BILLA', 29.93, 0, 0, 0, '2009-11-19 18:04:00'),
(33, 3, 'BILLA', 15.59, 0, 0, 0, '2009-11-13 17:15:00'),
(34, 2, 'BILLA', 23.15, 0, 0, 0, '2009-11-23 17:36:00'),
(35, 2, 'BILLA', 5.94, 0, 0, 0, '2009-11-10 15:36:00'),
(36, 1, 'BILLA', 18.63, 0, 0, 0, '2009-11-17 18:50:00'),
(37, 1, 'BILLA', 3.74, 0, 0, 0, '2009-11-25 19:30:00'),
(38, 1, 'BILLA', 10.69, 0, 0, 0, '2009-12-01 19:17:00'),
(39, 1, 'BILLA', 11.96, 0, 0, 0, '2009-11-04 18:31:00'),
(40, 1, 'BILLA', 11.98, 0, 0, 0, '2009-11-11 18:37:00'),
(41, 3, 'BILLA', 35.55, 0, 0, 0, '2009-12-02 17:35:00'),
(42, 2, 'BILLA', 20.39, 0, 0, 0, '2009-12-04 19:21:00'),
(43, 2, 'BILLA', 15.84, 0, 0, 0, '2009-11-30 18:13:00'),
(44, 3, 'BILLA', 13.45, 0, 0, 0, '2009-12-07 19:18:00'),
(45, 3, 'BILLA', 30.12, 0, 0, 0, '2009-12-14 08:22:00'),
(46, 2, 'BILLA', 8.89, 0, 0, 0, '2009-12-15 18:21:00'),
(47, 2, 'BILLA', 22.53, 0, 0, 0, '2009-12-12 17:48:00'),
(48, 2, 'BILLA', 29.86, 0, 0, 0, '2009-12-09 19:32:00'),
(49, 1, 'BILLA', 12.8, 0, 0, 0, '2009-12-07 17:36:00'),
(50, 1, 'MERKUR', 49.83, 0, 0, 0, '2010-01-11 18:05:00'),
(51, 1, 'MERKUR', 21.2, 0, 0, 0, '2009-12-16 18:35:00'),
(52, 1, 'BILLA', 8.83, 0, 0, 0, '2010-01-22 14:00:00'),
(53, 1, 'MERKUR', 16.52, 0, 0, 0, '2010-01-23 17:53:00'),
(54, 1, 'BILLA', 42.89, 0, 0, 0, '2010-01-18 18:05:00'),
(55, 1, 'BILLA', 22.64, 0, 0, 0, '2010-01-26 19:08:00'),
(56, 1, 'BILLA', 9.12, 0, 0, 0, '2010-01-05 16:35:00');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--
-- Creation: Oct 15, 2009 at 10:31 PM
-- Last update: Oct 18, 2009 at 05:56 PM
--

CREATE TABLE IF NOT EXISTS `users` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `forename` varchar(250) NOT NULL,
  `surname` varchar(250) NOT NULL,
  `short` varchar(3) NOT NULL,
  `pass` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `lastlogon` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`ID`, `forename`, `surname`, `short`, `pass`, `email`, `lastlogon`) VALUES
(1, 'Sebastian', 'Haas', 'SHA', 'uGlyeeUbg4/su3TQc8qpzNhu9wo=', 'haasi@strawberry.at', '2009-10-01 23:22:11'),
(2, 'Sebastian', 'Neumaier', 'SNE', 'xE5geJEfsbh2hYPFM6ejqxa27cU=', 'sebastian.neumaier@gmx.at', '2009-10-02 09:59:27'),
(3, 'Julian', 'Huemer', 'JHU', 'I8aDSx01Pqv5duUk7UicgS/4an0=', 'julian_huemer@sbg.at', '2009-10-01 10:00:14');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
