package com.ricky.jetpet.data.network.mappers

interface PetApiMapper<Domain, Entity> {
    fun mapToDomain(apiEntity: Entity):Domain
}