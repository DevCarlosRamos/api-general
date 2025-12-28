**API General — Documentación Completa**

Resumen: documentación en español para entender la API, sus endpoints, modelos, headers, validaciones y ejemplos. Usa `curls.sh` en la raíz para ejecutar ejemplos rápidos.

**Base URL**: http://localhost:9001 (ajusta `BASE` si tu servidor corre en otro host/puerto)

**Tecnología**: Spring WebFlux (reactive — `Mono`/`Flux`), MongoDB (modelos con `@Document`).

**Archivos relevantes**
- **Controladores**:
  - [src/main/java/uk/carlosramos/api/usuario/usuarioConContra/controlador/UsuarioConContraControlador.java](src/main/java/uk/carlosramos/api/usuario/usuarioConContra/controlador/UsuarioConContraControlador.java)
  - [src/main/java/uk/carlosramos/api/usuario/usuarioConCorreo/controlador/UsuarioConCorreoControlador.java](src/main/java/uk/carlosramos/api/usuario/usuarioConCorreo/controlador/UsuarioConCorreoControlador.java)
  - [src/main/java/uk/carlosramos/api/usuario/usuarioSinContra/controlador/UsuarioSinContraControlador.java](src/main/java/uk/carlosramos/api/usuario/usuarioSinContra/controlador/UsuarioSinContraControlador.java)
  - [src/main/java/uk/carlosramos/api/video/controlador/VideoController.java](src/main/java/uk/carlosramos/api/video/controlador/VideoController.java)
- **Modelos** (request / response):
  - [src/main/java/uk/carlosramos/api/usuario/usuarioConContra/modelo/UsuarioConContraModeloReq.java](src/main/java/uk/carlosramos/api/usuario/usuarioConContra/modelo/UsuarioConContraModeloReq.java)
  - [src/main/java/uk/carlosramos/api/usuario/usuarioConContra/modelo/UsuarioConContraModeloRes.java](src/main/java/uk/carlosramos/api/usuario/usuarioConContra/modelo/UsuarioConContraModeloRes.java)
  - [src/main/java/uk/carlosramos/api/usuario/usuarioConCorreo/modelo/UsuarioConCorreoModeloReq.java](src/main/java/uk/carlosramos/api/usuario/usuarioConCorreo/modelo/UsuarioConCorreoModeloReq.java)
  - [src/main/java/uk/carlosramos/api/usuario/usuarioConCorreo/modelo/UsuarioConCorreoModeloRes.java](src/main/java/uk/carlosramos/api/usuario/usuarioConCorreo/modelo/UsuarioConCorreoModeloRes.java)
  - [src/main/java/uk/carlosramos/api/usuario/usuarioSinContra/modelo/UsuarioSinContraModeloReq.java](src/main/java/uk/carlosramos/api/usuario/usuarioSinContra/modelo/UsuarioSinContraModeloReq.java)

**Autorización / Headers**
- No se ve un sistema de autenticación completo en los controladores; varios endpoints esperan un header `clave` (por ejemplo los endpoints de creación usan `@RequestHeader Map<String,String> header`).
- Incluye `clave: <valor>` cuando se indica en los ejemplos. Ajusta según lógica del servicio si hay validación adicional.

-------------------------
**Endpoints — Usuarios con contraseña**

1) Crear usuario con contraseña
- Método: POST
- Path: `/usuario-con-contra`
- Headers: `Content-Type: application/json`, **opcional** `clave: <valor>` (controlador recibe headers)
- Body (JSON):
  {
    "nombre": "Juan",
    "clave": "mi-clave-ejemplo",   // campo transient en modelo (no persiste)
    "password": "pass123"
  }
- Validaciones: `nombre` y `password` no pueden ser nulos (`@NotNull`); `clave` marcada `@Transient` pero también `@NotNull` en request.
- Respuesta: `Mono<UsuarioConContraModeloRes>` → objeto con `id`, `nombre`, `password`.
- Ejemplo curl (ver `curls.sh`):
  curl -i -X POST $BASE/usuario-con-contra -H "Content-Type: application/json" -H "clave: mi-clave-ejemplo" -d '{"nombre":"Juan","clave":"mi-clave-ejemplo","password":"pass123"}'

2) Listar usuarios con contraseña
- Método: GET
- Path: `/usuarios-con-contra`
- Respuesta: `Flux<UsuarioConContraModeloRes>` (lista de usuarios)

3) Obtener usuario por id
- Método: GET
- Path: `/usuario-con-contra/{id}`
- Respuesta: `Mono<UsuarioConContraModeloRes>` o error si no existe.

4) Actualizar usuario
- Método: PUT
- Path: `/usuario-con-contra`
- Headers: puede incluir `clave` si el servicio lo requiere para autorización
- Body: mismo esquema que crear; incluye `id` para identificar documento a actualizar
- Respuesta: `Mono<UsuarioConContraModeloRes>`

5) Eliminar usuario
- Método: DELETE
- Path: `/usuario-con-contra/{id}`
- Respuesta: `Mono<Void>` (204/200 según implementación)

-------------------------
**Endpoints — Usuarios con correo**

1) Crear usuario con correo
- Método: POST
- Path: `/usuario-con-correo`
- Headers: `Content-Type: application/json`, controlador también acepta headers en el método de creación
- Body (JSON):
  {
    "nombre": "Ana",
    "password": "pass123",
    "correo": "ana@ejemplo.com"
  }
- Validaciones: `nombre`, `password`, `correo` tienen `@NotNull`.
- Respuesta: `Mono<UsuarioConCorreoModeloRes>` (id, nombre, password, correo)

2) Listar usuarios con correo
- Método: GET
- Path: `/usuarios-con-correo`

3) Obtener usuario por id
- Método: GET
- Path: `/usuario-con-correo/{id}`

4) Actualizar usuario
- Método: PUT
- Path: `/usuario-con-correo`
- Body: incluye `id` para actualizar

5) Eliminar usuario
- Método: DELETE
- Path: `/usuario-con-correo/{id}`

-------------------------
**Endpoints — Usuarios sin contraseña**

1) Crear usuario sin contraseña
- Método: POST
- Path: `/usuario-sin-contra`
- Body: { "nombre": "Pepe" }
- Respuesta: `Mono<?>` — el servicio devuelve `Mono<?>` (revisar `UsuarioSinContraServicio` para tipo exacto)

2) Listar usuarios sin contraseña
- Método: GET
- Path: `/usuarios-sin-contra`

3) Obtener por id
- Método: GET
- Path: `/usuario-sin-contra/{id}`

4) Actualizar
- Método: PUT
- Path: `/usuario-sin-contra`
- Body: { "id":"...", "nombre":"..." }

5) Eliminar
- Método: DELETE
- Path: `/usuario-sin-contra/{id}`

-------------------------
**Endpoints — Vídeo**

1) Subir vídeo (multipart)
- Método: POST
- Path: `/upload`
- Content-Type: `multipart/form-data` (controlador usa `@RequestPart("file") FilePart`)
- Form field: `file` → fichero a subir
- Respuesta: `Mono<String>` — devuelve cadena (probablemente nombre/confirmación/path)
- Ejemplo:
  curl -i -X POST http://localhost:9001/upload -H "clave: mi-clave-ejemplo" -F "file=@/ruta/local/tu_video.mp4"

2) Descargar parte de vídeo
- Método: GET
- Path: `/partes/{nombre}`
- Respuesta: `Mono<Resource>` — recurso descargable (usa `curl -o` para guardar)
  curl -i http://localhost:9001/partes/NOMBRE_PARTE -o parte_descargada.mp4

-------------------------
**Modelado y validaciones**
- Los modelos usan `@Document` (MongoDB). Campos clave:
  - `UsuarioConContraModeloReq`:
    - `id` (String)
    - `nombre` (@NotNull)
    - `clave` (@Transient, @NotNull) — no persiste en DB pero es requerido en la request
    - `password` (@NotNull)
  - `UsuarioConContraModeloRes` y equivalentes contienen `id`, `nombre`, `password`, `correo` según caso.

**Tipos reactivos**
- Controladores devuelven `Mono<T>` o `Flux<T>`: la API es no-bloqueante. Cuando uses `curl`, el servidor responderá normalmente; la diferencia es interna a la implementación.

**Errores y excepciones**
- Existe un manejador global de excepciones en `uk.carlosramos.api.errores.controlador.GlobalExceptionHandlerControlador` (revisa para detalles sobre códigos y formato de error). Puedes ver esto en: [src/main/java/uk/carlosramos/api/errores/controlador/GlobalExceptionHandlerControlador.java](src/main/java/uk/carlosramos/api/errores/controlador/GlobalExceptionHandlerControlador.java)

**Ejemplos de requests/responses (JSON)**
- Crear usuario con contraseña — request:
  {
    "nombre": "Juan",
    "clave": "mi-clave-ejemplo",
    "password": "pass123"
  }
- Respuesta esperada (ejemplo):
  {
    "id": "64a1...",
    "nombre": "Juan",
    "password": "pass123"
  }

**Uso rápido**
- Scripts con ejemplos están en `curls.sh` en la raíz. Para ejecutar:
  ```bash
  chmod +x curls.sh
  ./curls.sh      # usa BASE por defecto http://localhost:9001
  BASE=http://127.0.0.1:8081 ./curls.sh  # si tu app corre en otro puerto
  ```

**Notas y recomendaciones**
- Revisa la implementación en los servicios (`servicio` packages) para entender detalles de negocio: validaciones extras, formatos de respuesta y uso de `header`.
- `clave` aparece en varios controladores: confirma en los servicios si se usa para autorización o para lógica de negocio. Si no es necesaria, puedes omitirla en las peticiones.
- Para pruebas de subida/descarga de vídeo usa ficheros pequeños primero y confirma la ruta de almacenamiento (revisa `VideoService` en [src/main/java/uk/carlosramos/api/video/service/VideoService.java](src/main/java/uk/carlosramos/api/video/service/VideoService.java)).

Si quieres, puedo:
- Añadir esta documentación al `README.md` o a `docs/`.
- Crear ejemplos de response reales tomando datos actuales del servidor (si arrancas la app aquí puedo ejecutar `curl` y completar ejemplos reales).

Fin de la documentación.
