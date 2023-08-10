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
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "basket", schema = "projectrestraunt", catalog = "dragondb")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BasketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity", updatable = false)
    private Long quantity;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id", referencedColumnName = "id")
    private List<DishEntity> dishId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private List<OrderEntity> orderId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserDataEntity userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasketEntity that = (BasketEntity) o;
        return userId == that.userId && dishId == that.dishId && quantity == that.quantity && id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, dishId, quantity, id);
    }

    @Override
    public String toString() {
        return "BasketEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", dishId=" + dishId +
                ", quantity=" + quantity +
                '}';
    }
}
