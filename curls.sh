#!/usr/bin/env bash
set -euo pipefail

# curls.sh - comandos curl para probar la API
# Ajusta BASE si tu app no corre en http://localhost:9001
BASE="${BASE:-http://localhost:9001}"

echo "Usando BASE=$BASE"

# Usuario con contraseña
echo "\n# Crear usuario con contraseña"
curl -i -X POST "$BASE/usuario-con-contra" \
  -H "Content-Type: application/json" \
  -H "clave: mi-clave-ejemplo" \
  -d '{"nombre":"Juan","clave":"mi-clave-ejemplo","password":"pass123"}'

echo "\n# Listar usuarios con contraseña"
curl -i "$BASE/usuarios-con-contra"

echo "\n# Obtener usuario con contraseña (reemplaza ID)"
echo "curl -i $BASE/usuario-con-contra/REEMPLAZA_ID"

echo "\n# Actualizar usuario con contraseña"
echo "curl -i -X PUT $BASE/usuario-con-contra -H 'Content-Type: application/json' -H 'clave: mi-clave-ejemplo' -d '{\"id\":\"REEMPLAZA_ID\",\"nombre\":\"Juan Actualizado\",\"clave\":\"mi-clave-ejemplo\",\"password\":\"nuevoPass\"}'"

echo "\n# Eliminar usuario con contraseña (reemplaza ID)"
echo "curl -i -X DELETE $BASE/usuario-con-contra/REEMPLAZA_ID"

# Usuario con correo
echo "\n# Crear usuario con correo"
curl -i -X POST "$BASE/usuario-con-correo" \
  -H "Content-Type: application/json" \
  -H "clave: mi-clave-ejemplo" \
  -d '{"nombre":"Ana","password":"pass123","correo":"ana@ejemplo.com"}'

echo "\n# Listar usuarios con correo"
curl -i "$BASE/usuarios-con-correo"

echo "\n# Obtener usuario con correo (reemplaza ID)"
echo "curl -i $BASE/usuario-con-correo/REEMPLAZA_ID"

echo "\n# Actualizar usuario con correo"
echo "curl -i -X PUT $BASE/usuario-con-correo -H 'Content-Type: application/json' -d '{\"id\":\"REEMPLAZA_ID\",\"nombre\":\"Ana M\",\"password\":\"nuevoPass\",\"correo\":\"ana@ejemplo.com\"}'"

echo "\n# Eliminar usuario con correo (reemplaza ID)"
echo "curl -i -X DELETE $BASE/usuario-con-correo/REEMPLAZA_ID"

# Usuario sin contraseña
echo "\n# Crear usuario sin contraseña"
curl -i -X POST "$BASE/usuario-sin-contra" \
  -H "Content-Type: application/json" \
  -H "clave: mi-clave-ejemplo" \
  -d '{"nombre":"Pepe"}'

echo "\n# Listar usuarios sin contraseña"
curl -i "$BASE/usuarios-sin-contra"

echo "\n# Obtener usuario sin contraseña (reemplaza ID)"
echo "curl -i $BASE/usuario-sin-contra/REEMPLAZA_ID"

echo "\n# Actualizar usuario sin contraseña"
echo "curl -i -X PUT $BASE/usuario-sin-contra -H 'Content-Type: application/json' -d '{\"id\":\"REEMPLAZA_ID\",\"nombre\":\"Pepe Nuevo\"}'"

echo "\n# Eliminar usuario sin contraseña (reemplaza ID)"
echo "curl -i -X DELETE $BASE/usuario-sin-contra/REEMPLAZA_ID"

# Vídeo
echo "\n# Subir vídeo (multipart)"
echo "Ejemplo: curl -i -X POST $BASE/upload -H 'clave: mi-clave-ejemplo' -F 'file=@/ruta/local/tu_video.mp4'"

echo "\n# Descargar parte de vídeo (reemplaza NOMBRE_PARTE) y guardar en fichero"
echo "curl -i $BASE/partes/NOMBRE_PARTE -o parte_descargada.mp4"

echo "\n# FIN - Reemplaza valores: REEMPLAZA_ID, /ruta/local/tu_video.mp4, NOMBRE_PARTE y ajusta BASE si hace falta"
