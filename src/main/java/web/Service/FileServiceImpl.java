
package web.Service;

import jakarta.servlet.ServletContext;
import jakarta.xml.bind.DatatypeConverter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private ServletContext servletContext;

    @Override
    public void saveFileWithBase64String(String base64Image, String fileName) {
        try {
            System.err.println("Save file in: "+servletContext.getRealPath("/")+"WEB-INF\\classes\\static\\images\\");
            String uploadDir = servletContext.getRealPath("/")+"WEB-INF\\classes\\static\\images\\";
            String filePath = uploadDir + fileName;
            Path path = new File(filePath).toPath();

            // Chuyển đổi base64Image thành byte[]
            byte[] imageBytes = DatatypeConverter.parseBase64Binary(base64Image.replaceAll("data:image/.+;base64,", ""));

            // Lưu hình ảnh vào thư mục
            Files.createDirectories(path.getParent());
            Files.write(path, imageBytes);
            System.err.println("Save successfully");
        } catch (Exception e) {
            throw new RuntimeException("Error saving file", e);
        }
    }
}
