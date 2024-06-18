package CMS.Project.board.product;

import CMS.Project.board.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Product")
public class Product extends BaseEntity {

    @Id
    private String code;

    @Column
    private String name;

    @Column
    private String endDate;

    @Column
    private String counts;
}
