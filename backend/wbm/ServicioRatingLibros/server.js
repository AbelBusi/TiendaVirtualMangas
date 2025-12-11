// server.js (Versión con Votación POST)

const express = require('express');
const cors = require('cors');
const fs = require('fs');
const path = require('path');

const app = express();
const port = 3000;
const ratingsFilePath = path.join(__dirname, 'bookRatings.json');

// Middleware para habilitar CORS
app.use(cors());

// Middleware para parsear el cuerpo de las solicitudes como JSON
app.use(express.json());

// --- Endpoint 1: Obtener las valoraciones (GET) ---
app.get('/api/ratings', (req, res) => {
    fs.readFile(ratingsFilePath, 'utf8', (err, data) => {
        if (err) {
            console.error('Error al leer el archivo JSON de valoraciones:', err);
            return res.status(500).json({ error: 'No se pudieron cargar las valoraciones.' });
        }
        try {
            const ratings = JSON.parse(data);
            res.json(ratings);
        } catch (parseError) {
            console.error('Error al parsear el JSON:', parseError);
            return res.status(500).json({ error: 'El formato del archivo de valoraciones es inválido.' });
        }
    });
});

// --- Endpoint 2: Registrar un voto (POST) ---
app.post('/api/vote', (req, res) => {
    const { idLibro, ratingIncrement } = req.body; // ratingIncrement será la cantidad a sumar (ej: 0.1)

    if (!idLibro || ratingIncrement === undefined) {
        return res.status(400).json({ error: 'Faltan parámetros: idLibro y ratingIncrement.' });
    }

    fs.readFile(ratingsFilePath, 'utf8', (err, data) => {
        if (err) return res.status(500).json({ error: 'No se pudieron cargar las valoraciones.' });

        try {
            let ratings = JSON.parse(data);

            // Buscar el libro por ID o crearlo si no existe
            let bookRating = ratings.find(r => r.idLibro === idLibro);

            if (bookRating) {
                // Sumar el incremento, asegurando que no exceda el máximo de 5.0
                let newRating = Math.min(5.0, bookRating.rating + ratingIncrement);
                bookRating.rating = parseFloat(newRating.toFixed(2)); // Redondeamos a dos decimales
            } else {
                // Si es el primer voto, inicializar el rating (ej: en 3.0 + incremento)
                let initialRating = Math.min(5.0, 3.0 + ratingIncrement);
                bookRating = {
                    idLibro: idLibro,
                    rating: parseFloat(initialRating.toFixed(2))
                };
                ratings.push(bookRating);
            }

            // Guardar el archivo JSON actualizado
            fs.writeFile(ratingsFilePath, JSON.stringify(ratings, null, 2), (writeErr) => {
                if (writeErr) {
                    console.error('Error al escribir el archivo JSON:', writeErr);
                    return res.status(500).json({ error: 'No se pudo guardar la nueva valoración.' });
                }

                // Respuesta exitosa con la nueva valoración
                res.status(200).json({
                    message: 'Voto registrado con éxito',
                    newRating: bookRating.rating,
                    idLibro: idLibro
                });
            });

        } catch (parseError) {
            return res.status(500).json({ error: 'Error interno del servidor.' });
        }
    });
});

app.listen(port, () => {
  console.log(`🚀 Servidor de valoraciones corriendo en: http://localhost:${port}`);
  console.log(`Endpoint de votación: POST http://localhost:${port}/api/vote`);
});