-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 25-07-2020 a las 02:59:47
-- Versión del servidor: 10.4.13-MariaDB
-- Versión de PHP: 7.2.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `floreria`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado`
--

CREATE TABLE `estado` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `estado`
--

INSERT INTO `estado` (`id`, `nombre`) VALUES
(1, 'Activo'),
(2, 'No Activo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE `pedido` (
  `id_pedido` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `correo` varchar(11) NOT NULL,
  `estado` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `pedido`
--

INSERT INTO `pedido` (`id_pedido`, `id_producto`, `cantidad`, `correo`, `estado`) VALUES
(1, 1, 20, 'aiep@aiep.c', 'pendiente'),
(2, 1, 20, 'Hello', 'Pendiente');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `codigo` int(20) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` text NOT NULL,
  `cantidad` int(11) NOT NULL,
  `precio` int(11) NOT NULL,
  `id_estado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`codigo`, `nombre`, `descripcion`, `cantidad`, `precio`, `id_estado`) VALUES
(1, 'Camelias', 'Flores del amazonas', 100, 1000, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` varchar(15) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `nombre`, `apellido`, `password`) VALUES
('1', 'Mauro', 'Moya', '123');

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `vista_pedido`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `vista_pedido` (
`identidad` int(11)
,`id_producto` int(11)
,`nombrepro` varchar(100)
,`correoped` varchar(11)
,`estado` varchar(50)
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `v_productos`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `v_productos` (
`codigo` int(20)
,`nombre` varchar(100)
,`descripcion` text
,`cantidad` int(11)
,`precio` int(11)
,`id` int(11)
,`e_nombre` varchar(100)
);

-- --------------------------------------------------------

--
-- Estructura para la vista `vista_pedido`
--
DROP TABLE IF EXISTS `vista_pedido`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vista_pedido`  AS  select `ped`.`id_pedido` AS `identidad`,`ped`.`id_producto` AS `id_producto`,`pro`.`nombre` AS `nombrepro`,`ped`.`correo` AS `correoped`,`ped`.`estado` AS `estado` from (`pedido` `ped` join `producto` `pro` on(`pro`.`codigo` = `ped`.`id_producto`)) ;

-- --------------------------------------------------------

--
-- Estructura para la vista `v_productos`
--
DROP TABLE IF EXISTS `v_productos`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_productos`  AS  (select `producto`.`codigo` AS `codigo`,`producto`.`nombre` AS `nombre`,`producto`.`descripcion` AS `descripcion`,`producto`.`cantidad` AS `cantidad`,`producto`.`precio` AS `precio`,`estado`.`id` AS `id`,`estado`.`nombre` AS `e_nombre` from (`producto` join `estado` on(`producto`.`id_estado` = `estado`.`id`))) ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `estado`
--
ALTER TABLE `estado`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`id_pedido`),
  ADD KEY `id_producto` (`id_producto`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `id_estado` (`id_estado`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `estado`
--
ALTER TABLE `estado`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `pedido`
--
ALTER TABLE `pedido`
  MODIFY `id_pedido` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`codigo`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`id_estado`) REFERENCES `estado` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
