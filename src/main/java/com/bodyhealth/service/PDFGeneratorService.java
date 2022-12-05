package com.bodyhealth.service;

import com.bodyhealth.model.ClienteDetalle;
import com.bodyhealth.model.Pedido;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PDFGeneratorService {
    public void exportPlan(HttpServletResponse response, ClienteDetalle clienteDetalle) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(35);
        Font fontBody = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontBody.setSize(15);

        document.add(new Paragraph("Factura",fontTitle));
        document.add(new Paragraph("Gym BodyHealth",fontBody));
        document.add(new Paragraph("Direccion: XXXX",fontBody));

        document.add( Chunk.NEWLINE );

        PdfPTable factura = new PdfPTable(2);

        PdfPCell celda1 = new PdfPCell();
        Paragraph fac = new Paragraph("Factura #"+clienteDetalle.getId_factura());
        fac.setAlignment(Element.ALIGN_CENTER); fac.setLeading(12f);
        celda1.addElement(fac);
        celda1.setColspan(2);
        factura.addCell(celda1);

        factura.addCell("Fecha de Compra");
        factura.addCell(dateFormat(clienteDetalle.getFecha_inicio()));

        PdfPCell info = new PdfPCell();
        Paragraph infoC = new Paragraph("Información de Cliente");
        info.addElement(infoC);
        info.setRowspan(4);
        factura.addCell(info);

        factura.addCell("Identificacion: "+clienteDetalle.getId_cliente().getTipo_documento()+" - "+clienteDetalle.getId_cliente().getDocumento());
        factura.addCell("Nombre: "+clienteDetalle.getId_cliente().getNombre());
        factura.addCell("Teléfono: "+clienteDetalle.getId_cliente().getTelefono());
        factura.addCell("Email: "+clienteDetalle.getId_cliente().getEmail());

        PdfPCell deta = new PdfPCell();
        Paragraph infoD = new Paragraph("Detalle de Plan");
        deta.addElement(infoD);
        deta.setRowspan(3);
        factura.addCell(deta);

        factura.addCell("ID: "+clienteDetalle.getId_detalle().getId_detalle()+"");
        factura.addCell(clienteDetalle.getId_detalle().getPlan()+"");
        factura.addCell("Precio: $"+clienteDetalle.getId_detalle().getPrecio()+"");

        factura.addCell("Suscripción");
        factura.addCell(clienteDetalle.getId_detalle().getMeses()+" Meses");

        factura.addCell("Fecha de Inicio");
        factura.addCell(dateFormat(clienteDetalle.getFecha_inicio()));

        factura.addCell("Fecha de Finalización");
        factura.addCell(dateFormat(clienteDetalle.getFecha_fin()));

        factura.addCell("Total");
        factura.addCell(clienteDetalle.getId_detalle().getPrecio()+"");

        factura.addCell("Método de Pago");
        factura.addCell(clienteDetalle.getId_metodopago().getDescripcion()+"");


        factura.setSpacingAfter(300);

        document.add(factura);

        document.add(new Paragraph("Vendido por BodyHealth",fontBody));


        document.close();
    }

    public void exportPedido(HttpServletResponse response, Pedido pedido) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(35);
        Font fontBody = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontBody.setSize(15);

        document.add(new Paragraph("Factura",fontTitle));
        document.add(new Paragraph("Gym BodyHealth",fontBody));
        document.add(new Paragraph("Direccion: XXXX",fontBody));

        document.add( Chunk.NEWLINE );

        PdfPTable factura = new PdfPTable(2);

        PdfPCell celda1 = new PdfPCell();
        Paragraph fac = new Paragraph("Factura #"+pedido.getId_pedido());
        fac.setAlignment(Element.ALIGN_CENTER); fac.setLeading(12f);
        celda1.addElement(fac);
        celda1.setColspan(2);
        factura.addCell(celda1);

        factura.addCell("Fecha de Compra");
        factura.addCell(dateFormat(pedido.getCompra().getFecha_compra()));

        PdfPCell info = new PdfPCell();
        Paragraph infoC = new Paragraph("Información del Producto");
        info.addElement(infoC);
        info.setRowspan(3);
        factura.addCell(info);

        factura.addCell("Nombre: "+pedido.getProducto().getNombre());
        factura.addCell("Proveedor: "+pedido.getProducto().getId_proveedor().getNombre_empresa());
        factura.addCell("Precio Unitario: $"+pedido.getProducto().getPrecio()+"");

        PdfPCell deta = new PdfPCell();
        Paragraph infoD = new Paragraph("Información del Cliente");
        deta.addElement(infoD);
        deta.setRowspan(4);
        factura.addCell(deta);

        factura.addCell("Identificación: "+pedido.getCompra().getId_cliente().getTipo_documento()+" - "+pedido.getCompra().getId_cliente().getDocumento());
        factura.addCell("Nombre: "+pedido.getCompra().getId_cliente().getNombre()+" "+pedido.getCompra().getId_cliente().getApellido());
        factura.addCell("Télefono: "+pedido.getCompra().getId_cliente().getTelefono());
        factura.addCell("Email: "+pedido.getCompra().getId_cliente().getEmail());

        factura.addCell("Cantidad");
        factura.addCell(pedido.getCantidad()+" Unidades");

        factura.addCell("Total");
        factura.addCell("$"+pedido.getCantidad()*pedido.getProducto().getPrecio());

        factura.addCell("Método de Pago");
        factura.addCell(pedido.getCompra().getId_metodopago().getDescripcion());


        factura.setSpacingAfter(300);

        document.add(factura);

        document.add(new Paragraph("Vendido por BodyHealth",fontBody));


        document.close();
    }

    public String dateFormat(Date date){
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        return dateFormat.format(date);
    }
}
