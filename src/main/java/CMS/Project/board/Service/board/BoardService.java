package CMS.Project.board.Service.board;

import CMS.Project.board.dto.board.BoardResponseDTO;
import CMS.Project.board.dto.board.BoardWriteRequestDTO;
import CMS.Project.board.dto.image.BoardImageUploadDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BoardService {

    Long saveBoard(BoardWriteRequestDTO boardWriteRequestDTO,
                   BoardImageUploadDTO boardImageUploadDTO,
                   String email);

    BoardResponseDTO boardDetail(Long id);

    Page<BoardResponseDTO> boardList(Pageable pageable);

    Page<BoardResponseDTO> searchingBoardList(String keyword, Pageable pageable);

    Long boardUpdate(Long id, BoardWriteRequestDTO boardWriteRequestDTO);

    void boardRemove(Long id);
}