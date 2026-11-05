package zzyzzy.spring.massai_mara_park.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// 교육용 간략화: 엔티티를 그대로 JSON으로 내보냅니다. 실서비스에서는 DTO를 따로 두세요.
@Entity
@Table(name = "images")
public class Animal {

    @Id
    @Column(name = "imageid")
    private Long id;

    private String description;

    private String url;

    private int viewCount;

    protected Animal() {
        // JPA는 기본 생성자를 요구합니다.
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public int getViewCount() {
        return viewCount;
    }
}
