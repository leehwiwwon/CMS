package CMS.Project.board.repository;

import CMS.Project.board.Board;
import CMS.Project.board.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByBoard(Board board);
}