document.getElementById("buscarDni").addEventListener("click", async () => {
    const dniInput = document.getElementById("dni");
    const dni = dniInput.value.trim();
    const errorDiv = document.getElementById("dniError");

    // Limpia el mensaje cada vez
    errorDiv.style.display = "none";
    errorDiv.innerText = "";

    if (dni.length !== 8) {
        errorDiv.innerText = "El DNI debe tener 8 dígitos.";
        errorDiv.style.display = "block";
        return;
    }

    try {
        const res = await fetch(`/dni/${dni}`);
        if (!res.ok) throw new Error("No se encontró información para el DNI ingresado.");

        const data = await res.json();

        document.getElementById("nombre").value = data.nombres || "";
        document.getElementById("apellidos").value =
            `${data.apellidoPaterno || ""} ${data.apellidoMaterno || ""}`.trim();

    } catch (error) {
        errorDiv.innerText = "No se encontró información para el DNI ingresado.";
        errorDiv.style.display = "block";
        console.error(error);
    }
});
