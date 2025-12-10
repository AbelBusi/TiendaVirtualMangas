
    $(document).ready(function() {

        const gallery = $('#mangaGallery');

        // Buscadores y Ordenamiento
        const searchInputDesktop = $('#searchInputDesktop');
        const searchInputMobile = $('#searchInputMobile');
        const sortSelectDesktop = $('#sortSelectDesktop');
        const sortSelectMobile = $('#sortSelectMobile');

        // Contadores
        const resultsCountDesktop = $('#resultsCountDesktop span');
        const resultsCountMobile = $('#resultsCountMobile span');
        const currentSortDesktop = $('#currentSortDesktop');

        // Función de Ordenamiento
        function sortGallery(sortValue) {
            const cards = gallery.children('.col').get();

            cards.sort(function(a, b) {
                const $a = $(a);
                const $b = $(b);

                // Asegurar que solo ordenamos elementos visibles después del filtrado
                if ($a.is(':hidden') && $b.is(':hidden')) return 0;
                if ($a.is(':hidden')) return 1;
                if ($b.is(':hidden')) return -1;

                let comparison = 0;

                if (sortValue === 'price_asc') {
                    const priceA = parseFloat($a.data('price'));
                    const priceB = parseFloat($b.data('price'));
                    comparison = priceA - priceB;
                } else if (sortValue === 'price_desc') {
                    const priceA = parseFloat($a.data('price'));
                    const priceB = parseFloat($b.data('price'));
                    comparison = priceB - priceA;
                } else if (sortValue === 'name_asc') {
                    const nameA = $a.data('name').toUpperCase();
                    const nameB = $b.data('name').toUpperCase();
                    if (nameA < nameB) comparison = -1;
                    if (nameA > nameB) comparison = 1;
                }
                // Si es 'relevance' o no coincide, no hay cambio (0)

                return comparison;
            });

            // Reinsertar elementos ordenados
            $.each(cards, function(idx, card) {
                gallery.append(card);
            });
        }

        // Función principal para aplicar filtros, búsqueda y ordenamiento
        function applyFiltersAndSearch(source = 'desktop') {

            // 1. Obtener valores (Sincronización de inputs)
            let searchText, sortValue;

            if (source === 'mobile') {
                searchText = searchInputMobile.val().toLowerCase();
                sortValue = sortSelectMobile.val();
                // Sincronizar Mobile a Desktop
                searchInputDesktop.val(searchInputMobile.val());
                sortSelectDesktop.val(sortValue);
            } else { // 'desktop' o inicial
                searchText = searchInputDesktop.val().toLowerCase();
                sortValue = sortSelectDesktop.val();
                // Sincronizar Desktop a Mobile (para tener el estado listo si abre el Offcanvas)
                searchInputMobile.val(searchInputDesktop.val());
                sortSelectMobile.val(sortValue);
            }

            // Determinar filtros de categoría y precio (Usamos Desktop para la lógica principal)
            const priceRange = $('#filterPrecio').val();
            const selectedCategories = [];

            $('.filter-checkbox-category:checked').each(function() {
                selectedCategories.push($(this).val());
            });
            // Sincronizar categorías Desktop -> Mobile
            syncCategoryFilters('.filter-checkbox-category:checked', '.filter-checkbox-category-mobile');
            syncPriceSelect('#filterPrecio', '#filterPrecioMobile');


            let resultsFound = 0;

            // 2. Filtrado
            gallery.find('.col').each(function() {
                const $card = $(this);
                const title = $card.find('.card-title').text().toLowerCase();
                const author = $card.find('.card-text.small.text-muted').text().toLowerCase();
                const category = $card.data('category');
                const price = parseFloat($card.data('price'));

                // Búsqueda (Título/Autor)
                let matchSearch = (searchText === "" || title.includes(searchText) || author.includes(searchText));

                // Categoría
                let matchCategory = (selectedCategories.length === 0 || selectedCategories.includes(category));

                // Precio
                let matchPrice = true;
                if (priceRange !== "0-9999") {
                    const [minPrice, maxPrice] = priceRange.split('-').map(Number);
                    matchPrice = (price >= minPrice && price <= maxPrice);
                }

                if (matchSearch && matchCategory && matchPrice) {
                    $card.show();
                    resultsFound++;
                } else {
                    $card.hide();
                }
            });

            // 3. Ordenamiento
            sortGallery(sortValue);

            // 4. Actualización de UI
            resultsCountDesktop.text(resultsFound);
            resultsCountMobile.text(resultsFound);

            // Actualizar etiqueta de ordenamiento
            currentSortDesktop.text($('#sortSelectDesktop option:selected').text());

            if (resultsFound === 0) {
                $('#noResultsMessage').show();
            } else {
                $('#noResultsMessage').hide();
            }
        }

        // --- SINCRONIZACIÓN AUXILIAR ---

        // Sincroniza el estado de los checkboxes entre Desktop y Mobile
        function syncCategoryFilters(sourceSelector, targetSelector) {
            let selectedValues = $(sourceSelector).map(function() { return this.value; }).get();
            $(targetSelector).each(function() {
                $(this).prop('checked', selectedValues.includes(this.value));
            });
        }

        // Sincroniza el select de precio
        function syncPriceSelect(sourceId, targetId) {
             $(targetId).val($(sourceId).val());
        }

        // --- EVENTOS ---

        // Desktop Events (Cambios en Desktop aplican al instante si no hay botón "Aplicar" específico, o al hacer click/enter)
        $('#searchButtonDesktop').on('click', applyFiltersAndSearch);
        searchInputDesktop.on('keyup', applyFiltersAndSearch);

        // Cambios en filtros y ordenamiento en Desktop (Aplicar al final)
        $('.filter-checkbox-category').on('change', applyFiltersAndSearch);
        $('#filterPrecio').on('change', applyFiltersAndSearch);
        sortSelectDesktop.on('change', applyFiltersAndSearch);

        // Botón Aplicar Filtros (Desktop)
        $('#applyFiltersBtn').on('click', applyFiltersAndSearch);

        // Limpiar categorías Desktop
        $('#clearCategoryFilter').on('click', function() {
            $('.filter-checkbox-category').prop('checked', false);
            $('.filter-checkbox-category-mobile').prop('checked', false);
            applyFiltersAndSearch();
        });


        // Mobile Events (Offcanvas - Se sincroniza y aplica al presionar 'Aplicar')
        $('#searchButtonMobile').on('click', () => applyFiltersAndSearch('mobile'));
        searchInputMobile.on('keyup', () => applyFiltersAndSearch('mobile'));

        // Sincronización de selects y checkboxes en Mobile
        $('.filter-checkbox-category-mobile').on('change', function() {
             syncCategoryFilters('.filter-checkbox-category-mobile:checked', '.filter-checkbox-category');
        });
        $('#filterPrecioMobile').on('change', function() {
             syncPriceSelect('#filterPrecioMobile', '#filterPrecio');
        });
        sortSelectMobile.on('change', function() {
             sortSelectDesktop.val(sortSelectMobile.val());
        });


        // Botón Aplicar Filtros (Mobile)
        $('#applyFiltersBtnMobile').on('click', () => applyFiltersAndSearch('mobile'));

        // Limpiar categorías Móvil
        $('#clearCategoryFilterMobile').on('click', function() {
            $('.filter-checkbox-category-mobile').prop('checked', false);
            $('.filter-checkbox-category').prop('checked', false);
            applyFiltersAndSearch();
        });


        // Disparar al cargar la página
        applyFiltersAndSearch();
    });
