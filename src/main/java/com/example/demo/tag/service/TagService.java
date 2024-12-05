package com.example.demo.tag.service;

import com.example.demo.book.entity.Book;
import com.example.demo.tag.dto.TagResponseDto;
import com.example.demo.tag.entity.Tag;
import com.example.demo.tag.repository.TagRepository;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    public TagResponseDto deleteTag(Long id) {
        Tag tag = tagRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("태그" + id + " 찾을 수 없습니다."));

        tag.getBooks().forEach(book -> book.getTags().remove(tag));

        tagRepository.deleteById(id);
        return new TagResponseDto(true);
    }
}
