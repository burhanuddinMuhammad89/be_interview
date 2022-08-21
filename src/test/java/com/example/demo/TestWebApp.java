package com.example.demo;


import org.junit.Before;
import org.junit.Test;
import java.nio.charset.Charset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.example.demo.dto.AnggotaReq;
import com.example.demo.dto.TransaksiReq;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;


public class TestWebApp extends SpringBootHelloWorldTests {

    @Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));


	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testCreateAnggotaPositiveCase() throws Exception {
		String url = "http://localhost:9090/api/anggota";
        AnggotaReq anObject = new AnggotaReq();
        anObject.setNamaAnggota("burhan5");
        anObject.setAlamat("Karawang");
        anObject.setNik("12338");
        anObject.setTanggalLahir("28/10/1989");
        anObject.setTempatLahir("Karawang");
        // ... more
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(anObject);

        mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isOk());

	}

    //negative case create anggota
    // - hit dengan mengganakan format tgl lahir yang salah
    // - hit dengan field nik yang kosong
    // - hit dengan nama kurang dari 3 huruf
    @Test
	public void testCreateAnggotaNegativeCase() throws Exception {
		String url = "http://localhost:9090/api/anggota";
        AnggotaReq anObject = new AnggotaReq();
        anObject.setNamaAnggota("burhan5");
        anObject.setAlamat("Karawang");
        anObject.setNik("12338");
        anObject.setTanggalLahir("28-10-1989");
        anObject.setTempatLahir("Karawang");
        // ... more
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(anObject);

        mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isBadRequest());
	}

    @Test
	public void testCreateTransaksiPositiveCase() throws Exception {
		String url = "http://localhost:9090/api/anggota/transaksi";
        TransaksiReq anObject = new TransaksiReq();
        anObject.setAmount("200000");
        anObject.setAction("simpan");
        anObject.setAnggotaReq(new AnggotaReq());
        anObject.getAnggotaReq().setNamaAnggota("burhan5");
        anObject.getAnggotaReq().setAlamat("Karawang");
        anObject.getAnggotaReq().setNik("12338");
        anObject.getAnggotaReq().setTanggalLahir("28-10-1989");
        anObject.getAnggotaReq().setTempatLahir("Karawang");
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(anObject);

        mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isOk());

	}

    //negative case create transaksi
    // - hit dengan menggunakan nik yang belum terdaftar jadi anggota
    // - hit dengan amount yang kosong
    // - hit dengan amount field bukan angka
    // - hit dengan amount dengan minus
    @Test
	public void testCreateTransaksiNegativeCase() throws Exception {
		String url = "http://localhost:9090/api/anggota/transaksi";
        TransaksiReq anObject = new TransaksiReq();
        anObject.setAmount("20000");
        anObject.setAction("simpan");
        anObject.setAnggotaReq(new AnggotaReq());
        anObject.getAnggotaReq().setNamaAnggota("burhan5");
        anObject.getAnggotaReq().setAlamat("Karawang");
        anObject.getAnggotaReq().setNik("4443344");
        anObject.getAnggotaReq().setTanggalLahir("28-10-1989");
        anObject.getAnggotaReq().setTempatLahir("Karawang");
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(anObject);

        mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isBadRequest());
	}
}
