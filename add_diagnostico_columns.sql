-- Script SQL para agregar soporte al resultado del diagnóstico en folios

-- Agregar columnas a la tabla folios
ALTER TABLE folios
ADD resultado_diagnostico ENUM(
  'REPARABLE',
  'NO_REPARABLE',
  'REQUIERE_REFACCION',
  'SIN_FALLA'
) DEFAULT NULL;

ALTER TABLE folios
ADD fecha_diagnostico DATETIME NULL;

-- Verificar que las columnas se agregaron correctamente
DESCRIBE folios;

-- Ver los datos (opcional)
-- SELECT id, folio, resultado_diagnostico, fecha_diagnostico FROM folios;

