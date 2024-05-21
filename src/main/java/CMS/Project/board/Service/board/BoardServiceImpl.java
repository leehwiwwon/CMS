package CMS.Project.board.Service.board;

import CMS.Project.board.Board;
import CMS.Project.board.BoardImage;
import CMS.Project.board.Member;
import CMS.Project.board.dto.board.BoardResponseDTO;
import CMS.Project.board.dto.board.BoardWriteRequestDTO;
import CMS.Project.board.dto.image.BoardImageUploadDTO;
import CMS.Project.board.repository.BoardImageRepository;
import CMS.Project.board.repository.BoardRepository;
import CMS.Project.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final BoardImageRepository boardImageRepository;

    @Value("${file.boardImagePath}")
    private String uploadFolder;

    @Override
    @Transactional
    public Long saveBoard(BoardWriteRequestDTO boardWriteRequestDTO,
                          BoardImageUploadDTO boardImageUploadDTO,
                          String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("이메일이 존재하지 않습니다."));

        Board result = Board.builder()
                .title(boardWriteRequestDTO.getTitle())
                .content(boardWriteRequestDTO.getContent())
                .member(member)
                .build();

        boardRepository.save(result);

        if (boardImageUploadDTO.getFiles() != null && !boardImageUploadDTO.getFiles().isEmpty()) {
            for (MultipartFile file : boardImageUploadDTO.getFiles()) {
                UUID uuid = UUID.randomUUID();
                String imageFileName = uuid + "_" + file.getOriginalFilename();

                File destinationFile = new File(uploadFolder + imageFileName);

                try {
                    file.transferTo(destinationFile);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                BoardImage image = BoardImage.builder()
                        .url("/boardImages/" + imageFileName)
                        .board(result)
                        .build();

                boardImageRepository.save(image);
            }
        }

        return result.getId();
    }

    @Override
    public BoardResponseDTO boardDetail(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
        BoardResponseDTO result = BoardResponseDTO.builder()
                .board(board)
                .build();

        return result;
    }

    @Override
    public Page<BoardResponseDTO> boardList(Pageable pageable) {
        Page<Board> boards = boardRepository.findAll(pageable);
        return getBoardResponseDTOS(pageable, boards);
    }

    @Override
    public Page<BoardResponseDTO> searchingBoardList(String keyword, Pageable pageable) {
        Page<Board> boards = boardRepository.findByTitle(keyword, pageable);
        return getBoardResponseDTOS(pageable, boards);
    }

    private Page<BoardResponseDTO> getBoardResponseDTOS(Pageable pageable, Page<Board> boards) {
        List<BoardResponseDTO> boardDTOs = new ArrayList<>();

        for (Board board : boards) {
            BoardResponseDTO result = BoardResponseDTO.builder()
                    .board(board)
                    .build();
            boardDTOs.add(result);
        }

        return new PageImpl<>(boardDTOs, pageable, boards.getTotalElements());
    }

    @Override
    public Long boardUpdate(Long id, BoardWriteRequestDTO boardWriteRequestDTO) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
        board.update(boardWriteRequestDTO.getTitle(), boardWriteRequestDTO.getContent());
        boardRepository.save(board);

        return board.getId();
    }

    @Override
    public void boardRemove(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
        boardRepository.delete(board);
    }
}