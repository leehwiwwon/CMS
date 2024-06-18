package CMS.Project.board.product;

import CMS.Project.board.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Product")
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String code;

    @Column
    private String name;

    @Column
    private String endDate;

    @Column
    private String CurrentCounts;

    @Column
    private String counts;
}
