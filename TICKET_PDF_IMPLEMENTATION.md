# Implementación: Generación de Tickets PDF

## Resumen

Se ha implementado un flujo completo para generar y descargar PDFs de tickets cuando se guardan o solicitan folios. Los PDFs se almacenan en el servidor con una estructura organizada por año y mes.

---

## 1. Cambios en la Base de Datos

### Script SQL a ejecutar:

```sql
-- Agregar columnas a la tabla folios
ALTER TABLE folios 
ADD ruta_ticket VARCHAR(500) DEFAULT NULL;

ALTER TABLE folios 
ADD fecha_ticket DATETIME NULL;
```

**Archivo**: `add_ticket_columns.sql`

---

## 2. Cambios en la Entity

### Archivo: `Folio.java`

**Nuevos campos agregados**:
```java
// CAMPOS PARA TICKET PDF
private String rutaTicket;
private java.time.LocalDateTime fechaTicket;
```

**Getters y Setters**:
```java
public String getRutaTicket() {
    return rutaTicket;
}

public void setRutaTicket(String rutaTicket) {
    this.rutaTicket = rutaTicket;
}

public java.time.LocalDateTime getFechaTicket() {
    return fechaTicket;
}

public void setFechaTicket(java.time.LocalDateTime fechaTicket) {
    this.fechaTicket = fechaTicket;
}
```

---

## 3. Nuevo Servicio PDF

### Archivo: `TicketPdfService.java` (Nuevo)

Ubicación: `src/main/java/com/hanoli/demojwt/services/TicketPdfService.java`

**Características**:
- Genera PDFs usando iText
- Estructura de directorios: `/tickets/{anio}/{mes}/`
- Incluye todos los datos solicitados:
  - Folio, fecha, cliente
  - Tipo de equipo, marca, modelo, número de serie
  - Comentarios
  - Encendido, cargador y sus detalles
  - Observaciones

**Método principal**:
```java
public String generateTicketPdf(Folio folio) throws Exception
```

Retorna: ruta del archivo PDF generado

---

## 4. Servicio de Folios (Modificado)

### Archivo: `FolioService.java`

**Nueva dependencia inyectada**:
```java
@Autowired
TicketPdfService ticketPdfService;
```

**Nuevo método**:
```java
public void generarTicketPdf(Long folioId)
```

Genera el PDF y actualiza el folio con:
- `rutaTicket`: ruta del archivo
- `fechaTicket`: fecha de generación (automática)

---

## 5. Controlador (Modificado)

### Archivo: `FoliosRestController.java`

**Nuevo endpoint**:
```
GET /api/{folioId}/ticket
```

### Características del endpoint:

✅ **Descarga el PDF** del folio
✅ **Genera automáticamente** si no existe
✅ **Devuelve archivo descargable** con nombre "{folio}.pdf"
✅ **Manejo de errores** completo

### Response:
- **Éxito (200)**: Archivo PDF descargable
- **No encontrado (404)**: Folio o archivo no existe
- **Error (500)**: Error en la generación

---

## 6. Estructura de archivos

Los PDFs se guardan en:
```
tickets/
├── 2026/
│   ├── 01/
│   │   ├── F-001.pdf
│   │   ├── F-002.pdf
│   └── 02/
│       ├── F-003.pdf
```

**Formato**: `tickets/{año}/{mes}/{folio}.pdf`

---

## 7. Contenido del PDF

El ticket incluye las siguientes secciones:

### Información del Folio
- Folio
- Fecha

### Información del Cliente
- Nombre
- Cédula
- Teléfono
- Dirección

### Información del Equipo
- Tipo de equipo
- Marca
- Modelo
- Número de serie
- Encendido (Sí/No)

### Accesorios
- Incluye cargador (Sí/No)
- Marca del cargador (si aplica)
- Serial del cargador (si aplica)

### Observaciones y Comentarios
- Comentarios del folio

---

## 8. Ejemplos de uso

### Generar y descargar ticket

**GET** `/api/1/ticket`

**Respuesta**: Descarga automática de `F-001.pdf`

### Si el ticket ya existe:
Se devuelve el archivo generado previamente.

### Si el ticket no existe:
- Se genera automáticamente
- Se actualiza el folio con ruta y fecha
- Se devuelve el PDF descargable

---

## 9. Flujo de operación

1. **Request**: `GET /api/{folioId}/ticket`
2. **Validación**: Se verifica que el folio exista
3. **Verificación**: Se comprueba si ya existe ticket
4. **Generación** (si no existe):
   - Se crea estructura de directorios
   - Se genera PDF con iText
   - Se guarda en servidor
   - Se actualiza BD con ruta y fecha
5. **Descarga**: Se devuelve el archivo como descargable

---

## 10. Pasos para implementar

### 1️⃣ Ejecutar Script SQL
```sql
ALTER TABLE folios 
ADD ruta_ticket VARCHAR(500) DEFAULT NULL,
ADD fecha_ticket DATETIME NULL;
```

### 2️⃣ Crear directorio de tickets (opcional)
```bash
mkdir -p tickets
```

### 3️⃣ Recompilar el proyecto
En IntelliJ: **Build > Build Project**

### 4️⃣ Reiniciar la aplicación

### 5️⃣ Probar el endpoint
```bash
GET http://localhost:8080/api/1/ticket
```

---

## 11. Validaciones y Seguridad

✅ **Validación de folio**: Verifica que exista
✅ **Manejo de errores**: Try-catch completo
✅ **Estructura de directorios**: Creación automática
✅ **Consistencia de datos**: BD y archivo sincronizados
✅ **Sin modificación de lógica existente**: Flujo de guardar folio intacto
✅ **Historial_estatus**: No afectado

---

## 12. Archivos modificados/creados

| Archivo | Tipo | Descripción |
|---------|------|-------------|
| `Folio.java` | Modificado | Agregados campos rutaTicket y fechaTicket |
| `TicketPdfService.java` | Nuevo | Servicio de generación PDF |
| `FolioService.java` | Modificado | Método generarTicketPdf() |
| `FoliosRestController.java` | Modificado | Endpoint GET /{folioId}/ticket |
| `add_ticket_columns.sql` | Nuevo | Script para BD |

---

## 13. Notas importantes

- ⚠️ El directorio `tickets/` se crea automáticamente en la raíz del proyecto
- ⚠️ Se requiere permiso de escritura en el sistema de archivos
- ✅ Compatible con la lógica existente de folios
- ✅ No modifica guardarFolio() ni historial_estatus
- ✅ Generación automática bajo demanda
- ✅ Reutiliza PDF si ya existe (sin regenerar)

---

## 14. Troubleshooting

### El PDF no se genera
- Verificar permisos de escritura en carpeta `tickets/`
- Verificar que el folio exista en BD
- Ver logs de la aplicación

### El archivo existe pero no se descarga
- Verificar ruta en BD (`folios.ruta_ticket`)
- Verificar que el archivo existe en servidor
- Verificar permisos de lectura

### Estructura de directorios incorrecta
- Verificar que `LocalDateTime.now()` devuelva la fecha correcta
- Revisar sistema de archivos (Windows vs Linux)


