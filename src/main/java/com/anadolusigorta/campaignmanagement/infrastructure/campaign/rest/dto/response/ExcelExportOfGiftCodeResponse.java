package com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.GiftCode;
import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.infrastructure.common.file.FileInputStreamHelper;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.rest.dto.request.DiscountCode;
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
public class ExcelExportOfGiftCodeResponse {

    private List<GiftCode> codes;

    @Data
    @Builder
    @AllArgsConstructor
    private static class ExcelFileGenerationOutput {
        private final InputStreamResource inputStreamResource;
        private final File file;
    }

    public ResponseEntity<InputStreamResource> toExcelResponseEntity() {
        try {
            var excelFileGenerationOutput = getExcelFileGenerationOutput(codes, UUID.randomUUID().toString());
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

    private ExcelFileGenerationOutput getExcelFileGenerationOutput(List<GiftCode> codes, String fileName) throws IOException {
        byte[] excelContent = generateExcel(codes);
        if(excelContent.length != 0) {
            String tempDirectoryPath = FileUtils.getTempDirectoryPath();
            var file = new File(tempDirectoryPath + "discountcodes" + ".xlsx");
            FileUtils.writeByteArrayToFile(file, excelContent);
            FileInputStreamHelper fileInputStreamHelper = new FileInputStreamHelper(file);
            var resource = new InputStreamResource(fileInputStreamHelper);
            return ExcelFileGenerationOutput.builder().file(file).inputStreamResource(resource).build();
        } else {
            return null;
        }
    }

    private static byte[] generateExcel(List<GiftCode> codes) throws IOException {
        Workbook workbook;
        try {
            workbook = new XSSFWorkbook();
        } catch (Exception e) {
            e.printStackTrace();
            throw new CampaignManagementException("could.not.create.xssf.workbook");
        }
        Sheet sheet = workbook.createSheet(DiscountCode.EXCEL_SHEET_NAME);
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue(DiscountCode.EXCEL_CODE_HEADER_NAME);
        cell = row.createCell(1);
        cell.setCellValue(DiscountCode.EXCEL_SHORT_LINK_HEADER_NAME);
        cell = row.createCell(2);
        cell.setCellValue(DiscountCode.EXCEL_CUSTOMER_NO_HEADER_NAME);
        AtomicInteger atomicIntegerRow = new AtomicInteger(1);
        codes.forEach(code -> {
            Row sheetRow = sheet.createRow(atomicIntegerRow.getAndIncrement());
            Cell rowCell = sheetRow.createCell(0);
            rowCell.setCellValue(code.getCode());
            rowCell = sheetRow.createCell(1);
            rowCell.setCellValue(code.getQrCodeUrl() != null ? code.getQrCodeUrl() : "");
            rowCell = sheetRow.createCell(2);
            rowCell.setCellValue(code.getContactNumber() != null ? code.getContactNumber() : "");
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
