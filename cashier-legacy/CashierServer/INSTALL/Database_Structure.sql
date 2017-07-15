-- phpMyAdmin SQL Dump
-- version 3.2.0.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Erstellungszeit: 21. September 2009 um 08:44
-- Server Version: 5.1.37
-- PHP-Version: 5.3.0
--
-- Dieses SQL Skript erezeugt die f�r CashierServer n�tigen Datenbankstrukturen. 
-- Sobald diese vorhanden sind, kann die Datenbank in der Konfiguration der Anwendung
-- eingetragen und CashierServer verwendet werden.
--

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Datenbank: Der Name der Datenbank kann frei gew�hlt werden,
--            muss jedoch ebenso in der Anwendung Konfiguriert werden.
--

-- --------------------------------------------------------

--
-- Tabellenstruktur f�r Tabelle `data`
-- (Darf nicht ge�ndert werden da fix im Code verankert)
--

-- !! KEINE �NDERUNGEN AM FOLGENDEN TEIL DURCHF�HREN !!

DROP TABLE IF EXISTS `data`;
CREATE TABLE IF NOT EXISTS `data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `price` double NOT NULL,
  `shop` varchar(255) NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=100 ;

--
-- Ende
--
