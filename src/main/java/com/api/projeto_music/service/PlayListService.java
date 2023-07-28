package com.api.projeto_music.service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.projeto_music.dto.PlayListDTO;
import com.api.projeto_music.model.Musica;
import com.api.projeto_music.model.PlayList;
import com.api.projeto_music.model.Usuario;
import com.api.projeto_music.repository.MusicaRepository;
import com.api.projeto_music.repository.PlayListRepository;
import com.api.projeto_music.repository.UsuarioRepository;

@Service
public class PlayListService {
	@Autowired
	private PlayListRepository playListRepository;
		
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private MusicaRepository musicaRepository;
	
	@Transactional(readOnly = true)
	public List<PlayListDTO> findAll(){
		
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<Usuario> opUsuario = usuarioRepository.findByEmail(email);
		List<PlayList> albuns = playListRepository.findByUsuario(opUsuario.get());
		List<PlayListDTO> dtos = new ArrayList<>();		
		for(PlayList playList:albuns) {
			dtos.add(new PlayListDTO(playList));
		}
		return dtos;
	}
	
	public PlayListDTO findById(Long id) {
		Optional<PlayList> optPlayList = playListRepository.findById(id);
		if (optPlayList.isPresent()) {
			return new PlayListDTO(optPlayList.get());
		}
		return null;
	}
	
	@Transactional
	public PlayListDTO inserir(PlayListDTO playListDto) throws IOException {
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<Usuario> opUsuario = usuarioRepository.findByEmail(email);
		PlayList playList = new PlayList();
		playList.setNome(playListDto.getNome());
		playList.setMusicas(new ArrayList<>());
		playList.setUsuario(opUsuario.get());
		for(Musica musica: playListDto.getMusicas()) {
			Optional<Musica> opMusica = musicaRepository.findById(musica.getId());
			if (opMusica.isPresent()) {
				playList.getMusicas().add(opMusica.get());
			}
		}
		playList = playListRepository.saveAndFlush(playList);
		return new PlayListDTO(playList);
	}
	
	@Transactional
	public PlayListDTO atualizar(Long id, PlayListDTO playListDto) throws IOException {
		Optional<PlayList> opPlayList = playListRepository.findById(id);
		if (opPlayList.isEmpty()) {
			return null;
		}
		PlayList playListBanco = opPlayList.get();
		playListBanco.setNome(playListDto.getNome());
		playListBanco.setMusicas(new ArrayList<>());
		for(Musica musica: playListDto.getMusicas()) {
			Optional<Musica> opMusica = musicaRepository.findById(musica.getId());
			if (opMusica.isPresent()) {
				playListBanco.getMusicas().add(opMusica.get());
			}
		}
		return new PlayListDTO(playListRepository.saveAndFlush(playListBanco));
	}
	
	public void apagar(Long id) {
		playListRepository.deleteById(id);
	}
	


}