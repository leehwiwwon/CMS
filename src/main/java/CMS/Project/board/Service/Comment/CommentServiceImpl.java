package CMS.Project.board.Service.Comment;

import CMS.Project.board.Board;
import CMS.Project.board.Comment;
import CMS.Project.board.Member;
import CMS.Project.board.dto.comment.CommentRequestDTO;
import CMS.Project.board.dto.comment.CommentResponseDTO;
import CMS.Project.board.repository.BoardRepository;
import CMS.Project.board.repository.CommentRepository;
import CMS.Project.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @Override
    public Long writeComment(CommentRequestDTO commentRequestDTO, Long boardId, String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("이메일이 존재하지 않습니다."));
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("게시물을 찾을 수 없습니다."));
        Comment result = Comment.builder()
                .content(commentRequestDTO.getContent())
                .board(board)
                .member(member)
                .build();
        commentRepository.save(result);

        return result.getId();
    }

    @Override
    public List<CommentResponseDTO> commentList(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("게시물을 찾을 수 없습니다."));
        List<Comment> comments = commentRepository.findByBoard(board);

        return comments.stream()
                .map(comment -> CommentResponseDTO.builder()
                        .comment(comment)
                        .build())
                .collect(Collectors.toList());

    }

    @Override
    public void updateComment(CommentRequestDTO commentRequestDTO, Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));
        comment.update(commentRequestDTO.getContent());
        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}