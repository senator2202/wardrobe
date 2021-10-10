-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               8.0.23 - MySQL Community Server - GPL
-- Операционная система:         Win64
-- HeidiSQL Версия:              11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Дамп структуры базы данных wardrobe
DROP DATABASE IF EXISTS `wardrobe`;
CREATE DATABASE IF NOT EXISTS `wardrobe` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `wardrobe`;

-- Дамп структуры для таблица wardrobe.color
DROP TABLE IF EXISTS `color`;
CREATE TABLE IF NOT EXISTS `color` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `hex_code` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы wardrobe.color: ~4 rows (приблизительно)
/*!40000 ALTER TABLE `color` DISABLE KEYS */;
REPLACE INTO `color` (`id`, `name`, `hex_code`) VALUES
	(1, 'RED', '	#FF0000'),
	(2, 'YELLOW', '	#FFFF00'),
	(3, 'WHITE', '	#FFFFFF'),
	(4, 'BLACK', '	#000000'),
	(5, 'BLUE', '	#0000FF');
/*!40000 ALTER TABLE `color` ENABLE KEYS */;

-- Дамп структуры для таблица wardrobe.item_image
DROP TABLE IF EXISTS `item_image`;
CREATE TABLE IF NOT EXISTS `item_image` (
  `item_id` bigint DEFAULT NULL,
  `image_path` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  KEY `FK_item_image_wardrobe_item` (`item_id`),
  CONSTRAINT `FK_item_image_wardrobe_item` FOREIGN KEY (`item_id`) REFERENCES `wardrobe_item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы wardrobe.item_image: ~31 rows (приблизительно)
/*!40000 ALTER TABLE `item_image` DISABLE KEYS */;
REPLACE INTO `item_image` (`item_id`, `image_path`) VALUES
	(9, '/images/product/103b8bdd-9f80-4785-a7a3-50796e21d438.png'),
	(9, '/images/product/16903a87-8e86-4c18-b93f-5c0fbfa4a7f6.png'),
	(12, '/images/product/6abdd429-a756-4d29-b499-f227f1fe80ad.png'),
	(12, '/images/product/d3a29602-eac1-4d3f-b7f6-12f4854397b6.png'),
	(12, '/images/product/30dcbe1e-01bf-41c0-9e4c-738ad9692eac.png'),
	(14, '/images/product/aed96288-b7e9-4077-9dce-dce2065ab088.png'),
	(14, '/images/product/04e87165-f8bb-4454-8593-e3a612de9569.png'),
	(15, '/images/product/24cf1359-c308-4822-b8cf-a65d07f55603.png'),
	(15, '/images/product/575d53de-46e4-4a63-81f3-764e726061ee.png'),
	(17, '/images/product/83ac310d-b074-4311-a63d-c983932e99f3.png'),
	(17, '/images/product/396caf63-04cd-4048-9ad8-5083c5bc634e.png'),
	(18, '/images/product/5fcf0d91-1b70-4919-a216-d260da814f16.png'),
	(18, '/images/product/020ca3a9-4ffe-483f-b2de-50b9c5dc0b50.png'),
	(19, '/images/product/5eedff55-dfa9-42f1-ac49-a144f6aef2d5.png'),
	(19, '/images/product/a1455f66-94ae-4d6c-b572-ca673bfd456b.png'),
	(20, '/images/product/243f4090-9266-42be-940a-19f6709af101.png'),
	(20, '/images/product/48bafcdf-bfc3-4860-8624-5630b4d1f433.png'),
	(21, '/images/product/4645fdb4-af7d-4526-8e42-d6e95d6b516a.png'),
	(21, '/images/product/d4dd1aa8-c040-4954-928c-54e4da94144d.png'),
	(22, '/images/product/20a0eadf-3441-488a-9456-ca4e79ffccb6.png'),
	(22, '/images/product/68fc3ec6-14c5-49f8-a89b-0a73b3948bc7.png'),
	(26, '/images/product/d1b005ba-de32-4895-817d-c9bb79444f5a.png'),
	(26, '/images/product/a15faef2-d8e5-4650-a093-bc49dc61579b.png'),
	(26, '/images/product/092f35d7-7a5f-459c-a024-b14eb57f3d36.png'),
	(26, '/images/product/63e265f5-b48d-48c0-ab47-2961fb25da48.png'),
	(26, '/images/product/245cff78-f7a3-483c-a4ef-8bb8c7d26296.png'),
	(3, '/images/product/fe3ec2bd-3aa2-418c-b843-abff615004b7.png'),
	(3, '/images/product/1.png'),
	(2, '/images/product/9f44e42b-9ef2-4a6d-9d31-6528ac11162e.png'),
	(5, '/images/product/default.png'),
	(30, '/images/product/default.png'),
	(31, '/images/product/5a8fb562-edfb-4698-b88a-a779ed771478.png'),
	(31, '/images/product/4067cdc9-a03e-425b-95e2-120709a0393e.png');
/*!40000 ALTER TABLE `item_image` ENABLE KEYS */;

-- Дамп структуры для таблица wardrobe.item_size
DROP TABLE IF EXISTS `item_size`;
CREATE TABLE IF NOT EXISTS `item_size` (
  `item_id` bigint DEFAULT NULL,
  `size` varchar(50) DEFAULT NULL,
  KEY `FK_item_size_wardrobe_item` (`item_id`),
  CONSTRAINT `FK_item_size_wardrobe_item` FOREIGN KEY (`item_id`) REFERENCES `wardrobe_item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Дамп данных таблицы wardrobe.item_size: ~100 rows (приблизительно)
/*!40000 ALTER TABLE `item_size` DISABLE KEYS */;
REPLACE INTO `item_size` (`item_id`, `size`) VALUES
	(9, '44'),
	(9, '52'),
	(9, '56'),
	(9, '58'),
	(12, '48'),
	(12, '50'),
	(12, '52'),
	(12, '54'),
	(12, '56'),
	(12, '58'),
	(12, '60'),
	(14, '56'),
	(14, '58'),
	(14, '60'),
	(15, '52'),
	(15, '54'),
	(15, '56'),
	(15, '58'),
	(15, '60'),
	(17, '48'),
	(17, '50'),
	(17, '52'),
	(17, '58'),
	(18, '48'),
	(18, '50'),
	(18, '54'),
	(18, '58'),
	(18, '60'),
	(19, '44'),
	(19, '46'),
	(19, '48'),
	(19, '50'),
	(19, '52'),
	(19, '54'),
	(19, '56'),
	(19, '58'),
	(20, '42'),
	(20, '46'),
	(20, '50'),
	(20, '54'),
	(20, '58'),
	(20, '60'),
	(21, '44'),
	(21, '48'),
	(21, '50'),
	(21, '52'),
	(21, '56'),
	(21, '58'),
	(22, '44'),
	(22, '48'),
	(22, '50'),
	(22, '52'),
	(22, '56'),
	(22, '58'),
	(26, '56'),
	(26, '60'),
	(26, '62'),
	(26, '64'),
	(26, '68'),
	(26, 'S'),
	(26, 'L'),
	(26, 'XL'),
	(26, 'XXL'),
	(26, 'XXXL'),
	(26, 'XXXXL'),
	(3, '58'),
	(3, '60'),
	(3, '62'),
	(3, '64'),
	(3, '66'),
	(3, '68'),
	(3, '70'),
	(3, 'XXL'),
	(3, 'XXXL'),
	(3, 'XXXXL'),
	(2, '62'),
	(2, '64'),
	(2, '66'),
	(2, '68'),
	(2, '70'),
	(2, 'XL'),
	(2, 'XXL'),
	(2, 'XXXL'),
	(2, 'XXXXL'),
	(5, '46'),
	(5, '48'),
	(5, '52'),
	(5, '56'),
	(5, '60'),
	(30, 'XL'),
	(30, 'XXL'),
	(30, 'XXXL'),
	(30, 'XXXXL'),
	(31, 'S'),
	(31, 'M'),
	(31, 'L'),
	(31, 'XL'),
	(31, 'XXL'),
	(31, 'XXXL'),
	(31, 'XXXXL');
/*!40000 ALTER TABLE `item_size` ENABLE KEYS */;

-- Дамп структуры для таблица wardrobe.users
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` varchar(45) DEFAULT 'USER',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Дамп данных таблицы wardrobe.users: ~2 rows (приблизительно)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
REPLACE INTO `users` (`id`, `username`, `password`, `role`) VALUES
	(1, 'admin', '$2a$12$XdKM.38L9SHQR7tWvGh3CebfnR790Gw7tWVA6R9/7qKivaXTzfYyi', 'ADMIN'),
	(2, 'user', '$2a$12$SrsbZFrq8QWIdZHLZ9MtXOBLEmGLIsQgz81PImufcSxvRtZCu2cKq', 'USER');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

-- Дамп структуры для таблица wardrobe.wardrobe_item
DROP TABLE IF EXISTS `wardrobe_item`;
CREATE TABLE IF NOT EXISTS `wardrobe_item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `price` int DEFAULT NULL,
  `color_id` bigint DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_wardrobe_item_item_color` (`color_id`),
  CONSTRAINT `FK_wardrobe_item_item_color` FOREIGN KEY (`color_id`) REFERENCES `color` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы wardrobe.wardrobe_item: ~14 rows (приблизительно)
/*!40000 ALTER TABLE `wardrobe_item` DISABLE KEYS */;
REPLACE INTO `wardrobe_item` (`id`, `name`, `price`, `color_id`, `type`) VALUES
	(2, 'World', 55, 2, 'SUIT'),
	(3, 'World', 55, 2, 'PANTS'),
	(5, 'Ocean', 55, 5, 'PANTS'),
	(9, 'Forest', 45, 3, 'PANTS'),
	(12, 'Africa', 95, 2, 'SUIT'),
	(14, 'Europe', 55, 1, 'DRESS'),
	(15, 'America', 155, 2, 'BLOUSE'),
	(17, 'Florida', 44, 5, 'BLOUSE'),
	(18, 'Wahington', 123, 1, 'DRESS'),
	(19, 'Belarus', 77, 5, 'PANTS'),
	(20, 'Finland', 125, 4, 'PANTS'),
	(21, 'Belgium', 75, 5, 'BLOUSE'),
	(22, 'Baratrum', 65, 5, 'BLOUSE'),
	(26, 'Banana', 145, 1, 'DRESS'),
	(30, 'BigWorld', 75, 2, 'SUIT'),
	(31, 'Engine', 45, 2, 'DRESS');
/*!40000 ALTER TABLE `wardrobe_item` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
