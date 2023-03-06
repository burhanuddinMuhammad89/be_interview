package com.example.demo.service.impl;

import com.example.demo.dto.EroWebReq;
import com.example.demo.model.EroWeb;
import com.example.demo.repository.EroWebRepository;
import com.example.demo.service.EroService;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EroServiceImpl implements EroService {

  @Autowired
  EroWebRepository eroWebRepository;  

  @Override
  public List<EroWebReq> getListData() {
    // TODO Auto-generated method stub
    List<EroWeb> eroWebs = (List<EroWeb>) eroWebRepository.findAll();
    List<EroWebReq> eroWebReqs = new ArrayList<>();
    for(EroWeb eroWeb : eroWebs){
        EroWebReq eroWebReq = new EroWebReq();
        eroWebReq.setName_file(eroWeb.getNameFile());
        eroWebReq.setOriginal_title(eroWeb.getOriginalTitle());
        eroWebReq.setVote_average(eroWeb.getVoteAverage());
        eroWebReq.setCreatedAt(eroWeb.getCreatedAt());
        eroWebReqs.add(eroWebReq);
    }

    return eroWebReqs;
  }

  @Override
  public void createData(EroWebReq eroWebReq) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException(
      "Unimplemented method 'createData'"
    );
  }

  @Override
  public void batchCreateData() {
    // TODO Auto-generated method stub
    try {
      File folder = new File(
        "/Users/admin/Documents/react/react1/my-second-web-react/public/images"
      );
      File[] listOfFiles = folder.listFiles();

      for (int i = 0; i < listOfFiles.length; i++) {
        File files = listOfFiles[i];
        if (files.isFile() && !files.getName().equals(".DS_Store")) {
            EroWeb eroWeb = new EroWeb();
            eroWeb.setNameFile(files.getName());
            eroWeb.setOriginalTitle(files.getName());
            eroWeb.setVoteAverage(9.9);
            eroWebRepository.save(eroWeb);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
