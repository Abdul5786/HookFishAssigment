package com.Abdul.HookiFish.dtos;

import com.Abdul.HookiFish.entities.GenerateBill;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;


@Component
public class PdfGenerator {
    public static byte[] generatePdf(GeneratedBillDto generatedBillDto) throws IOException {
        PDDocument pdDocument = new PDDocument();
        PDPage pdPage = new PDPage();
        pdDocument.addPage(pdPage);
        PDPageContentStream contentStream = new PDPageContentStream(pdDocument, pdPage);
        contentStream.setFont(PDType1Font.HELVETICA, 12);

        // Start position for text
        float startX = 50;
        float startY = 700;

        // Cell dimensions
        float cellWidth = 150;
        float cellHeight = 40;

        // Cell padding
        float cellPaddingX = 2;
        float cellPaddingY = 5;


        // Array of data to display
        String[][] content = {
                {"Bill ID :", String.valueOf(generatedBillDto.getBillId())},
                {"Bill Date  :", generatedBillDto.getBillDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))},
                {"Customer Name :", generatedBillDto.getCustomer().getName()},
                {"Product Name :", generatedBillDto.getProductName()},
                {"Product Price:",  String.valueOf(generatedBillDto.getProductPrice())},
                {"GST:", String.valueOf(generatedBillDto.getGst())},
                {"Discount :", String.valueOf(generatedBillDto.getDiscount())},
                {"Total Amount :", String.valueOf(generatedBillDto.getTotalAmount())},
                {"Grand Total : = ",String.valueOf(generatedBillDto.getGrandTotal())},

        };

        // Draw the content
        for (int i = 0; i < content.length; i++) {
            float y = startY - (i * cellHeight);
            for (int j = 0; j < content[i].length; j++) {
                float x = startX + (j * cellWidth);
                float textWidth = PDType1Font.HELVETICA.getStringWidth(content[i][j]) / 1000 * 12;
                float textHeight = PDType1Font.HELVETICA.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * 12;
                float xOffset = (cellWidth - textWidth) / 2;
                float yOffset = (cellHeight - textHeight) / 2;
                contentStream.beginText();
                contentStream.newLineAtOffset(x + cellPaddingX + xOffset, y - cellPaddingY - yOffset); // Adjust for center alignment and padding
                contentStream.showText(content[i][j]);
                contentStream.endText();
            }
        }

        // Draw table lines
        for (int i = 0; i <= content.length; i++) {
            contentStream.moveTo(startX, startY - (i * cellHeight));
            contentStream.lineTo(startX + (content[0].length * cellWidth), startY - (i * cellHeight));
        }

        for (int j = 0; j <= content[0].length; j++) {
            contentStream.moveTo(startX + (j * cellWidth), startY);
            contentStream.lineTo(startX + (j * cellWidth), startY - (content.length * cellHeight));
        }

        contentStream.stroke();
        contentStream.close();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        pdDocument.save(byteArrayOutputStream);
        pdDocument.close();

        return byteArrayOutputStream.toByteArray();

    }

}