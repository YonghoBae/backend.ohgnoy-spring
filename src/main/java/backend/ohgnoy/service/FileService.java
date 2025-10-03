package backend.ohgnoy.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileService {
    private final String uploadDir = System.getProperty("user.dir") + "/uploads/";

    public String uploadFile(MultipartFile file) {
        // 1. 파일이 비어있으면 null 반환
        if(file == null || file.isEmpty()) {
            return null;
        }

        // 2. 폴더가 없으면 생성
        File dir = new File(uploadDir);
        if(!dir.exists()){
            dir.mkdirs();
        }

        // 3. 파일 이름 중복 방지를 위해 UUID로 고유한 이름 생성
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String savedFilename = UUID.randomUUID() + extension;

        try {
            // 4. 실제 지정된 경로에 파일 저장 (Multer의 역할)
            file.transferTo(new File(uploadDir + savedFilename));
            return "/uploads/" + savedFilename; // 클라이언트가 이미지에 접근할 수 있는 경로 반환
        }catch(IOException e) {
            throw new RuntimeException("파일 업로드에 실패했습니다.", e);
        }
    }
}
