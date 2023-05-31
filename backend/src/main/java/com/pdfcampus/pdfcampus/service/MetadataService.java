package com.pdfcampus.pdfcampus.service;

import com.pdfcampus.pdfcampus.dto.PageDto;
import com.pdfcampus.pdfcampus.dto.TextDto;
import com.pdfcampus.pdfcampus.entity.Page;
import com.pdfcampus.pdfcampus.entity.Text;
import com.pdfcampus.pdfcampus.repository.PageRepository;
import com.pdfcampus.pdfcampus.repository.TextRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MetadataService {
    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private TextRepository textRepository;

    private ModelMapper modelMapper;

    public List<PageDto> getMetadataByBookId(Integer bookId) {
        List<Page> pages = pageRepository.findByBid(bookId);

        List<PageDto> pageDtoList = new ArrayList<>();

        for (Page page : pages) {
            List<Text> texts = textRepository.findByPage(page);

            List<TextDto> textDtoList = texts.stream()
                    .map(text -> modelMapper.map(text, TextDto.class))
                    .collect(Collectors.toList());

            PageDto pageDto = modelMapper.map(page, PageDto.class);
            pageDto.setTexts(textDtoList);
            pageDtoList.add(pageDto);
        }

        return pageDtoList;
    }
}

