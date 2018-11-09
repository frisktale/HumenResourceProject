package com.frisk.hrs.mapper;


import com.frisk.hrs.pojo.Offers;
import com.frisk.hrs.pojo.OffersCustom;
import com.frisk.hrs.pojo.OffersFind;

import java.util.List;

public interface OffersMapper {
    public OffersCustom findOffersById(Integer id) throws Exception;
    public Offers findOffersByIdBase(Integer id) throws Exception;

    public List<OffersCustom> findOffersByCustomer(OffersFind OffersFind) throws Exception;

    public Integer insertOffers(OffersCustom OffersCustom) throws Exception;

    public Integer updateOffers(OffersCustom OffersCustom) throws Exception;

    public Integer deleteList(List<Integer> ids) throws Exception;

    public List<OffersCustom> showDelete()throws Exception;

    public Integer recoverOffers(List<Integer> ids)throws Exception;

    public Integer cleanOffers()throws Exception;
}