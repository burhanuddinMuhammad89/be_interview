package com.example.demo.service;
import java.util.List;
import com.example.demo.dto.TransaksiReq;

public interface TransaksiService {
    String createTransaction(TransaksiReq transaksiReq);
    List<TransaksiReq> getAllTransaction();
    List<TransaksiReq> getTransactionByDate(String dateBefore, String dateAfter, String nik);
    List<TransaksiReq> getTransactionByAnggotaNik(String nik);
}
