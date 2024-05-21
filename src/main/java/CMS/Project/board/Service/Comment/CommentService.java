package CMS.Project.board.Service.Comment;

import CMS.Project.board.dto.comment.CommentRequestDTO;
import CMS.Project.board.dto.comment.CommentResponseDTO;

import java.util.List;

public interface CommentService {

    Long writeComment(CommentRequestDTO commentRequestDTO, Long boardId, String email);

    List<CommentResponseDTO> commentList(Long id);

    void updateComment(CommentRequestDTO commentRequestDTO, Long commentId);

    void deleteComment(Long commentId);
}