package com.ricky.jetpet.data.network.mappers

import com.ricky.jetpet.data.network.models.Address
import com.ricky.jetpet.data.network.models.Animal
import com.ricky.jetpet.data.network.models.Contact
import com.ricky.jetpet.data.network.models.Photo
import com.ricky.jetpet.domain.model.Pet
import com.ricky.jetpet.domain.model.PetOwnerContacts
import com.ricky.jetpet.domain.model.PetPhoto

class AnimalApiMapperImpl : AnimalApiMapper<Pet, Animal> {
    companion object {
        private const val EMPTY_DATA = "unknown"
    }

    override fun mapToDomain(animal: Animal): Pet {
        return animal.run {
            Pet(
                id = formatData(id.toString()),
                age = formatData(age),
                breeds = formatData(breeds.primary),
                colors = formatData(colors.primary),
                contact = formatContacts(contact),
                description = formatData(description),
                distance = formatData(distance.toString()),
                gender = formatData(gender),
                name = formatData(name),
                photos = formatPhotos(photos),
                size = formatData(size),
                species = formatData(species),
                status = formatData(status),
                tags = tags,
                type = formatData(type),
                currentPage = 0
            )
        }
    }

    private fun formatContacts(contact: Contact?): PetOwnerContacts {
        return PetOwnerContacts(
            address = formatData(formatAddress(contact?.address)),
            email = formatData(contact?.email),
            phone = formatData(contact?.phone)
        )
    }

    private fun formatAddress(address: Address?): String {
        val dot = "u25CF"
        if (address != null) {
            return "${address.city}$dot${address.country}"
        }

        return ""
    }

    private fun formatData(data: String?): String {
        return data ?: EMPTY_DATA
    }

    private fun formatPhotos(photoList: List<Photo>?): List<PetPhoto> {
        return photoList?.map { photo ->
            PetPhoto(
                full = formatData(photo.full),
                large = formatData(photo.large),
                medium = formatData(photo.medium),
                small = formatData(photo.small)
            )
        } ?: listOf()
    }


}

