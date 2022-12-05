-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 05-12-2022 a las 18:02:53
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
(2, '2022-12-04', '2022-12-04', 9, 1, 1),
(8, '2022-12-04', '2022-12-04', 1, 1, 1),
(9, '2022-12-04', '2022-12-04', 1, 1, 1),
(10, '2022-12-04', '2022-12-04', 1, 1, 1),
(11, '2023-04-10', '2022-12-05', 11, 1, 1);

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
(1, 1, 2),
(2, 9, 2),
(3, 18, 1);

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
(193, 1, 2),
(194, 1, 3),
(195, 2, 2),
(196, 2, 3),
(197, 3, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compra`
--

CREATE TABLE `compra` (
  `id_compra` int(11) NOT NULL,
  `fecha_compra` date DEFAULT NULL,
  `id_cliente` int(11) DEFAULT NULL,
  `id_metodopago` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
(2, 12, '2022-12-04', 12, NULL),
(3, 12, '2022-12-04', 12, NULL),
(4, 12, '2022-12-15', 12, 1),
(5, 195, '2022-12-05', 90, 9);

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
(1, 3, 'Plan Intermedio', 80000),
(5, 1, 'Plan Basico', 80000),
(6, 3, 'Plan Avanzado', 80000);

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
(1, 1, 2);

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
  `id_producto` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
(1, b'1', '1647534352-41Tp711NJeL._SL500_.jpg', 'Creatina XL', 80000, 10, 1);

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
(1, 'Calle 1 #4-49 Carlos Ramirez Paris', 'UFPS', '3219238493');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rutina`
--

CREATE TABLE `rutina` (
  `id_rutina` int(11) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `nombre_rutina` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `rutina`
--

INSERT INTO `rutina` (`id_rutina`, `descripcion`, `nombre_rutina`) VALUES
(1, 'Basica', 'Rutina 1'),
(2, 'EEE', 'Rutina 2');

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
(3, 2, 2);

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
('CLIENTE', 1, 'Uno', 1111, 'cliente@prueba.com', '2022-12-05', 'Cliente', '$2a$10$9pyggOgJfou1NSCl3keG0OsBFfJ6D9Pl5kc0PIrUABwl1sThRbHce', '3219238493', 'CC', NULL, b'0', '261278641_626726082271096_3891881707494189074_n.jpg', 'Tarde', NULL, NULL, NULL),
('TRAINER', 2, 'Uno', 333, 'trainer@prueba.com', '2022-12-04', 'Entrenador', '$2a$10$FC25fMHsKRR4mR.uhJ80UujYWYvZvy/DJKRFkcV1iAjA/9aTO4Yqm', '3152545654', 'CC', NULL, b'1', 'Foto trainer', 'Tarde', '24 Meses', 'Hoja de vida trainer', NULL),
('ADMIN', 3, 'Uno', 1004926016, 'admin@prueba.com', '2022-12-04', 'Admin', '$2a$10$eCQOUdeftLm/4PmI4b0dXO.4tK2iwr7dzRKa.H4MvklGG7vw81zK2', '3212546565', 'CC', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('CLIENTE', 9, 'Orozco', 9959, 'anderson07rolon@gmail.com', '2022-11-29', 'Anderson', '$2a$10$W.bsc1gNK8wQPFlVRT5TP./UH1wxBAV/E.sdn.Zgq/Fx2zdIkMoC.', '3219238493', 'CC', NULL, b'0', 'megaplex-8087-7616511-1-product.jpg', 'Mañana', NULL, NULL, NULL),
('CLIENTE', 10, 'Orozco', 123, 'anderson07rolon2@gmail.com', '2022-12-13', 'Anderson', '$2a$10$XonF2gDFSii2hLo..6L7z.Q80z3XKEnoyrL9s7jjAtqAri1hmHQ1q', '3219238493', 'CC', NULL, b'0', 'perfil-ruben.png', 'Mañana', NULL, NULL, NULL),
('CLIENTE', 11, 'Orozco', 1235989, 'anderson07rolon3@gmail.com', '2022-12-05', 'Adrian2', '$2a$10$3b8VUEZVaxSIQciGCSsOAea8lT5ikEVP/M4iUS15dnJ8cK/M/n7BC', '3219238493', 'CC', NULL, b'1', NULL, 'Mañana', NULL, NULL, NULL),
('TRAINER', 13, 'Orozco', 98562, 'anderson07rolon4@gmail.com', '2022-12-06', 'Anderson', '$2a$10$PZMLFwam.FKdjl5pZHJrUeuon3tufj0qDDJ9niGhyc2KMKGIalKyi', '3219238493', 'CC', NULL, b'1', 'PERFIL.jpeg', 'Mañana', '1', 'Hoja de Vida', 'Entrenador Profesional'),
('CLIENTE', 17, 'Orozco', 1004, 'andersn@gmail.com', '2022-12-04', 'Anderson', '$2a$10$SrttWO1Zxj1x6W7Qax4g/OcygG0LBHpvtrJVlWs6GgVqhO0f7zsHS', '3219238493', 'CC', NULL, b'0', 'PERFIL.jpeg', 'Mañana', NULL, NULL, NULL),
('CLIENTE', 18, 'Orozco', 1004926017, 'andersonrolon@gmail.com', '2022-12-04', 'Anderson', '$2a$10$xfcoyB09BZnSxb6Md6rowORQvQ5mVo0i7EPWdXui1EbqnSDGW7ysK', '3219238493', 'CC', NULL, b'0', 'PERFIL.jpeg', 'Mañana', NULL, NULL, NULL),
('TRAINER', 19, 'Orozco2', 1111222, 'aedede@gmail.com', '2022-12-04', 'Anderson2', '$2a$10$snyEZ.GzmGpMrM5zZnABPOwPfsDC8NAnLKngX15RC4P9wxiP.nncW', '3219238493', 'CC', NULL, b'0', 'PERFIL.jpeg', 'Mañana', '1', 'Hoja de Vida', 'Entrenador Profesional'),
('TRAINER', 20, 'Orozco', 3232, 'awewe@gmail.com', '2022-11-29', 'Anderson', '$2a$10$RGc1vawubdQtVAUThyEzXe.XLohciIrq58m42DA1dzXG/cGx0z0VS', '3219238493', 'CC', NULL, b'1', 'PERFIL.jpeg', 'Mañana', '1', 'Hoja de Vida', 'Entrenador Profesional');

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
  MODIFY `id_factura` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `cliente_rutina`
--
ALTER TABLE `cliente_rutina`
  MODIFY `id_clienterutina` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `cliente_rutina_ejercicio`
--
ALTER TABLE `cliente_rutina_ejercicio`
  MODIFY `id_cliente_rutina_ejercicio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=198;

--
-- AUTO_INCREMENT de la tabla `compra`
--
ALTER TABLE `compra`
  MODIFY `id_compra` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `control_fisico`
--
ALTER TABLE `control_fisico`
  MODIFY `id_controlcliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `detalle`
--
ALTER TABLE `detalle`
  MODIFY `id_detalle` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `ejercicio`
--
ALTER TABLE `ejercicio`
  MODIFY `id_ejercicio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `entrenador_cliente`
--
ALTER TABLE `entrenador_cliente`
  MODIFY `id_asignacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

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
  MODIFY `id_pedido` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id_producto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  MODIFY `id_proveedor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `rutina`
--
ALTER TABLE `rutina`
  MODIFY `id_rutina` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `rutina_ejercicio`
--
ALTER TABLE `rutina_ejercicio`
  MODIFY `id_rutina_ejercicio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

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
