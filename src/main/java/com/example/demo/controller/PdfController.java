package com.example.demo.controller;
import com.example.demo.service.JasperSoftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/pdf")
@RestController
public class PdfController {
    @Autowired
    private JasperSoftService service;

    @GetMapping( "/users")
    public String sdaprintln() {
        System.out.println("sad");
        return "null";
    }

    @GetMapping("/jasperReport")
    public ResponseEntity<byte[]> generatePdf() {
        byte[] pdfData = service.generatePdf();
        if (pdfData != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", "report.pdf");
            return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
