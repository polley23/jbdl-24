package com.example.jbdl24urlshortener.services;
import com.example.jbdl24urlshortener.entity.CachedUrl;
import com.example.jbdl24urlshortener.entity.Client;
import com.example.jbdl24urlshortener.entity.Url;
import com.example.jbdl24urlshortener.model.UrlRequest;
import com.example.jbdl24urlshortener.model.UrlResponse;
import com.example.jbdl24urlshortener.repository.CachedUrlRepository;
import com.example.jbdl24urlshortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Base64;
import java.util.Optional;

@Service
public class UrlServiceImpl implements UrlService{
    @Autowired
    private UrlRepository urlRepository;
    @Autowired
    private ClientService clientService ;
    @Autowired
    private CachedUrlRepository cachedUrlRepository;
    @Override
    public UrlResponse getShortUrl(final UrlRequest request, String clientName) throws Exception {
        Optional<CachedUrl> optionalCachedUrl = cachedUrlRepository.findById(request.getLongUrl()+ clientName);
        if(optionalCachedUrl.isPresent()){
            return UrlResponse.builder().url(optionalCachedUrl.get().getUrl()).build();
        }
        Client client = clientService.getClient(clientName).orElseThrow(()->new Exception("unknow client"));
        Url url = Url.builder().longUrl(request.getLongUrl()).build();
        url = urlRepository.save(url);
        client.getUrlList().add(url);
        clientService.updateClient(client);
        Long id = url.getId();
        String encodedId = Base64.getEncoder().encodeToString(String.valueOf(id).getBytes());
        String shortUrl =  "http://"+client.getHost()+":"+client.getPort()+"/"+ encodedId;
        cachedUrlRepository.save(CachedUrl.builder().id(request.getLongUrl()+ clientName).url(shortUrl).build());
        return UrlResponse.builder().url(shortUrl).build();
    }

    @Override
    public UrlResponse getLongUrl(final String encryptedId, String client) throws Exception {
        Optional<CachedUrl> optionalCachedUrl = cachedUrlRepository.findById(encryptedId);
        if(optionalCachedUrl.isPresent()){
            return UrlResponse.builder().url(optionalCachedUrl.get().getUrl()).build();
        }
        String idString = new String(Base64.getDecoder().decode(encryptedId),"UTF-8");
        Long id = Long.decode(idString);
        Url url = urlRepository.findById(id).orElseThrow(()->new Exception("No such url"));
        cachedUrlRepository.save(CachedUrl.builder().id(encryptedId).url(url.getLongUrl()).build());
        return UrlResponse.builder().url(url.getLongUrl()).build();
    }
}
