package competition.vo;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/8/17/017.
 */
public class Upload {
    public static String upload(MultipartFile file, HttpServletRequest request) {

        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat ff = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String tempUrl= "upload//" + ft.format(dNow).toString() + "//";
        String uploadurl = request.getSession().getServletContext().getRealPath("/") +tempUrl;

        String img[] = file.getOriginalFilename().split("\\.");
        String suffix = img[img.length - 1];
        String filename = ff.format(dNow).toString() + "." + suffix;
        System.out.println(uploadurl);
        File dir = new File(uploadurl);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        System.out.println("文件上传到" + uploadurl + filename);
        File targetfile = new File(uploadurl + filename);
        if (!targetfile.exists()) {
            try {
                targetfile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            file.transferTo(targetfile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tempUrl + filename;
    }

    public static boolean isImage(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String[] img = fileName.split("\\.");
        String suffix = img[img.length - 1];
        if (suffix.equalsIgnoreCase("jpg") || suffix.equalsIgnoreCase("jpeg") || suffix.equalsIgnoreCase("png")) {
            return true;
        } else {
            return false;
        }

    }
}
