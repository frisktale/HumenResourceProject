package com.frisk.hrs.service.impl;

import com.frisk.hrs.mapper.OffersMapper;
import com.frisk.hrs.pojo.OffersCustom;
import com.frisk.hrs.pojo.OffersFind;
import com.frisk.hrs.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author frisktale
 * @date 2018/10/10
 */
@Service
public class OfferServiceImpl implements OfferService {
    @Autowired
    @Qualifier("offersMapper")
    private OffersMapper offersMapper;

    @Override
    public List<OffersCustom> getAllOffers() throws Exception {
        OffersFind offersFind = new OffersFind();
        offersFind.andNotHappened();
        List<OffersCustom> offers = offersMapper.findOffersByCustomer(offersFind);
        return offers;
    }

    @Override
    public OffersCustom getOfferById(Integer id) throws Exception {
        OffersCustom offersById = offersMapper.findOffersById(id);
        return offersById;
    }

    @Override
    public Integer addOffer(OffersCustom offersCustom) throws Exception {
        Integer line = offersMapper.insertOffers(offersCustom);
        return line;
    }

    @Override
    public Integer updateOffer(OffersCustom offersCustom) throws Exception {
        Integer line = offersMapper.updateOffers(offersCustom);
        return line;
    }

    @Override
    public Integer deleteOffers(List<Integer> ids) throws Exception {
        Integer row = offersMapper.deleteList(ids);
        return row;
    }

    @Override
    public Integer recoverDeleteOffers(List<Integer> ids) throws Exception {
        Integer row = offersMapper.recoverOffers(ids);
        return row;
    }

    @Override
    public List<OffersCustom> showDeleteOffers() throws Exception {
        List<OffersCustom> offersList = offersMapper.showDelete();
        return offersList;
    }

    @Override
    public Integer cleanDeleteOffers() throws Exception {
        Integer row = offersMapper.cleanOffers();
        return row;
    }
}
