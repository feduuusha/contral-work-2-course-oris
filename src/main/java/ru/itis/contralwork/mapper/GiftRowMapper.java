package ru.itis.contralwork.mapper;

import ru.itis.contralwork.entity.Gift;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GiftRowMapper implements RowMapper<Gift> {
    @Override
    public Optional<Gift> mapRow(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return Optional.of(Gift.builder()
                            .id(resultSet.getLong("id"))
                            .name(resultSet.getString("name"))
                            .giverName(resultSet.getString("giver_name"))
                            .receiverName(resultSet.getString("receiver_name"))
                            .dateOfGiven(resultSet.getTimestamp("date_of_given"))
                    .build());
        }
        return Optional.empty();
    }

    @Override
    public List<Gift> mapRows(ResultSet resultSet) throws SQLException {
        List<Gift> gifts = new ArrayList<>(100);
        Optional<Gift> optional = mapRow(resultSet);
        while (optional.isPresent()) {
            gifts.add(optional.get());
            optional = mapRow(resultSet);
        }
        return gifts;
    }
}
