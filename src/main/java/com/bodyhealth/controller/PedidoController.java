package com.bodyhealth.controller;

import com.bodyhealth.model.ClienteDetalle;
import com.bodyhealth.model.Pedido;
import com.bodyhealth.model.Producto;
import com.bodyhealth.service.PDFGeneratorService;
import com.bodyhealth.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    private final PDFGeneratorService pdfGeneratorService;

    public PedidoController(PDFGeneratorService pdfGeneratorService) {
        this.pdfGeneratorService = pdfGeneratorService;
    }

    @GetMapping("/dash-fact-productos")
    public String listarFactPedidos(Model model){
        List<Pedido> pedidos = pedidoService.listarPedidos();

        model.addAttribute("pedidos",pedidos);

        return "/admin/fact-pedidos/dash-fact-productos";
    }

    @GetMapping("/dash-fact-productos/expand/{id_pedido}")
    public String expandCompraPedido(Pedido pedido, Model model){

        pedido = pedidoService.encontrarPedido(pedido);

        model.addAttribute("pedido",pedido);

        return "/admin/fact-pedidos/factura-producto-expand";
    }

    //GENERAR PDF
    @GetMapping("/dash-fact-productos/expand/pdf/{id_pedido}")
    public void generarPDF(Pedido pedido, HttpServletResponse response) throws IOException {

        pedido = pedidoService.encontrarPedido(pedido);

        response.setContentType("application/pdf");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=factura_pedido_"+pedido.getId_pedido()+".pdf";

        response.setHeader(headerKey, headerValue);

        this.pdfGeneratorService.exportPedido(response, pedido);

    }
}
