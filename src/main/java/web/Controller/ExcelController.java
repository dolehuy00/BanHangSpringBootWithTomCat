package web.Controller;

import jakarta.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import web.DTO.OrderExcelDTO;
import web.DTO.ProductOrderExcelDTO;
import web.Model.OrderItem;
import web.Model.Orders;
import web.Model.User;
import web.Service.ExportExcelService;
import web.Service.OrderService;

@RestController
public class ExcelController {

    @Autowired
    private OrderService orderServ;
    @Autowired
    private HttpSession session;
    @Autowired
    private ExportExcelService excelServ;

    //Xuất excel đơn hàng
    @GetMapping("admin/export-excel/{id}")
    public ResponseEntity<ByteArrayResource> ExportExcel(Model model,
            @PathVariable("id") Integer orderID) throws IOException {
        Orders order = orderServ.findOrderById(orderID);
        OrderExcelDTO orderDTO = new OrderExcelDTO();
        orderDTO.setCustomerName(order.getCustomerID().getName());
        orderDTO.setDate(order.getDate().format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        orderDTO.setEmployeeName(order.getSeller().getName());
        orderDTO.setOrderId("#" + order.getOrderID());
        orderDTO.setPhoneNumber(order.getPhoneNumber());
        orderDTO.setShippingAddress(order.getAddress());
        orderDTO.setTotalAmount(order.getTotalPrice().toString());
        List<ProductOrderExcelDTO> listProduct = new ArrayList<>();
        for (OrderItem orderItem : order.getOrderItemList()) {
            ProductOrderExcelDTO item = new ProductOrderExcelDTO();
            item.setName(orderItem.getProduct().getName());
            item.setPrice(orderItem.getProduct().getPrice().toString());
            item.setQuantity(String.valueOf(orderItem.getQuantity()));
            BigInteger totalPrice = BigInteger.valueOf(orderItem.getQuantity()).multiply(orderItem.getProduct().getPrice());
            item.setTotalPrice(totalPrice.toString());
            listProduct.add(item);
        }
        orderDTO.setProductList(listProduct);
        Workbook workbook = excelServ.exportToExcel(orderDTO);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);

        // Tạo ByteArrayResource từ ByteArrayOutputStream
        ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());

        // Xây dựng phản hồi HTTP
        HttpHeaders headers = new HttpHeaders();
        String fileName = "attachment; filename="+
                orderDTO.getOrderId()+"-"+orderDTO.getDate()+".xlsx";
        headers.add(HttpHeaders.CONTENT_DISPOSITION, fileName);

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
