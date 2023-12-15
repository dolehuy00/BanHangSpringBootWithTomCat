package web.Service;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.springframework.stereotype.Service;
import web.DTO.OrderExcelDTO;
import web.DTO.ProductOrderExcelDTO;

@Service
public class ExportExcelServiceImpl implements ExportExcelService {

    @Override
    public Workbook exportToExcel(OrderExcelDTO order) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Hóa đơn");
        sheet.setDisplayGridlines(false);
        sheet.setZoom(145);
        
        // Tạo các cell style
        CellStyle boldCellStyleCenter = workbook.createCellStyle();
        Font boldFont = workbook.createFont();
        boldFont.setBold(true);
        boldCellStyleCenter.setFont(boldFont);
        boldCellStyleCenter.setVerticalAlignment(VerticalAlignment.CENTER);
        boldCellStyleCenter.setAlignment(HorizontalAlignment.CENTER);
        boldCellStyleCenter.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        boldCellStyleCenter.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        
        CellStyle titleCellStyleCenter = workbook.createCellStyle();
        Font boldTitleFont = workbook.createFont();
        boldTitleFont.setBold(true);
        boldTitleFont.setFontHeightInPoints((short) 14);
        titleCellStyleCenter.setFont(boldTitleFont);
        titleCellStyleCenter.setVerticalAlignment(VerticalAlignment.CENTER);
        titleCellStyleCenter.setAlignment(HorizontalAlignment.CENTER);
        
        CellStyle cellStyleCenter = workbook.createCellStyle();
        cellStyleCenter.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyleCenter.setAlignment(HorizontalAlignment.CENTER);
        
        CellStyle boldCellStyleCenterNoColor = workbook.createCellStyle();
        boldCellStyleCenterNoColor.setFont(boldFont);
        boldCellStyleCenterNoColor.setVerticalAlignment(VerticalAlignment.CENTER);
        boldCellStyleCenterNoColor.setAlignment(HorizontalAlignment.CENTER);
        boldCellStyleCenterNoColor.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // Điền dữ liệu vào từng ô tương ứng
        int rowIndex = 0;

        // Hàng đầu tiên là mã hóa đơn
        Row row = sheet.createRow(rowIndex);
        Cell cell = row.createCell(1);
        cell.setCellValue("Mã hóa đơn: " + order.getOrderId());
        sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 1, 4)); // Gộp các ô thành một ô
        cell.setCellStyle(boldCellStyleCenter);
        rowIndex++;

        // Hàng thứ 2 là tiêu đề "Hóa đơn bán hàng"
        row = sheet.createRow(rowIndex);
        cell = row.createCell(1);
        cell.setCellValue("Hóa đơn bán hàng");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 1, 4)); // Gộp các ô thành một ô
        cell.setCellStyle(titleCellStyleCenter);
        rowIndex++;

        // Hàng rỗng
        row = sheet.createRow(rowIndex);
        cell = row.createCell(1);
        cell.setCellValue("");
        rowIndex++;
        
        // Hàng thứ 3 là thông tin ngày đặt hàng
        row = sheet.createRow(rowIndex);
        cell = row.createCell(1);
        cell.setCellValue("Ngày đặt hàng: " + order.getDate());
        rowIndex++;

        // Hàng thứ 4 là thông tin tên khách hàng
        row = sheet.createRow(rowIndex);
        cell = row.createCell(1);
        cell.setCellValue("Tên khách hàng: " + order.getCustomerName());

        rowIndex++;

        // Hàng thứ 5 là thông tin số điện thoại khách hàng
        row = sheet.createRow(rowIndex);
        cell = row.createCell(1);
        cell.setCellValue("Số điện thoại: " + order.getPhoneNumber());
        rowIndex++;

        // Hàng thứ 6 là thông tin địa chỉ giao hàng
        row = sheet.createRow(rowIndex);
        cell = row.createCell(1);
        cell.setCellValue("Địa chỉ giao hàng: " + order.getShippingAddress());
        rowIndex++;

        // Hàng thứ 7 là thông tin tên nhân viên
        row = sheet.createRow(rowIndex);
        cell = row.createCell(1);
        cell.setCellValue("Tên nhân viên: " + order.getEmployeeName());
        rowIndex++;
    
        //title
        row = sheet.createRow(rowIndex);
        cell = row.createCell(1);
        cell.setCellValue("Tên sản phẩm");
        cell.setCellStyle(boldCellStyleCenterNoColor);

        cell = row.createCell(2);
        cell.setCellValue("SL");
        cell.setCellStyle(boldCellStyleCenterNoColor);

        cell = row.createCell(3);
        cell.setCellValue("Giá");
        cell.setCellStyle(boldCellStyleCenterNoColor);

        cell = row.createCell(4);
        cell.setCellValue("Thành tiền");
        cell.setCellStyle(boldCellStyleCenterNoColor);

        rowIndex++;

        // Hàng thứ 8 là danh sách sản phẩm
        for (ProductOrderExcelDTO product : order.getProductList()) {
            row = sheet.createRow(rowIndex);
            cell = row.createCell(1);
            cell.setCellValue(product.getName());
            cell.setCellStyle(cellStyleCenter);

            cell = row.createCell(2);
            cell.setCellValue(product.getQuantity());

            cell = row.createCell(3);
            cell.setCellValue(product.getPrice());

            cell = row.createCell(4);
            cell.setCellValue(product.getTotalPrice());

            rowIndex++;
        }

        // Hàng tiếp theo là tổng tiền phải thanh toán
        row = sheet.createRow(rowIndex);
        cell = row.createCell(3);
        cell.setCellValue("Tổng tiền:");
        cell.setCellStyle(boldCellStyleCenterNoColor);

        cell = row.createCell(4);
        cell.setCellValue(order.getTotalAmount());

        rowIndex++;
        
        // Hàng rỗng
        row = sheet.createRow(rowIndex);
        cell = row.createCell(1);
        cell.setCellValue("");
        rowIndex++;
        
        // Hàng tiếp theo là hotline
        row = sheet.createRow(rowIndex);
        cell = row.createCell(1);
        cell.setCellValue("Hotline: 090909009");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 1, 4)); // Gộp các ô thành một ô
        cell.setCellStyle(boldCellStyleCenter);

        // Hàng cuối cùng là câu "Cảm ơn quý khách đã mua hàng"
        rowIndex++;
        row = sheet.createRow(rowIndex);
        cell = row.createCell(1);
        cell.setCellValue("Cảm ơn quý khách đã mua hàng");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 1, 4)); // Gộp các ô thành một ô
        cell.setCellStyle(boldCellStyleCenter);
        
        // Ẩn các đường kẻ
        for (int i = 0; i <= rowIndex; i++) {
            for (int j = 0; j <= 3; j++) {
                CellRangeAddress cellRangeAddress = new CellRangeAddress(i, i, j, j);
                RegionUtil.setBorderTop(BorderStyle.NONE, cellRangeAddress, sheet);
                RegionUtil.setBorderBottom(BorderStyle.NONE, cellRangeAddress, sheet);
                RegionUtil.setBorderLeft(BorderStyle.NONE, cellRangeAddress, sheet);
                RegionUtil.setBorderRight(BorderStyle.NONE, cellRangeAddress, sheet);
            }
        }

        // Tự động điều chỉnh độ rộng cột để vừa với nội dung
        for (int i = 0; i < 4; i++) {
            sheet.autoSizeColumn(i);
        }

        return workbook;
    }

}
