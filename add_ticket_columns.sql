-- Script SQL para agregar soporte de generación de tickets PDF

-- Agregar columnas a la tabla folios
ALTER TABLE folios
ADD ruta_ticket VARCHAR(500) DEFAULT NULL;

ALTER TABLE folios
ADD fecha_ticket DATETIME NULL;

-- Verificar que las columnas se agregaron correctamente
DESCRIBE folios;

-- Ver los datos (opcional)
-- SELECT id, folio, ruta_ticket, fecha_ticket FROM folios;

