package com.example.News.Service;

import com.example.News.Model.PaginationResponse;
import com.example.News.Model.Writer;
import com.example.News.Repository.WriterRepository;
import com.example.News.Utils.EntityURLBuilder;
import com.example.News.Utils.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Objects;

@Service
public class WriterService {

    private static final String WRITER_PATH = "writer";

    @Autowired
    private WriterRepository writerRepository;

    public Writer getWriterById(Integer id){
        return writerRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public PaginationResponse<Writer> getAll(Integer page, Integer size) {
        if (!Objects.isNull(page) && !Objects.isNull(size)) {
            Pageable pageable = PageRequest.of(page, size);
            Page<Writer> writerPage = writerRepository.findAll(pageable);
            return new PaginationResponse<>(writerPage.getContent(), writerPage.getTotalPages(), writerPage.getTotalElements());
        }
        List<Writer> writerList = writerRepository.findAll();
        return new PaginationResponse<>(writerList, 1, (long) writerList.size());
    }

    public List<Writer> getAll() {
        return writerRepository.findAll();
    }

    public PostResponse addWriter(Writer writer) {
        final Writer writerAdded = writerRepository.save(writer);
        return PostResponse.builder().status(HttpStatus.CREATED).url(EntityURLBuilder.buildURL(WRITER_PATH, writerAdded.getId().toString())).build();
    }

    public PostResponse deleteWriter(Integer id) {
        writerRepository.deleteById(id);
        return PostResponse.builder().status(HttpStatus.ACCEPTED).url(EntityURLBuilder.buildURL(WRITER_PATH, id.toString())).build();
    }

    public PostResponse updateWriter(Writer writer) {
        Writer writer1 = writerRepository.findById(writer.getId()).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        if (!Objects.isNull(writer1)){
            writer1.setId(writer.getId());
            writer1.setName(writer.getName());
            writer1.setLastname(writer.getLastname());
            writer1.setDni(writer.getDni());
            writer1.setEmail(writer.getEmail());
            return PostResponse.builder().status(HttpStatus.ACCEPTED).url(EntityURLBuilder.buildURL(WRITER_PATH, writer1.getId().toString())).build();
        } else {
            return PostResponse.builder().status(HttpStatus.NOT_MODIFIED).url(EntityURLBuilder.buildURL(WRITER_PATH, writer.getId().toString())).build() ;
        }
    }

    public PostResponse updateWriterById(Integer id, Writer writer) {
        Writer writer1 = writerRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        if (!Objects.isNull(writer1)){
            writer1.setId(writer.getId());
            writer1.setName(writer.getName());
            writer1.setLastname(writer.getLastname());
            writer1.setDni(writer.getDni());
            writer1.setEmail(writer.getEmail());
            return PostResponse.builder().status(HttpStatus.ACCEPTED).url(EntityURLBuilder.buildURL(WRITER_PATH, writer1.getId().toString())).build();
        } else {
            return PostResponse.builder().status(HttpStatus.NOT_MODIFIED).url(EntityURLBuilder.buildURL(WRITER_PATH, writer.getId().toString())).build() ;
        }
    }
}
