package com.api.projeto_music.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.projeto_music.dto.AlbumSimplesDTO;
import com.api.projeto_music.model.Album;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    
@Query("select "
        + " ab.id as id, "
        + " ab.nome as nome, "
        + " at.id as idArtista, "
        + " at.nome as nomeArtista, "
        + " sum(m.minutos) as minutos "
        + " from Album ab join ab.artista at join ab.musicas m "
        + " group by ab.id, ab.nome, at.id, at.nome ")
public List<AlbumSimplesDTO> buscaSimples();

}
