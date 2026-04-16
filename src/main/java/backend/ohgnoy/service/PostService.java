package backend.ohgnoy.service;

import backend.ohgnoy.dto.PostModifyRequestDto;
import backend.ohgnoy.dto.PostRequestDto;
import backend.ohgnoy.entity.Post;
import backend.ohgnoy.entity.User;
import backend.ohgnoy.exception.PostNotFoundException;
import backend.ohgnoy.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Service
public class PostService {
    private final PostRepository postRepository;
    private final FileService fileService;

    public PostService(PostRepository postRepository, FileService fileService) {
        this.postRepository = postRepository;
        this.fileService = fileService;
    }

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    public Post createPost(PostRequestDto postRequestDto, User author, MultipartFile coverImage){
        // 1. DTO 데이터를 Entity로 옮겨 담기
        Post post = new Post();
        post.setTitle(postRequestDto.getTitle());
        post.setExcerpt(postRequestDto.getExcerpt());
        post.setDisplayYn(postRequestDto.getDisplayYn());

        // 2. SecurityContext에서 가져온 로그인 사용자 정보를 작성자로 자동 매핑
        post.setAuthor(author);

        // 3. 파일이 전달되었다면 업로드 수행 후 URL 저장
        if(coverImage != null && !coverImage.isEmpty()) {
            String imageUrl = fileService.uploadFile(coverImage);
            post.setCoverImage(imageUrl);
        }

        // 4. DB에 저장
        return postRepository.save(post);
    }

    public Post modifyPost(Integer postId, PostModifyRequestDto postModifyRequestDto, MultipartFile coverImage){
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);

        post.setTitle(postModifyRequestDto.getTitle());
        post.setExcerpt(postModifyRequestDto.getExcerpt());

        if(coverImage != null && !coverImage.isEmpty()) {
            String imageUrl =  fileService.uploadFile(coverImage);
            post.setCoverImage(imageUrl);
        }

        return post;
    }
}

