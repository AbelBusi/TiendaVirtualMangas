// Importamos el módulo HTTP nativo de Node.js
const http = require('http');

// Definimos el puerto solicitado
const PORT = 8007;

// =======================================================
// JSON (La Data de la API)
// =======================================================
const dataFromHTML = {
  "metadata": {
    "tituloDocumento": "WEbStorm (Contacto)",
    "idioma": "es",
    "puertoServido": PORT,
    "frameworks": ["Bootstrap 5.3", "Font Awesome 6", "SweetAlert2", "DataTables"]
  },
  "pagina": "Contacto y Sobre Nosotros",
  "seccionContacto": {
    "tituloPrincipal": "Envíanos un mensaje",
    "descripcionBanner": "Estamos aquí para responder tus dudas, sugerencias o pedidos especiales. ¡No dudes en escribirnos!",
    "formulario": {
      "id": "contactForm",
      "accionBoton": "Enviar Mensaje",
      "campos": [
        { "id": "name", "label": "Nombre", "tipo": "text" },
        { "id": "email", "label": "Correo electrónico", "tipo": "email" },
        { "id": "subject", "label": "Asunto", "tipo": "text" },
        { "id": "message", "label": "Mensaje", "tipo": "textarea" }
      ]
    }
  },
  "seccionSobreNosotros": {
    "titulo": "Sobre Nosotros",
    "nombreCompania": "MangaStore",
    "descripcionGeneral": "Somos MangaStore, tu espacio para explorar mangas, anime y todo lo relacionado con el mundo otaku. Compartimos noticias, reseñas, productos de colección y mucho más. Nuestro objetivo es crear una comunidad donde los fans puedan conectarse y disfrutar de su pasión por el anime.",
    "mision": "Fomentar la cultura del manga y anime, ofreciendo información, entretenimiento y productos de calidad.",
    "vision": "Ser la tienda y comunidad otaku de referencia en Latinoamérica."
  },
  "seccionEquipo": {
    "titulo": "Nuestro Equipo",
    "miembros": [
      {
        "nombre": "Hiroshi Tanaka",
        "rol": "Fundador y CEO, amante de los shonen clásicos.",
        "imagen": "../assets/media/images/iconos/personaje.webp",
        "redes": ["fab fa-facebook-f", "fab fa-twitter", "fab fa-instagram"]
      },
      {
        "nombre": "Yumi Nakamura",
        "rol": "Editor de contenido y reseñas, especialista en shojo.",
        "imagen": "../assets/media/images/iconos/personaje.webp",
        "redes": ["fab fa-facebook-f", "fab fa-twitter", "fab fa-instagram"]
      }
    ]
  },
  "notasAdicionales": "La página usa Thymeleaf (th:replace) para componentes de encabezado y pie de página."
};

// Convertimos el objeto de datos a una cadena JSON
const jsonResponse = JSON.stringify(dataFromHTML, null, 2);

// =======================================================
// Lógica del Servidor HTTP
// =======================================================
const server = http.createServer((req, res) => {
  // Manejo de CORS (Crucial para que el HTML pueda hacer la petición)
  res.setHeader('Access-Control-Allow-Origin', '*'); // Permite acceso desde cualquier origen
  res.setHeader('Access-Control-Allow-Methods', 'GET');
  res.setHeader('Access-Control-Max-Age', 2592000); // 30 days

  if (req.method === 'GET' && req.url === '/') {
    res.statusCode = 200;
    res.setHeader('Content-Type', 'application/json');
    res.end(jsonResponse);
  } else {
    res.statusCode = 404;
    res.setHeader('Content-Type', 'text/plain');
    res.end(`404 Not Found. API solo expone JSON en la ruta principal (/) usando el método GET.`);
  }
});

// Iniciamos el servidor para escuchar en el puerto 8007
server.listen(PORT, () => {
  console.log('----------------------------------------------------');
  console.log(`✅ API Pura Node.js iniciada.`);
  console.log(`🌐 Endpoint de Datos: http://localhost:${PORT}/`);
  console.log('----------------------------------------------------');
});