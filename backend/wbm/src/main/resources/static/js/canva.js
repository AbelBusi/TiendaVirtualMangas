        // Seleccionamos todos los botones de agregar al carrito
        const cartButtons = document.querySelectorAll(".card-link[data-bs-target='#offcanvasRight']");

        cartButtons.forEach(btn => {
            btn.addEventListener("click", (e) => {
                e.preventDefault(); // Evita abrir el offcanvas inmediatamente

                // Mostrar alerta con SweetAlert2
                Swal.fire({
                    icon: 'success',
                    title: '¡Producto agregado!',
                    text: 'Se ha agregado al carrito correctamente.',
                    confirmButtonText: 'Ver carrito'
                }).then((result) => {
                    if (result.isConfirmed) {
                        // Abrir offcanvas manualmente
                        const offcanvasEl = document.querySelector(btn.dataset.bsTarget);
                        const bsOffcanvas = new bootstrap.Offcanvas(offcanvasEl);
                        bsOffcanvas.show();
                    }
                });
            });
        });


