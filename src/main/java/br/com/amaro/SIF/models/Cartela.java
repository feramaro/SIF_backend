package br.com.amaro.SIF.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter @Setter @NoArgsConstructor
public class Cartela {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String serie;
    @ManyToOne
    @JoinColumn(name = "modelo_id", referencedColumnName = "id")
    private ModeloCartela modeloCartela;
    @Column(nullable = false)
    private Integer selos = 0;
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario owner;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date dataExpiracao;
    @Column(nullable = false)
    private boolean completa = false;

}
