package ies.alcores.services;

import ies.alcores.persistence.model.Character;
import ies.alcores.persistence.repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    //Obtiene todos los personajes
    public List<Character> findAll() {
        return this.characterRepository.findAll();
    }

    //Obtiene un personaje dado el id
    public Optional<Character> findById(final String id) {
        return this.characterRepository.findById(id);
    }

    //Dado un character lo persiste en base de datos
    public Optional<Character> save(final Character character) {
        return Optional.of(this.characterRepository.save(character));
    }

    //Dado un id de character, lo elimina
    public Optional<Character> delete(final String id) {
        return this.characterRepository.findById(id)
                .map(character -> {
                    this.characterRepository.delete(character);
                    return character;
                });
    }

    //Dado un JSON de un Character (el cual incluye ID) lo actualiza
    public Optional<Character> update(final String id, final Character character) {
        return this.characterRepository.findById(character.getId())
                .map(c -> {
                    character.setId(id);
                    return this.characterRepository.save(character);
                });
    }

    public List<Character> findMultiRole() {
        return this.characterRepository.findAll().stream()
                .filter(character -> character.getRoles() != null && character.getRoles().size() > 1)
                .toList();
    }

    public List<Character> findTanks() {
        return this.characterRepository.findTanks();
    }

}