-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 20 May 2018, 18:22:31
-- Sunucu sürümü: 10.1.31-MariaDB
-- PHP Sürümü: 7.2.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `kutuphane`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `islem`
--

CREATE TABLE `islem` (
  `islem_id` int(11) NOT NULL,
  `kisi_id` int(11) NOT NULL,
  `kitap_id` int(11) NOT NULL,
  `alinma_tarih` date NOT NULL,
  `iadeTarih` date DEFAULT NULL,
  `ceza` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Tablo döküm verisi `islem`
--

INSERT INTO `islem` (`islem_id`, `kisi_id`, `kitap_id`, `alinma_tarih`, `iadeTarih`, `ceza`) VALUES
(3, 3, 21, '2018-05-24', '2018-05-29', 0),
(4, 123, 21, '2018-05-08', '2018-05-29', 0),
(6, 3, 23, '2018-05-20', '2018-05-27', NULL),
(7, 3, 38, '2018-05-20', NULL, NULL),
(8, 3, 38, '2018-05-20', NULL, NULL),
(9, 1, 38, '2018-05-20', NULL, NULL),
(10, 2, 38, '2018-05-21', NULL, NULL),
(11, 33, 38, '2018-05-20', NULL, NULL),
(12, 2, 38, '2018-05-20', NULL, NULL);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `kategoriler`
--

CREATE TABLE `kategoriler` (
  `kat_id` int(11) NOT NULL,
  `kat_ad` varchar(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `kat_genelaciklama` varchar(100) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Tablo döküm verisi `kategoriler`
--

INSERT INTO `kategoriler` (`kat_id`, `kat_ad`, `kat_genelaciklama`) VALUES
(1, 'Tarih', 'Tarih alanında'),
(2, 'Edebiyat', 'Roman ve hikayeleri içerir fealan filanwerwt'),
(3, 'Ders', 'Ders kitaplarını içeren kategoridir'),
(4, 'qwqe', 'qwe'),
(5, 'sfd', 'fdgfg');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `kisiler`
--

CREATE TABLE `kisiler` (
  `kisi_tc` int(11) NOT NULL,
  `kisi_ad` varchar(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `kisi_soyad` varchar(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `kisi_id` int(12) NOT NULL,
  `kisi_sifre` varchar(12) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Tablo döküm verisi `kisiler`
--

INSERT INTO `kisiler` (`kisi_tc`, `kisi_ad`, `kisi_soyad`, `kisi_id`, `kisi_sifre`) VALUES
(0, '', '', 0, ''),
(1, '5', '5', 5, '5'),
(13, 'ert', 'rwe', 0, 'ewrt'),
(133, 'wertr', 'rwe', 0, 'ewrt');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `kitap`
--

CREATE TABLE `kitap` (
  `kitap_id` int(11) NOT NULL,
  `kitap_ad` varchar(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `kitap_aciklama` varchar(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `kitap_basimyili` int(11) NOT NULL,
  `kitap_rafta` varchar(5) CHARACTER SET utf8 COLLATE utf8_turkish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Tablo döküm verisi `kitap`
--

INSERT INTO `kitap` (`kitap_id`, `kitap_ad`, `kitap_aciklama`, `kitap_basimyili`, `kitap_rafta`) VALUES
(22, 'asd', 'dsa', 3, 'H'),
(23, 'test', 'test', 4, 'H'),
(38, 'wed', '6', 4, 'E'),
(49, 'asd', 'dsa', 3, 'H'),
(99, 'sreg', 'res', 4, 'E'),
(503, 'qwe', 'qwe', 4, 'H'),
(565, 'qwe', 'qwe', 2, 'H');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `kitapvekatid`
--

CREATE TABLE `kitapvekatid` (
  `kitap_id` int(11) NOT NULL,
  `kat_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Tablo döküm verisi `kitapvekatid`
--

INSERT INTO `kitapvekatid` (`kitap_id`, `kat_id`) VALUES
(22, 1),
(23, 7),
(503, 2);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `kitapveyazarid`
--

CREATE TABLE `kitapveyazarid` (
  `kitapid` int(11) NOT NULL,
  `yazarid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Tablo döküm verisi `kitapveyazarid`
--

INSERT INTO `kitapveyazarid` (`kitapid`, `yazarid`) VALUES
(22, 4),
(23, 7),
(503, 6),
(565, 4);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `kutuphaneciler`
--

CREATE TABLE `kutuphaneciler` (
  `kutid` int(11) NOT NULL,
  `kutsifre` varchar(10) NOT NULL,
  `kutad` varchar(50) NOT NULL,
  `kutsoyad` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Tablo döküm verisi `kutuphaneciler`
--

INSERT INTO `kutuphaneciler` (`kutid`, `kutsifre`, `kutad`, `kutsoyad`) VALUES
(3, 'se', 'vwerwer', 'ali'),
(4, 'se', 'veli', 'ali');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `yazarlar`
--

CREATE TABLE `yazarlar` (
  `yazar_id` int(11) NOT NULL,
  `yazar_ad` varchar(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `yazar_bio` text CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Tablo döküm verisi `yazarlar`
--

INSERT INTO `yazarlar` (`yazar_id`, `yazar_ad`, `yazar_bio`) VALUES
(2, 'Yahya Kemal ', 'Şair ve yazar'),
(4, 'Nazım Hikmet', 'Türk Şair'),
(6, 'Mehmet Akif', 'Şair'),
(7, 'Tolstoy', 'Rus yazar'),
(8, 'd', 'ds'),
(9, 'Refik Halit', 'Romanc?'),
(10, 'ed', 'wer'),
(11, 'ter', 'yrt'),
(12, 'ertre', 'erte'),
(13, '', ''),
(14, 'khj', 'jh'),
(15, 'sdfa', 'dsf'),
(16, 'agfd', 'hfmgv'),
(17, 'gd', 'g'),
(18, 'fgsd', 'gd'),
(19, 'srhtd', 'drth'),
(20, 'ahmet', 'yazar test');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `yöneticiler`
--

CREATE TABLE `yöneticiler` (
  `yonID` int(11) NOT NULL,
  `yonSifre` varchar(10) NOT NULL,
  `yonAd` varchar(50) NOT NULL,
  `yonSoyad` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Tablo döküm verisi `yöneticiler`
--

INSERT INTO `yöneticiler` (`yonID`, `yonSifre`, `yonAd`, `yonSoyad`) VALUES
(1, 'sifre', 'ahmet', 'mehmet');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `islem`
--
ALTER TABLE `islem`
  ADD PRIMARY KEY (`islem_id`);

--
-- Tablo için indeksler `kategoriler`
--
ALTER TABLE `kategoriler`
  ADD PRIMARY KEY (`kat_id`);

--
-- Tablo için indeksler `kisiler`
--
ALTER TABLE `kisiler`
  ADD PRIMARY KEY (`kisi_tc`);

--
-- Tablo için indeksler `kitap`
--
ALTER TABLE `kitap`
  ADD PRIMARY KEY (`kitap_id`);

--
-- Tablo için indeksler `kitapvekatid`
--
ALTER TABLE `kitapvekatid`
  ADD PRIMARY KEY (`kitap_id`,`kat_id`);

--
-- Tablo için indeksler `kitapveyazarid`
--
ALTER TABLE `kitapveyazarid`
  ADD PRIMARY KEY (`kitapid`,`yazarid`);

--
-- Tablo için indeksler `yazarlar`
--
ALTER TABLE `yazarlar`
  ADD PRIMARY KEY (`yazar_id`);

--
-- Tablo için indeksler `yöneticiler`
--
ALTER TABLE `yöneticiler`
  ADD PRIMARY KEY (`yonID`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `islem`
--
ALTER TABLE `islem`
  MODIFY `islem_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Tablo için AUTO_INCREMENT değeri `kategoriler`
--
ALTER TABLE `kategoriler`
  MODIFY `kat_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Tablo için AUTO_INCREMENT değeri `kitap`
--
ALTER TABLE `kitap`
  MODIFY `kitap_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=566;

--
-- Tablo için AUTO_INCREMENT değeri `yazarlar`
--
ALTER TABLE `yazarlar`
  MODIFY `yazar_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- Tablo için AUTO_INCREMENT değeri `yöneticiler`
--
ALTER TABLE `yöneticiler`
  MODIFY `yonID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Dökümü yapılmış tablolar için kısıtlamalar
--

--
-- Tablo kısıtlamaları `kitapvekatid`
--
ALTER TABLE `kitapvekatid`
  ADD CONSTRAINT `kitapvekatid_ibfk_1` FOREIGN KEY (`kitap_id`) REFERENCES `kitap` (`kitap_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Tablo kısıtlamaları `kitapveyazarid`
--
ALTER TABLE `kitapveyazarid`
  ADD CONSTRAINT `kitapveyazarid_ibfk_1` FOREIGN KEY (`kitapid`) REFERENCES `kitap` (`kitap_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
