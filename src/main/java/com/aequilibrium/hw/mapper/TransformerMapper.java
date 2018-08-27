package com.aequilibrium.hw.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.aequilibrium.hw.data.entity.Transformer;
import com.aequilibrium.hw.model.TransformerTO;

@Mapper(componentModel = "spring")
public interface TransformerMapper {

	TransformerTO fromEntityToModel(Transformer entity);
	Transformer fromModelToEntity(TransformerTO model);
	
	List<TransformerTO> fromEntityToModel(List<Transformer> entity);
	List<Transformer> fromModelToEntity(List<TransformerTO> model);
}
