package com.firstlook.Firstlook.service;

import com.firstlook.Firstlook.model.ActiveImage;
import com.firstlook.Firstlook.repository.FemaleActiveImageRepository;
import com.firstlook.Firstlook.repository.MaleActiveImageRepository;
import com.firstlook.Firstlook.utility.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RandomImageSelectionServiceImpl implements ImageSelectionService {

    @Autowired
    private MaleActiveImageRepository maleActiveImageRepository;
    @Autowired
    private FemaleActiveImageRepository femaleActiveImageRepository;

    public List<ActiveImage> getImagesBasedOnCriteria(Gender gender) {
        Iterable<? extends ActiveImage> imageList;
        Long count;
        if(Gender.MALE.equals(gender)) {
            count = maleActiveImageRepository.count();
        } else {
            count = femaleActiveImageRepository.count();
        }
        List<Long> idHolder = new ArrayList<>();
        for(int i=0; i<count ; i++) {
            idHolder.add(new Long(i+1));
        }
        Collections.shuffle(idHolder);
        if(Gender.MALE.equals(gender)) {
            imageList = maleActiveImageRepository.findAll(idHolder.subList(0,4));
        } else {
            imageList = femaleActiveImageRepository.findAll(idHolder.subList(0, 4));
        }

        List<ActiveImage> images = new ArrayList<>();
        imageList.forEach(images :: add);

        return images;

    }
}
