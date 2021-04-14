package com.example.News.Converter;

import com.example.News.Model.DTO.WriterDTO;
import com.example.News.Model.Writer;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class WriterDTOConverter implements Converter<Writer, WriterDTO> {

    private final ModelMapper modelMapper;

    public WriterDTOConverter(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public WriterDTO convert(Writer source) {
        return modelMapper.map(source, WriterDTO.class);
    }
}
