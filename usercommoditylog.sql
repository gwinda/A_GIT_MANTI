-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 2018-04-22 17:10:56
-- 服务器版本： 10.1.16-MariaDB
-- PHP Version: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `shiro`
--

-- --------------------------------------------------------

--
-- 表的结构 `usercommoditylog`
--

CREATE TABLE `usercommoditylog` (
  `UCId` int(11) NOT NULL,
  `UId` int(11) NOT NULL,
  `CId` int(11) NOT NULL,
  `InFocus` int(2) NOT NULL,
  `startDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `cancellDate` datetime DEFAULT NULL,
  `cancell_date` datetime DEFAULT NULL,
  `in_focus` int(11) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户订阅商品表';

--
-- Indexes for dumped tables
--

--
-- Indexes for table `usercommoditylog`
--
ALTER TABLE `usercommoditylog`
  ADD PRIMARY KEY (`UCId`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
