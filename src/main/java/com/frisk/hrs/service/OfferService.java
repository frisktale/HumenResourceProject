package com.frisk.hrs.service;

import com.frisk.hrs.pojo.OffersCustom;

import java.util.List;

/**
 * @author frisktale
 * @date 2018/10/10
 */
public interface OfferService {
    public List<OffersCustom> getAllOffers() throws Exception;

    public OffersCustom getOfferById(Integer id) throws Exception;


    public Integer addOffer(OffersCustom offersCustom) throws Exception;

    public Integer updateOffer(OffersCustom offersCustom) throws Exception;

    public Integer deleteOffers(List<Integer> ids) throws Exception;

    public Integer recoverDeleteOffers(List<Integer> ids) throws Exception;

    public List<OffersCustom> showDeleteOffers() throws Exception;

    public Integer cleanDeleteOffers() throws Exception;
}
