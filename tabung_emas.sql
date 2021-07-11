-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 11, 2021 at 02:11 PM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tabung_emas`
--

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `idtrans` int(11) NOT NULL,
  `iduser` int(11) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `waktu` varchar(100) NOT NULL,
  `jenis` varchar(30) NOT NULL,
  `nominal` double NOT NULL,
  `total` double NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`idtrans`, `iduser`, `alamat`, `waktu`, `jenis`, `nominal`, `total`) VALUES
(29, 2, 'rawa bebek', '2021/07/10 14:12:50', 'simpanan', 0.591, 0.591),
(30, 2, 'rawa bebek', '2021/07/10 14:12:57', 'simpanan', 0.473, 1.064),
(31, 2, 'rawa bebek', '2021/07/10 14:13:05', 'penarikan', 1, 0.064),
(32, 2, 'rawa bebek', '2021/07/10 14:13:14', 'simpanan', 0.355, 0.419),
(33, 2, 'rawa bebek', '2021/07/10 14:13:22', 'simpanan', 0.236, 0.655),
(34, 2, 'rawa bebek', '2021/07/10 14:13:31', 'simpanan', 0.121, 0.776),
(35, 3, 'rawa lumbu', '2021/07/10 21:00:15', 'simpanan', 1.182, 1.182),
(36, 3, 'rawa lumbu', '2021/07/10 21:00:33', 'simpanan', 1.773, 2.955),
(37, 3, 'rawa lumbu', '2021/07/10 21:00:36', 'penarikan', 2, 0.955),
(38, 3, 'rawa buaya', '2021/07/10 23:43:21', 'simpanan', 2.364, 3.319),
(39, 3, 'rawa buaya', '2021/07/10 23:43:27', 'penarikan', 2, 1.319),
(40, 6, 'sleman', '2021/07/11 13:12:26', 'simpanan', 1.182, 1.182),
(41, 6, 'sleman', '2021/07/11 13:12:50', 'simpanan', 0.118, 1.3),
(42, 6, 'sleman', '2021/07/11 13:13:00', 'simpanan', 0.236, 1.536),
(43, 6, 'sleman', '2021/07/11 13:13:36', 'penarikan', 1, 0.536),
(44, 6, 'sleman', '2021/07/11 13:13:57', 'simpanan', 0.591, 1.127),
(45, 6, 'sleman', '2021/07/11 13:14:12', 'simpanan', 0.364, 1.491),
(46, 6, 'jakarta', '2021/07/11 13:16:15', 'simpanan', 2.423, 3.914),
(47, 6, 'jakarta', '2021/07/11 13:17:02', 'penarikan', 3, 0.914),
(48, 7, 'jakarta', '2021/07/11 19:08:46', 'simpanan', 4.138, 4.138),
(49, 7, 'jakarta', '2021/07/11 19:09:10', 'penarikan', 2, 2.138);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `iduser` int(11) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `umur` int(11) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `tabungan` double NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`iduser`, `email`, `password`, `nama`, `umur`, `alamat`, `tabungan`) VALUES
(1, 'admin', 'admin', 'admin', 20, 'jakarta', 0.255),
(2, 'tes@mail.com', '123', 'gal', 19, 'rawa bebek', 0.776),
(3, 'nugraha@mail.com', '123', 'nugraha', 31, 'depok', 1.319),
(7, 'galih@mail.com', '123', 'galih nugraha', 20, 'jakarta', 2.138);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`idtrans`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`iduser`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `idtrans` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `iduser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
