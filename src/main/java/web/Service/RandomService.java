
package web.Service;

import org.springframework.stereotype.Service;

@Service
public interface RandomService {
    public String generateRandomPassword(int length);
}
