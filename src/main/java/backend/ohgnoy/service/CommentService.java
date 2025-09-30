package backend.ohgnoy.service;

import backend.ohgnoy.entity.Comment;
import backend.ohgnoy.repository.CommentRepository;
import backend.ohgnoy.repository.PostRepository;
import backend.ohgnoy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//@Service : 이 클래스가 비즈니스 로직을 담당하는 서비스 계층의 컴포넌트임을 Spring에게 알려줌
@Service
//@RequiredArgsConstructor: final 키워드가 붙은 필드를 인자로 받는 생성장를 자동으로 만들어줌
@RequiredArgsConstructor
public class CommentService {
    //final 키워드를 사용하여 CommentRepository 의존성을 선언
    //@RequiredArgsConstructor가 이 필드를 위한 생성자 주입 코드를 만듬
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;//사용자 정보를 가져오기 위해 추가

    /*
    * 특정 게시글의 모든 댓글을 조회하는 메소드
    * @param postId 댓글을 조회할 게시글의 ID
    * @return 해당 게시글의 댓글 리스트
     */
    @Transactional(readOnly = true)//데이터가 변경하지 않는 조회 기능이므로 readOnly=true로 설정하여 최적화
    public Comment getComment(Integer postId){
        //commentRepository에서 postId를 기준으로 모든 댓글을 찾아 반환
        //Spirng Data JPA는 메소드 이름을 분석하여 자동으로 쿼리 생성
        //CommentRepository에 findByPost_PostId 메소드를 추가
        return commentRepository.findById(postId).orElse(null);
    }
}
