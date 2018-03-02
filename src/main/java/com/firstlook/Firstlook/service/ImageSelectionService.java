package com.firstlook.Firstlook.service;

import com.firstlook.Firstlook.model.ActiveImage;
import com.firstlook.Firstlook.utility.Gender;

import java.util.List;

public interface ImageSelectionService {

    public List<ActiveImage> getImagesBasedOnCriteria(Gender gender);
}
