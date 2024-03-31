package com.example.demo.service;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.management.JMRuntimeException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Optional;

@Service
public class JasperSoftService {

    public byte[] generatePdf() {
            try {
                // Generate PDF file using JasperReports
                // Assume generatePdfFromXml is a method to convert XML to PDF
                byte[] pdfData = generatePdfFromXml("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<jasperReport name=\"SimpleReport\" pageWidth=\"595\" pageHeight=\"842\" columnWidth=\"555\" leftMargin=\"20\" rightMargin=\"20\" topMargin=\"20\" bottomMargin=\"20\">\n" +
                        "    <title>\n" +
                        "        <band height=\"50\">\n" +
                        "            <staticText>\n" +
                        "                <reportElement x=\"0\" y=\"0\" width=\"100\" height=\"20\"/>\n" +
                        "                <text><![CDATA[Static Report Title]]></text>\n" +
                        "            </staticText>\n" +
                        "        </band>\n" +
                        "    </title>\n" +
                        "    <detail>\n" +
                        "        <band height=\"20\">\n" +
                        "            <staticText>\n" +
                        "                <reportElement x=\"0\" y=\"0\" width=\"100\" height=\"20\"/>\n" +
                        "                <text><![CDATA[Static Text]]></text>\n" +
                        "            </staticText>\n" +
                        "        </band>\n" +
                        "    </detail>\n" +
                        "</jasperReport>\n");
                return pdfData;
            } catch (Exception e) {
                // Handle exception
            }
        return null;
    }

    private byte[] generatePdfFromXml(String xmlData) throws JMRuntimeException, JRException {
        try {
        InputStream templateStream = new ByteArrayInputStream(xmlData.getBytes(StandardCharsets.UTF_8));
        JasperReport jasperReport = JasperCompileManager.compileReport(templateStream);

        // Fill the report with data (if needed)
        // For example, you can use a JRXmlDataSource to provide XML data to the report
        JRXmlDataSource dataSource = new JRXmlDataSource(new ByteArrayInputStream(xmlData.getBytes(StandardCharsets.UTF_8)), "/path/to/data/xpath");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), dataSource);

        // Export the filled report to a PDF file
        byte[] pdfData = JasperExportManager.exportReportToPdf(jasperPrint);
        return pdfData;
        } catch (JRException e) {
            e.printStackTrace();
        }
        return null;
    }
}