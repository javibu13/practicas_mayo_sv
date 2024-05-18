CREATE TABLE Users (
        idUser INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
        firstName VARCHAR(255),
        lastName VARCHAR(255),
        email VARCHAR(255) UNIQUE,
        phoneNumber VARCHAR(20),
        password VARCHAR(64) 
    );

  CREATE TABLE Movies (
        idMovie INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
        title VARCHAR(255),
        director VARCHAR(255),
        synopsis VARCHAR(1000),
        trailer VARCHAR(500),
        quantity INT,
        path VARCHAR(255) 
    );


 CREATE TABLE Loans (
        idLoan INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
        idUser INT,
        idMovie INT,
        startDate DATE,
        expectedDate DATE,
        returnDate DATE,
        FOREIGN KEY (idMovie) REFERENCES Movies(idMovie),
        FOREIGN KEY (idUser) REFERENCES Users(idUser)
    );


-- Procedimiento de contingencia 
CREATE OR REPLACE PROCEDURE DROP_ALL_TABLES AS
BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE Loans CASCADE CONSTRAINTS';
    EXECUTE IMMEDIATE 'DROP TABLE Movies CASCADE CONSTRAINTS';
    EXECUTE IMMEDIATE 'DROP TABLE Users CASCADE CONSTRAINTS';
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('ERROR AL ELIMINAR TABLAS: ' || SQLERRM);
        ROLLBACK;
END;
/

