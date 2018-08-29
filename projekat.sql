-- phpMyAdmin SQL Dump
-- version 4.8.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 29, 2018 at 12:24 PM
-- Server version: 10.1.33-MariaDB
-- PHP Version: 7.2.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `projekat`
--

-- --------------------------------------------------------

--
-- Table structure for table `grupa`
--

CREATE TABLE `grupa` (
  `idGrupa` int(11) NOT NULL,
  `nazivGrupe` varchar(20) NOT NULL,
  `idProfil` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `grupa`
--

INSERT INTO `grupa` (`idGrupa`, `nazivGrupe`, `idProfil`) VALUES
(51, 'Proba', 30),
(53, 'Grupa', 30),
(55, 'asddd', 30),
(57, 'w33', 30),
(58, 'code', 31),
(61, 'stagod', 31);

-- --------------------------------------------------------

--
-- Table structure for table `grupnaporuka`
--

CREATE TABLE `grupnaporuka` (
  `idGrupnaPoruka` int(11) NOT NULL,
  `idProfil` int(11) NOT NULL,
  `idGrupa` int(11) NOT NULL,
  `tekstPoruke` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `grupnaporuka`
--

INSERT INTO `grupnaporuka` (`idGrupnaPoruka`, `idProfil`, `idGrupa`, `tekstPoruke`) VALUES
(128, 31, 51, 'poz'),
(129, 31, 57, 'cao'),
(130, 30, 57, 'pozdrav');

-- --------------------------------------------------------

--
-- Table structure for table `privatnaporuka`
--

CREATE TABLE `privatnaporuka` (
  `idPrivatnaPoruka` int(11) NOT NULL,
  `idProfil` int(11) NOT NULL,
  `idPrijatelj` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `tekstPoruke` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `privatnaporuka`
--

INSERT INTO `privatnaporuka` (`idPrivatnaPoruka`, `idProfil`, `idPrijatelj`, `status`, `tekstPoruke`) VALUES
(52, 31, 30, 1, 'poz'),
(53, 30, 31, 1, 'cao'),
(54, 31, 30, 1, 'cao'),
(55, 30, 31, 1, 'da li ovo funkcionise ?');

-- --------------------------------------------------------

--
-- Table structure for table `profil`
--

CREATE TABLE `profil` (
  `idProfil` int(11) NOT NULL,
  `korisnickoIme` varchar(20) NOT NULL,
  `lozinka` varchar(20) NOT NULL,
  `ime` varchar(20) NOT NULL,
  `prezime` varchar(20) NOT NULL,
  `eMail` varchar(30) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `pol` varchar(1) NOT NULL,
  `zanimanje` varchar(30) NOT NULL,
  `datumRodjenja` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `profil`
--

INSERT INTO `profil` (`idProfil`, `korisnickoIme`, `lozinka`, `ime`, `prezime`, `eMail`, `status`, `pol`, `zanimanje`, `datumRodjenja`) VALUES
(30, 'marko', 'marko', 'Marko', 'Markovic', 'marko@gmail.com', 1, 'M', 'Stomatolog', '01.03.1990.'),
(31, 'petar', 'petar', 'Petar', 'Petrovic', 'petar@gmail.com', 1, 'M', 'Advokat', '20.11.1991.'),
(32, 'jovan', 'jovan', 'Jovan', 'jovan', 'jovan', 1, 'M', 'jovan', '01.03.1990.');

-- --------------------------------------------------------

--
-- Table structure for table `profilgrupa`
--

CREATE TABLE `profilgrupa` (
  `idProfil` int(11) NOT NULL,
  `idGrupa` int(11) NOT NULL,
  `status` int(1) NOT NULL,
  `statusPoruka` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `profilgrupa`
--

INSERT INTO `profilgrupa` (`idProfil`, `idGrupa`, `status`, `statusPoruka`) VALUES
(30, 51, 0, 1),
(30, 53, 0, 1),
(30, 55, 0, 1),
(30, 57, 0, 1),
(31, 58, 0, 0),
(31, 61, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `statusprijateljstva`
--

CREATE TABLE `statusprijateljstva` (
  `idProfil1` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `idProfil2` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `statusprijateljstva`
--

INSERT INTO `statusprijateljstva` (`idProfil1`, `status`, `idProfil2`) VALUES
(30, 7, 31),
(31, 7, 30),
(31, 7, 32),
(32, 7, 31);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `grupa`
--
ALTER TABLE `grupa`
  ADD PRIMARY KEY (`idGrupa`),
  ADD UNIQUE KEY `nazivGrupe` (`nazivGrupe`);

--
-- Indexes for table `grupnaporuka`
--
ALTER TABLE `grupnaporuka`
  ADD PRIMARY KEY (`idGrupnaPoruka`),
  ADD KEY `idProfil` (`idProfil`),
  ADD KEY `idGrupa` (`idGrupa`);

--
-- Indexes for table `privatnaporuka`
--
ALTER TABLE `privatnaporuka`
  ADD PRIMARY KEY (`idPrivatnaPoruka`),
  ADD KEY `idProfil` (`idProfil`),
  ADD KEY `idPrijatelj` (`idPrijatelj`);

--
-- Indexes for table `profil`
--
ALTER TABLE `profil`
  ADD PRIMARY KEY (`idProfil`);

--
-- Indexes for table `profilgrupa`
--
ALTER TABLE `profilgrupa`
  ADD PRIMARY KEY (`idProfil`,`idGrupa`),
  ADD KEY `idGrupa` (`idGrupa`);

--
-- Indexes for table `statusprijateljstva`
--
ALTER TABLE `statusprijateljstva`
  ADD PRIMARY KEY (`idProfil1`,`idProfil2`),
  ADD KEY `idProfil` (`idProfil2`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `grupa`
--
ALTER TABLE `grupa`
  MODIFY `idGrupa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;

--
-- AUTO_INCREMENT for table `grupnaporuka`
--
ALTER TABLE `grupnaporuka`
  MODIFY `idGrupnaPoruka` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=131;

--
-- AUTO_INCREMENT for table `privatnaporuka`
--
ALTER TABLE `privatnaporuka`
  MODIFY `idPrivatnaPoruka` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;

--
-- AUTO_INCREMENT for table `profil`
--
ALTER TABLE `profil`
  MODIFY `idProfil` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `grupnaporuka`
--
ALTER TABLE `grupnaporuka`
  ADD CONSTRAINT `grupnaporuka_ibfk_1` FOREIGN KEY (`idProfil`) REFERENCES `profil` (`idProfil`),
  ADD CONSTRAINT `grupnaporuka_ibfk_2` FOREIGN KEY (`idGrupa`) REFERENCES `grupa` (`idGrupa`);

--
-- Constraints for table `privatnaporuka`
--
ALTER TABLE `privatnaporuka`
  ADD CONSTRAINT `privatnaporuka_ibfk_1` FOREIGN KEY (`idProfil`) REFERENCES `profil` (`idProfil`),
  ADD CONSTRAINT `privatnaporuka_ibfk_2` FOREIGN KEY (`idPrijatelj`) REFERENCES `profil` (`idProfil`);

--
-- Constraints for table `profilgrupa`
--
ALTER TABLE `profilgrupa`
  ADD CONSTRAINT `profilgrupa_ibfk_1` FOREIGN KEY (`idProfil`) REFERENCES `profil` (`idProfil`),
  ADD CONSTRAINT `profilgrupa_ibfk_2` FOREIGN KEY (`idGrupa`) REFERENCES `grupa` (`idGrupa`);

--
-- Constraints for table `statusprijateljstva`
--
ALTER TABLE `statusprijateljstva`
  ADD CONSTRAINT `statusprijateljstva_ibfk_1` FOREIGN KEY (`idProfil2`) REFERENCES `profil` (`idProfil`),
  ADD CONSTRAINT `statusprijateljstva_ibfk_2` FOREIGN KEY (`idProfil1`) REFERENCES `profil` (`idProfil`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
