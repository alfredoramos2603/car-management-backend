-- ===================================================================
-- SCRIPT SQL PARA CREAR Y POBLAR LA BASE DE DATOS CAR_DB EN SQL SERVER
-- ===================================================================

-- 1. Crear la base de datos CAR_DB si no existe
IF NOT EXISTS (SELECT name FROM master.dbo.sysdatabases WHERE name = N'CAR_DB')
BEGIN
    CREATE DATABASE CAR_DB;
END
GO

-- 2. Usar la base de datos CAR_DB
USE CAR_DB;
GO

-- 3. Eliminar tablas existentes si ya existen para evitar errores al recrearlas
IF OBJECT_ID('cars', 'U') IS NOT NULL
DROP TABLE cars;

IF OBJECT_ID('users', 'U') IS NOT NULL
DROP TABLE users;
GO

-- 4. Crear la tabla 'users'
CREATE TABLE users (
    id INT IDENTITY(1,1) PRIMARY KEY,
    username NVARCHAR(50) NOT NULL UNIQUE,
    password NVARCHAR(255) NOT NULL
);
GO

-- 5. Crear la tabla 'cars'
CREATE TABLE cars (
    id INT IDENTITY(1,1) PRIMARY KEY,
    brand NVARCHAR(50) NOT NULL,
    model NVARCHAR(50) NOT NULL,
    year INT NOT NULL,
    license_plate NVARCHAR(10) NOT NULL UNIQUE,
    color NVARCHAR(30) NOT NULL,
    user_id INT NOT NULL,

    -- Definir la clave foránea
    CONSTRAINT FK_Car_User FOREIGN KEY (user_id) REFERENCES users(id)
        ON DELETE CASCADE
        ON UPDATE NO ACTION
);
GO

-- 6. Poblar la tabla 'users'
INSERT INTO users (username, password) VALUES
('juanperez', 'password123'),
('mariagomez', 'securepass'),
('carlosruiz', 'testpass');
GO

-- 7. Poblar la tabla 'cars'
-- Asumiendo que los IDs de los usuarios se generaron en orden (1, 2, 3...)
-- Verificar los IDs reales ejecutando SELECT * FROM users;

-- Coches para juanperez (id=1)
INSERT INTO cars (brand, model, year, license_plate, color, user_id) VALUES
('Toyota', 'Corolla', 2020, 'ABC-123', 'Gris', (SELECT id FROM users WHERE username = 'juanperez')),
('Honda', 'Civic', 2018, 'XYZ-789', 'Azul', (SELECT id FROM users WHERE username = 'juanperez'));

-- Coches para mariagomez (id=2)
INSERT INTO cars (brand, model, year, license_plate, color, user_id) VALUES
('Ford', 'Focus', 2022, 'DEF-456', 'Rojo', (SELECT id FROM users WHERE username = 'mariagomez')),
('Nissan', 'Versa', 2021, 'GHI-012', 'Blanco', (SELECT id FROM users WHERE username = 'mariagomez'));

-- Coches para carlosruiz (id=3)
INSERT INTO cars (brand, model, year, license_plate, color, user_id) VALUES
('Mazda', 'CX-5', 2023, 'JKL-345', 'Negro', (SELECT id FROM users WHERE username = 'carlosruiz'));
GO

-- 8. Verificar los datos
SELECT * FROM users;
SELECT * FROM cars;
GO
