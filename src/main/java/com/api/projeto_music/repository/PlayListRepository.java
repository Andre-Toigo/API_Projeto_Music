package com.api.projeto_music.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.projeto_music.model.PlayList;
import com.api.projeto_music.model.Usuario;

@Repository
public interface PlayListRepository extends JpaRepository<PlayList, Long>{

    public List<PlayList> findByUsuario(Usuario usuario);
}