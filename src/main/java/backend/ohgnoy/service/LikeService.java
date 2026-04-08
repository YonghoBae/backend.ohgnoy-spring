package backend.ohgnoy.service;

import backend.ohgnoy.entity.Like;
import backend.ohgnoy.entity.Post;
import backend.ohgnoy.entity.User;
import backend.ohgnoy.exception.PostNotFoundException;
import backend.ohgnoy.repository.LikeRepository;
import backend.ohgnoy.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;

    @Transactional
    public String toggleLike(Integer postId, User user) {
        //1. 게시글 존재 여부 확인
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        //2. 사용자가 해당 게시글에 이미 좋아요를 눌렀는지 확인
        Optional<Like> existingLike = likeRepository.findByPostAndUser(post, user);

        //3-1. 이미 좋아요가 존재하면 삭제 (좋아요 취소)
        if(existingLike.isPresent()) {
            likeRepository.delete(existingLike.get());
            return "좋아요가 취소되었습니다.";
        }
        //3-2. 좋아요가 없으면 새로 생성(좋아요 추가)
        else {
            Like newLike = Like.builder()
                    .post(post)
                    .user(user)
                    .build();
            likeRepository.save(newLike);
            return "좋아요가 추가되었습니다.";
        }
    }
}
