--
-- Host: 127.0.0.1
-- Erstellungszeit: 27. Jun 2015 um 00:28
-- Server-Version: 5.6.24
-- PHP-Version: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Datenbank: `campinaasaservice`
--
CREATE DATABASE IF NOT EXISTS `campinaasaservice` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `campinaasaservice`;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `catagorie`
--

DROP TABLE IF EXISTS `catagorie`;
CREATE TABLE IF NOT EXISTS `catagorie` (
  `ID` int(11) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `DESCRIPTION` text NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=latin1;

--
-- TRUNCATE Tabelle vor dem Einfügen `catagorie`
--

TRUNCATE TABLE `catagorie`;
--
-- Daten für Tabelle `catagorie`
--

INSERT INTO `catagorie` (`ID`, `NAME`, `DESCRIPTION`) VALUES
(59, 'Vegetarisch', 'Vegetarische Speisen'),
(60, 'Fleisch', 'Fleisch');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `meal`
--

DROP TABLE IF EXISTS `meal`;
CREATE TABLE IF NOT EXISTS `meal` (
  `ID` int(11) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `DESCRIPTION` varchar(255) NOT NULL,
  `DATE_FROM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `DATE_TO` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `PRICE` double NOT NULL,
  `CATAGORIE_ID` int(11) NOT NULL,
  `MENU_ID` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;

--
-- TRUNCATE Tabelle vor dem Einfügen `meal`
--

TRUNCATE TABLE `meal`;
--
-- Daten für Tabelle `meal`
--

INSERT INTO `meal` (`ID`, `NAME`, `DESCRIPTION`, `DATE_FROM`, `DATE_TO`, `PRICE`, `CATAGORIE_ID`, `MENU_ID`) VALUES
(39, 'Wiener Schnitzel', 'Wiener Schnitzel vom Schwein', '2015-06-16 22:21:10', '2015-07-26 22:21:10', 15, 60, NULL),
(40, 'Gemüse Laibchen', 'Gemüse Laibchen', '2015-06-16 22:00:00', '2015-07-26 22:00:00', 14.6, 59, NULL);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `menu`
--

DROP TABLE IF EXISTS `menu`;
CREATE TABLE IF NOT EXISTS `menu` (
  `ID` varchar(255) NOT NULL,
  `DESCRIPTION` varchar(255) NOT NULL,
  `DATE_FROM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `DATE_TO` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- TRUNCATE Tabelle vor dem Einfügen `menu`
--

TRUNCATE TABLE `menu`;
--
-- Daten für Tabelle `menu`
--

INSERT INTO `menu` (`ID`, `DESCRIPTION`, `DATE_FROM`, `DATE_TO`) VALUES
('Tägliches Menü', 'Tägliches Menü', '2015-06-16 22:21:10', '2015-07-26 22:21:10');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `order`
--

DROP TABLE IF EXISTS `order`;
CREATE TABLE IF NOT EXISTS `order` (
  `ID` int(11) NOT NULL,
  `USER_ID` varchar(255) NOT NULL,
  `MEAL_ID` int(11) NOT NULL,
  `ORDER_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `SERVE_DATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `ADDITIONAL_TEXT` varchar(10000) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- TRUNCATE Tabelle vor dem Einfügen `order`
--

TRUNCATE TABLE `order`;
--
-- Daten für Tabelle `order`
--

INSERT INTO `order` (`ID`, `USER_ID`, `MEAL_ID`, `ORDER_DATE`, `SERVE_DATE`, `ADDITIONAL_TEXT`) VALUES
(11, 'w.lumetsberger@aon.at', 39, '2015-06-26 21:51:10', '2015-06-26 22:51:10', '');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `ID` varchar(255) NOT NULL,
  `FIRST_NAME` varchar(255) NOT NULL,
  `LAST_NAME` varchar(255) NOT NULL,
  `PASSWORD` varchar(255) NOT NULL,
  `LOCKED` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- TRUNCATE Tabelle vor dem Einfügen `user`
--

TRUNCATE TABLE `user`;
--
-- Daten für Tabelle `user`
--

INSERT INTO `user` (`ID`, `FIRST_NAME`, `LAST_NAME`, `PASSWORD`, `LOCKED`) VALUES
('w.lumetsberger@aon.at', 'Wolfgang', 'Lumetsberger', 'passphrase', 0);

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `catagorie`
--
ALTER TABLE `catagorie`
  ADD PRIMARY KEY (`ID`);

--
-- Indizes für die Tabelle `meal`
--
ALTER TABLE `meal`
  ADD PRIMARY KEY (`ID`), ADD KEY `CATAGORIE_ID` (`CATAGORIE_ID`);

--
-- Indizes für die Tabelle `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`ID`);

--
-- Indizes für die Tabelle `order`
--
ALTER TABLE `order`
  ADD PRIMARY KEY (`ID`), ADD KEY `USER_ID_IDX` (`USER_ID`), ADD KEY `fk_order_meal` (`MEAL_ID`);

--
-- Indizes für die Tabelle `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `catagorie`
--
ALTER TABLE `catagorie`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=61;
--
-- AUTO_INCREMENT für Tabelle `meal`
--
ALTER TABLE `meal`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=41;
--
-- AUTO_INCREMENT für Tabelle `order`
--
ALTER TABLE `order`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=12;
--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `meal`
--
ALTER TABLE `meal`
ADD CONSTRAINT `fk_catagorie` FOREIGN KEY (`CATAGORIE_ID`) REFERENCES `catagorie` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `order`
--
ALTER TABLE `order`
ADD CONSTRAINT `fk_order_meal` FOREIGN KEY (`MEAL_ID`) REFERENCES `meal` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `fk_order_user` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
