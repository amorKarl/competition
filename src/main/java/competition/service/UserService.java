package competition.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/7/007.
 */
public interface UserService {
    public Map enroll(String username,String password,String name);
    public Map login(String username,String password);
    public Map modify(String username,String newPassword);
    //2017.8.15
    public Map consumeTimes(String username);
    public Map personMessage(String username);
    public Map queryHadPublishTrade(String username);

    //2017.8.19
    public Map uploadIcon(MultipartFile img, HttpServletRequest request,String username);
}
