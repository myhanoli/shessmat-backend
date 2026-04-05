# 🎫 Guía Rápida: Generación de Tickets PDF

## Resumen de Cambios

Se agregó la capacidad de **generar y descargar PDFs de tickets** para cada folio.

---

## 📝 Base de Datos

Ejecuta este script SQL:

```sql
ALTER TABLE folios 
ADD ruta_ticket VARCHAR(500) DEFAULT NULL,
ADD fecha_ticket DATETIME NULL;
```

---

## 🛠️ Cambios en el Código

### 1. Folio.java (Entidad)
```java
private String rutaTicket;
private java.time.LocalDateTime fechaTicket;
// + getters/setters
```

### 2. TicketPdfService.java (Nuevo)
- Servicio que genera PDFs con iText
- Crea estructura: `/tickets/{año}/{mes}/{folio}.pdf`
- Incluye: folio, cliente, equipo, accesorios, comentarios

### 3. FolioService.java (Modificado)
```java
public void generarTicketPdf(Long folioId)
```
- Genera PDF y actualiza BD

### 4. FoliosRestController.java (Modificado)
```
GET /api/{folioId}/ticket
```
- Descarga el PDF del ticket

---

## 💡 Uso

### Generar y descargar ticket:
```bash
curl -X GET http://localhost:8080/api/1/ticket > ticket.pdf
```

O en Insomnia:
- **Método**: GET
- **URL**: http://localhost:8080/api/1/ticket
- **Response**: Descarga automática del PDF

---

## 🎯 Flujo

1. Cliente solicita ticket: `GET /api/{folioId}/ticket`
2. Sistema verifica si existe
3. Si no existe → **genera automáticamente**
4. Devuelve PDF descargable

---

## 📁 Archivos Generados

Se crean en:
```
proyecto/
└── tickets/
    ├── 2026/
    │   ├── 01/
    │   │   ├── F-001.pdf
    │   │   └── F-002.pdf
    │   └── 02/
    │       └── F-003.pdf
```

---

## ✅ Características

- ✅ Generación automática bajo demanda
- ✅ Almacenamiento estructurado por año/mes
- ✅ Descarga directa como archivo
- ✅ Persiste ruta en BD
- ✅ No modifica flujo de guardar folio
- ✅ Manejo de errores completo

---

## 📋 Contenido del PDF

El ticket incluye las siguientes secciones:

| Sección | Datos |
|---------|-------|
| Folio | Número, fecha |
| Cliente | Nombre, número, teléfono, dirección |
| Equipo | Tipo, marca, modelo, serie, encendido |
| Accesorios | Cargador (marca, serial) |
| Observaciones | Comentarios del folio |

---

## ⚡ Pasos de Implementación (Resumen)

1. **SQL**: Ejecutar script `add_ticket_columns.sql`
2. **Compilar**: Recompilar el proyecto
3. **Reiniciar**: Reiniciar la aplicación
4. **Probar**: `GET /api/1/ticket`

¡Listo! 🚀


