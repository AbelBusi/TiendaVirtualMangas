import express from "express";
import cors from "cors";
import fs from "fs";
import path from "path";

const app = express();
app.use(cors());
app.use(express.json()); // Middleware para parsear bodies JSON

// --- CONFIGURACIÓN DE RUTAS Y HELPERS ---

// Carpeta pública para imágenes
app.use("/", express.static("public")); // Ahora puedes acceder a /assets/ por ejemplo

// Ruta base de los JSON (Asegúrate de que esta ruta sea correcta en tu entorno)
const dataPath = path.resolve("data");

/**
 * Helper para leer archivos JSON de manera síncrona.
 * @param {string} filename - Nombre del archivo JSON.
 * @returns {object|array} Contenido parseado.
 */
function readJson(filename) {
  const filePath = path.join(dataPath, filename);
  try {
    const content = fs.readFileSync(filePath, "utf8");
    return JSON.parse(content);
  } catch (error) {
    console.error(`Error al leer ${filename}:`, error);
    return null;
  }
}

/**
 * Helper para escribir archivos JSON de manera síncrona.
 * @param {string} filename - Nombre del archivo JSON.
 * @param {object|array} data - Objeto o array a escribir.
 */
function writeJson(filename, data) {
  const filePath = path.join(dataPath, filename);
  try {
    fs.writeFileSync(filePath, JSON.stringify(data, null, 2), "utf8");
  } catch (error) {
    console.error(`Error al escribir en ${filename}:`, error);
  }
}

/**
 * Función para generar un ID único simple para los arrays.
 * Asume que el último elemento tiene el ID más alto.
 * @param {Array<Object>} arr
 * @param {string} idKey
 * @returns {number}
 */
function generateId(arr, idKey = 'id') {
  if (arr.length === 0) return 1;
  const maxId = arr.reduce((max, item) => Math.max(max, item[idKey] || 0), 0);
  return maxId + 1;
}

// --- ENDPOINTS GENÉRICOS DE LECTURA (GET) ---

// Para objetos simples (Hero, About)
app.get("/api/hero", (_, res) => res.json(readJson("hero.json")));
app.get("/api/about", (_, res) => res.json(readJson("about.json")));

// Para arrays (Mangas, Posts, Recent)
app.get("/api/mangas", (_, res) => res.json(readJson("mangas.json")));
app.get("/api/posts", (_, res) => res.json(readJson("posts.json")));
app.get("/api/recent", (_, res) => res.json(readJson("recent.json")));


// --- ENDPOINTS DE ESCRITURA (CRUD) ---

// ===================================
// 1. HERO (Objeto simple)
// ===================================
app.put("/api/hero", (req, res) => {
  const data = req.body;
  writeJson("hero.json", data);
  res.json({ message: "Hero actualizado con éxito", data });
});

// ===================================
// 2. ABOUT (Objeto simple)
// ===================================
app.put("/api/about", (req, res) => {
  const data = req.body;
  writeJson("about.json", data);
  res.json({ message: "About actualizado con éxito", data });
});


// ===================================
// 3. MANGAS (Array con ID)
// ===================================

// POST: Agregar nuevo manga
app.post("/api/mangas", (req, res) => {
  const mangas = readJson("mangas.json");
  const nuevoManga = req.body;

  // Asignar un ID único (necesario para gestionar en el frontend)
  nuevoManga.id = generateId(mangas, 'id');

  mangas.push(nuevoManga);
  writeJson("mangas.json", mangas);
  res.status(201).json({ message: "Manga agregado con éxito", data: nuevoManga });
});

// PUT: Editar manga existente
app.put("/api/mangas/:id", (req, res) => {
  let mangas = readJson("mangas.json");
  const id = parseInt(req.params.id);
  const updatedData = req.body;

  const index = mangas.findIndex(m => m.id === id);

  if (index !== -1) {
    // Aseguramos que el ID no cambie
    updatedData.id = id;
    mangas[index] = updatedData;
    writeJson("mangas.json", mangas);
    res.json({ message: "Manga actualizado con éxito", data: updatedData });
  } else {
    res.status(404).json({ message: "Manga no encontrado" });
  }
});

// DELETE: Eliminar manga
app.delete("/api/mangas/:id", (req, res) => {
  let mangas = readJson("mangas.json");
  const id = parseInt(req.params.id);

  const initialLength = mangas.length;
  // Filtramos todos los elementos cuyo ID no coincida con el ID a eliminar
  mangas = mangas.filter(m => m.id !== id);

  if (mangas.length < initialLength) {
    writeJson("mangas.json", mangas);
    return res.json({ message: `Manga con ID ${id} eliminado con éxito` });
  }
  res.status(404).json({ message: "Manga no encontrado" });
});


// ===================================
// 4. POSTS y RECENT (Siguen la misma lógica CRUD que Mangas)
// ===================================

// POSTS
app.post("/api/posts", (req, res) => {
  const posts = readJson("posts.json");
  const nuevoPost = req.body;
  nuevoPost.id = generateId(posts, 'id');
  posts.push(nuevoPost);
  writeJson("posts.json", posts);
  res.status(201).json({ message: "Post agregado con éxito", data: nuevoPost });
});

app.put("/api/posts/:id", (req, res) => {
  let posts = readJson("posts.json");
  const id = parseInt(req.params.id);
  const updatedData = req.body;
  const index = posts.findIndex(p => p.id === id);

  if (index !== -1) {
    updatedData.id = id;
    posts[index] = updatedData;
    writeJson("posts.json", posts);
    return res.json({ message: "Post actualizado con éxito", data: updatedData });
  }
  res.status(404).json({ message: "Post no encontrado" });
});

app.delete("/api/posts/:id", (req, res) => {
  let posts = readJson("posts.json");
  const id = parseInt(req.params.id);
  const initialLength = posts.length;
  posts = posts.filter(p => p.id !== id);

  if (posts.length < initialLength) {
    writeJson("posts.json", posts);
    return res.json({ message: `Post con ID ${id} eliminado con éxito` });
  }
  res.status(404).json({ message: "Post no encontrado" });
});

// RECENT
app.post("/api/recent", (req, res) => {
  const recent = readJson("recent.json");
  const nuevaEntrada = req.body;
  nuevaEntrada.id = generateId(recent, 'id');
  recent.push(nuevaEntrada);
  writeJson("recent.json", recent);
  res.status(201).json({ message: "Entrada reciente agregada con éxito", data: nuevaEntrada });
});

app.put("/api/recent/:id", (req, res) => {
  let recent = readJson("recent.json");
  const id = parseInt(req.params.id);
  const updatedData = req.body;
  const index = recent.findIndex(r => r.id === id);

  if (index !== -1) {
    updatedData.id = id;
    recent[index] = updatedData;
    writeJson("recent.json", recent);
    return res.json({ message: "Entrada reciente actualizada con éxito", data: updatedData });
  }
  res.status(404).json({ message: "Entrada reciente no encontrada" });
});

app.delete("/api/recent/:id", (req, res) => {
  let recent = readJson("recent.json");
  const id = parseInt(req.params.id);
  const initialLength = recent.length;
  recent = recent.filter(r => r.id !== id);

  if (recent.length < initialLength) {
    writeJson("recent.json", recent);
    return res.json({ message: `Entrada reciente con ID ${id} eliminada con éxito` });
  }
  res.status(404).json({ message: "Entrada reciente no encontrada" });
});


// --- INICIAR SERVIDOR ---
const PORT = 3008;
app.listen(PORT, () => {
  console.log(`API corriendo en http://localhost:${PORT}`);
  console.log(`Ruta de datos: ${dataPath}`);
});