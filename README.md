# 🚗 Car Management Backend

Este es el backend del proyecto Car Management, desarrollado con Spring Boot y SQL Server.

## Requisitos

- Java Development Kit (JDK): Versión 17 o superior (recomendado: Java 21 LTS)
- Gradle (incluido en el wrapper)
- SQL Server: Una instancia de SQL Server (puede ser SQL Server Express, Developer Edition o Docker con SQL Server).
- SQL Server Management Studio (SSMS) o Azure Data Studio (o cualquier cliente SQL Server para ejecutar scripts).
- IDE recomendado: IntelliJ IDEA o VS Code

## Configuración

1. Clonar el repositorio:

    ```bash
    git clone https://github.com/alfredoramos2603/car-management-backend.git
    cd car-management-backend
    ```

2. Crear la Base de Datos
   Abre SQL Server Management Studio (SSMS) o tu cliente SQL preferido y conéctate a tu instancia de SQL Server.
    El archivo "database.sql" tiene los scripts SQL para crear la base de datos y poblar sus tablas
    
    En caso de usar Docker con SQL Server, está la configuración en el "archivo docker-compose.yml"

3. Configurar la Conexión a la Base de Datos
   Actalizar los valores del archivo application.properties para que coincidan con tu configuración de SQL Server.
   Ubicación: src/main/resources/application.properties

El backend se iniciará por defecto, en http://localhost:8080

4. Ejecutar el Backend (Spring Boot)
   Desde la carpeta raíz de tu backend (backend/):
   ```bash
    ./gradlew bootRun
   ```

## Endpoints de autenticación

- `POST /api/auth/register`
- `POST /api/auth/login`

## Endpoints protegidos (requieren JWT)

- `GET /api/cars`
- `POST /api/cars`
- `PUT /api/cars/{id}`
- `DELETE /api/cars/{id}`

Incluye el JWT en los headers:

```http
Authorization: Bearer <token>
