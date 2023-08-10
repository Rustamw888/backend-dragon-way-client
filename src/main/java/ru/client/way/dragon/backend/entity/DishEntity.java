package ru.client.way.dragon.backend.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Objects;

@Entity
@Table(name = "dish", schema = "projectrestraunt", catalog = "dragondb")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DishEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "weight", nullable = false)
    private Long weight;

    @Column(name = "calories", nullable = false)
    private Long calories;

    // блюдо может иметь только одну категорию
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id") // по каким полям связывать (foreign key)
    private CategoryEntity categoryId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DishEntity that = (DishEntity) o;
        return price == that.price && weight == that.weight && calories == that.calories && id == that.id && categoryId == that.categoryId && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, price, weight, calories, id, categoryId);
    }

    @Override
    public String toString() {
        return "DishEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", calories=" + calories +
                ", categoryId=" + categoryId +
                '}';
    }
}
