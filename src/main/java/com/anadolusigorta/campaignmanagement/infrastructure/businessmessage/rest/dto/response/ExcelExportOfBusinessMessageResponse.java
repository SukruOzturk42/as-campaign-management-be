package com.anadolusigorta.campaignmanagement.infrastructure.businessmessage.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.infrastructure.businessmessage.rest.dto.request.CreateBusinessMessageRequest;
import com.anadolusigorta.campaignmanagement.infrastructure.common.file.FileInputStreamHelper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExcelExportOfBusinessMessageResponse {

    private List<BusinessMessageResponse> messageList;

    @Data
    @Builder
    @AllArgsConstructor
    private static class ExcelFileGenerationOutput {
        private final InputStreamResource inputStreamResource;
        //private final ByteArrayResource inputStreamResource;
        private final File file;
    }

    public ResponseEntity<InputStreamResource> toExcelResponseEntity() {
        try {
            var excelFileGenerationOutput = getExcelFileGenerationOutput(messageList, UUID.randomUUID().toString());
            if(excelFileGenerationOutput != null) {
                var resource = excelFileGenerationOutput.getInputStreamResource();
                var file = excelFileGenerationOutput.getFile();
                var headers = new HttpHeaders();
                headers.add("Content-Disposition", "attachment;filename:sdasd.xlsx");
                return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                        .contentLength(file.length()).headers(headers).body(resource);
            }
        } catch (Exception e) {
            throw new CampaignManagementException("excel.file.generation.output.failed");
        }

        return null;
    }

    private ExcelExportOfBusinessMessageResponse.ExcelFileGenerationOutput getExcelFileGenerationOutput(List<BusinessMessageResponse> messages, String fileName) throws IOException {
        byte[] excelContent = generateExcel(messages);
        if(excelContent.length != 0) {
            String tempDirectoryPath = FileUtils.getTempDirectoryPath();
            var file = new File(tempDirectoryPath + "campusmessages" + fileName + ".xlsx");
            FileUtils.writeByteArrayToFile(file, excelContent);
            FileInputStreamHelper fileInputStreamHelper = new FileInputStreamHelper(file);
            var resource = new InputStreamResource(fileInputStreamHelper);
            //Path path = Paths.get(file.getAbsolutePath());
            //ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
            return ExcelExportOfBusinessMessageResponse.ExcelFileGenerationOutput.builder().file(file).inputStreamResource(resource).build();
        } else {
            return null;
        }
    }

    private static byte[] generateExcel(List<BusinessMessageResponse> messages) throws IOException {
        Workbook workbook;
        try {
            workbook = new XSSFWorkbook();
        } catch (Exception e) {
            e.printStackTrace();
            throw new CampaignManagementException("could.not.create.xssf.workbook");
        }
        Sheet sheet = workbook.createSheet("BusinessMessages");
        Row rowHeader = sheet.createRow(0);
        Cell cell = rowHeader.createCell(0);
        cell.setCellValue("ID");
        cell = rowHeader.createCell(1);
        cell.setCellValue("MESAJ KODU");
        cell = rowHeader.createCell(2);
        cell.setCellValue("MESAJ KEY");
        cell = rowHeader.createCell(3);
        cell.setCellValue("MESAJ AÇIKLAMASI");
        cell = rowHeader.createCell(4);
        cell.setCellValue("MESAJ NOTU");

        AtomicInteger atomicIntegerRow = new AtomicInteger(1);
        messages.forEach(message -> {
            Row row = sheet.createRow(atomicIntegerRow.getAndIncrement());
            Cell dataCell = row.createCell(0);
            dataCell.setCellValue(message.getId() != null ? message.getId().toString() : "");
            dataCell = row.createCell(1);
            dataCell.setCellValue(message.getCode() != null ? message.getCode() : "");
            dataCell = row.createCell(2);
            dataCell.setCellValue(message.getKey() != null ? message.getKey() : "");
            dataCell = row.createCell(3);
            dataCell.setCellValue(message.getDescription() != null ? message.getDescription() : "");
            dataCell = row.createCell(4);
            dataCell.setCellValue(message.getNote() != null ? message.getNote() : "");

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
