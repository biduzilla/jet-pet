package com.ricky.jetpet.data.network.mappers

interface AnimalApiMapper<Domain, Entity> {
    fun mapToDomain(animal: Entity):Domain
}