package com.example.demo.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.TransaksiReq;
import com.example.demo.model.Anggota;
import com.example.demo.model.Transaksi;
import com.example.demo.repository.AnggotaRepository;
import com.example.demo.repository.TransaksiRepository;
import com.example.demo.service.TransaksiService;

@Service
public class TransaksiServiceImpl implements TransaksiService {

    @Autowired
    AnggotaRepository anggotaRepository;

    @Autowired
    TransaksiRepository transaksiRepository;

    @Override
    public String createTransaction(TransaksiReq transaksiReq) {
        try {
            if (!isNumeric(transaksiReq.getAmount())) {
                return "error-amount wajib diisi angka";
            }

            if (StringUtils.isEmpty(transaksiReq.getAmount())) {
                return "error-amount wajib diisi";
            }

            if (transaksiReq.getAmount().contains("-")) {
                return "error-amount tidak boleh minus";
            }

            Anggota anggota = anggotaRepository.findByNik(transaksiReq.getAnggotaReq().getNik());
            if (anggota == null) {
                return "error-nik belum bergabung mohon melakukan pendaftaran terlebih dahulu";
            }

            String transactionNo = transaksiReq.getAnggotaReq().getNik().substring(
                    transaksiReq.getAnggotaReq().getNik().length() - 3,
                    transaksiReq.getAnggotaReq().getNik().length())
                    + "-" + this.getDateHourNow().get("date") + this.getDateHourNow().get("month")
                    + this.getDateHourNow().get("year")
                    + this.getDateHourNow().get("hour") + this.getDateHourNow().get("minute")
                    + this.getDateHourNow().get("second");
            transaksiReq.setNoTrans(transactionNo);
            Transaksi transaksi = new Transaksi(transaksiReq);
            transaksi.setNikAnggota(anggota);
            transaksi.setCreatedAt(new Date());
            transaksiRepository.save(transaksi);
        } catch (Exception e) {
            return "error-" + e.getMessage();
        }
        return "success menambah data";
    }

    @Override
    public List<TransaksiReq> getAllTransaction() {
        List<TransaksiReq> transaksiReqs = new ArrayList<>();
        return transaksiReqs;
    }

    @Override
    public List<TransaksiReq> getTransactionByDate(String dateBefore, String dateAfter, String nik) {
        Date dateStart = this.convertStringToDate(dateBefore, 0, 0, 0);
        Date dateEnd = this.convertStringToDate(dateAfter, 23, 59, 59);
        List<Transaksi> transaksiList = transaksiRepository.findBycreatedAtBetweenAndNik_anggota(dateStart, dateEnd, nik);
        List<TransaksiReq> transaksisRespist = new ArrayList<>();
        transaksiList.forEach(data -> {
            TransaksiReq transaksiReq = new TransaksiReq(data);
            transaksiReq.setCreatedAt(data.getCreatedAt());
            transaksisRespist.add(transaksiReq);
        });

        return transaksisRespist;
    }

    @Override
    public List<TransaksiReq> getTransactionByAnggotaNik(String nik) {
        Anggota anggota = anggotaRepository.findByNik(nik);
        List<Transaksi> transaksis = anggota.getTList();
        List<TransaksiReq> transaksisRespist = new ArrayList<>();
        transaksis.forEach(data -> {
            TransaksiReq transaksiReq = new TransaksiReq(data);
            transaksisRespist.add(transaksiReq);
        });
        return transaksisRespist;
    }

    private Map<String, String> getDateHourNow() {
        Map<String, String> dateHourNow = new HashMap<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String stringDateTime = dtf.format(now);
        String stringDate = stringDateTime.split(" ")[0];
        String stringTime = stringDateTime.split(" ")[1];
        String year = stringDate.split("/")[0];
        String month = stringDate.split("/")[1];
        String date = stringDate.split("/")[2];
        String hour = stringTime.split(":")[0];
        String minute = stringTime.split(":")[1];
        String second = stringTime.split(":")[2];
        dateHourNow.put("year", year);
        dateHourNow.put("month", month);
        dateHourNow.put("date", date);
        dateHourNow.put("hour", hour);
        dateHourNow.put("minute", minute);
        dateHourNow.put("second", second);
        return dateHourNow;
    }

    private Date convertStringToDate(String dateString, int hour, int minutes, int second) {
        Calendar calendar = Calendar.getInstance();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            calendar.setTime(sdf.parse(dateString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minutes);
        calendar.set(Calendar.SECOND, second);
        return calendar.getTime();
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
