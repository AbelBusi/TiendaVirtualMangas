package com.example.wbm.controller.user; // Asegúrate de que este sea el paquete correcto

import com.example.wbm.implementation.DetalleVentaServicioImpl;
import com.example.wbm.implementation.VentaServicioImpl;
import com.example.wbm.model.dto.DetalleVentaDTO;
import com.example.wbm.model.dto.VentaDTO;
import com.example.wbm.model.entity.Usuario;
import jakarta.servlet.http.HttpSession; // Importante: usar la interfaz correcta (jakarta o javax)
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/usuario/miscompras")
@RequiredArgsConstructor
public class usuarioController {

    private final VentaServicioImpl ventaServicio;
    private final DetalleVentaServicioImpl detalleVentaServicio;

    /**
     * Obtiene el ID del usuario actualmente logueado desde la HttpSession.
     */
    private Integer obtenerIdUsuarioLogueado(HttpSession session) {
        // Obtenemos el objeto Usuario que guardaste en la sesión
        Usuario usuario = (Usuario) session.getAttribute("usuarioSesion");

        if (usuario == null) {
            // Si no hay usuario en sesión, lanzamos una excepción o devolvemos null
            // para que el método de llamada maneje la redirección a login.
            throw new IllegalStateException("Usuario no logueado. Redirigiendo a inicio de sesión.");
        }
        return usuario.getIdUsuario();
    }

    /**
     * Muestra la lista de todas las compras del usuario logueado.
     */
    @GetMapping
    public String verComprasUsuario(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        try {
            Integer idUsuario = obtenerIdUsuarioLogueado(session);

            // Cargar las ventas (pedidos) solo para el usuario actual
            List<VentaDTO> ventas = ventaServicio.leerVentasPorUsuarioId(idUsuario);

            model.addAttribute("ventas", ventas);

            return "usuario/index";

        } catch (IllegalStateException e) {
            // Si el usuario no está logueado, redirigir a la página de login/inicio
            redirectAttributes.addFlashAttribute("error", "Debes iniciar sesión para ver tus compras.");
            return "redirect:/inicio"; // Redirige a tu página de inicio o login
        }
    }

    /**
     * Muestra el detalle de una compra específica.
     */
    @GetMapping("/detalle/{idVenta}")
    public String verDetalleVentaUsuario(Model model, @PathVariable Integer idVenta, HttpSession session, RedirectAttributes redirectAttributes) {
        try {
            Integer idUsuario = obtenerIdUsuarioLogueado(session);

            VentaDTO venta = ventaServicio.obtenerVentaPorId(idVenta);

            // IMPORTANTE: Verificar que la venta pertenezca al usuario logueado
            if (venta.getUsuario() == null || !venta.getUsuario().getIdUsuario().equals(idUsuario)) {
                redirectAttributes.addFlashAttribute("alerta", "Acceso denegado. No puedes ver el detalle de ese pedido.");
                return "redirect:/usuario/miscompras";
            }

            // 💡 SOLUCIÓN: Cargar los detalles de la venta por separado
            List<DetalleVentaDTO> detalles = detalleVentaServicio.leerDetalleVentas(idVenta);

            model.addAttribute("venta", venta);
            // 💡 AGREGAR ESTA LÍNEA al modelo
            model.addAttribute("detalles", detalles);

            return "/usuario/detalle-compra";

        } catch (IllegalStateException e) {
            // Usuario no logueado
            redirectAttributes.addFlashAttribute("error", "Debes iniciar sesión para ver tus compras.");
            return "redirect:/inicio";

        } catch (java.util.NoSuchElementException e) {
            // Venta no encontrada
            redirectAttributes.addFlashAttribute("alerta", "El pedido solicitado no existe.");
            return "redirect:/usuario/miscompras";
        }
    }
}