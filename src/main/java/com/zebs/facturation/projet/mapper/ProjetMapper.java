package com.zebs.facturation.projet.mapper;

import com.zebs.facturation.commun.util.IModelMapper;
import com.zebs.facturation.projet.model.dto.response.ProjetDto;
import com.zebs.facturation.projet.model.entity.Projet;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class ProjetMapper implements IModelMapper<Projet, ProjetDto> {

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Projet convertToEntity(ProjetDto dto) {
        return modelMapper.map(dto, Projet.class);
    }

    @Override
    public ProjetDto convertToDto(Projet entity) {
        return modelMapper.map(entity, ProjetDto.class);
    }
}
