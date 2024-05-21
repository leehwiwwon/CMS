package CMS.Project.board.repository;

import CMS.Project.board.Image;
import CMS.Project.board.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {

    Image findByMember(Member member);
}