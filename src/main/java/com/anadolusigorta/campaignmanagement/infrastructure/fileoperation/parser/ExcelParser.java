/* dks20165 created on 25.08.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.fileoperation.parser */

package com.anadolusigorta.campaignmanagement.infrastructure.fileoperation.parser;

import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.infrastructure.fileoperation.util.ExcelUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

@Component
@RequiredArgsConstructor
@Slf4j
public class ExcelParser {

	private final ExcelUtils excelUtils;

	public <T> List<T> parseExcel(Class<T> type,String base64FileData, String sheetName) {
          var workbook=getWorkbook(base64FileData);
          var sheet = workbook.getSheet(sheetName);
          var sheetData =  getExcelSheetData(sheet);
/*
		try {
			FileOutputStream out = new FileOutputStream(new File(""));
			workbook.write(out);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
*/
		try{
			  Gson gson = new Gson();

			  String jsonOutput = gson.toJson(sheetData);
			  Type typeOfT = TypeToken.getParameterized(List.class, type).getType();

			  return new Gson().fromJson(jsonOutput, typeOfT);
		  }catch (Exception e){
			  throw new CampaignManagementException("campaign.excel.error");
		  }
	}
	private Workbook getWorkbook(String base64FileData){

		byte[] decodedBytes = Base64.getDecoder().decode(base64FileData);
		InputStream inputStream = new ByteArrayInputStream(decodedBytes);
		try {
			return new XSSFWorkbook(inputStream);
		} catch (Exception e) {
			throw new CampaignManagementException("campaign.excel.error");
		}
	}

	private List<Map<String, String>> getExcelSheetData(Sheet sheet){
		try{
			DataFormatter dataFormatter = new DataFormatter();
			List<Map<String, String>> mapResponse = new ArrayList<>();
			Supplier<Stream<Row>> rowStreamSupplier = excelUtils.getRowStreamSupplier(sheet);
			if (rowStreamSupplier.get().findFirst().isPresent()) {

				var optionalHeader = rowStreamSupplier.get().findFirst();
				if (optionalHeader.isPresent()) {
					Row headerRow = optionalHeader.get();

					List<String> headerCells = excelUtils.getStream(headerRow)
							.map(Cell::getStringCellValue)
							.map(String::valueOf)
							.filter(StringUtils::isNoneBlank)
							.collect(Collectors.toList());

					int colCount = headerCells.size();

					mapResponse = rowStreamSupplier.get().skip(1).map(row -> {

						List<String> cellList = excelUtils.getStream(row)
								.map(dataFormatter::formatCellValue)
								.map(String::trim)
								.filter(StringUtils::isNoneBlank)
								.collect(Collectors.toList());

						if (!cellList.isEmpty()) {
							return excelUtils.cellIteratorSupplier(colCount).get()
									.collect(toMap(headerCells::get, cellList::get));
						} else {
							return null;
						}

					}).filter(Objects::nonNull).collect(Collectors.toList());
				}
			}

			return mapResponse;
		}catch (Exception e){
			throw new CampaignManagementException("campaign.excel.error");
		}
	}
	private Object getCellValueByCellType(Cell cell) {

		if (cell.getCellTypeEnum() == CellType.STRING) {
			return cell.getStringCellValue();
		} else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
			return cell.getNumericCellValue();
		} else if (cell.getCellTypeEnum() == CellType.BLANK) {
			return "";
		} else {
			return cell.getErrorCellValue();
		}
	}
}
