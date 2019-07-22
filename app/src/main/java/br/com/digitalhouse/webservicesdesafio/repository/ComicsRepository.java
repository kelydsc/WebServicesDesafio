package br.com.digitalhouse.webservicesdesafio.repository;

import br.com.digitalhouse.webservicesdesafio.model.ComicsResponse;
import io.reactivex.Single;

import static br.com.digitalhouse.webservicesdesafio.data.network.ApiService.PRIVATE_KEY;
import static br.com.digitalhouse.webservicesdesafio.data.network.ApiService.PUBLIC_KEY;
import static br.com.digitalhouse.webservicesdesafio.data.network.ApiService.getApiService;
import static br.com.digitalhouse.webservicesdesafio.util.AppUtils.md5;
public class ComicsRepository {

    public Single<ComicsResponse> getComics() {
        String ts = Long.toString(System.currentTimeMillis() / 1000);
        String hash = md5(ts + PRIVATE_KEY + PUBLIC_KEY);
        return getApiService().getComics(
                "magazine", "comic", true,
                "focDate", "50", ts, hash, PUBLIC_KEY);
    }
}
