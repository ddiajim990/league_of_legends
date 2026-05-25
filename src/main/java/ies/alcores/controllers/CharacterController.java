package ies.alcores.controllers;

import ies.alcores.persistence.model.Character;
import ies.alcores.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @GetMapping
    public ResponseEntity<List<Character>> getAll() {
        return ResponseEntity.ok(this.characterService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Character> getOne(@PathVariable final String id) {
        return this.characterService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Character> save(@RequestBody final Character character) {
        Character saved = this.characterService.save(character);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable final String id) {
        return characterService.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

}