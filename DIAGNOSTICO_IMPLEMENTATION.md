# Implementación: Resultado del Diagnóstico en Folios

## Resumen de cambios

Se ha agregado soporte completo para guardar el resultado del diagnóstico de cada folio de forma independiente del estatus. Esto permite registrar si un equipo es REPARABLE, NO_REPARABLE, REQUIERE_REFACCION o SIN_FALLA.

---

## 1. Cambios en la Base de Datos

### Script SQL a ejecutar:

```sql
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
```

**Archivo**: `add_diagnostico_columns.sql`

---

## 2. Cambios en la Entity

### Archivo: `Folio.java`

**Nuevos campos agregados**:
```java
@Enumerated(EnumType.STRING)
private ResultadoDiagnostico resultadoDiagnostico;

private java.time.LocalDateTime fechaDiagnostico;
```

**Getters y Setters**:
```java
public ResultadoDiagnostico getResultadoDiagnostico() {
    return resultadoDiagnostico;
}

public void setResultadoDiagnostico(ResultadoDiagnostico resultadoDiagnostico) {
    this.resultadoDiagnostico = resultadoDiagnostico;
}

public java.time.LocalDateTime getFechaDiagnostico() {
    return fechaDiagnostico;
}

public void setFechaDiagnostico(java.time.LocalDateTime fechaDiagnostico) {
    this.fechaDiagnostico = fechaDiagnostico;
}
```

---

## 3. Enum

### Archivo: `ResultadoDiagnostico.java` (Nuevo)

```java
package com.hanoli.demojwt.entity;

public enum ResultadoDiagnostico {
    REPARABLE,
    NO_REPARABLE,
    REQUIERE_REFACCION,
    SIN_FALLA
}
```

**Ubicación**: `src/main/java/com/hanoli/demojwt/entity/ResultadoDiagnostico.java`

---

## 4. DTO de entrada

### Archivo: `SeguimientoFolioDTO.java`

**Campo nuevo agregado**:
```java
private String resultadoDiagnostico;

// Getters y Setters
public String getResultadoDiagnostico() {
    return resultadoDiagnostico;
}

public void setResultadoDiagnostico(String resultadoDiagnostico) {
    this.resultadoDiagnostico = resultadoDiagnostico;
}
```

---

## 5. Lógica en Service

### Archivo: `FolioService.java`

**Método `actualizarEstatus()` modificado**:

La lógica para guardar el diagnóstico se agregó al final del método:

```java
// NUEVO: Guardar resultado del diagnóstico si viene en el request
if (dto.getResultadoDiagnostico() != null && !dto.getResultadoDiagnostico().isEmpty()) {
    try {
        folio.setResultadoDiagnostico(
            ResultadoDiagnostico.valueOf(
                dto.getResultadoDiagnostico().toUpperCase()
            )
        );
        folio.setFechaDiagnostico(LocalDateTime.now());
    } catch (IllegalArgumentException e) {
        throw new IllegalArgumentException("Resultado de diagnóstico inválido: " + 
            dto.getResultadoDiagnostico());
    }
}

folioRepository.save(folio);
```

---

## 6. Ejemplos de uso

### Request JSON al endpoint `/api/seguimiento`

**Ejemplo 1: Con diagnóstico**
```json
{
    "folioId": 1,
    "estatusId": 2,
    "comentario": "Se realizó el diagnóstico del equipo",
    "resultadoDiagnostico": "REPARABLE",
    "cierre": null
}
```

**Ejemplo 2: Sin diagnóstico (opcional)**
```json
{
    "folioId": 1,
    "estatusId": 2,
    "comentario": "Actualizando estatus",
    "cierre": null
}
```

**Ejemplo 3: Con cierre y diagnóstico**
```json
{
    "folioId": 1,
    "estatusId": 4,
    "comentario": "Folio cerrado",
    "resultadoDiagnostico": "SIN_FALLA",
    "cierre": {
        "usoPiezas": true,
        "manoObra": 100.00,
        "total": 250.00,
        "piezas": [
            {
                "descripcion": "Batería",
                "costo": 150.00
            }
        ]
    }
}
```

### Response esperado:

```json
{
    "id": 1,
    "folio": "F-001",
    "fecha": "2026-04-01",
    "tipoEquipo": "Teléfono",
    "marca": "Samsung",
    "modelo": "A12",
    "numSerie": "ABC123",
    "comentarios": "Pantalla rota",
    "encendido": true,
    "traeCargador": true,
    "marcaCargador": "Samsung",
    "numSerieCargador": "CHG001",
    "estatusActual": {
        "id": 2,
        "nombre": "DIAGNOSTICADO"
    },
    "cliente": {
        "id": 1,
        "nombre": "Juan Pérez"
    },
    "resultadoDiagnostico": "REPARABLE",
    "fechaDiagnostico": "2026-04-03T14:30:45",
    "historial": [...]
}
```

---

## 7. Validaciones implementadas

✅ El campo `resultadoDiagnostico` es **opcional** (puede ser null)
✅ Solo se guarda cuando viene en el request
✅ Se convierte automáticamente a mayúsculas para flexibilidad
✅ Valida que sea un valor válido del enum
✅ Se registra la fecha del diagnóstico automáticamente
✅ NO rompe la lógica existente de estatus y cierre
✅ NO se guarda como un estatus en historial

---

## 8. Pasos para implementar

1. **Ejecutar el script SQL** en tu base de datos MySQL:
   ```
   Archivo: add_diagnostico_columns.sql
   ```

2. **Recompila el proyecto** en IntelliJ IDEA

3. **Reinicia la aplicación Spring Boot**

4. **Prueba el endpoint** con Insomnia/Postman

---

## 9. Valores permitidos para resultadoDiagnostico

| Valor | Descripción |
|-------|-------------|
| `REPARABLE` | El equipo se puede reparar |
| `NO_REPARABLE` | El equipo no puede repararse |
| `REQUIERE_REFACCION` | Necesita partes de repuesto |
| `SIN_FALLA` | El equipo no tiene problemas |

**Nota**: Los valores son case-insensitive en el request (pueden estar en minúsculas o mayúsculas).

---

## 10. Archivos modificados/creados

| Archivo | Tipo | Descripción |
|---------|------|-------------|
| `Folio.java` | Modificado | Agregados campos de diagnóstico |
| `ResultadoDiagnostico.java` | Nuevo | Enum con valores posibles |
| `SeguimientoFolioDTO.java` | Modificado | Campo resultadoDiagnostico |
| `FolioService.java` | Modificado | Lógica para guardar diagnóstico |
| `add_diagnostico_columns.sql` | Nuevo | Script para BD |

---

## 11. Notas importantes

- ⚠️ El diagnóstico es **independiente del estatus**, no crea nuevos estatus
- ⚠️ La fecha del diagnóstico se asigna automáticamente (no necesita enviarla)
- ⚠️ Si envías un valor inválido, la API retornará error 400
- ✅ Compatible con el cierre de folios existente
- ✅ El flujo de historial_estatus no cambia

