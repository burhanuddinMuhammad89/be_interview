package com.example.demo.service;

import com.example.demo.dto.EroWebReq;
import java.util.List;

public interface EroService {
  List<EroWebReq> getListData();
  void createData(EroWebReq eroWebReq);
  void batchCreateData();
}
