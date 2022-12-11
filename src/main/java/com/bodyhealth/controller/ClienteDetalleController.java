package com.bodyhealth.controller;

import com.bodyhealth.model.Cliente;
import com.bodyhealth.model.ClienteDetalle;
import com.bodyhealth.model.Detalle;
import com.bodyhealth.repository.UsuarioRepository;
import com.bodyhealth.service.ClienteDetalleService;
import com.bodyhealth.service.DetalleService;
import com.bodyhealth.service.PDFGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class ClienteDetalleController {

    @Autowired
    private ClienteDetalleService clienteDetalleService;

    private final PDFGeneratorService pdfGeneratorService;

    public ClienteDetalleController(PDFGeneratorService pdfGeneratorService) {
        this.pdfGeneratorService = pdfGeneratorService;
    }


    @GetMapping("/admin/dash-fact-planes")
    public String listarFacturacionPlan(Model model){
        List<ClienteDetalle> clientesDetalle = clienteDetalleService.listarClientesDetalles();
        model.addAttribute("factPlan",clientesDetalle);
        return "admin/fact-planes/dash-fact-planes";
    }

    @GetMapping("/admin/dash-fact-planes/expand/{id_factura}")
    public String expandCompraPlan(ClienteDetalle clienteDetalle, Model model){

        clienteDetalle = clienteDetalleService.encontrarClienteDetalle(clienteDetalle);

        model.addAttribute("detalle",clienteDetalle);

        return "admin/fact-planes/factura-plan-expand";
    }

    //GENERAR PDF
    @GetMapping("/admin/dash-fact-planes/expand/pdf/{id_factura}")
    public void generarPDF(ClienteDetalle clienteDetalle, HttpServletResponse response) throws IOException {

        clienteDetalle = clienteDetalleService.encontrarClienteDetalle(clienteDetalle);

        response.setContentType("application/pdf");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=factura_"+clienteDetalle.getId_factura()+".pdf";

        response.setHeader(headerKey, headerValue);

        this.pdfGeneratorService.exportPlan(response, clienteDetalle);

    }


}
