$(document).ready(function () {

    // Inicializar DataTable
    const table = $('#personasTable').DataTable({
        dom: '<"d-flex justify-content-between align-items-center mb-3"Bf>rtip',
        buttons: [
            { extend: 'excelHtml5', text: '<i class="fas fa-file-excel"></i> Excel', className: 'btn buttons-excel' },
            { extend: 'pdfHtml5', text: '<i class="fas fa-file-pdf"></i> PDF', className: 'btn buttons-pdf' },
            { extend: 'print', text: '<i class="fas fa-print"></i> Imprimir', className: 'btn buttons-print' }
        ],
        order: [ [0, "asc"] ],
        columnDefs: [{ targets: 2, type: "string" }],
        language: {
            search: "Buscar:",
            lengthMenu: "Mostrar _MENU_ registros",
            info: "Mostrando _START_ a _END_ de _TOTAL_ registros",
            paginate: { previous: "⬅", next: "➡" }
        }
    });

    // CLICK EN EDITAR
    $('#personasTable tbody').on('click', '.btn-warning', function () {

        const row = $(this).closest('tr');

        const id = row.data('id');
        const dni = row.find('td:eq(1)').text().trim();
        const nombre = row.find('td:eq(2)').text().trim();
        const apellidos = row.find('td:eq(3)').text().trim();
        const edad = row.find('td:eq(4)').text().trim();
        const direccion = row.find('td:eq(5)').text().trim();
        const telefono = row.find('td:eq(6)').text().trim();
        const pais = row.find('td:eq(7)').text().trim();
        const estado = row.find('td:eq(8)').text().trim() === 'Activo' ? 1 : 0;

        // Modal editar
        const modal = document.getElementById('editarPersonaModal');

        // Limpiar validaciones previas
        const form = modal.querySelector('#formEditarPersona');
        form.classList.remove('was-validated');

        // Asignar valores
        modal.querySelector('#edit-idPersona').value = id || '';
        modal.querySelector('#edit-dni').value = dni || '';
        modal.querySelector('#edit-nombre').value = nombre || '';
        modal.querySelector('#edit-apellidos').value = apellidos || '';
        modal.querySelector('#edit-edad').value = edad || '';
        modal.querySelector('#edit-direccion').value = direccion || '';
        modal.querySelector('#edit-telefono').value = telefono || '';
        modal.querySelector('#edit-pais').value = pais || '';
        modal.querySelector('#edit-estado').value = estado;

        // Mostrar modal
        new bootstrap.Modal(modal).show();
    });

    // Validaciones dinámicas Bootstrap 5
    (function () {
        'use strict';
        const forms = document.querySelectorAll('.needs-validation');
        Array.prototype.slice.call(forms).forEach(function (form) {
            form.addEventListener('submit', function (event) {
                if (!form.checkValidity()) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated');
            }, false);
        });
    })();

});