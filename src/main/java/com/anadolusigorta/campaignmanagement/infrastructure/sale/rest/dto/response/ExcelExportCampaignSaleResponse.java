package com.anadolusigorta.campaignmanagement.infrastructure.sale.rest.dto.response;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.GiftCode;
import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.common.Constants;
import com.anadolusigorta.campaignmanagement.domain.sale.model.SaleInformation;
import com.anadolusigorta.campaignmanagement.infrastructure.common.file.FileInputStreamHelper;
import com.anadolusigorta.campaignmanagement.infrastructure.fileoperation.parser.ExcelCampaign;
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
public class ExcelExportCampaignSaleResponse {

    private List<SaleInformation> campaignSales;

    @Data
    @Builder
    @AllArgsConstructor
    private static class ExcelFileGenerationOutput {
        private final InputStreamResource inputStreamResource;
        private final File file;
    }

    public ResponseEntity<InputStreamResource> toExcelResponseEntity() {
        try {
            var excelFileGenerationOutput = getExcelFileGenerationOutput(campaignSales, UUID.randomUUID().toString());
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

    private ExcelExportCampaignSaleResponse.ExcelFileGenerationOutput getExcelFileGenerationOutput(List<SaleInformation> campaignSales, String fileName) throws IOException {
        byte[] excelContent = generateExcel(campaignSales);
        if(excelContent.length != 0) {
            String tempDirectoryPath = FileUtils.getTempDirectoryPath();
            var file = new File(tempDirectoryPath + "campuscampaigns" + ".xlsx");
            FileUtils.writeByteArrayToFile(file, excelContent);
            FileInputStreamHelper fileInputStreamHelper = new FileInputStreamHelper(file);
            var resource = new InputStreamResource(fileInputStreamHelper);
            return ExcelExportCampaignSaleResponse.ExcelFileGenerationOutput.builder().file(file).inputStreamResource(resource).build();
        } else {
            return null;
        }
    }

    private static byte[] generateExcel(List<SaleInformation> campaignSales) throws IOException {
        Workbook workbook;
        try {
            workbook = new XSSFWorkbook();
        } catch (Exception e) {
            e.printStackTrace();
            throw new CampaignManagementException("could.not.create.xssf.workbook");
        }

        Sheet sheet = workbook.createSheet(ExcelCampaign.EXCEL_SHEET_NAME);
        Row rowHeader = sheet.createRow(0);
        Cell cell = rowHeader.createCell(0);

        cell.setCellValue(ExcelCampaign.EXCEL_CAMPAIGN_ID_HEADER_NAME);

        cell = rowHeader.createCell(1);
        cell.setCellValue(ExcelCampaign.EXCEL_CAMPAIGN_NAME_HEADER_NAME);

        cell = rowHeader.createCell(2);
        cell.setCellValue(ExcelCampaign.EXCEL_CONTACT_NO_HEADER_NAME);

        cell = rowHeader.createCell(3);
        cell.setCellValue(ExcelCampaign.EXCEL_RULE_GROUP_HEADER_NAME);

        cell = rowHeader.createCell(4);
        cell.setCellValue(ExcelCampaign.EXCEL_SALE_PARTICIPATE_DATE_HEADER_NAME);

        cell = rowHeader.createCell(5);
        cell.setCellValue(ExcelCampaign.EXCEL_REWARD_SEND_DATE_HEADER_NAME);

        cell = rowHeader.createCell(6);
        cell.setCellValue(ExcelCampaign.EXCEL_REWARD_OWNER_HEADER_NAME);

        cell = rowHeader.createCell(7);
        cell.setCellValue(ExcelCampaign.EXCEL_REWARD_SEND_TYPE_HEADER_NAME);

        cell = rowHeader.createCell(8);
        cell.setCellValue(ExcelCampaign.EXCEL_REWARD_SEND_STATE_HEADER_NAME);

        cell = rowHeader.createCell(9);
        cell.setCellValue(ExcelCampaign.EXCEL_REWARD_SEND_DESCRIPTION_HEADER_NAME);

        cell = rowHeader.createCell(10);
        cell.setCellValue(ExcelCampaign.EXCEL_POLICY_NO_HEADER_NAME);

        cell = rowHeader.createCell(11);
        cell.setCellValue(ExcelCampaign.EXCEL_PROPOSAL_NO_HEADER_NAME);

        cell = rowHeader.createCell(12);
        cell.setCellValue(ExcelCampaign.EXCEL_GIFT_CODE_HEADER_NAME);

        cell = rowHeader.createCell(13);
        cell.setCellValue(ExcelCampaign.EXCEL_DISCOUNT_HEADER_NAME);

        cell = rowHeader.createCell(14);
        cell.setCellValue(ExcelCampaign.EXCEL_DISCOUNT_CODE_HEADER_NAME);

        AtomicInteger atomicIntegerRow = new AtomicInteger(1);
        campaignSales.forEach(saleCampaignInformation -> {
            Row row = sheet.createRow(atomicIntegerRow.getAndIncrement());
            Cell dataCell = row.createCell(0);
            dataCell.setCellValue(saleCampaignInformation.getCampaignInformation()!= null ?
                    saleCampaignInformation.getCampaignInformation().getCampaignId().toString()
                    : "");
            dataCell = row.createCell(1);
            dataCell.setCellValue(saleCampaignInformation.getCampaignInformation()!= null ?
                    saleCampaignInformation.getCampaignInformation().getCampaignName()
                    : "");

            dataCell = row.createCell(2);
            dataCell.setCellValue(saleCampaignInformation.getContactNumber());


            dataCell = row.createCell(3);
            dataCell.setCellValue(saleCampaignInformation.getCampaignRuleGroup()!= null ?
                    saleCampaignInformation.getCampaignRuleGroup().getName()
                    : "");

            dataCell = row.createCell(4);
            dataCell.setCellValue(saleCampaignInformation.getCreateDate().toString());

            dataCell = row.createCell(5);
            dataCell.setCellValue(saleCampaignInformation.getSaleRewardGift()!= null ?
                    saleCampaignInformation.getSaleRewardGift().getRewardSendDate().toString()
                    : "");



            dataCell = row.createCell(6);
            dataCell.setCellValue(saleCampaignInformation.getSaleRewardGift()!= null ?
                    saleCampaignInformation.getSaleRewardGift().getRewardOwnerContactNo()
                    : "");

            dataCell = row.createCell(7);
            dataCell.setCellValue(saleCampaignInformation.getSaleRewardGift()!= null ?
                    saleCampaignInformation.getSaleRewardGift()
                            .getCampaignRewardGift().
                            getCampaignRewardGift()
                            .getRewardGiftSendMethodType().getName()
                    : "");

            dataCell = row.createCell(8);
            dataCell.setCellValue(saleCampaignInformation.getSaleRewardGift()!= null ?
                    saleCampaignInformation.getSaleRewardGift().getRewardNotificationStatus()
                            .getDescription()
                    : "");


            dataCell = row.createCell(9);
            dataCell.setCellValue(saleCampaignInformation.getSaleRewardGift()!= null ?
                    saleCampaignInformation.getSaleRewardGift().getNotificationDeliveryDescription()
                    : "");

            dataCell = row.createCell(10);
            dataCell.setCellValue(saleCampaignInformation.getSoldPolicyDetail()!= null ?
                    saleCampaignInformation.getSoldPolicyDetail().getPolicyNumber()
                    : "");

            dataCell = row.createCell(11);
            dataCell.setCellValue(saleCampaignInformation.getSoldPolicyDetail()!= null ?
                    saleCampaignInformation.getSoldPolicyDetail().getProposalNumber()
                    : "");


            dataCell = row.createCell(12);
            dataCell.setCellValue(saleCampaignInformation.getSaleRewardGift()!= null ?
                    saleCampaignInformation.getSaleRewardGift().getGiftCodes()!=null?
                            String.join(Constants.ATTRIBUTE_VALUE_DELIMITER + " ",
                                    saleCampaignInformation.getSaleRewardGift().getGiftCodes()
                                            .stream()
                                            .map(GiftCode::getCode)
                                            .toList()):
                            saleCampaignInformation.getSaleRewardGift().getGiftCode().toString()
                    : "");

            dataCell = row.createCell(13);
            dataCell.setCellValue(saleCampaignInformation.getSoldPolicyDetail()!= null ?
                    saleCampaignInformation.getSoldPolicyDetail().getDiscountValue()
                    : "");

            dataCell = row.createCell(14);
            dataCell.setCellValue(saleCampaignInformation.getSoldPolicyDetail()!= null ?
                    saleCampaignInformation.getCampaignCode()
                    : "");



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
