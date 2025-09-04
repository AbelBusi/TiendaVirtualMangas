const precioUnitario = 25.00; // precio fijo del libro
const cantidadInput = document.querySelector('.cantidad');
const totalCarrito = document.getElementById('totalCarrito');

cantidadInput.addEventListener('input', () => {
    let cantidad = parseInt(cantidadInput.value) || 1;
    totalCarrito.textContent = "S/ " + (precioUnitario * cantidad).toFixed(2);
});
