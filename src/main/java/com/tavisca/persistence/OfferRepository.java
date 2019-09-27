package com.tavisca.persistence;

import com.tavisca.model.Offer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;


@ApplicationScoped
public class OfferRepository {

    private static final Logger LOGGER= LoggerFactory.getLogger(OfferRepository.class);

    @Inject
    private DataSource dataSource;

    public void saveOffer(Offer offer){
        try {
            PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(Query.saveOfferQuery);
            preparedStatement.setLong(1,offer.getId());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(offer.getStartDate().toLocalDateTime()));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(offer.getEndDate().toLocalDateTime()));
            preparedStatement.setString(4,offer.getTitle());
            preparedStatement.execute();
        }catch (SQLException e){
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }

    }
}
