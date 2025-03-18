package ru.academy.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "limits", schema = "limitdb")
public class Limit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "value")
    private Double value;

    public Limit(Long userId, Double value) {
        this.userId = userId;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Limit{" +
                "id=" + id +
                ", userId=" + userId +
                ", value=" + value +
                '}';
    }
}
