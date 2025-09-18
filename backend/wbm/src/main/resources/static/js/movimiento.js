// Seleccionamos todas las tarjetas
const cards = document.querySelectorAll('.cardIndividual, .textTitle');

function animateOnScroll() {
    const triggerBottom = window.innerHeight * 0.85; // punto de activación

    cards.forEach(card => {
        const cardTop = card.getBoundingClientRect().top;

        if (cardTop < triggerBottom) {
            card.classList.add('animate');
        }
    });
}

// Ejecutar al cargar y al hacer scroll
window.addEventListener('scroll', animateOnScroll);
window.addEventListener('load', animateOnScroll);



