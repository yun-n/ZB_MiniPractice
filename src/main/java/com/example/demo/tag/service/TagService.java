package com.example.demo.tag.service;

import com.example.demo.tag.dto.TagRequestDto;
import com.example.demo.tag.dto.TagResponseDto;
import com.example.demo.tag.dto.TagResultResponseDto;
import com.example.demo.tag.entity.Tag;
import com.example.demo.tag.repository.TagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public TagResponseDto saveTag(TagRequestDto tagRequestDto) {
        Tag tag = new Tag(tagRequestDto.getName());
        Tag savedTag = tagRepository.save(tag);
        return new TagResponseDto(savedTag);
    }

    @Transactional
    public TagResultResponseDto deleteTag(Long id) {
        Tag tag = tagRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("태그" + id + " 찾을 수 없습니다."));
        tagRepository.delete(tag);
        tag.getBooks().forEach(book -> book.getTags().remove(tag));

        tagRepository.deleteById(id);
        return new TagResultResponseDto(true);
    }
}
