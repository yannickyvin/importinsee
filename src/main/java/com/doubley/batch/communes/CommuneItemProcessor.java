package com.doubley.batch.communes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.doubley.batch.communes.bean.Commune;

public class CommuneItemProcessor implements ItemProcessor<Commune, Commune> {

    private static final Logger log = LoggerFactory.getLogger(CommuneItemProcessor.class);

    @Override
    public Commune process(final Commune commune) throws Exception {

    	final Commune transformedCommune = new Commune(commune.getCodeInsee(), 
        		commune.getCodePostal(),
        		commune.getNom().toLowerCase(),
        		commune.getDepartement(), 
        		commune.getRegion(), 
        		commune.getStatut(),
        		commune.getAltitude(),
        		commune.getSuperficie(),
        		commune.getPopulation(),
        		commune.getGeo());

        log.info("Converting (" + commune + ") into (" + transformedCommune + ")");

        return transformedCommune;
    }

}
