package com.zebs.facturation.commun.util;


/**
 * Les classe qui implementent cette interface peuvent
 * convertir la conversion entité - DTO et DTO - entité
 *
 * @param <X>  Le type de l'entite
 * @param <Y>  Le type de la DTO
 */
public interface IModelMapper<X,Y> {

    public X convertToEntity(Y dto);

    public Y convertToDto(X entity );
}
