
        async function loadAnimeHub() {
            try {
                // 1) Manga random
                const resManga = await fetch("https://api.jikan.moe/v4/top/manga?page=1");
                const mangas = (await resManga.json()).data;
                const manga = mangas[Math.floor(Math.random() * mangas.length)];
                document.getElementById("manga-title").textContent = manga.title;
                document.getElementById("manga-synopsis").textContent = manga.synopsis || "Sin sinopsis disponible.";
                document.getElementById("manga-extra").textContent =
                    `⭐ Score: ${manga.score || "N/A"} | Autor: ${(manga.authors?.[0]?.name || "Desconocido")}`;
                document.getElementById("manga-image").src = manga.images.jpg.image_url;

                // 2) Personaje random
                const resChar = await fetch("https://api.jikan.moe/v4/characters?page=1");
                const chars = (await resChar.json()).data;
                const char = chars[Math.floor(Math.random() * chars.length)];
                document.getElementById("char-name").textContent = char.name;
                document.getElementById("char-anime").textContent = char.about ? char.about.substring(0, 80) + "..." : "Personaje de anime/manga";
                document.getElementById("char-img").src = char.images.jpg.image_url;

                // 3) Imagen random
                try {
                    const resWaifu = await fetch("https://api.waifu.pics/sfw/waifu");
                    const waifu = await resWaifu.json();
                    document.getElementById("waifu-img").src = waifu.url;
                } catch {
                    document.getElementById("waifu-img").src = "https://i.imgur.com/3yR5U0X.jpeg";
                }

                // 4) Anime popular
                const resAnime = await fetch("https://api.jikan.moe/v4/top/anime?page=1");
                const animes = (await resAnime.json()).data;
                const anime = animes[Math.floor(Math.random() * animes.length)];
                document.getElementById("anime-title").textContent = anime.title;
                document.getElementById("anime-desc").textContent = anime.synopsis || "Sin sinopsis disponible.";
                document.getElementById("anime-img").src = anime.images.jpg.image_url;

            } catch (err) {
                console.error("Error cargando APIs:", err);
            }
        }

        document.getElementById("generate-quote-btn").addEventListener("click", loadAnimeHub);
        window.onload = loadAnimeHub;



