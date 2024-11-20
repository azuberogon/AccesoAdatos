CREATE TABLE facturas (
                          codigo INTEGER PRIMARY KEY,
                          destinatario VARCHAR(90) NOT NULL,
                          cuenta INTEGER NOT NULL,
                          importe DECIMAL(10,2) NOT NULL,
                          fecha_hora DATETIME NOT NULL UNIQUE
);

INSERT INTO facturas (CODIGO, destinatario, cuenta, importe, fecha_hora)
VALUES
    (710349, 'Linus Torvalds', 86284266, 30.19, '2012-08-09 17:15:23'),
    (629430, 'Steve Wozniak', 62842668, 386.02, '2017-09-04 07:57:23');

