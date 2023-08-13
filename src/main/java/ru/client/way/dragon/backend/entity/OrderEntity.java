package ru.client.way.dragon.backend.entity;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Objects;

@Entity
@Table(name = "order", schema = "projectrestraunt", catalog = "dragondb")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = org.hibernate.type.NumericBooleanConverter.class) // для автоматической конвертации числа в true/false
    private Boolean completed; // 1 = true, 0 = false

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "basket_id", referencedColumnName = "id")
    private BasketEntity basketId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserDataEntity userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return basketId == that.basketId && userId == that.userId && id == that.id && Objects.equals(completed, that.completed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(basketId, userId, completed, id);
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", completed=" + completed +
                ", basketId=" + basketId +
                ", userId=" + userId +
                '}';
    }
}
