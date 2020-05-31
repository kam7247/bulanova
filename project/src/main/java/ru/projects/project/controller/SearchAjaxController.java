package ru.egprojects.sw1_springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.egprojects.sw1_springboot.model.Pages;
import ru.egprojects.sw1_springboot.service.PostService;
import ru.egprojects.sw1_springboot.dto.PostDto;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(Pages.SEARCH + "/ajax")
public class SearchAjaxController {

    @Autowired
    private PostService postService;

    @GetMapping
    public List<PostDto> search(@RequestParam String query) {
        System.out.println("Searching for " + query);
        List<PostDto> result = postService.search(query)
                .stream()
                .map(PostDto::from)
                .collect(Collectors.toList());
        System.out.println(result);
        return result;
    }
}
