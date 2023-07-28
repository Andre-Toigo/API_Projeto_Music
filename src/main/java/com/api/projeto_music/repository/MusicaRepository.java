package com.api.projeto_music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.projeto_music.model.Musica;

@Repository
public interface MusicaRepository extends JpaRepository<Musica, Long> {
    
}
