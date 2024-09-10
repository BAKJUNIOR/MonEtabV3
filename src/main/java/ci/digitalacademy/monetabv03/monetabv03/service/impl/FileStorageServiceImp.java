package ci.digitalacademy.monetabv03.monetabv03.service.impl;

import ci.digitalacademy.monetabv03.monetabv03.service.FileStorageService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FileStorageServiceImp implements ci.digitalacademy.monetabv03.monetabv03.service.FileStorageService {
    private  final Cloudinary cloudinary;

    @Override
    public String upload(MultipartFile file) throws IOException {

// Upload the image
        Map params1 = ObjectUtils.asMap(
                "use_filename", true,
                "unique_filename", false,
                "overwrite", true
        );

        Map upload = cloudinary.uploader().upload(file.getBytes(), params1);
        System.out.println(upload);
        return  upload.get("url").toString();
    }
}

