package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.example.demo.dto.EroWebReq;
import com.example.demo.service.EroService;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/ero")
public class EroWebController {

    @Autowired
    private EroService eroService;

    @PostMapping
    public ResponseEntity<String> createAnggota() {
        eroService.batchCreateData();
        return ResponseEntity.ok().body("test");
    }

    @GetMapping("/data-list")
    public ResponseEntity<List<EroWebReq>> getDataEro() {
        List<EroWebReq> eroWebReqs = eroService.getListData();
        return eroWebReqs.contains("error") ? ResponseEntity.badRequest().body(eroWebReqs)
                : ResponseEntity.ok().body(eroWebReqs);
    }
}
