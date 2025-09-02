// Array de objetos con las citas y sus autores
const quotes = [
    {
        text: "Un joven con poderes especiales lucha por proteger a su familia y amigos mientras descubre los secretos de su pasado.",
        author: "Naruto"
    },
    {
        text: "Un grupo de estudiantes con habilidades únicas se enfrenta a misterios y peligros sobrenaturales en su escuela.",
        author: "My Hero Academia"
    },
    {
        text: "Un chico se une a un torneo de cartas que mezcla magia y estrategia, descubriendo la importancia de la amistad y la perseverancia.",
        author: "Yu-Gi-Oh!"
    },
    {
        text: "En un mundo devastado por la guerra entre humanos y titanes, un joven promete vengar a su familia y proteger la humanidad.",
        author: "Attack on Titan"
    },
    {
        text: "Un detective con poderes deductivos excepcionales resuelve casos complejos mientras lidia con sus propios traumas y secretos.",
        author: "Death Note"
    },
    {
        text: "Una joven y su gato negro viven aventuras mágicas en un mundo lleno de hechicería y misterios ocultos.",
        author: "Sailor Moon"
    },
    {
        text: "Un grupo de amigos se embarca en un viaje épico a través de mares desconocidos en busca del tesoro más grande del mundo.",
        author: "One Piece"
    },
    {
        text: "Un joven piloto debe enfrentarse a enormes criaturas biomecánicas para proteger a la humanidad y descubrir la verdad detrás de su misión.",
        author: "Neon Genesis Evangelion"
    },
    {
        text: "Un estudiante ordinario obtiene habilidades extraordinarias tras entrar en un mundo virtual y debe luchar para sobrevivir y alcanzar la victoria.",
        author: "Sword Art Online"
    },
    {
        text: "Un grupo de héroes lucha contra amenazas sobrenaturales en un mundo donde la magia y la tecnología se mezclan.",
        author: "Bleach"
    }
];


let lastQuoteIndex = -1; // Para evitar repetir la misma cita dos veces seguidas

const quoteTextElement = document.getElementById('quote-text');
const quoteAuthorElement = document.getElementById('quote-author');
const generateQuoteBtn = document.getElementById('generate-quote-btn');
const quoteCardElement = document.getElementById('quote-card'); // Elemento de la tarjeta para cambiar el fondo

// Función para mostrar una cita aleatoria
function generateQuote() {
    let randomIndex;
    do {
        randomIndex = Math.floor(Math.random() * quotes.length);
    } while (randomIndex === lastQuoteIndex && quotes.length > 1); // Evitar la misma cita si hay más de una

    const randomQuote = quotes[randomIndex];

    quoteTextElement.textContent = `"${randomQuote.text}"`;
    quoteAuthorElement.textContent = `- ${randomQuote.author}`;

    // Cambiar el estilo de la tarjeta si la cita es de Maquiavelo
    if (randomQuote.category === "Maquiavelo") {
        quoteCardElement.classList.add('machiavelli');
    } else {
        quoteCardElement.classList.remove('machiavelli');
    }

    lastQuoteIndex = randomIndex; // Actualizar el índice de la última cita mostrada
}

// Event Listener para el botón
generateQuoteBtn.addEventListener('click', generateQuote);

// Generar una nueva cita al cargar la página
document.addEventListener('DOMContentLoaded', () => generateQuote()); 
