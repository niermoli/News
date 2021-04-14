package com.example.News.Converter;

import com.example.News.Model.DTO.WriterDTO;
import com.example.News.Model.Writer;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Component
@Setter
public class WriterDTOListConverter implements Converter<List<Writer>, List<WriterDTO>> {

    private final ModelMapper modelMapper;

    public WriterDTOListConverter(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public List<WriterDTO> convert(final List<Writer> source) {
        return modelMapper.map(source, new TypeToken<List<WriterDTO>>() {
        }.getType());
    }
}
