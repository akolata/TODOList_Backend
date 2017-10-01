package com.company.repository;

import com.company.domain.TODONote;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Aleksander on 01.10.2017.
 */
public interface TODONotesRepository
extends MongoRepository<TODONote, String>{

    List<TODONote> findAllByTitleContainingIgnoreCase(String title);
}
