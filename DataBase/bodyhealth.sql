-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 12-12-2022 a las 00:27:19
-- Versión del servidor: 8.0.17
-- Versión de PHP: 7.3.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bodyhealth`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente_detalle`
--

CREATE TABLE `cliente_detalle` (
  `id_factura` int(11) NOT NULL,
  `fecha_fin` date NOT NULL,
  `fecha_inicio` date NOT NULL,
  `id_cliente` int(11) DEFAULT NULL,
  `id_detalle` int(11) DEFAULT NULL,
  `id_metodopago` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `cliente_detalle`
--

INSERT INTO `cliente_detalle` (`id_factura`, `fecha_fin`, `fecha_inicio`, `id_cliente`, `id_detalle`, `id_metodopago`) VALUES
(30, '2023-03-11', '2022-12-11', 34, 14, 2),
(31, '2022-12-10', '2022-12-11', 31, 14, 2),
(33, '2022-12-10', '2022-12-11', 35, 5, 1),
(34, '2023-01-11', '2022-12-11', 35, 12, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente_rutina`
--

CREATE TABLE `cliente_rutina` (
  `id_clienterutina` int(11) NOT NULL,
  `id_cliente` int(11) DEFAULT NULL,
  `id_rutina` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `cliente_rutina`
--

INSERT INTO `cliente_rutina` (`id_clienterutina`, `id_cliente`, `id_rutina`) VALUES
(6, 31, 3),
(7, 35, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente_rutina_ejercicio`
--

CREATE TABLE `cliente_rutina_ejercicio` (
  `id_cliente_rutina_ejercicio` int(11) NOT NULL,
  `id_cliente_rutina` int(11) DEFAULT NULL,
  `id_rutina_ejercicio` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `cliente_rutina_ejercicio`
--

INSERT INTO `cliente_rutina_ejercicio` (`id_cliente_rutina_ejercicio`, `id_cliente_rutina`, `id_rutina_ejercicio`) VALUES
(628, 7, 4),
(633, 6, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compra`
--

CREATE TABLE `compra` (
  `id_compra` int(11) NOT NULL,
  `fecha_compra` date NOT NULL,
  `id_cliente` int(11) DEFAULT NULL,
  `id_metodopago` int(11) DEFAULT NULL,
  `total` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `compra`
--

INSERT INTO `compra` (`id_compra`, `fecha_compra`, `id_cliente`, `id_metodopago`, `total`) VALUES
(4, '2022-12-11', 34, 2, 80000),
(6, '2022-12-11', 34, 3, 240000),
(7, '2022-12-11', 34, 3, 80000),
(9, '2022-12-11', 34, 1, 80000),
(10, '2022-12-11', 34, 1, 80000),
(12, '2022-12-11', 34, 1, 80000),
(21, '2022-12-11', 34, 2, 160000),
(22, '2022-12-11', 34, 2, 160000),
(23, '2022-12-11', 35, 1, 0),
(24, '2022-12-11', 35, 1, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `control_fisico`
--

CREATE TABLE `control_fisico` (
  `id_controlcliente` int(11) NOT NULL,
  `estatura` double NOT NULL,
  `fecha` date DEFAULT NULL,
  `peso` double NOT NULL,
  `id_cliente` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `control_fisico`
--

INSERT INTO `control_fisico` (`id_controlcliente`, `estatura`, `fecha`, `peso`, `id_cliente`) VALUES
(9, 180, '2022-12-06', 80, 31),
(10, 190, '2022-12-11', 90, 31);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle`
--

CREATE TABLE `detalle` (
  `id_detalle` int(11) NOT NULL,
  `meses` int(11) NOT NULL,
  `plan` varchar(255) DEFAULT NULL,
  `precio` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `detalle`
--

INSERT INTO `detalle` (`id_detalle`, `meses`, `plan`, `precio`) VALUES
(5, 1, 'Plan Avanzado', 80000),
(12, 1, 'Plan Basico', 50000),
(13, 1, 'Plan Intermedio', 65000),
(14, 3, 'Plan Novato', 150000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ejercicio`
--

CREATE TABLE `ejercicio` (
  `id_ejercicio` int(11) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `repeticiones` int(11) NOT NULL,
  `series` varchar(255) DEFAULT NULL,
  `url_video` varchar(255) DEFAULT NULL,
  `id_musculo` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `ejercicio`
--

INSERT INTO `ejercicio` (`id_ejercicio`, `descripcion`, `repeticiones`, `series`, `url_video`, `id_musculo`) VALUES
(1, 'Ejercicios para biceps', 12, '12', 'https://www.youtube.com/watch?v=XSQrTe0mDGo', 1),
(2, 'HOS', 12, '12', 'https://www.youtube.com/watch?v=XSQrTe0mDGo', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entrenador_cliente`
--

CREATE TABLE `entrenador_cliente` (
  `id_asignacion` int(11) NOT NULL,
  `id_cliente` int(11) DEFAULT NULL,
  `id_entrenador` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `entrenador_cliente`
--

INSERT INTO `entrenador_cliente` (`id_asignacion`, `id_cliente`, `id_entrenador`) VALUES
(4, 31, 13);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `maquina`
--

CREATE TABLE `maquina` (
  `id` int(11) NOT NULL,
  `id_maquina` int(11) NOT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `observacion` varchar(255) DEFAULT NULL,
  `id_proveedor` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `maquina`
--

INSERT INTO `maquina` (`id`, `id_maquina`, `estado`, `nombre`, `observacion`, `id_proveedor`) VALUES
(1, 2, 'Bien', 'Anderson', 'no', 1),
(3, 4, 'asdasd', 'asdasd', 'asd', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `metodo_pago`
--

CREATE TABLE `metodo_pago` (
  `id_metodopago` int(11) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `metodo_pago`
--

INSERT INTO `metodo_pago` (`id_metodopago`, `descripcion`) VALUES
(1, 'Pago en efectivo'),
(2, 'PSE'),
(3, 'Tarjeta de crédito');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `musculo`
--

CREATE TABLE `musculo` (
  `id_musculo` int(11) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `musculo`
--

INSERT INTO `musculo` (`id_musculo`, `descripcion`) VALUES
(1, 'Biceps'),
(2, 'Triceps'),
(3, 'Cuadriceps');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE `pedido` (
  `id_pedido` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `id_compra` int(11) DEFAULT NULL,
  `id_producto` int(11) DEFAULT NULL,
  `total` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `pedido`
--

INSERT INTO `pedido` (`id_pedido`, `cantidad`, `id_compra`, `id_producto`, `total`) VALUES
(101, 1, 4, 2, 80000),
(102, 1, 6, 1, 80000),
(103, 1, 6, 2, 80000),
(104, 1, 6, 3, 80000),
(105, 1, 7, 2, 80000),
(107, 1, 9, 1, 80000),
(108, 1, 10, 1, 80000),
(109, 1, 12, 1, 80000),
(114, 2, 21, 3, 160000),
(115, 2, 22, 3, 160000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id_producto` int(11) NOT NULL,
  `estado` bit(1) NOT NULL,
  `foto` varchar(150) DEFAULT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `id_proveedor` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id_producto`, `estado`, `foto`, `nombre`, `precio`, `stock`, `id_proveedor`) VALUES
(1, b'1', '1647534352-41Tp711NJeL._SL500_.jpg', 'Creatina XL', 80000, 20, 1),
(2, b'1', 'megaplex-8087-7616511-1-product.jpg', 'Proteina BiPro', 80000, 18, 1),
(3, b'1', '1647534457-41UoQGPKAL._SL500_.jpg', 'Anderson', 80000, 50, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

CREATE TABLE `proveedor` (
  `id_proveedor` int(11) NOT NULL,
  `direccion` varchar(80) DEFAULT NULL,
  `nombre_empresa` varchar(50) DEFAULT NULL,
  `telefono` varchar(13) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `proveedor`
--

INSERT INTO `proveedor` (`id_proveedor`, `direccion`, `nombre_empresa`, `telefono`) VALUES
(1, 'Calle 1 #4-49 Carlos Ramirez Paris', 'UFPS', '3219238493'),
(2, 'Calle 10A #14-56 Aniversario I', 'Maquinaria Gym', '3225125254');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rutina`
--

CREATE TABLE `rutina` (
  `id_rutina` int(11) NOT NULL,
  `descripcion` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `nombre_rutina` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `rutina`
--

INSERT INTO `rutina` (`id_rutina`, `descripcion`, `nombre_rutina`) VALUES
(1, 'Basicaaasdd dBasicaaas dddBa sicaaasdddBa sicaaasdddBa sicaaasd ddBasica aasdddBasic aaasd ddBa sicaaas dddB asicaaasd ddBasic aaasddd', 'Rutina 1'),
(2, 'EEE', 'Rutina 2'),
(3, 'ads', 'Hola');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rutina_ejercicio`
--

CREATE TABLE `rutina_ejercicio` (
  `id_rutina_ejercicio` int(11) NOT NULL,
  `id_ejercicio` int(11) DEFAULT NULL,
  `id_rutina` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `rutina_ejercicio`
--

INSERT INTO `rutina_ejercicio` (`id_rutina_ejercicio`, `id_ejercicio`, `id_rutina`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 2, 2),
(4, 1, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `rol` varchar(31) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `apellido` varchar(40) DEFAULT NULL,
  `documento` int(15) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `nombre` varchar(40) DEFAULT NULL,
  `password` varchar(250) DEFAULT NULL,
  `telefono` varchar(13) DEFAULT NULL,
  `tipo_documento` varchar(3) DEFAULT NULL,
  `comentario` varchar(255) DEFAULT NULL,
  `estado` bit(1) DEFAULT NULL,
  `foto` varchar(255) DEFAULT NULL,
  `jornada` varchar(255) DEFAULT NULL,
  `experiencia` varchar(255) DEFAULT NULL,
  `hoja_vida` varchar(255) DEFAULT NULL,
  `titulo_academico` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`rol`, `id_usuario`, `apellido`, `documento`, `email`, `fecha_nacimiento`, `nombre`, `password`, `telefono`, `tipo_documento`, `comentario`, `estado`, `foto`, `jornada`, `experiencia`, `hoja_vida`, `titulo_academico`) VALUES
('TRAINER', 2, 'Uno', 333, 'trainer@prueba.com', '2022-12-04', 'Entrenador', '$2a$10$FC25fMHsKRR4mR.uhJ80UujYWYvZvy/DJKRFkcV1iAjA/9aTO4Yqm', '3152545654', 'CC', NULL, b'1', 'gym.jpg', 'Mañana', '2', 'Hoja de vida trainer', 'Entrenador profesional'),
('ADMIN', 3, 'Uno', 1004926016, 'admin@prueba.com', '2022-12-04', 'Admin', '$2a$10$eCQOUdeftLm/4PmI4b0dXO.4tK2iwr7dzRKa.H4MvklGG7vw81zK2', '3212546565', 'CC', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('TRAINER', 13, 'Orozco', 98562, 'anderson07rolon4@gmail.com', '2022-12-06', 'Anderson', '$2a$10$PZMLFwam.FKdjl5pZHJrUeuon3tufj0qDDJ9niGhyc2KMKGIalKyi', '3219238493', 'CC', NULL, b'0', 'PERFIL.jpeg', 'Mañana', '1', 'Hoja de Vida', 'Entrenador Profesional'),
('TRAINER', 19, 'Orozco2', 1111222, 'aedede@gmail.com', '2022-12-04', 'Anderson2', '$2a$10$snyEZ.GzmGpMrM5zZnABPOwPfsDC8NAnLKngX15RC4P9wxiP.nncW', '3219238493', 'CC', NULL, b'0', 'PERFIL.jpeg', 'Mañana', '1', 'Hoja de Vida', 'Entrenador Profesional'),
('TRAINER', 20, 'Orozco', 3232, 'awewe@gmail.com', '2022-11-29', 'Adriancho2', '$2a$10$RGc1vawubdQtVAUThyEzXe.XLohciIrq58m42DA1dzXG/cGx0z0VS', '3219238493', 'CC', NULL, b'0', 'ovallos-trainer.jfif', 'Mañana', '1', 'Hoja de Vida', 'Entrenador Profesional'),
('CLIENTE', 31, 'Definitiva', 95846251, 'pruebadefi@gmail.com', '2022-12-07', 'Prueba', '$2a$10$TKLlMVwgVuRZvE9jW7XLRuG.QJdoaDV.Z57/2CewC66fdghPPkWXS', '3215465', 'CC', NULL, b'0', 'PERFIL.jpeg', 'Mañana', NULL, NULL, NULL),
('CLIENTE', 34, 'Orozco', 526265847, 'anderson07rolon@gmail.com', '2003-07-07', 'Anderson', '$2a$10$KJZjcMmEBpJmQLjBxlygVeGttBdAIFg.dnKHNWpqYk3/EAT93yKz6', '3219238493', 'CC', NULL, b'1', 'perfil-ruben.png', 'Mañana', NULL, NULL, NULL),
('CLIENTE', 35, 'Orozco', 121212, 'anderson@gmail.com', '2022-12-11', 'Anderson', '$2a$10$.h1sHqQyv7xvbzwbmB8HS.SZV4Qfw2.gqPUTEGfSYWwHaxEECsgna', '3219238497', 'CC', NULL, b'1', 'PERFIL.jpeg', 'Mañana', NULL, NULL, NULL);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente_detalle`
--
ALTER TABLE `cliente_detalle`
  ADD PRIMARY KEY (`id_factura`),
  ADD KEY `FK8xlpr1puad821ivx0sit4jlse` (`id_cliente`),
  ADD KEY `FKb2d0o1tepadrnfk5bmgfymoym` (`id_detalle`),
  ADD KEY `FKhfcel7qt26amqoae809uu2c4m` (`id_metodopago`);

--
-- Indices de la tabla `cliente_rutina`
--
ALTER TABLE `cliente_rutina`
  ADD PRIMARY KEY (`id_clienterutina`),
  ADD KEY `FK37knd87yw2vd9jxh7d0bd4m26` (`id_rutina`),
  ADD KEY `FKh98uuxqsdmrssdysv3xadm8q7` (`id_cliente`);

--
-- Indices de la tabla `cliente_rutina_ejercicio`
--
ALTER TABLE `cliente_rutina_ejercicio`
  ADD PRIMARY KEY (`id_cliente_rutina_ejercicio`),
  ADD KEY `FKjd2tku9crq1tagso3djtsj91g` (`id_cliente_rutina`),
  ADD KEY `FKslcelvfptw9y2hevrkq5ma52e` (`id_rutina_ejercicio`);

--
-- Indices de la tabla `compra`
--
ALTER TABLE `compra`
  ADD PRIMARY KEY (`id_compra`),
  ADD KEY `FKh8k30jq7thcsvt426pc31e9d0` (`id_metodopago`),
  ADD KEY `FKj612tq2t6clpj47xjbqgyb0ob` (`id_cliente`);

--
-- Indices de la tabla `control_fisico`
--
ALTER TABLE `control_fisico`
  ADD PRIMARY KEY (`id_controlcliente`),
  ADD KEY `FKc4e1amspumus5g31pyigngs78` (`id_cliente`);

--
-- Indices de la tabla `detalle`
--
ALTER TABLE `detalle`
  ADD PRIMARY KEY (`id_detalle`);

--
-- Indices de la tabla `ejercicio`
--
ALTER TABLE `ejercicio`
  ADD PRIMARY KEY (`id_ejercicio`),
  ADD KEY `FKbx570cvvhlfjkti85sk0y0cnl` (`id_musculo`);

--
-- Indices de la tabla `entrenador_cliente`
--
ALTER TABLE `entrenador_cliente`
  ADD PRIMARY KEY (`id_asignacion`),
  ADD KEY `FKn9w53tx04ivox2vm5765k7ygx` (`id_entrenador`),
  ADD KEY `FKphjgs6l4frbvq186wgbr8jaq5` (`id_cliente`);

--
-- Indices de la tabla `maquina`
--
ALTER TABLE `maquina`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD KEY `FK7rna1se3828cuiwneg5h4i3kf` (`id_proveedor`);

--
-- Indices de la tabla `metodo_pago`
--
ALTER TABLE `metodo_pago`
  ADD PRIMARY KEY (`id_metodopago`);

--
-- Indices de la tabla `musculo`
--
ALTER TABLE `musculo`
  ADD PRIMARY KEY (`id_musculo`);

--
-- Indices de la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`id_pedido`),
  ADD KEY `FKc1emammk1tjnowrcgjp9ygpjj` (`id_producto`),
  ADD KEY `FKl9jgm1mq7ia39dfgo61yaul6r` (`id_compra`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id_producto`),
  ADD KEY `FKkinjnx6sxv6kf9s6i21ttfnfo` (`id_proveedor`);

--
-- Indices de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  ADD PRIMARY KEY (`id_proveedor`);

--
-- Indices de la tabla `rutina`
--
ALTER TABLE `rutina`
  ADD PRIMARY KEY (`id_rutina`);

--
-- Indices de la tabla `rutina_ejercicio`
--
ALTER TABLE `rutina_ejercicio`
  ADD PRIMARY KEY (`id_rutina_ejercicio`),
  ADD KEY `FKl5sn6h67cl8dae2ff8a9m5eqd` (`id_ejercicio`),
  ADD KEY `FKp3pwmcjo7gjcgupm9nw8eiil` (`id_rutina`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `UK_sqdsrgo7yd5nlfxh382v44rj9` (`documento`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cliente_detalle`
--
ALTER TABLE `cliente_detalle`
  MODIFY `id_factura` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT de la tabla `cliente_rutina`
--
ALTER TABLE `cliente_rutina`
  MODIFY `id_clienterutina` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `cliente_rutina_ejercicio`
--
ALTER TABLE `cliente_rutina_ejercicio`
  MODIFY `id_cliente_rutina_ejercicio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=634;

--
-- AUTO_INCREMENT de la tabla `compra`
--
ALTER TABLE `compra`
  MODIFY `id_compra` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT de la tabla `control_fisico`
--
ALTER TABLE `control_fisico`
  MODIFY `id_controlcliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `detalle`
--
ALTER TABLE `detalle`
  MODIFY `id_detalle` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de la tabla `ejercicio`
--
ALTER TABLE `ejercicio`
  MODIFY `id_ejercicio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `entrenador_cliente`
--
ALTER TABLE `entrenador_cliente`
  MODIFY `id_asignacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `maquina`
--
ALTER TABLE `maquina`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `metodo_pago`
--
ALTER TABLE `metodo_pago`
  MODIFY `id_metodopago` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `musculo`
--
ALTER TABLE `musculo`
  MODIFY `id_musculo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `pedido`
--
ALTER TABLE `pedido`
  MODIFY `id_pedido` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=116;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id_producto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  MODIFY `id_proveedor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `rutina`
--
ALTER TABLE `rutina`
  MODIFY `id_rutina` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `rutina_ejercicio`
--
ALTER TABLE `rutina_ejercicio`
  MODIFY `id_rutina_ejercicio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cliente_detalle`
--
ALTER TABLE `cliente_detalle`
  ADD CONSTRAINT `FK8xlpr1puad821ivx0sit4jlse` FOREIGN KEY (`id_cliente`) REFERENCES `usuario` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FKb2d0o1tepadrnfk5bmgfymoym` FOREIGN KEY (`id_detalle`) REFERENCES `detalle` (`id_detalle`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FKhfcel7qt26amqoae809uu2c4m` FOREIGN KEY (`id_metodopago`) REFERENCES `metodo_pago` (`id_metodopago`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `cliente_rutina`
--
ALTER TABLE `cliente_rutina`
  ADD CONSTRAINT `FK37knd87yw2vd9jxh7d0bd4m26` FOREIGN KEY (`id_rutina`) REFERENCES `rutina` (`id_rutina`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FKh98uuxqsdmrssdysv3xadm8q7` FOREIGN KEY (`id_cliente`) REFERENCES `usuario` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `cliente_rutina_ejercicio`
--
ALTER TABLE `cliente_rutina_ejercicio`
  ADD CONSTRAINT `FKjd2tku9crq1tagso3djtsj91g` FOREIGN KEY (`id_cliente_rutina`) REFERENCES `cliente_rutina` (`id_clienterutina`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FKslcelvfptw9y2hevrkq5ma52e` FOREIGN KEY (`id_rutina_ejercicio`) REFERENCES `rutina_ejercicio` (`id_rutina_ejercicio`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `compra`
--
ALTER TABLE `compra`
  ADD CONSTRAINT `FKh8k30jq7thcsvt426pc31e9d0` FOREIGN KEY (`id_metodopago`) REFERENCES `metodo_pago` (`id_metodopago`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FKj612tq2t6clpj47xjbqgyb0ob` FOREIGN KEY (`id_cliente`) REFERENCES `usuario` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `control_fisico`
--
ALTER TABLE `control_fisico`
  ADD CONSTRAINT `FKc4e1amspumus5g31pyigngs78` FOREIGN KEY (`id_cliente`) REFERENCES `usuario` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `ejercicio`
--
ALTER TABLE `ejercicio`
  ADD CONSTRAINT `FKbx570cvvhlfjkti85sk0y0cnl` FOREIGN KEY (`id_musculo`) REFERENCES `musculo` (`id_musculo`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `entrenador_cliente`
--
ALTER TABLE `entrenador_cliente`
  ADD CONSTRAINT `FKn9w53tx04ivox2vm5765k7ygx` FOREIGN KEY (`id_entrenador`) REFERENCES `usuario` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FKphjgs6l4frbvq186wgbr8jaq5` FOREIGN KEY (`id_cliente`) REFERENCES `usuario` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `maquina`
--
ALTER TABLE `maquina`
  ADD CONSTRAINT `FK7rna1se3828cuiwneg5h4i3kf` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedor` (`id_proveedor`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `FKc1emammk1tjnowrcgjp9ygpjj` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FKl9jgm1mq7ia39dfgo61yaul6r` FOREIGN KEY (`id_compra`) REFERENCES `compra` (`id_compra`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `FKkinjnx6sxv6kf9s6i21ttfnfo` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedor` (`id_proveedor`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `rutina_ejercicio`
--
ALTER TABLE `rutina_ejercicio`
  ADD CONSTRAINT `FKl5sn6h67cl8dae2ff8a9m5eqd` FOREIGN KEY (`id_ejercicio`) REFERENCES `ejercicio` (`id_ejercicio`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FKp3pwmcjo7gjcgupm9nw8eiil` FOREIGN KEY (`id_rutina`) REFERENCES `rutina` (`id_rutina`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
