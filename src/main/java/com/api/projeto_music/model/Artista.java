package com.api.projeto_music.model;

import com.api.projeto_music.enums.TipoArtistaEnum;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "artista")
public class Artista {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_artista")
    @SequenceGenerator(name = "sequence_artista", sequenceName = "seq_artista", allocationSize = 1)
    @Column(name="art_cd_id")
    private Long id;

    @Size(min=5, max=100)
    @Column(name="art_tx_nome", length = 100, nullable = false)
    private String nome;

    @NotNull(message = "Tipo de Artista deve ser selecionado")
    @Column(name="art_tx_tipo", length = 1, nullable = false)
    private TipoArtistaEnum tipoArtista;
    
    
    public Artista(Long id, String nome, TipoArtistaEnum tipoArtista) {
        super();
        this.id = id;
        this.nome = nome;
        this.tipoArtista = tipoArtista;
    }
    
    public Artista() {
        super();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public TipoArtistaEnum getTipoArtista() {
        return tipoArtista;
    }
    public void setTipoArtista(TipoArtistaEnum tipoArtista) {
        this.tipoArtista = tipoArtista;
    }

    
    
}
