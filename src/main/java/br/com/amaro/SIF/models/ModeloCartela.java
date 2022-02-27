package br.com.amaro.SIF.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter @NoArgsConstructor
public class ModeloCartela {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false)
    private Integer quantidadeSelos;
    @Column(nullable = false)
    private String brinde;
    @ManyToOne
    @JoinColumn(name = "criador_id", referencedColumnName = "id")
    private Usuario criador;
}
