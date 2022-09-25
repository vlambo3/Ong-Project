package com.alkemy.ong.mapper;

import java.util.List;
import static java.util.stream.Collectors.toList;

import com.alkemy.ong.dto.PageDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GenericMapper {

    private final ModelMapper mapper;

    @Value("${alkemy.pageQueryLink}")
    private String pageQueryLink;

    public <S, D> D map(S source, Class<D> destinationClass) {
        return mapper.map(source, destinationClass);
    }

    public <S, D> List<D> mapAll(List<S> sourceList, Class<D> destinationClass) {
        return sourceList.stream()
                .map(e -> map(e, destinationClass))
                .collect(toList());
    }

    public <P, D> PageDto<D> mapPage(Page<P> page, Class<D> destinationClass, String classPath) {
        PageDto<D> dto = new PageDto<>();
        if (!page.isFirst())
            dto.setPreviousPage(String.format(pageQueryLink, classPath) + (page.getNumber() - 1));
        if (!page.isLast())
            dto.setNextPage(String.format(pageQueryLink, classPath) + (page.getNumber() + 1));
        dto.setContent(mapAll(page.getContent(), destinationClass));
        return dto;
    }
}