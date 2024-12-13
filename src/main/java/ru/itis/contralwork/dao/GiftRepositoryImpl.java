package ru.itis.contralwork.dao;

import lombok.RequiredArgsConstructor;
import ru.itis.contralwork.entity.Gift;
import ru.itis.contralwork.mapper.RowMapper;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class GiftRepositoryImpl implements GiftRepository {

    //language=sql
    private static final String FIND_ALL_GISTS = "select * from gift";
    //language=sql
    private static final String FIND_GIFT_BY_ID = "select * from gift where id = ?";

    private final DataSource dataSource;
    private final RowMapper<Gift> giftRowMapper;


    @Override
    public List<Gift> findAllGifts() {
        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement()
        ) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_GISTS);
            return giftRowMapper.mapRows(resultSet);
        } catch (SQLException e) {
            throw new IllegalStateException("Exception when finding gifts: " + e.getMessage());
        }
    }

    @Override
    public Optional<Gift> findById(Long id) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(FIND_GIFT_BY_ID)
        ) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            return giftRowMapper.mapRow(resultSet);
        } catch (SQLException e) {
            throw new IllegalStateException("Exception when finding gifts: " + e.getMessage());
        }
    }
}
