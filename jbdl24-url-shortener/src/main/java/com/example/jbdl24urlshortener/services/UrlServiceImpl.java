package com.example.jbdl24urlshortener.services;
import com.example.jbdl24urlshortener.entity.Client;
import com.example.jbdl24urlshortener.entity.Url;
import com.example.jbdl24urlshortener.model.UrlRequest;
import com.example.jbdl24urlshortener.model.UrlResponse;
import com.example.jbdl24urlshortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Base64;

@Service
public class UrlServiceImpl implements UrlService{
    @Autowired
    private UrlRepository urlRepository;
    @Autowired
    private ClientService clientService ;
    @Override
    public UrlResponse getShortUrl(final UrlRequest request, String clientName) throws Exception {
        Client client = clientService.getClient(clientName).orElseThrow(()->new Exception("unknow client"));
        Url url = Url.builder().longUrl(request.getLongUrl()).build();
        url = urlRepository.save(url);
        client.getUrlList().add(url);
        clientService.updateClient(client);
        Long id = url.getId();
        String encodedId = Base64.getEncoder().encodeToString(String.valueOf(id).getBytes());
        return UrlResponse.builder().url("http://"+client.getHost()+":"+client.getPort()+"/"+ encodedId).build();
    }

    @Override
    public UrlResponse getLongUrl(final String encryptedId, String client) throws Exception {
        String idString = new String(Base64.getDecoder().decode(encryptedId),"UTF-8");
        Long id = Long.decode(idString);
        Url url = urlRepository.findById(id).orElseThrow(()->new Exception("No such url"));
        return UrlResponse.builder().url(url.getLongUrl()).build();
    }
}
