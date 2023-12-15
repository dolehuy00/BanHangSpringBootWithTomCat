
package web.Service;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import web.DTO.OrderExcelDTO;

@Service
public interface ExportExcelService {
    public Workbook exportToExcel(OrderExcelDTO order);
}
