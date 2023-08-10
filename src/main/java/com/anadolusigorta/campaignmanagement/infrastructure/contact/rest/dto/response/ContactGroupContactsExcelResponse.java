package com.anadolusigorta.campaignmanagement.infrastructure.contact.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.contact.model.Contact;
import com.anadolusigorta.campaignmanagement.infrastructure.common.file.FileInputStreamHelper;
import com.anadolusigorta.campaignmanagement.infrastructure.fileoperation.model.ExcelContact;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactGroupContactsExcelResponse {

    private List<Contact> contacts=new ArrayList<>();

    @Data
    @Builder
    @AllArgsConstructor
    private static class ExcelFileGenerationOutput {
        private final InputStreamResource inputStreamResource;
        private final File file;
    }

    public ResponseEntity<InputStreamResource> toExcelResponseEntity() {
        try {
            var excelFileGenerationOutput = getExcelFileGenerationOutput();
            if(excelFileGenerationOutput != null) {
                var resource = excelFileGenerationOutput.getInputStreamResource();
                var file = excelFileGenerationOutput.getFile();
                var headers = new HttpHeaders();
                headers.add("Content-Disposition", "attachment;filename:sdasd.xlsx");
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                        .contentLength(file.length()).headers(headers).body(resource);
            }
        } catch (Exception e) {
            throw new CampaignManagementException("excel.file.generation.output.failed");
        }

        return null;
    }

    private ExcelFileGenerationOutput getExcelFileGenerationOutput() throws IOException {
        byte[] excelContent = generateExcel();
        if(excelContent.length != 0) {
            String tempDirectoryPath = FileUtils.getTempDirectoryPath();
            var file = new File(tempDirectoryPath + "template-contact-group" + ".xlsx");
            FileUtils.writeByteArrayToFile(file, excelContent);
            FileInputStreamHelper fileInputStreamHelper = new FileInputStreamHelper(file);
            var resource = new InputStreamResource(fileInputStreamHelper);
            return ExcelFileGenerationOutput.builder().file(file).inputStreamResource(resource).build();
        } else {
            return null;
        }
    }

    private  byte[] generateExcel() throws IOException {
        Workbook workbook;
        try {
            workbook = new XSSFWorkbook();
        } catch (Exception e) {
            e.printStackTrace();
            throw new CampaignManagementException("could.not.create.xssf.workbook");
        }
           var sheet =workbook.createSheet(ExcelContact.EXCEL_SHEET_NAME);
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue(ExcelContact.RULE_GROUP_CUSTOMER_EXCEL_CUSTOMER_NO_HEADER_NAME);
        AtomicInteger atomicIntegerRow = new AtomicInteger(1);
           this.contacts.forEach(contact -> {
            var dataRow = sheet.createRow(atomicIntegerRow.getAndIncrement());
            var dataRowCell = dataRow.createCell(0);
               dataRowCell.setCellValue(contact.getContactNo());
        });

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            workbook.write(byteArrayOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            workbook.close();
            byteArrayOutputStream.close();
        }
        return byteArrayOutputStream.toByteArray();
    }

}
