package com.ruoxi.caronline.repositories;

import com.ruoxi.caronline.data.UrlEntity;

public interface UrlRepo {
    String findLongUrl(String shortUrlCode);
    
    void saveUrlData(UrlEntity urlEntity);

    Long findMaxIndex();
}
