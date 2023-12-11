
package web.Service;

import org.springframework.stereotype.Service;

@Service
public interface FileService {
    public void saveFileWithBase64String(String base64Image, String fileName);
}
